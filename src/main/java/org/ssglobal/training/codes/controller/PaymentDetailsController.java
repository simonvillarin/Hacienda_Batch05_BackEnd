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
import org.ssglobal.training.codes.model.Payment;
import org.ssglobal.training.codes.model.PaymentDetails;
import org.ssglobal.training.codes.response.PaymentDetailsResponse;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.service.PaymentDetailsService;

import lombok.RequiredArgsConstructor;

@Component
@Path("/farming")
@RequiredArgsConstructor
public class PaymentDetailsController {
	private final PaymentDetailsService paymentDetailsService;
	
	@GET
	@Path("/payment-details")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PaymentDetails> getAllPayment() {
		return paymentDetailsService.getAllPaymentDetails();
	}

	@GET
	@Path("/payment-details/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PaymentDetails getPaymentDetailsById(@PathParam("id") Long id) {
		return paymentDetailsService.getPaymentDetailsById(id);
	}

	@GET
	@Path("/payment-details/transaction/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PaymentDetailsResponse getPaymentDetailsByTransactionId(@PathParam("id") Long id) {
		return paymentDetailsService.getPaymentDetailsByTransactionId(id);
	}
	
	@POST
	@Path("/payment-details")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPaymentDetails(@RequestBody PaymentDetails paymentDetails) {
		return paymentDetailsService.addPaymentDetails(paymentDetails);
	}
	
	@PUT
	@Path("/payment-details/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePaymentDetails(@PathParam("id") Long id, @RequestBody PaymentDetails paymentDetails) {
		return paymentDetailsService.updatePaymentDetails(id, paymentDetails);
	}

}
