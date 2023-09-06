package org.ssglobal.training.codes.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.ssglobal.training.codes.model.Advertisement;
import org.ssglobal.training.codes.model.Offer;
import org.ssglobal.training.codes.model.User;
import org.ssglobal.training.codes.response.OfferResponse;
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
	
	public List<OfferResponse> getOfferByFarmerId(Long id) {
		try (Session session = sf.openSession()) {
			List<Offer> offers = session.createQuery("FROM Offer WHERE farmerId = :farmerId", Offer.class)
					.setParameter("farmerId", id)
					.list();
			
			List<OfferResponse> offerResponses = new ArrayList<>();
			offers.stream().forEach(offer -> {
				Query<User> query = session.createQuery("FROM User WHERE userId = :userId", User.class)
						.setParameter("userId", offer.getFarmerId());
				User farmer = query.uniqueResult();
				
				Query<User> query1 = session.createQuery("FROM User WHERE userId = :userId", User.class)
						.setParameter("userId", offer.getSupplierId());
				User supplier = query1.uniqueResult();
				
				Query<Advertisement> query2 = session.createQuery("FROM Advertisement WHERE postId = :postId", Advertisement.class)
						.setParameter("postId", offer.getPostId());
				Advertisement advertisement = query2.uniqueResult();
				
				OfferResponse offerResponse = OfferResponse.builder()
						.offerId(offer.getOfferId())
						.farmer(farmer)
						.supplier(supplier)
						.advertisement(advertisement)
						.measurement(offer.getMeasurement())
						.value(offer.getValue())
						.price(offer.getPrice())
						.offerDate(offer.getOfferDate())
						.offerTime(offer.getOfferTime())
						.isAccepted(offer.getIsAccepted())
						.isViewed(offer.getIsViewed())
						.status(offer.getStatus())
						.build();
				
				offerResponses.add(offerResponse);
			});
			
			return offerResponses;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<OfferResponse> getOfferBySupplierId(Long id) {
		try (Session session = sf.openSession()) {
			List<Offer> offers = session.createQuery("FROM Offer WHERE supplierId = :supplierId", Offer.class)
					.setParameter("supplierId", id)
					.list();
			
			List<OfferResponse> offerResponses = new ArrayList<>();
			offers.stream().forEach(offer -> {
				Query<User> query = session.createQuery("FROM User WHERE userId = :userId", User.class)
						.setParameter("userId", offer.getFarmerId());
				User farmer = query.uniqueResult();
				
				Query<User> query1 = session.createQuery("FROM User WHERE userId = :userId", User.class)
						.setParameter("userId", offer.getSupplierId());
				User supplier = query1.uniqueResult();
				
				Query<Advertisement> query2 = session.createQuery("FROM Advertisement WHERE postId = :postId", Advertisement.class)
						.setParameter("postId", offer.getPostId());
				Advertisement advertisement = query2.uniqueResult();
				
				OfferResponse offerResponse = OfferResponse.builder()
						.offerId(offer.getOfferId())
						.farmer(farmer)
						.supplier(supplier)
						.advertisement(advertisement)
						.measurement(offer.getMeasurement())
						.value(offer.getValue())
						.price(offer.getPrice())
						.offerDate(offer.getOfferDate())
						.offerTime(offer.getOfferTime())
						.isAccepted(offer.getIsAccepted())
						.isViewed(offer.getIsViewed())
						.status(offer.getStatus())
						.build();
				
				offerResponses.add(offerResponse);
			});
			
			return offerResponses;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<OfferResponse> getOfferByPostId(Long id) {
		try (Session session = sf.openSession()) {
			List<Offer> offers = session.createQuery("FROM Offer WHERE postId = :postId", Offer.class)
					.setParameter("postId", id)
					.list();
			
			List<OfferResponse> offerResponses = new ArrayList<>();
			offers.stream().forEach(offer -> {
				Query<User> query = session.createQuery("FROM User WHERE userId = :userId", User.class)
						.setParameter("userId", offer.getFarmerId());
				User farmer = query.uniqueResult();
				
				Query<User> query1 = session.createQuery("FROM User WHERE userId = :userId", User.class)
						.setParameter("userId", offer.getSupplierId());
				User supplier = query1.uniqueResult();
				
				Query<Advertisement> query2 = session.createQuery("FROM Advertisement WHERE postId = :postId", Advertisement.class)
						.setParameter("postId", offer.getPostId());
				Advertisement advertisement = query2.uniqueResult();
				
				OfferResponse offerResponse = OfferResponse.builder()
						.offerId(offer.getOfferId())
						.farmer(farmer)
						.supplier(supplier)
						.advertisement(advertisement)
						.measurement(offer.getMeasurement())
						.value(offer.getValue())
						.price(offer.getPrice())
						.offerDate(offer.getOfferDate())
						.offerTime(offer.getOfferTime())
						.isAccepted(offer.getIsAccepted())
						.isViewed(offer.getIsViewed())
						.status(offer.getStatus())
						.build();
				
				offerResponses.add(offerResponse);
			});
			
			return offerResponses;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Response addOffer(Offer offer) {
	    try (Session session = sf.openSession()) {
	        session.beginTransaction();

	        if (offer.getPostId() == null || offer.getPostId() <= 0) {
	            return Response.builder()
	                    .status(400)
	                    .message("Invalid Post Id")
	                    .timestamp(LocalDateTime.now())
	                    .build();
	        }	        
	        if (offer.getFarmerId() == null || offer.getFarmerId() <= 0) {
	            return Response.builder()
	                    .status(400)
	                    .message("Invalid Farmer Id")
	                    .timestamp(LocalDateTime.now())
	                    .build();
	        }
	        if (offer.getSupplierId() == null || offer.getSupplierId() <= 0) {
	            return Response.builder()
	                    .status(400)
	                    .message("Invalid Supplier Id")
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
	                .supplierId(offer.getSupplierId())
	        		.measurement(offer.getMeasurement())
					.value(offer.getValue())
	                .price(offer.getPrice())
	                .offerDate(LocalDate.now())
	                .offerTime(LocalTime.now())
	                .isAccepted(false)
	                .isViewed(false)
	                .status(true)
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
	
	public Response updateOffer(Long id, Offer offer) {
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
	        
	        if (offer.getPostId() != null) {
	            existingOffer.setPostId(offer.getPostId());
	        }
	        if (offer.getFarmerId() != null) {
	            existingOffer.setFarmerId(offer.getFarmerId());
	        }
	        if (offer.getSupplierId() != null) {
	            existingOffer.setSupplierId(offer.getSupplierId());
	        }
	        if (offer.getMeasurement() != null) {
	            existingOffer.setMeasurement(offer.getMeasurement());
	        }
	        if (offer.getValue() != null) {
	            existingOffer.setValue(offer.getValue());;
	        }
	        if (offer.getPrice() != null) {
	            existingOffer.setPrice(offer.getPrice());
	        }
	        if (offer.getIsAccepted() != null) {
	        	existingOffer.setIsAccepted(offer.getIsAccepted());
	        }
	        if (offer.getIsViewed() != null) {
	            existingOffer.setIsViewed(offer.getIsViewed());
	        }
	        if (offer.getStatus() != null) {
	        	existingOffer.setStatus(offer.getStatus());
	        }

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
	
	public Response deleteOffer(Long id) {
		try (Session session = sf.openSession()) {
            session.beginTransaction();

            Offer offer = session.get(Offer.class, id);
            
            if (offer != null) {
            	session.delete(offer);
            }

            session.getTransaction().commit();

            return Response.builder()
                    .status(201)
                    .message("Offer successfully deleted")
                    .timestamp(LocalDateTime.now())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
	}
}
