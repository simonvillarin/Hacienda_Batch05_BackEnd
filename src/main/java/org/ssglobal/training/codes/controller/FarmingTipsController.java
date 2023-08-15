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
import org.ssglobal.training.codes.model.FarmingTips;
import org.ssglobal.training.codes.request.FarmingTipsRequest;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.service.impl.FarmingTipsServiceImpl;

import lombok.RequiredArgsConstructor;

@Component
@Path("/farming")
@RequiredArgsConstructor
public class FarmingTipsController {
	private final FarmingTipsServiceImpl farmingTipsServiceImpl;
	
	@GET
	@Path("/tips")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FarmingTips> findAll() {
		return farmingTipsServiceImpl.findAllFarmingTips();
	}
	
	@GET
	@Path("/tip/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public FarmingTips findById(@PathParam("id")  Long tipId) {
		return farmingTipsServiceImpl.findById(tipId);
	}
	
	@POST
	@Path("/tip")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTio(@RequestBody FarmingTips tip) {
		return farmingTipsServiceImpl.addTip(tip);
	}
	
	@POST
	@Path("/tip/image")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTipWithImage(@RequestBody FarmingTipsRequest tip) {
		return farmingTipsServiceImpl.addTipWithImage(tip);
	}
	
	@PUT
	@Path("/tip/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTip(@PathParam("id") Long tipId, @RequestBody FarmingTips tip) {
		return farmingTipsServiceImpl.updateTip(tipId, tip);
	}
	
	@PUT
	@Path("/tip/image/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTipWithImage(@PathParam("id") Long tipId, @RequestBody FarmingTipsRequest tip) {
		return farmingTipsServiceImpl.updateTipWithImage(tipId, tip);
	}
}
