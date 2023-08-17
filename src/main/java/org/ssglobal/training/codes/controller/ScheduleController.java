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
import org.ssglobal.training.codes.model.Schedule;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@Component
@Path("/farming")
@RequiredArgsConstructor
public class ScheduleController {
	private final ScheduleService scheduleService;
	
	@GET
	@Path("/schedule")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Schedule> getAllEnrolledCourse() {
		return scheduleService.getAllSchedule();
	}
	
	@GET
	@Path("/schedule/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Schedule getEnrolledCourseById(@PathParam("id") Long id) {
		return scheduleService.getScheduleById(id);
	}
	
	@GET
	@Path("/schedule/farmer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Schedule> getScheduleByFarmerId(@PathParam("id") Integer id) {
		return scheduleService.getScheduleByFarmerId(id);
	}

	
	@POST
	@Path("/schedule")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEnrolledCourse(@RequestBody Schedule schedule) {
		return scheduleService.addSchedule(schedule);
	}
	
	@PUT
	@Path("/schedule/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateSchedule(@PathParam("id") Long id, @RequestBody Schedule schedule) {
		return scheduleService.updateSchedule(id, schedule);
	}

}
