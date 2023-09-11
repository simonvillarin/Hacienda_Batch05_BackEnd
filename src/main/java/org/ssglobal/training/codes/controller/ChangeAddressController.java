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
import org.ssglobal.training.codes.model.ChangeAddress;
import org.ssglobal.training.codes.response.ChangeAddressResponse;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.service.ChangeAddressService;

import lombok.RequiredArgsConstructor;

@Component
@Path("/farming")
@RequiredArgsConstructor
public class ChangeAddressController {
	private final ChangeAddressService changeAddressService;
	
	@GET
	@Path("/change-addresss")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ChangeAddress> getChangeAddress() {
		return changeAddressService.getAllChangeAddress();
	}
	
	@GET
	@Path("/change-address/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ChangeAddress getChangeAddressById(@PathParam("id") Long id) {
		return changeAddressService.getChangeAddressById(id);
	}

	@GET
	@Path("/change-address/transaction/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ChangeAddressResponse getChangeAddressByTransactionId(@PathParam("id") Long id) {
		return changeAddressService.getChangeAddressByTransactionId(id);
	}
	
	@POST
	@Path("/transaction/change-address")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addChangeAddress(@RequestBody ChangeAddress changeAddress) {
		return changeAddressService.addChangeAddress(changeAddress);
	}
	
	@PUT
	@Path("/change-address/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateChangeAddress(@PathParam("id") Long id, @RequestBody ChangeAddress changeAddress) {
		return changeAddressService.updateChangeAddress(id, changeAddress);
	}
}
