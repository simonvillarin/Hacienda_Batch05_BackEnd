package org.ssglobal.training.codes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.Payment;
import org.ssglobal.training.codes.repository.PaymentRepository;
import org.ssglobal.training.codes.response.PaymentResponse;
import org.ssglobal.training.codes.response.PaymentResponse1;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	private final PaymentRepository paymentRepository;
	
	public Payment getPaymentById(Long id) {
		return paymentRepository.getPaymentById(id);
	}
	
	public List<Payment> getAllPayment() {
		return paymentRepository.getAllPayment();
	}
	
	public PaymentResponse getPaymentByTransactionId(Long id) {
		 return paymentRepository.getPaymentByTransactionId(id);
	}
	
	public List<PaymentResponse1> getPaymentByFarmerId(Long id) {
		return paymentRepository.getPaymentByFarmerId(id);
	}
	
	public List<PaymentResponse1> getPaymentBySupplierId(Long id) {
		return paymentRepository.getPaymentBySupplierId(id);
	}
    
    @Transactional
    public Response addPayment(Payment payment) {
        return paymentRepository.addPayment(payment);
    }
    
    @Transactional
    public Response updatePayment(Long id, Payment payment) {
        return paymentRepository.updatePayment(id, payment);
    }

}
