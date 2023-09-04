package org.ssglobal.training.codes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.Offer;
import org.ssglobal.training.codes.model.Transaction;
import org.ssglobal.training.codes.repository.TransactionRepository;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.response.TransactionResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
	private final TransactionRepository transactionRepository;
	
	public Transaction getTransactionById(Long id) {
		return transactionRepository.getTransactionById(id);
	}
	
	public List<TransactionResponse> getTransactionBySupplierId(Long id) {
		return transactionRepository.getTransactionBySupplierId(id);
	}
	
	public List<TransactionResponse> getTransactionByFarmerId(Long id) {
		return transactionRepository.getTransactionByFarmerId(id);
	}
	
	@Transactional
	public Response addTransaction(Transaction transaction) {
		return transactionRepository.addTransaction(transaction);
	}
	
	@Transactional
	public Response updateTransaction(Long id, Transaction transaction) {
		return transactionRepository.updateTransaction(id, transaction);
	}
	
	@Transactional
	public Response deleteTransaction(Long id) {
		return transactionRepository.deleteTransaction(id);
	}
}
