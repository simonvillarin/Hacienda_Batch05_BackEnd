package org.ssglobal.training.codes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.Course;
import org.ssglobal.training.codes.repository.CourseRepository;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {
	private final CourseRepository courseRepository;
	
	public List<Course> getAllCourse() {
		return courseRepository.getAllCourse();
	}
	
	public Course getCourseById(Long id) {
		return courseRepository.getCourseById(id);
	}
	
	@Transactional
	public Response addCourse(Course course) {
		return courseRepository.addCourse(course);
	}
	
	@Transactional
	public Response updateCourse(Long id, Course course) {
		return courseRepository.updateCourse(id, course);
	}
}
