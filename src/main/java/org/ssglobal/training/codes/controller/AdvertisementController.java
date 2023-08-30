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
import org.ssglobal.training.codes.model.Advertisement;
import org.ssglobal.training.codes.request.AdvertisementRequest;
import org.ssglobal.training.codes.response.AdvertisementResponse;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.service.AdvertisementService;

import lombok.RequiredArgsConstructor;

@Component
@Path("/farming")
@RequiredArgsConstructor
public class AdvertisementController {
	private final AdvertisementService advertisementService;
	
	@GET
	@Path("/advertisements")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AdvertisementResponse> getAllAdvertisement() {
		return advertisementService.getAllAdvertisement();
	}
	
	@GET
	@Path("/ad/supplier/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AdvertisementResponse> getAdvertisementBySupplierId(@PathParam("id") Long id) {
		return advertisementService.getAdvertisementBySupplierId(id);
	}
	
	@GET
	@Path("/ad/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Advertisement getAdvertisementById(@PathParam("id") Long id) {
		return advertisementService.getAdvertisementById(id);
	}
	
	@POST
	@Path("/ad")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAdvertisement(@RequestBody AdvertisementRequest advertisement) {
		return advertisementService.addAdvertisement(advertisement);
	}
	
	@PUT
	@Path("/ad/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAdvertisement(@PathParam("id") Long id, @RequestBody AdvertisementRequest advertisement) {
		return advertisementService.updateAdvertisement(id, advertisement);
	}
}
