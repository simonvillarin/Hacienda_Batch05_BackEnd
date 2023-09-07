package org.ssglobal.training.codes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.PaymentDetails;
import org.ssglobal.training.codes.repository.PaymentDetailsRepository;
import org.ssglobal.training.codes.response.PaymentDetailsResponse;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentDetailsService {
	private final PaymentDetailsRepository paymentDetailsRepository;
	
	public PaymentDetails getPaymentDetailsById(Long id) {
		return paymentDetailsRepository.getPaymentDetailsById(id);
	}
	
	public List<PaymentDetails> getAllPaymentDetails() {
		return paymentDetailsRepository.getAllPaymentDetails();
	}
	
	public PaymentDetailsResponse getPaymentDetailsByTransactionId(Long id) {
		 return paymentDetailsRepository.getPaymentDetailsByTransactionId(id);
	}
    
    @Transactional
    public Response addPaymentDetails(PaymentDetails paymentDetails) {
        return paymentDetailsRepository.addPaymentDetails(paymentDetails);
    }
    
    @Transactional
    public Response updatePaymentDetails(Long id, PaymentDetails paymentDetails) {
        return paymentDetailsRepository.updatePaymentDetails(id, paymentDetails);
    }
}
