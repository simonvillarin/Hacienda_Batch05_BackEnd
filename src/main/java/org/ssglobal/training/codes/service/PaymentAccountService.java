package org.ssglobal.training.codes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.PaymentAccount;
import org.ssglobal.training.codes.repository.PaymentAccountRepository;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentAccountService {
	private final PaymentAccountRepository paymentAccountRepository;

	public List<PaymentAccount> getAllPaymentAccount() {
		return paymentAccountRepository.getAllPaymentAccount();
	}

	public PaymentAccount getPaymentAccountById(Long id) {
		return paymentAccountRepository.getPaymentAccountById(id);
	}

	public PaymentAccount getPaymentAccountByFarmerId(Integer id) {
		return paymentAccountRepository.getPaymentAccountByFarmerId(id);
	}

	@Transactional
	public Response addPaymentAccount(PaymentAccount paymentAccount) {
		return paymentAccountRepository.addPaymentAccount(paymentAccount);
	}

	@Transactional
	public Response updatePaymentAccount(Long id, PaymentAccount paymentAccount) {
		return paymentAccountRepository.updatePaymentAccount(id, paymentAccount);
	}
}
