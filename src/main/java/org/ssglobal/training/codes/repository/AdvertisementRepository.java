package org.ssglobal.training.codes.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.ssglobal.training.codes.model.Advertisement;
import org.ssglobal.training.codes.model.Image;
import org.ssglobal.training.codes.model.Offer;
import org.ssglobal.training.codes.model.User;
import org.ssglobal.training.codes.request.AdvertisementRequest;
import org.ssglobal.training.codes.response.AdvertisementResponse;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdvertisementRepository {
	private final SessionFactory sf;
	
	public List<AdvertisementResponse> getAllAdvertisement(Long id) {
		try (Session session = sf.openSession()) {
			List<Advertisement> ads = session.createQuery("FROM Advertisement", Advertisement.class).list();
			
			List<AdvertisementResponse> adsResponse = new ArrayList<>();
			ads.stream().forEach((ad) -> {
				Query<User> query = session.createQuery("FROM User WHERE userId = :userId", User.class)
						.setParameter("userId", ad.getSupplierId());
				User supplier = query.uniqueResult();
				
				List<Offer> offers = session.createQuery("FROM Offer WHERE postId = :postId", Offer.class)
						.setParameter("postId", ad.getPostId())
						.list();
				
				Offer offer = session.createQuery("FROM Offer WHERE postId = :postId AND farmerId = :farmerId AND status = :status", Offer.class)
						.setParameter("postId", ad.getPostId())
						.setParameter("farmerId", id)
						.setParameter("status", true)
						.uniqueResult();
				
				System.out.println(offer);
				
				boolean isOffered = false;
				if (offer != null) {
					isOffered = true;
				}
				
				AdvertisementResponse adResponse = AdvertisementResponse.builder()
						.postId(ad.getPostId())
						.supplier(supplier)
						.name(ad.getName())
						.category(ad.getCategory())
						.description(ad.getDescription())
						.measurement(ad.getMeasurement())
						.value(ad.getValue())
						.price(ad.getPrice())
						.image(ad.getImage())
						.postDate(ad.getPostDate())
						.status(ad.getStatus())
						.numOfOffers(offers.size())
						.isOffered(isOffered)
						.build();
				adsResponse.add(adResponse);
			});
			return adsResponse;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<AdvertisementResponse> getAdvertisementBySupplierId(Long id) {
		try (Session session = sf.openSession()) {
			List<Advertisement> ads = session.createQuery("FROM Advertisement WHERE supplierId = :supplierId", Advertisement.class)
					.setParameter("supplierId", id)
					.list();
			
			List<AdvertisementResponse> adsResponse = new ArrayList<>();
			ads.stream().forEach((ad) -> {
				Query<User> query = session.createQuery("FROM User WHERE userId = :userId", User.class)
						.setParameter("userId", ad.getSupplierId());
				User supplier = query.uniqueResult();
				
				List<Offer> offers = session.createQuery("FROM Offer WHERE postId = :postId", Offer.class)
						.setParameter("postId", ad.getPostId())
						.list();
				
				AdvertisementResponse adResponse = AdvertisementResponse.builder()
						.postId(ad.getPostId())
						.supplier(supplier)
						.name(ad.getName())
						.category(ad.getCategory())
						.description(ad.getDescription())
						.measurement(ad.getMeasurement())
						.value(ad.getValue())
						.price(ad.getPrice())
						.image(ad.getImage())
						.postDate(ad.getPostDate())
						.status(ad.getStatus())
						.numOfOffers(offers.size())
						.isOffered(ad.getIsOffered())
						.build();
				adsResponse.add(adResponse);
			});
			return adsResponse;
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
			
			Advertisement adver = session.createQuery("FROM Advertisement WHERE name = :name", Advertisement.class)
					.setParameter("name", advertisement.getName())
					.uniqueResult();
			if (adver != null) {
				return Response.builder()
						.status(400)
						.message("Crop name already exists")
						.timestamp(LocalDateTime.now())
						.build();
			}
			
			Advertisement ad = new Advertisement();
			ad.setPostDate(LocalDate.now());
			ad.setStatus(true);
			ad.setIsOffered(false);
			
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
			if (advertisement.getMeasurement() != null) {
				ad.setMeasurement(advertisement.getMeasurement());
			}
			if (advertisement.getValue() != null) {
				ad.setValue(advertisement.getValue());
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
				if (advertisement.getMeasurement() != null) {
					ad.setMeasurement(advertisement.getMeasurement());
				}
				if (advertisement.getValue() != null) {
					ad.setValue(advertisement.getValue());
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
				if (advertisement.getIsOffered() != null) {
					ad.setIsOffered(advertisement.getIsOffered());
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
