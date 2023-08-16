package org.ssglobal.training.codes.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.ssglobal.training.codes.model.Offer;
import org.ssglobal.training.codes.model.SoldCrop;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SoldCropRepository {
	private final SessionFactory sf;
	
	public List<SoldCrop> getAllSoldCrop() {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM soldCrop", SoldCrop.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public SoldCrop getSoldCropById(Long id) {
		try (Session session = sf.openSession()) {
			return session.get(SoldCrop.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<SoldCrop> getSolfCropByOfferId(Integer id) {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM soldCrop WHERE offerId = :offerId", SoldCrop.class)
					.setParameter("offerId", id)
					.list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	 public Response addSoldCrop(SoldCrop soldCrop) {
	        try (Session session = sf.openSession()) {
	            session.beginTransaction();

	            if (soldCrop.getOfferId() == null || soldCrop.getOfferId() <= 0) {
	                return Response.builder()
	                        .status(400)
	                        .message("Invalid Offer ID")
	                        .timestamp(LocalDateTime.now())
	                        .build();
	            }

	            SoldCrop newSoldCrop = SoldCrop.builder()
	                    .offerId(soldCrop.getOfferId())
	                    .soldCropDate(LocalDate.now())
	                    .build();

	            session.persist(newSoldCrop);

	            session.getTransaction().commit();

	            return Response.builder()
	                    .status(201)
	                    .message("Sold Crop successfully added")
	                    .timestamp(LocalDateTime.now())
	                    .build();
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	        }
	    }

	    public Response updateSoldCrop(Long id, SoldCrop soldCrop) {
	        try (Session session = sf.openSession()) {
	            session.beginTransaction();

	            SoldCrop existingSoldCrop = session.get(SoldCrop.class, id);

	            if (existingSoldCrop == null) {
	                return Response.builder()
	                        .status(404)
	                        .message("Sold Crop not found")
	                        .timestamp(LocalDateTime.now())
	                        .build();
	            }


	            if (soldCrop.getOfferId() != null && soldCrop.getOfferId() <= 0) {
	                return Response.builder()
	                        .status(400)
	                        .message("Invalid Offer ID")
	                        .timestamp(LocalDateTime.now())
	                        .build();
	            }

	            if (soldCrop.getOfferId() != null) {
	                existingSoldCrop.setOfferId(soldCrop.getOfferId());
	            }

	            session.update(existingSoldCrop);

	            session.getTransaction().commit();

	            return Response.builder()
	                    .status(200)
	                    .message("Sold Crop successfully updated")
	                    .timestamp(LocalDateTime.now())
	                    .build();
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	        }
	    }
}
