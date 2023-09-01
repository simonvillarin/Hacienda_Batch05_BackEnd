package org.ssglobal.training.codes.controller;

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
import org.ssglobal.training.codes.request.EmailRequest;
import org.ssglobal.training.codes.request.UserRequest;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.service.EmailService;

import lombok.RequiredArgsConstructor;

@Component
@Path("/farming")
@RequiredArgsConstructor
public class EmailController {
	private final EmailService emailService;
	
	@POST
	@Path("/email/send")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Integer sendEmail(@RequestBody EmailRequest email) {
		return emailService.sendEmail(email);
	}
	
	@GET
	@Path("/email/expired")
	@Produces(MediaType.TEXT_PLAIN)
	public Boolean isOTPExpired() {
		return emailService.isOtpExpired();
	}
	
	@POST
	@Path("/email/otp")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Boolean checkOTP(@RequestBody EmailRequest email) {
		return emailService.checkOTP(email);
	}
	
	@POST
	@Path("/email/check")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkEmail(@RequestBody EmailRequest email) {
		return emailService.checkEmail(email);
	}
	
	@GET
	@Path("/email/userId")
	@Produces(MediaType.TEXT_PLAIN)
	public Long getUserId() {
		return emailService.getUserId();
	}
	
	@PUT
	@Path("/email/user/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") Long id, @RequestBody  UserRequest user) {
		return emailService.updateUser(id, user);
	}
} 
