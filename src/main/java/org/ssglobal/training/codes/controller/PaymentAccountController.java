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
import org.ssglobal.training.codes.model.CourseEnrolled;
import org.ssglobal.training.codes.model.PaymentAccount;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.service.PaymentAccountService;

import lombok.RequiredArgsConstructor;

@Component
@Path("/farming")
@RequiredArgsConstructor
public class PaymentAccountController {
	private final PaymentAccountService paymentAccountService;

	@GET
	@Path("/payment-accounts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PaymentAccount> getAllPaymentAccount() {
		return paymentAccountService.getAllPaymentAccount();
	}

	@GET
	@Path("/payment-account/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PaymentAccount getPaymentAccountById(@PathParam("id") Long id) {
		return paymentAccountService.getPaymentAccountById(id);
	}

	@GET
	@Path("/payment-account/farmer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PaymentAccount getPaymentAcccountByFarmerId(@PathParam("id") Integer id) {
		return paymentAccountService.getPaymentAccountByFarmerId(id);
	}

	@POST
	@Path("/payment-accounts")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPaymentAccount(@RequestBody PaymentAccount paymentAccount) {
		return paymentAccountService.addPaymentAccount(paymentAccount);
	}

	@PUT
	@Path("/payment-account/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePaymentAccount(@PathParam("id") Long id, @RequestBody PaymentAccount paymentAccount) {
		return paymentAccountService.updatePaymentAccount(id, paymentAccount);
	}
}
