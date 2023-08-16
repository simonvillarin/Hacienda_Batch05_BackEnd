package org.ssglobal.training.codes.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.ssglobal.training.codes.model.FarmingTips;
import org.ssglobal.training.codes.model.Image;
import org.ssglobal.training.codes.request.FarmingTipsRequest;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FarmingTipsRepository {
	private final SessionFactory sf;
	
	public List<FarmingTips> getAllFarmingTips() {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM FarmingTips", FarmingTips.class).list();
		}
	}
	
	public FarmingTips getFarmingTipsById(Long id) {
		try (Session session = sf.openSession()) {
			return session.get(FarmingTips.class, id);
		}
	}
	
	public Response addFarmingTip(FarmingTips farmingTip) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();
			session.persist(farmingTip);
			session.getTransaction().commit();
		}
		
		return Response.builder()
				.status(201)
				.message("Farming tip successfully created")
				.timestamp(LocalDateTime.now())
				.build();
	}
	
	public Response addFarmingTipWithImage(FarmingTipsRequest farmingTip) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();
	
			Query<Image> query = session.createQuery("FROM Image WHERE filename = :filename", Image.class)
					.setParameter("filename", farmingTip.getFilename());
			Image image = query.uniqueResult();
			if (image == null) {
				Image img = Image.builder()
						.filename(farmingTip.getFilename())
						.mimeType(farmingTip.getMimeType())
						.data(farmingTip.getData())
						.build();
				session.persist(img);
			}
			
			FarmingTips tip = FarmingTips.builder()
					.tip(farmingTip.getTip())
					.image(createImageLink(farmingTip.getFilename()))
					.status(true)
					.build();
			session.persist(tip);
			
			session.getTransaction().commit();
		}
		
		return Response.builder()
				.status(201)
				.message("Farming tip successfully created")
				.timestamp(LocalDateTime.now())
				.build();
	}
	
	public Response updateFarmingTip(Long id, FarmingTips farmingTip) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();
			
			FarmingTips tip = session.get(FarmingTips.class, id);
			if (tip != null) {
				if (farmingTip.getTip() != null) {
					tip.setTip(farmingTip.getTip());
				}
				if (farmingTip.getStatus() != null) {
					tip.setStatus(farmingTip.getStatus());
				}
				
			session.getTransaction().commit();

				return Response.builder()
						.status(201)
						.message("Farming tip successfully updated")
						.timestamp(LocalDateTime.now()).build();
			} else {
				return Response.builder()
						.status(404)
						.message("Farming tip not found")
						.timestamp(LocalDateTime.now())
						.build();
			}
		}
	}
	
	public Response updateFarmingTipWithImage(Long id, FarmingTipsRequest farmingTip) {
		try (Session session = sf.openSession()) {		
			FarmingTips tip = session.get(FarmingTips.class, id);
			if (tip != null) {
				session.beginTransaction();
				
				Query<Image> query = session.createQuery("FROM Image WHERE filename = :filename", Image.class)
						.setParameter("filename", farmingTip.getFilename());
				Image image = query.uniqueResult();
				if (image == null) {
					Image img = Image.builder()
							.filename(farmingTip.getFilename())
							.mimeType(farmingTip.getMimeType())
							.data(farmingTip.getData())
							.build();
					session.persist(img);
				}
				if (farmingTip.getTip() != null) {
					tip.setTip(farmingTip.getTip());
				}
				if (farmingTip.getFilename() != null) {
					tip.setImage(createImageLink(farmingTip.getFilename()));
				}
				
				session.getTransaction().commit();

				return Response.builder()
						.status(201)
						.message("Farming tip successfully updated")
						.timestamp(LocalDateTime.now()).build();
				
			} else {
				return Response.builder()
						.status(404)
						.message("Farming tip not found")
						.timestamp(LocalDateTime.now())
						.build();
			}
		}
	}
	
	private String createImageLink(String filename) {
		return ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/api/image/" + filename).toUriString();
	}
}
