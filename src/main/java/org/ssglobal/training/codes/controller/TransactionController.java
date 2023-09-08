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
import org.ssglobal.training.codes.model.Payment;
import org.ssglobal.training.codes.model.Transaction;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.response.TransactionResponse;
import org.ssglobal.training.codes.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Component
@Path("/farming")
@RequiredArgsConstructor
public class TransactionController {
	private final TransactionService transactionService;
	
	@GET
	@Path("/transactions")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transaction> getAllTransaction() {
		return transactionService.getAllTransaction();
	}
	
	@GET
	@Path("/transaction/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Transaction getTransactionById(@PathParam("id") Long id) {
		return transactionService.getTransactionById(id);
	}
	
	@GET
	@Path("/transaction/supplier/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TransactionResponse> getTransactionBySupplierId(@PathParam("id") Long id) {
		return transactionService.getTransactionBySupplierId(id);
	}
	
	@GET
	@Path("/transaction/farmer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TransactionResponse> getTransactionByFarmerId(@PathParam("id") Long id) {
		return transactionService.getTransactionByFarmerId(id);
	}
	
	@POST
	@Path("/transaction")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTransaction(@RequestBody Transaction transaction) {
		return transactionService.addTransaction(transaction);
	}
	
	@PUT
	@Path("/transaction/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTransaction(@PathParam("id") Long id, @RequestBody Transaction transaction) {
		return transactionService.updateTransaction(id, transaction);
	}
	
	@DELETE
	@Path("/transaction/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTransaction(@PathParam("id") Long id) {
		return transactionService.deleteTransaction(id);
	}
}
