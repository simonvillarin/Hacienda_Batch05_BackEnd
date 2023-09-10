package org.ssglobal.training.codes.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.ssglobal.training.codes.model.Advertisement;
import org.ssglobal.training.codes.model.Offer;
import org.ssglobal.training.codes.model.Payment;
import org.ssglobal.training.codes.model.PaymentAccount;
import org.ssglobal.training.codes.model.PaymentDetails;
import org.ssglobal.training.codes.model.Transaction;
import org.ssglobal.training.codes.model.User;
import org.ssglobal.training.codes.response.OfferResponse;
import org.ssglobal.training.codes.response.PaymentDetailsResponse1;
import org.ssglobal.training.codes.response.PaymentResponse;
import org.ssglobal.training.codes.response.PaymentResponse1;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.response.TransactionResponse;

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
					.fullName(payment.getFullName())
					.unit(payment.getUnit())
					.street(payment.getStreet())
					.village(payment.getVillage())
					.barangay(payment.getBarangay())
					.city(payment.getProvince())
					.province(payment.getProvince())
					.region(payment.getRegion())
					.contact(payment.getContact())
					.status(payment.getStatus())
					.build();
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<PaymentResponse1> getPaymentByFarmerId(Long id) {
		try (Session session = sf.openSession()) {
			List<Payment> payments = session.createQuery("FROM Payment", Payment.class)
					.list();
			
			List<PaymentResponse1> history = new ArrayList<>();
			payments.stream().forEach(payment -> {
				Transaction transaction = session.get(Transaction.class, payment.getTransactionId());
				if (transaction.getFarmerId() == id && transaction.getDeliverDate() != null) {		
					Query<User> query = session.createQuery("FROM User WHERE userId = :userId", User.class)
							.setParameter("userId", transaction.getSupplierId());
					User supplier = query.uniqueResult();
					
					Query<User> query1 = session.createQuery("FROM User WHERE userId = :userId", User.class)
							.setParameter("userId", transaction.getFarmerId());
					User farmer = query1.uniqueResult();
					
					Offer offer = session.get(Offer.class, transaction.getOfferId());
					
					Advertisement advertisement = session.get(Advertisement.class, offer.getPostId());
					
					OfferResponse offerResponse = OfferResponse.builder()
							.offerId(offer.getOfferId())
							.farmer(farmer)
							.supplier(supplier)
							.advertisement(advertisement)
							.measurement(offer.getMeasurement())
							.value(offer.getValue())
							.price(offer.getPrice())
							.offerDate(offer.getOfferDate())
							.offerTime(offer.getOfferTime())
							.isViewed(offer.getIsViewed())
							.build();
					
					TransactionResponse transactionResponse = TransactionResponse.builder()
							.transactionId(transaction.getTransactionId())
							.supplier(supplier)
							.farmer(farmer)
							.offer(offerResponse)
							.acceptDate(transaction.getAcceptDate())
							.acceptTime(transaction.getAcceptTime())
							.paidDate(transaction.getPaidDate())
							.paidTime(transaction.getPaidTime())
							.deliverDate(transaction.getDeliverDate())
							.deliverTime(transaction.getDeliverTime())
							.status(transaction.getStatus())
							.isViewed(transaction.getIsViewed())
							.build();
					
					PaymentDetails details = session.createQuery("FROM PaymentDetails WHERE transactionId = :transactionId", PaymentDetails.class)
							.setParameter("transactionId", payment.getTransactionId())
							.uniqueResult();
					
					PaymentAccount paymentAccount = session.get(PaymentAccount.class, details.getPaymentAccountId());
					
					PaymentDetailsResponse1 paymentDetails = PaymentDetailsResponse1.builder()
							.paymentDetailsId(details.getPaymentDetailsId())
							.paymentIdRef(details.getPaymentIdRef())
							.transaction(transactionResponse)
							.paymentMode(details.getPaymentMode())
							.paymentAccount(paymentAccount)
							.accountNumber(details.getAccountNumber())
							.accountName(details.getAccountName())
							.status(details.getStatus())
							.build();
					
					PaymentResponse1 paymentResponse = PaymentResponse1.builder()
							.paymentId(payment.getPaymentId())
							.orderIdRef(payment.getOrderIdRef())
							.payment(paymentDetails)
							.paymentMode(payment.getPaymentMode())
							.paymentDate(payment.getPaymentDate())
							.paymentTime(payment.getPaymentTime())
							.fullName(payment.getFullName())
							.unit(payment.getUnit())
							.street(payment.getStreet())
							.village(payment.getVillage())
							.barangay(payment.getBarangay())
							.city(payment.getCity())
							.province(payment.getProvince())
							.region(payment.getRegion())
							.contact(payment.getContact())
							.status(payment.getStatus())
							.build();
					history.add(paymentResponse);
				}
			});
			
			return history;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<PaymentResponse1> getPaymentBySupplierId(Long id) {
		try (Session session = sf.openSession()) {
			List<Payment> payments = session.createQuery("FROM Payment", Payment.class)
					.list();
			
			List<PaymentResponse1> history = new ArrayList<>();
			payments.stream().forEach(payment -> {
				Transaction transaction = session.get(Transaction.class, payment.getTransactionId());
				if (transaction.getSupplierId() == id && transaction.getDeliverDate() != null) {
					Query<User> query = session.createQuery("FROM User WHERE userId = :userId", User.class)
							.setParameter("userId", transaction.getSupplierId());
					User supplier = query.uniqueResult();
					
					Query<User> query1 = session.createQuery("FROM User WHERE userId = :userId", User.class)
							.setParameter("userId", transaction.getFarmerId());
					User farmer = query1.uniqueResult();
					
					Offer offer = session.get(Offer.class, transaction.getOfferId());
					
					Advertisement advertisement = session.get(Advertisement.class, offer.getPostId());
					
					OfferResponse offerResponse = OfferResponse.builder()
							.offerId(offer.getOfferId())
							.farmer(farmer)
							.supplier(supplier)
							.advertisement(advertisement)
							.measurement(offer.getMeasurement())
							.value(offer.getValue())
							.price(offer.getPrice())
							.offerDate(offer.getOfferDate())
							.offerTime(offer.getOfferTime())
							.isViewed(offer.getIsViewed())
							.build();
					
					TransactionResponse transactionResponse = TransactionResponse.builder()
							.transactionId(transaction.getTransactionId())
							.supplier(supplier)
							.farmer(farmer)
							.offer(offerResponse)
							.acceptDate(transaction.getAcceptDate())
							.acceptTime(transaction.getAcceptTime())
							.paidDate(transaction.getPaidDate())
							.paidTime(transaction.getPaidTime())
							.deliverDate(transaction.getDeliverDate())
							.deliverTime(transaction.getDeliverTime())
							.status(transaction.getStatus())
							.isViewed(transaction.getIsViewed())
							.build();
					
					PaymentDetails details = session.createQuery("FROM PaymentDetails WHERE transactionId = :transactionId", PaymentDetails.class)
							.setParameter("transactionId", payment.getTransactionId())
							.uniqueResult();
					
					PaymentAccount paymentAccount = session.get(PaymentAccount.class, details.getPaymentAccountId());
					
					PaymentDetailsResponse1 paymentDetails = PaymentDetailsResponse1.builder()
							.paymentDetailsId(details.getPaymentDetailsId())
							.paymentIdRef(details.getPaymentIdRef())
							.transaction(transactionResponse)
							.paymentMode(details.getPaymentMode())
							.paymentAccount(paymentAccount)
							.accountNumber(details.getAccountNumber())
							.accountName(details.getAccountName())
							.status(details.getStatus())
							.build();
					
					PaymentResponse1 paymentResponse = PaymentResponse1.builder()
							.paymentId(payment.getPaymentId())
							.orderIdRef(payment.getOrderIdRef())
							.payment(paymentDetails)
							.paymentMode(payment.getPaymentMode())
							.paymentDate(payment.getPaymentDate())
							.paymentTime(payment.getPaymentTime())
							.fullName(payment.getFullName())
							.unit(payment.getUnit())
							.street(payment.getStreet())
							.village(payment.getVillage())
							.barangay(payment.getBarangay())
							.city(payment.getCity())
							.province(payment.getProvince())
							.region(payment.getRegion())
							.contact(payment.getContact())
							.status(payment.getStatus())
							.build();
					history.add(paymentResponse);
				}
			});
			
			return history;
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
                    .fullName(payment.getFullName())
					.unit(payment.getUnit())
					.street(payment.getStreet())
					.village(payment.getVillage())
					.barangay(payment.getBarangay())
					.city(payment.getProvince())
					.province(payment.getProvince())
					.region(payment.getRegion())
					.contact(payment.getContact())
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
            
            if (payment.getFullName() == null || payment.getFullName().isEmpty()) {
                existingPayment.setFullName(payment.getFullName());
            }
            
            if (payment.getUnit() == null || payment.getUnit().isEmpty()) {
                existingPayment.setUnit(payment.getUnit());
            }
            
            if (payment.getStreet() == null || payment.getStreet().isEmpty()) {
                existingPayment.setStreet(payment.getStreet());
            }
            
            if (payment.getVillage() == null || payment.getVillage().isEmpty()) {
                existingPayment.setVillage(payment.getVillage());
            }
            
            if (payment.getBarangay() == null || payment.getBarangay().isEmpty()) {
                existingPayment.setBarangay(payment.getBarangay());
            }
            
            if (payment.getCity() == null || payment.getCity().isEmpty()) {
                existingPayment.setCity(payment.getCity());
            }
            
            if (payment.getProvince() == null || payment.getProvince().isEmpty()) {
                existingPayment.setProvince(payment.getProvince());
            }
            
            if (payment.getRegion() == null || payment.getRegion().isEmpty()) {
                existingPayment.setRegion(payment.getRegion());
            }
            
            if (payment.getContact() == null || payment.getContact().isEmpty()) {
                existingPayment.setContact(payment.getContact());
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
