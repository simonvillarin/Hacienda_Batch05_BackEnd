package org.ssglobal.training.codes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.Payment;
import org.ssglobal.training.codes.repository.PaymentRepository;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	private final PaymentRepository paymentRepository;
	
	public List<Payment> getAllPayment() {
		return paymentRepository.getAllPayment();
	}
	
	public Payment getPaymentById(Integer id) {
		return paymentRepository.getPaymentById(id);
	}
	
    public Payment getPaymentByOrderIdRef(Integer orderIdRef) {
    	return paymentRepository.getPaymentByOrderIdRef(orderIdRef);
    }
    
    public Payment getPaymentByOfferId(Integer offerId) {
    	return paymentRepository.getPaymentByOfferId(offerId);
    }
    
    @Transactional
    public Response addPayment(Payment paymentRequest) {
        return paymentRepository.addPayment(paymentRequest);
    }
    
    @Transactional
    public Response updatePayment(Integer id, Payment paymentRequest) {
        return paymentRepository.updatePayment(id, paymentRequest);
    }

}
