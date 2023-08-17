package org.ssglobal.training.codes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.CourseEnrolled;
import org.ssglobal.training.codes.repository.CourseEnrolledRepository;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseEnrolledService {
	private final CourseEnrolledRepository courseEnrolledRepository;

	public List<CourseEnrolled> getAllEnrolledCourse() {
		return courseEnrolledRepository.getAllEnrolledCourse();
	}
	
	public CourseEnrolled getEnrolledCourseById(Long id) {
		return courseEnrolledRepository.getEnrolledCourseById(id);
	}
	
	public List<CourseEnrolled> getEnrolledCourseByFarmerId(Integer id) {
		return courseEnrolledRepository.getEnrolledCourseByFarmerId(id);
	}
	
	public List<CourseEnrolled> getEnrolledCourseByCourseId(Integer id) {
		return courseEnrolledRepository.getEnrolledCourseByCourseId(id);
	}

	@Transactional
	public Response addEnrolledCourse(CourseEnrolled courseEnrolled) {
		return courseEnrolledRepository.addEnrolledCourse(courseEnrolled);
	}

	@Transactional
	public Response updateEnrolledCourse(Long id, CourseEnrolled courseEnrolled) {
		return courseEnrolledRepository.updateEnrolledCourse(id, courseEnrolled);
	}
}
