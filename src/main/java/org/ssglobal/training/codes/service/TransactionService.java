package org.ssglobal.training.codes.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.Transaction;
import org.ssglobal.training.codes.repository.TransactionRepository;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.response.TransactionResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
	private final TransactionRepository transactionRepository;
	
	public TransactionResponse getTransactionById(Long id) {
		return transactionRepository.getTransactionById(id);
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
