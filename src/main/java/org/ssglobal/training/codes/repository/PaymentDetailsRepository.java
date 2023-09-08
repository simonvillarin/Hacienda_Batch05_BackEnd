package org.ssglobal.training.codes.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.ssglobal.training.codes.model.PaymentDetails;
import org.ssglobal.training.codes.model.Transaction;
import org.ssglobal.training.codes.response.PaymentDetailsResponse;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PaymentDetailsRepository {
	private final SessionFactory sf;

	public PaymentDetails getPaymentDetailsById(Long id) {
		try (Session session = sf.openSession()) {
			return session.get(PaymentDetails.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<PaymentDetails> getAllPaymentDetails() {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM PaymentDetails", PaymentDetails.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public PaymentDetailsResponse getPaymentDetailsByTransactionId(Long id) {
		try (Session session = sf.openSession()) {
			Query<PaymentDetails> query = session
					.createQuery("FROM PaymentDetails WHERE transactionId = :transactionId", PaymentDetails.class)
					.setParameter("transactionId", id);
			PaymentDetails paymentDetails = query.uniqueResult();

			Transaction transaction = session.get(Transaction.class, paymentDetails.getTransactionId());

			return PaymentDetailsResponse.builder().paymentDetailsId(paymentDetails.getPaymentDetailsId())
					.paymentIdRef(paymentDetails.getPaymentIdRef()).transaction(transaction)
					.paymentAccountId(paymentDetails.getPaymentAccountId()).paymentMode(paymentDetails.getPaymentMode())
					.accountNumber(paymentDetails.getAccountNumber()).accountName(paymentDetails.getAccountName())
					.status(paymentDetails.getStatus()).build();

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Response addPaymentDetails(PaymentDetails paymentDetails) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();

			if (paymentDetails.getTransactionId() == null || paymentDetails.getTransactionId() <= 0) {
				return Response.builder().status(400).message("Invalid transaction Id").timestamp(LocalDateTime.now())
						.build();
			}

			if (paymentDetails.getPaymentAccountId() == null || paymentDetails.getPaymentAccountId() <= 0) {
				return Response.builder().status(400).message("Invalid Payment Account Id")
						.timestamp(LocalDateTime.now()).build();
			}

			if (paymentDetails.getPaymentMode() == null || paymentDetails.getPaymentMode().isEmpty()) {
				return Response.builder().status(400).message("Invalid Payment Mode").timestamp(LocalDateTime.now())
						.build();
			}

			if (paymentDetails.getAccountNumber() == null) {
				return Response.builder().status(400).message("Invalid account number").timestamp(LocalDateTime.now())
						.build();
			}

			if (paymentDetails.getAccountName() == null || paymentDetails.getAccountName().isEmpty()) {
				return Response.builder().status(400).message("Invalid account name").timestamp(LocalDateTime.now())
						.build();
			}

			PaymentDetails newPaymentDetails = PaymentDetails.builder().paymentIdRef(generatePaymentIdRef())
					.transactionId(paymentDetails.getTransactionId())
					.paymentAccountId(paymentDetails.getPaymentAccountId()).paymentMode(paymentDetails.getPaymentMode())
					.accountNumber(paymentDetails.getAccountNumber()).accountName(paymentDetails.getAccountName())
					.status(true).build();

			session.persist(newPaymentDetails);

			session.getTransaction().commit();

			return Response.builder().status(201).message("Payment Details successfully created")
					.timestamp(LocalDateTime.now()).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Response updatePaymentDetails(Long id, PaymentDetails paymentDetails) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();

			PaymentDetails existingPaymentDetails = session.get(PaymentDetails.class, id);

			if (paymentDetails.getPaymentMode() != null) {
				existingPaymentDetails.setPaymentMode(paymentDetails.getPaymentMode());
			}

			if (paymentDetails.getAccountNumber() != null) {
				existingPaymentDetails.setAccountNumber(paymentDetails.getAccountNumber());
			}

			if (paymentDetails.getAccountName() != null) {
				existingPaymentDetails.setAccountName(paymentDetails.getAccountName());
			}

			if (paymentDetails.getStatus() != null) {
				existingPaymentDetails.setStatus(paymentDetails.getStatus());
			}

			session.update(existingPaymentDetails);
			session.getTransaction().commit();

			return Response.builder().status(200).message("Payment Details successfully updated")
					.timestamp(LocalDateTime.now()).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public String generatePaymentIdRef() {
		long timestamp = System.currentTimeMillis();

		Random random = new Random();
		int randomNumber = random.nextInt(10000);
		String paymentIdRef = "PAY" + timestamp + randomNumber;

		return paymentIdRef;
	}

}
