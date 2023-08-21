package org.ssglobal.training.codes.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.ssglobal.training.codes.model.Advertisement;
import org.ssglobal.training.codes.model.Image;
import org.ssglobal.training.codes.request.AdvertisementRequest;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdvertisementRepository {
	private final SessionFactory sf;
	
	public List<Advertisement> getAdvertisementBySupplierId(Integer id) {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM Advertisement WHERE supplierId = :supplierId", Advertisement.class)
					.setParameter("supplierId", id)
					.list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Advertisement getAdvertisementById(Long id) {
		try (Session session = sf.openSession()) {
			return session.get(Advertisement.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Response addAdvertisement(AdvertisementRequest advertisement) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();
			
			Advertisement ad = new Advertisement();
			ad.setPostDate(LocalDate.now());
			ad.setStatus(true);
			
			if (advertisement.getSupplierId() != null) {
				ad.setSupplierId(advertisement.getSupplierId());
			}
			if (advertisement.getName() != null && advertisement.getName() != "") {
				ad.setName(advertisement.getName());
			}
			if (advertisement.getCategory() != null && advertisement.getCategory() != "") {
				ad.setCategory(advertisement.getCategory());
			}
			if (advertisement.getDescription() != null && advertisement.getDescription() != "") {
				ad.setDescription(advertisement.getDescription());
			}
			if (advertisement.getQuantity() != null) {
				ad.setQuantity(advertisement.getQuantity());
			}
			if (advertisement.getMass() != null) {
				ad.setMass(advertisement.getMass());
			}
			if (advertisement.getPrice() != null) {
				ad.setPrice(advertisement.getPrice());
			}
			if (advertisement.getFilename() != null && advertisement.getFilename() != "") {
				Query<Image> query = session.createQuery("FROM Image WHERE filename = :filename", Image.class)
						.setParameter("filename", advertisement.getFilename());
				Image image = query.uniqueResult();
				if (image == null) {
					Image img = Image.builder()
							.filename(advertisement.getFilename())
							.mimeType(advertisement.getMimeType())
							.data(advertisement.getData())
							.build();
					session.persist(img);
				}
				ad.setImage(createImageLink(advertisement.getFilename()));
			}
			session.persist(ad);
			
			session.getTransaction().commit();
			
			return Response.builder()
					.status(201)
					.message("Advertisement successfully created")
					.timestamp(LocalDateTime.now())
					.build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Response updateAdvertisement(Long id, AdvertisementRequest advertisement) {
		try (Session session = sf.openSession()) {
			Advertisement ad = session.get(Advertisement.class, id);
			if (ad != null) {
				session.beginTransaction();
				
				if (advertisement.getSupplierId() != null) {
					ad.setSupplierId(advertisement.getSupplierId());
				}
				if (advertisement.getName() != null && advertisement.getName() != "") {
					ad.setName(advertisement.getName());
				}
				if (advertisement.getCategory() != null && advertisement.getCategory() != "") {
					ad.setCategory(advertisement.getCategory());
				}
				if (advertisement.getDescription() != null && advertisement.getDescription() != "") {
					ad.setDescription(advertisement.getDescription());
				}
				if (advertisement.getQuantity() != null) {
					ad.setQuantity(advertisement.getQuantity());
				}
				if (advertisement.getMass() != null) {
					ad.setMass(advertisement.getMass());
				}
				if (advertisement.getPrice() != null) {
					ad.setPrice(advertisement.getPrice());
				}
				if (advertisement.getFilename() != null && advertisement.getFilename() != "") {
					Query<Image> query = session.createQuery("FROM Image WHERE filename = :filename", Image.class)
							.setParameter("filename", advertisement.getFilename());
					Image image = query.uniqueResult();
					if (image == null) {
						Image img = Image.builder()
								.filename(advertisement.getFilename())
								.mimeType(advertisement.getMimeType())
								.data(advertisement.getData())
								.build();
						session.persist(img);
					}
					ad.setImage(createImageLink(advertisement.getFilename()));
				}
				if (advertisement.getPostDate() != null) {
					ad.setPostDate(advertisement.getPostDate());
				}
				if (advertisement.getStatus() != null) {
					ad.setStatus(advertisement.getStatus());
				}
				
				session.getTransaction().commit();
				
				return Response.builder()
						.status(200)
						.message("Advertisement successfully updated")
						.timestamp(LocalDateTime.now())
						.build();
			} else {
				return Response.builder()
						.status(404)
						.message("Advertisement not found")
						.timestamp(LocalDateTime.now())
						.build();
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private String createImageLink(String filename) {
		return ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/api/image/" + filename).toUriString();
	}
}
