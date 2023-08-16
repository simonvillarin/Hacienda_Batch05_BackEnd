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
import org.ssglobal.training.codes.service.FarmingTipsService;

import lombok.RequiredArgsConstructor;

@Component
@Path("/farming")
@RequiredArgsConstructor
public class FarmingTipsController {
	private final FarmingTipsService farmingTipsService;
	
	@GET
	@Path("/tips")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FarmingTips> getAllFarmingTips() {
		return farmingTipsService.getAllFarmingTips();
	}
	
	@GET
	@Path("/tip/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public FarmingTips getFarmingTipsById(@PathParam("id") Long id) {
		return farmingTipsService.getFarmingTipsById(id);
	}
	
	@POST
	@Path("/tip")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFarmingTip(@RequestBody FarmingTips farmingTip) {
		return farmingTipsService.addFarmingTip(farmingTip);
	}
	
	@POST
	@Path("/tip/image")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFarmingTipWithImage(@RequestBody FarmingTipsRequest farmingTip) {
		return farmingTipsService.addFarmingTipWithImage(farmingTip);
	}
	
	@PUT
	@Path("/tip/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateFarmingTip(@PathParam("id") Long id, @RequestBody FarmingTips farmingTip) {
		return farmingTipsService.updateFarmingTip(id, farmingTip);
	}
	
	@PUT
	@Path("/tip/image/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateFarmingTip(@PathParam("id") Long id,  @RequestBody FarmingTipsRequest farmingTip) {
		return farmingTipsService.updateFarmingTipWithImage(id, farmingTip);
	}
}
