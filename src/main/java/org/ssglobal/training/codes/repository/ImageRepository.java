package org.ssglobal.training.codes.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.ssglobal.training.codes.model.Image;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ImageRepository {
	private final SessionFactory sf;
	
	public Image getByFilename(String filename) {
		try (Session session = sf.openSession()) {
			Query<Image> query = session.createQuery("FROM Image WHERE filename = :filename", Image.class)
					.setParameter("filename", filename);
			Image image = query.uniqueResult();
			if (image != null) {
				return image;
			} else {
				throw new RuntimeException("Image not found");
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
