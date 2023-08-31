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
	@Path("/sms")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendSMS(SMSRequest sms) {
		return smsService.sendSMS(sms);
	}
}
