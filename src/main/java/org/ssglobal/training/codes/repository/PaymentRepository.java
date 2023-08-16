package org.ssglobal.training.codes.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.ssglobal.training.codes.model.Payment;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PaymentRepository {
	private final SessionFactory sf;
	
	public List<Payment> getAllPayment() {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM Payment", Payment.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Payment getPaymentById(Integer id) {
		try (Session session = sf.openSession()) {
			return session.get(Payment.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
    public Payment getPaymentByOrderIdRef(Integer orderIdRef) {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Payment WHERE order_id_ref = :orderIdRef", Payment.class)
                .setParameter("orderIdRef", orderIdRef)
                .uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public Payment getPaymentByOfferId(Integer offerId) {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Payment WHERE offer_id = :offerId", Payment.class)
                .setParameter("offerId", offerId)
                .uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public Response addPayment(Payment payment) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            // Validate input fields
            if (payment.getOrderIdRef() == null || payment.getOrderIdRef() <= 0) {
                return Response.builder()
                        .status(400)
                        .message("Invalid Order ID Reference")
                        .timestamp(LocalDateTime.now())
                        .build();
            }
            
            if (payment.getOfferId() == null || payment.getOfferId() <= 0) {
                return Response.builder()
                        .status(400)
                        .message("Invalid Offer ID")
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
                    .orderIdRef(payment.getOrderIdRef())
                    .offerId(payment.getOfferId())
                    .paymentMode(payment.getPaymentMode())
                    .paymentDate(LocalDate.now())
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
    
    public Response updatePayment(Integer id, Payment payment) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            
            Payment existingPayment = session.get(Payment.class, id);

            if (existingPayment == null) {
                return Response.builder()
                        .status(404)
                        .message("Payment not found")
                        .timestamp(LocalDateTime.now())
                        .build();
            }

            if (payment.getOrderIdRef() != null && payment.getOrderIdRef() <= 0) {
                return Response.builder()
                        .status(400)
                        .message("Invalid Order ID Reference")
                        .timestamp(LocalDateTime.now())
                        .build();
            }
            
            if (payment.getOfferId() != null && payment.getOfferId() <= 0) {
                return Response.builder()
                        .status(400)
                        .message("Invalid Offer ID")
                        .timestamp(LocalDateTime.now())
                        .build();
            }
            
            if (payment.getPaymentMode() != null && payment.getPaymentMode().isEmpty()) {
                return Response.builder()
                        .status(400)
                        .message("Invalid Payment Mode")
                        .timestamp(LocalDateTime.now())
                        .build();
            }
            
            if (payment.getOrderIdRef() != null) {
                existingPayment.setOrderIdRef(payment.getOrderIdRef());
            }
            if (payment.getOfferId() != null) {
                existingPayment.setOfferId(payment.getOfferId());
            }
            if (payment.getPaymentMode() != null) {
                existingPayment.setPaymentMode(payment.getPaymentMode());
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
}
