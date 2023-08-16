package org.ssglobal.training.codes.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.ssglobal.training.codes.model.Offer;
import org.ssglobal.training.codes.request.OfferRequest;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OfferRepository {
	private final SessionFactory sf;
	
	public List<Offer> getAllOffer() {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM Offer", Offer.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Offer getOfferById(Long id) {
		try (Session session = sf.openSession()) {
			return session.get(Offer.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<Offer> getOfferByFarmerId(Integer id) {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM Offer WHERE farmerId = :farmerId", Offer.class)
					.setParameter("farmerId", id)
					.list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<Offer> getOfferIdByPostId(Integer id) {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM Offer WHERE postId = :postId", Offer.class)
					.setParameter("postId", id)
					.list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Response addOffer(OfferRequest offer) {
	    try (Session session = sf.openSession()) {
	        session.beginTransaction();

	        if (offer.getPostId() == null || offer.getPostId() <= 0) {
	            return Response.builder()
	                    .status(400)
	                    .message("Invalid Post ID")
	                    .timestamp(LocalDateTime.now())
	                    .build();
	        }
	        
	        if (offer.getFarmerId() == null || offer.getFarmerId() <= 0) {
	            return Response.builder()
	                    .status(400)
	                    .message("Invalid Farmer ID")
	                    .timestamp(LocalDateTime.now())
	                    .build();
	        }
	        
	        if (offer.getQuantity() == null || offer.getQuantity() <= 0) {
	            return Response.builder()
	                    .status(400)
	                    .message("Invalid Quantity")
	                    .timestamp(LocalDateTime.now())
	                    .build();
	        }
	        
	        if (offer.getPrice() == null || offer.getPrice() <= 0) {
	            return Response.builder()
	                    .status(400)
	                    .message("Invalid Price")
	                    .timestamp(LocalDateTime.now())
	                    .build();
	        }

	        Offer newOffer = Offer.builder()
	                .postId(offer.getPostId())
	                .farmerId(offer.getFarmerId())
	                .quantity(offer.getQuantity())
	                .price(offer.getPrice())
	                .offerDate(LocalDate.now())
	                .build();

	        session.persist(newOffer);

	        session.getTransaction().commit();

	        return Response.builder()
	                .status(201)
	                .message("Offer successfully created")
	                .timestamp(LocalDateTime.now())
	                .build();
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	    }
	}
	
	public Response updateOffer(Long id, OfferRequest offer) {
	    try (Session session = sf.openSession()) {
	        session.beginTransaction();

	        Offer existingOffer = session.get(Offer.class, id);

	        if (existingOffer == null) {
	            return Response.builder()
	                    .status(404)
	                    .message("Offer not found")
	                    .timestamp(LocalDateTime.now())
	                    .build();
	        }

	        if (offer.getPostId() != null && offer.getPostId() <= 0) {
	            return Response.builder()
	                    .status(400)
	                    .message("Invalid Post ID")
	                    .timestamp(LocalDateTime.now())
	                    .build();
	        }
	        
	        if (offer.getFarmerId() != null && offer.getFarmerId() <= 0) {
	            return Response.builder()
	                    .status(400)
	                    .message("Invalid Farmer ID")
	                    .timestamp(LocalDateTime.now())
	                    .build();
	        }
	        
	        if (offer.getQuantity() != null && offer.getQuantity() <= 0) {
	            return Response.builder()
	                    .status(400)
	                    .message("Invalid Quantity")
	                    .timestamp(LocalDateTime.now())
	                    .build();
	        }
	        
	        if (offer.getPrice() != null && offer.getPrice() <= 0) {
	            return Response.builder()
	                    .status(400)
	                    .message("Invalid Price")
	                    .timestamp(LocalDateTime.now())
	                    .build();
	        }

	        if (offer.getPostId() != null) {
	            existingOffer.setPostId(offer.getPostId());
	        }
	        if (offer.getFarmerId() != null) {
	            existingOffer.setFarmerId(offer.getFarmerId());
	        }
	        if (offer.getQuantity() != null) {
	            existingOffer.setQuantity(offer.getQuantity());
	        }
	        if (offer.getPrice() != null) {
	            existingOffer.setPrice(offer.getPrice());
	        }

	        session.update(existingOffer);

	        session.getTransaction().commit();

	        return Response.builder()
	                .status(200)
	                .message("Offer successfully updated")
	                .timestamp(LocalDateTime.now())
	                .build();
	    } catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	    }
	}
}
