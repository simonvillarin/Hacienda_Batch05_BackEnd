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

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.ssglobal.training.codes.model.Payment;
import org.ssglobal.training.codes.request.OfferRequest;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Component
@Path("/payment")
@RequiredArgsConstructor
public class PaymentController {
	private final PaymentService paymentService;
	
	@GET
	@Path("/payments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Payment> getAllPayment() {
		return paymentService.getAllPayment();
	}
	
	@GET
	@Path("/payments/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Payment getPaymentById(@PathParam("id") Integer id) {
		return paymentService.getPaymentById(id);
	}
	
	@GET
	@Path("/payments/ref/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Payment getPaymentByOrderRefId(@PathParam("id") Integer id) {
		return paymentService.getPaymentByOrderIdRef(id);
	}
	
	@GET
	@Path("/payments/ref/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Payment getPaymentByOfferId(@PathParam("id") Integer id) {
    	return paymentService.getPaymentByOfferId(id);
    }
	
	@POST
	@Path("/payments")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPayment(@RequestBody Payment payment) {
		return paymentService.addPayment(payment);
	}
	
	@PUT
	@Path("/payments/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePayment(@PathParam("id") Integer id, @RequestBody Payment complaint) {
		return paymentService.updatePayment(id, complaint);
	}
}
