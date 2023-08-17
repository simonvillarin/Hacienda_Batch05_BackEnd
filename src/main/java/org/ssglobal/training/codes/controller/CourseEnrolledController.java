package org.ssglobal.training.codes.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.ssglobal.training.codes.model.CourseEnrolled;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.service.CourseEnrolledService;

import lombok.RequiredArgsConstructor;

@Component
@Path("/farming")
@RequiredArgsConstructor
public class CourseEnrolledController {
	private final CourseEnrolledService courseEnrolledService;

	@GET
	@Path("/enrolled-courses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CourseEnrolled> getAllEnrolledCourse() {
		return courseEnrolledService.getAllEnrolledCourse();
	}
	
	@GET
	@Path("/enrolled-courses/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CourseEnrolled getEnrolledCourseById(@PathParam("id") Long id) {
		return courseEnrolledService.getEnrolledCourseById(id);
	}
	
	@GET
	@Path("/enrolled-courses/farmer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CourseEnrolled> getEnrolledCourseByFarmerId(@PathParam("id") Integer id) {
		return courseEnrolledService.getEnrolledCourseByFarmerId(id);
	}
	
	@GET
	@Path("/enrolled-courses/course/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CourseEnrolled> getEnrolledCourseByCourseId(@PathParam("id") Integer id) {
		return courseEnrolledService.getEnrolledCourseByCourseId(id);
	}
	
	@POST
	@Path("/enrolled-courses")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEnrolledCourse(@RequestBody CourseEnrolled courseEnrolled) {
		return courseEnrolledService.addEnrolledCourse(courseEnrolled);
	}
	
	@PUT
	@Path("/enrolled-courses/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEnrolledCourse(@PathParam("id") Long id, @RequestBody CourseEnrolled courseEnrolled) {
		return courseEnrolledService.updateEnrolledCourse(id, courseEnrolled);
	}
}
