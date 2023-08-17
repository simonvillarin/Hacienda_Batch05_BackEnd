package org.ssglobal.training.codes.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.ssglobal.training.codes.model.CourseEnrolled;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CourseEnrolledRepository {
	private final SessionFactory sf;

	public List<CourseEnrolled> getAllEnrolledCourse() {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM CourseEnrolled", CourseEnrolled.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public CourseEnrolled getEnrolledCourseById(Long id) {
		try (Session session = sf.openSession()) {
			return session.get(CourseEnrolled.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<CourseEnrolled> getEnrolledCourseByFarmerId(Integer id) {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM CourseEnrolled WHERE farmerId = :farmerId", CourseEnrolled.class)
					.setParameter("farmerId", id)
					.list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<CourseEnrolled> getEnrolledCourseByCourseId(Integer id) {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM CourseEnrolled WHERE courseId = :courseId", CourseEnrolled.class)
					.setParameter("courseId", id)
					.list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Response addEnrolledCourse(CourseEnrolled courseEnrolled) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();
			
			CourseEnrolled ce = new CourseEnrolled();
			ce.setStatus(true);
			
			if (courseEnrolled.getFarmerId() != null) {
				ce.setFarmerId(courseEnrolled.getFarmerId());
			}
			
			if (courseEnrolled.getCourseId() != null) {
				ce.setCourseId(courseEnrolled.getCourseId());
			}
			
			if (courseEnrolled.getEnrollDate() != null) {
				ce.setEnrollDate(courseEnrolled.getEnrollDate());
			}
			
			session.persist(ce);
			
			session.getTransaction().commit();
			
			return Response.builder()
					.status(201)
					.message("Enrolled Course successfully created")
					.timestamp(LocalDateTime.now())
					.build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Response updateEnrolledCourse(Long id, CourseEnrolled courseEnrolled) {
		try (Session session = sf.openSession()) {
			CourseEnrolled ce = session.get(CourseEnrolled.class, id);
			if (ce != null) {
				session.beginTransaction();
				
				if (courseEnrolled.getFarmerId() != null) {
					ce.setFarmerId(courseEnrolled.getFarmerId());
				}
				
				if (courseEnrolled.getCourseId() != null) {
					ce.setCourseId(courseEnrolled.getCourseId());
				}
				
				if (courseEnrolled.getEnrollDate() != null) {
					ce.setEnrollDate(courseEnrolled.getEnrollDate());
				}
				if (courseEnrolled.getStatus() != null) {
					ce.setStatus(courseEnrolled.getStatus());
				}
				
				session.getTransaction().commit();
				
				return Response.builder()
						.status(200)
						.message("Enrolled Course successfully updated")
						.timestamp(LocalDateTime.now())
						.build();
			} else {
				return Response.builder()
						.status(404)
						.message("Enrolled Course not found")
						.timestamp(LocalDateTime.now())
						.build();
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
