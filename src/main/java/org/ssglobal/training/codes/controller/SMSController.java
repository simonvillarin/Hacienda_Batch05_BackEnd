package org.ssglobal.training.codes.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.ssglobal.training.codes.request.SMSRequest;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.service.SMSService;

import lombok.RequiredArgsConstructor;

@Component
@Path("/farming")
@RequiredArgsConstructor
public class SMSController {
	private final SMSService smsService;
	
	@POST
	@Path("/sms/admin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendAdminSMS(SMSRequest sms) {
		return smsService.sendAdminSMS(sms);
	}
	
	@POST
	@Path("/sms/farmer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendFarmerSMS(SMSRequest sms) {
		return smsService.sendFarmerSMS(sms);
	}
	
	@POST
	@Path("/sms/supplier")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendSupplierSMS(SMSRequest sms) {
		return smsService.sendSupplierSMS(sms);
	}
}
