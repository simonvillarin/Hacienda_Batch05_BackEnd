package org.ssglobal.training.codes.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.ssglobal.training.codes.model.CourseEnrolled;
import org.ssglobal.training.codes.model.PaymentAccount;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PaymentAccountRepository {
	private final SessionFactory sf;

	public PaymentAccount getPaymentAccountById(Long id) {
		try (Session session = sf.openSession()) {
			return session.get(PaymentAccount.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<PaymentAccount> getAllPaymentAccount() {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM PaymentAccount", PaymentAccount.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public PaymentAccount getPaymentAccountByFarmerId(Integer id) {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM PaymentAccount WHERE farmerId = :farmerId", PaymentAccount.class)
					.setParameter("farmerId", id)
					.uniqueResult();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Response addPaymentAccount(PaymentAccount paymentAccount) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();

			PaymentAccount pa = new PaymentAccount();
			pa.setStatus(true);

			if (paymentAccount.getFarmerId() != null) {
				pa.setFarmerId(paymentAccount.getFarmerId());
			}

			if (paymentAccount.getAccountNumber() != null) {
				pa.setAccountNumber(paymentAccount.getAccountNumber());
			}

			if (paymentAccount.getAccountName() != null) {
				pa.setAccountName(paymentAccount.getAccountName());
			}
			
			PaymentAccount _paymentAccount = session.createQuery("FROM PaymentAccount WHERE farmerId = :farmerId", PaymentAccount.class)
					.setParameter("farmerId", paymentAccount.getFarmerId())
					.uniqueResult();
			
			if (_paymentAccount == null) {
				session.persist(pa);
			}

			session.getTransaction().commit();

			return Response.builder().status(201).message("Payment Account successfully created")
					.timestamp(LocalDateTime.now()).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Response updatePaymentAccount(Long id, PaymentAccount paymentAccount) {
		try (Session session = sf.openSession()) {
			PaymentAccount pa = session.get(PaymentAccount.class, id);
			if (pa != null) {
				session.beginTransaction();

				if (paymentAccount.getFarmerId() != null) {
					pa.setFarmerId(paymentAccount.getFarmerId());
				}

				if (paymentAccount.getAccountName() != null) {
					pa.setAccountName(paymentAccount.getAccountName());
				}
				
				if (paymentAccount.getAccountNumber() != null) {
					pa.setAccountNumber(paymentAccount.getAccountNumber());
				}
				
				if (paymentAccount.getStatus() != null) {
					pa.setStatus(paymentAccount.getStatus());
				}

				session.getTransaction().commit();

				return Response.builder().status(200).message("Payment Account successfully updated")
						.timestamp(LocalDateTime.now()).build();
			} else {
				return Response.builder().status(404).message("Payment Account not found")
						.timestamp(LocalDateTime.now()).build();
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
