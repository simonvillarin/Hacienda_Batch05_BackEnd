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
import org.ssglobal.training.codes.model.SoldCrop;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.service.SoldCropService;

import lombok.RequiredArgsConstructor;

@Component
@Path("/sold")
@RequiredArgsConstructor
public class SoldCropController {
	private final SoldCropService cropService;
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SoldCrop> getAllSoldCrop() {
		return cropService.getAllSoldCrop();
	}
	
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SoldCrop getSoldCropById(@PathParam("id") Long id) {
		return cropService.getSoldCropById(id);
	}
	
	@GET
	@Path("/get/offer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SoldCrop> getSolfCropByOfferId(@PathParam("id") Integer id) {
		return cropService.getSolfCropByOfferId(id);
	}
	
	@POST
	@Path("/crop")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSoldCrop(SoldCrop soldCrop) {
		return cropService.addSoldCrop(soldCrop);
	}
	
	@PUT
	@Path("/crop/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateSoldCrop(@PathParam("id") Long id, SoldCrop soldCrop) {
		return cropService.updateSoldCrop(id ,soldCrop);
	}
}
