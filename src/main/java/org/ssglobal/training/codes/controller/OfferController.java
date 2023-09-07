package org.ssglobal.training.codes.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.ssglobal.training.codes.model.Offer;
import org.ssglobal.training.codes.response.OfferResponse;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.service.OfferService;

import lombok.RequiredArgsConstructor;

@Component
@Path("/farming")
@RequiredArgsConstructor
public class OfferController {
	private final OfferService offerService;
	
	@GET
	@Path("/offers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Offer> getAllOffer() {
		return offerService.getAllOffer();
	}
	
	@GET
	@Path("/offers/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Offer getOfferById(@PathParam("id") Long id) {
		return offerService.getOfferById(id);
	}
	
	@GET
	@Path("/offers/farmer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OfferResponse> getOfferByFarmerId(@PathParam("id") Long id) {
		return offerService.getOfferByFarmerId(id);
	}
	
	@GET
	@Path("/offers/supplier/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OfferResponse> getOfferBySupplierId(@PathParam("id") Long id) {
		return offerService.getOfferBySupplierId(id);
	}
	
	@GET
	@Path("/offers/post/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OfferResponse> getOfferPostId(@PathParam("id") Long id) {
		return offerService.getOfferByPostId(id);
	}
	
	@POST
	@Path("/offers")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addOffer(@RequestBody Offer offer) {
		return offerService.addOffer(offer);
	}
	
	@PUT
	@Path("/offers/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateOffer(@PathParam("id") Long id, @RequestBody Offer complaint) {
		return offerService.updateOffer(id, complaint);
	}
	
	@DELETE
	@Path("/offers/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteOffer(@PathParam("id") Long id) {
		return offerService.deleteOffer(id);
	}
}