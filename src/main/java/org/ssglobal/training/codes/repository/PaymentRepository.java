package org.ssglobal.training.codes.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.ssglobal.training.codes.model.Course;
import org.ssglobal.training.codes.model.Payment;
import org.ssglobal.training.codes.model.Transaction;
import org.ssglobal.training.codes.response.PaymentResponse;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PaymentRepository {
	private final SessionFactory sf;
	
	public Payment getPaymentById(Long id) {
		try (Session session = sf.openSession()) {
			return session.get(Payment.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<Payment> getAllPayment() {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM Payment", Payment.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public PaymentResponse getPaymentByTransactionId(Long id) {
		try (Session session = sf.openSession()) {
			Query<Payment> query = session.createQuery("FROM Payment WHERE transactionId = :transactionId", Payment.class)
					.setParameter("transactionId", id);
			Payment payment = query.uniqueResult();
			
			Transaction transaction = session.get(Transaction.class, payment.getTransactionId());
			
			return PaymentResponse.builder()
					.paymentId(payment.getPaymentId())
					.orderIdRef(payment.getOrderIdRef())
					.transaction(transaction)
					.paymentMode(payment.getPaymentMode())
					.paymentDate(payment.getPaymentDate())
					.paymentTime(payment.getPaymentTime())
					.status(payment.getStatus())
					.build();
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
     
    public Response addPayment(Payment payment) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            if (payment.getTransactionId() == null || payment.getTransactionId() <= 0 ) {
                return Response.builder()
                        .status(400)
                        .message("Invalid transaction Id")
                        .timestamp(LocalDateTime.now())
                        .build();
            }
            if (payment.getPaymentMode() == null || payment.getPaymentMode().isEmpty()) {
                return Response.builder()
                        .status(400)
                        .message("Invalid Payment Mode")
                        .timestamp(LocalDateTime.now())
                        .build();
            }
                
            Payment newPayment = Payment.builder()
                    .orderIdRef(generateOrderIdRef())
                    .transactionId(payment.getTransactionId())
                    .paymentMode(payment.getPaymentMode())
                    .paymentDate(LocalDate.now())
                    .paymentTime(LocalTime.now())
                    .status(true)
                    .build();

            session.persist(newPayment);

            session.getTransaction().commit();

            return Response.builder()
                    .status(201)
                    .message("Payment successfully created")
                    .timestamp(LocalDateTime.now())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public Response updatePayment(Long id, Payment payment) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            
            Payment existingPayment = session.get(Payment.class, id);
            
            if (payment.getPaymentMode() != null) {
                existingPayment.setPaymentMode(payment.getPaymentMode());
            }
            if (payment.getStatus() != null) {
                existingPayment.setStatus(payment.getStatus());
            }
            
            session.update(existingPayment);
            session.getTransaction().commit();

            return Response.builder()
                    .status(200)
                    .message("Payment successfully updated")
                    .timestamp(LocalDateTime.now())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public String generateOrderIdRef() {
        long timestamp = System.currentTimeMillis();

        Random random = new Random();
        int randomNumber = random.nextInt(10000);
        String orderIdRef = "ORD" + timestamp + randomNumber;

        return orderIdRef;
    }
}
