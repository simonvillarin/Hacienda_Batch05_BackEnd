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
import org.ssglobal.training.codes.model.Complaint;
import org.ssglobal.training.codes.request.ComplaintRequest;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.service.ComplaintService;

import lombok.RequiredArgsConstructor;

@Component
@Path("/farming")
@RequiredArgsConstructor
public class ComplaintController {
	private final ComplaintService complaintService;
	
	@GET
	@Path("/complaints")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Complaint> getAllComplaint() {
		return complaintService.getAllComplaints();
	}
	
	@GET
	@Path("/complaint/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Complaint getComplaintById(@PathParam("id") Long id) {
		return complaintService.getComplaintById(id);
	}
	
	@GET
	@Path("/complaint/farmer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Complaint> getComplaintByFarmerId(@PathParam("id") Integer id) {
		return complaintService.getComplaintByFarmerId(id);
	}
	
	@POST
	@Path("/complaint")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addComplaint(@RequestBody ComplaintRequest complaint) {
		return complaintService.addComplaint(complaint);
	}
	
	@PUT
	@Path("/complaint/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateComplaint(@PathParam("id") Long id, @RequestBody ComplaintRequest complaint) {
		return complaintService.updateComplaint(id, complaint);
	}
}
