package org.ssglobal.training.codes.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.ssglobal.training.codes.model.Course;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CourseRepository {
	private final SessionFactory sf;
	
	public List<Course> getAllCourse() {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM Course", Course.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Course getCourseById(Long id) {
		try (Session session = sf.openSession()) {
			return session.get(Course.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Response addCourse(Course course) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();
			
			Course c = new Course();
			c.setStatus(true);
			
			if (course.getCourseName() != null && course.getCourseName() != "") {
				course.setCourseName(course.getCourseName());
			}
			
			if (course.getDescription() != null && course.getDescription() != "") {
				course.setDescription(course.getDescription());
			}
			
			if (course.getYtLink() != null && course.getYtLink() != "") {
				course.setYtLink(course.getYtLink());
			}
	
			session.persist(course);
			
			session.getTransaction().commit();
			
			return Response.builder()
					.status(201)
					.message("Course successfully created")
					.timestamp(LocalDateTime.now())
					.build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Response updateCourse(Long id, Course course) {
		try (Session session = sf.openSession()) {		
			Course c = session.get(Course.class, id);
			if (c != null) {
				session.beginTransaction();
				
				if (course.getCourseName() != null && course.getCourseName() != "") {
					c.setCourseName(course.getCourseName());
				}
				
				if (course.getDescription() != null && course.getDescription() != "") {
					course.setDescription(course.getDescription());
				}
				
				if (course.getYtLink() != null && course.getYtLink() != "") {
					course.setYtLink(course.getYtLink());
				}
				
				if (course.getStatus() != null) {
					c.setStatus(course.getStatus());
				}
				session.getTransaction().commit();
				
				return Response.builder()
						.status(201)
						.message("Course successfully updated")
						.timestamp(LocalDateTime.now()).build();
				
			} else {
				return Response.builder()
						.status(404)
						.message("Course not found")
						.timestamp(LocalDateTime.now())
						.build();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
