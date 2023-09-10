package org.ssglobal.training.codes.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.ssglobal.training.codes.model.Advertisement;
import org.ssglobal.training.codes.model.Offer;
import org.ssglobal.training.codes.model.Transaction;
import org.ssglobal.training.codes.model.User;
import org.ssglobal.training.codes.response.OfferResponse;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.response.TransactionResponse;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {
	private final SessionFactory sf;

	public List<Transaction> getAllTransaction() {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM Transaction", Transaction.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Transaction getTransactionById(Long id) {
		try (Session session = sf.openSession()) {
			return session.get(Transaction.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<TransactionResponse> getTransactionBySupplierId(Long id) {
		try (Session session = sf.openSession()) {
			List<Transaction> transactions = session
					.createQuery("FROM Transaction WHERE supplierId = :supplierId", Transaction.class)
					.setParameter("supplierId", id).list();

			List<TransactionResponse> transactionReponses = new ArrayList<>();
			transactions.stream().forEach(transaction -> {
				Query<User> query = session.createQuery("FROM User WHERE userId = :userId", User.class)
						.setParameter("userId", transaction.getSupplierId());
				User supplier = query.uniqueResult();

				Query<User> query1 = session.createQuery("FROM User WHERE userId = :userId", User.class)
						.setParameter("userId", transaction.getFarmerId());
				User farmer = query1.uniqueResult();

				Offer offer = session.get(Offer.class, transaction.getOfferId());

				Advertisement advertisement = session.get(Advertisement.class, offer.getPostId());

				OfferResponse offerResponse = OfferResponse.builder().offerId(offer.getOfferId()).farmer(farmer)
						.supplier(supplier).advertisement(advertisement).measurement(offer.getMeasurement())
						.value(offer.getValue()).price(offer.getPrice()).offerDate(offer.getOfferDate())
						.offerTime(offer.getOfferTime()).isViewed(offer.getIsViewed()).build();

				TransactionResponse transactionResponse = TransactionResponse.builder()
						.transactionId(transaction.getTransactionId()).supplier(supplier).farmer(farmer)
						.offer(offerResponse).acceptDate(transaction.getAcceptDate())
						.acceptTime(transaction.getAcceptTime()).paidDate(transaction.getPaidDate())
						.paidTime(transaction.getPaidTime()).deliverDate(transaction.getDeliverDate())
						.deliveredTime(transaction.getDeliveredTime())
						.status(transaction.getStatus()).isViewed(transaction.getIsViewed())
						.isDelivered(transaction.getIsDelivered()).build();
				transactionReponses.add(transactionResponse);
			});

			return transactionReponses;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<TransactionResponse> getTransactionByFarmerId(Long id) {
		try (Session session = sf.openSession()) {
			List<Transaction> transactions = session
					.createQuery("FROM Transaction WHERE farmerId = :farmerId", Transaction.class)
					.setParameter("farmerId", id).list();

			List<TransactionResponse> transactionReponses = new ArrayList<>();
			transactions.stream().forEach(transaction -> {
				Query<User> query = session.createQuery("FROM User WHERE userId = :userId", User.class)
						.setParameter("userId", transaction.getSupplierId());
				User supplier = query.uniqueResult();

				Query<User> query1 = session.createQuery("FROM User WHERE userId = :userId", User.class)
						.setParameter("userId", transaction.getFarmerId());
				User farmer = query1.uniqueResult();

				Offer offer = session.get(Offer.class, transaction.getOfferId());

				Advertisement advertisement = session.get(Advertisement.class, offer.getPostId());

				OfferResponse offerResponse = OfferResponse.builder().offerId(offer.getOfferId()).farmer(farmer)
						.supplier(supplier).advertisement(advertisement).measurement(offer.getMeasurement())
						.value(offer.getValue()).price(offer.getPrice()).offerDate(offer.getOfferDate())
						.offerTime(offer.getOfferTime()).isViewed(offer.getIsViewed()).build();

				TransactionResponse transactionResponse = TransactionResponse.builder()
						.transactionId(transaction.getTransactionId()).supplier(supplier).farmer(farmer)
						.offer(offerResponse).acceptDate(transaction.getAcceptDate())
						.acceptTime(transaction.getAcceptTime()).paidDate(transaction.getPaidDate())
						.paidTime(transaction.getPaidTime()).deliverDate(transaction.getDeliverDate())
						.deliveredTime(transaction.getDeliveredTime())
						.status(transaction.getStatus()).isViewed(transaction.getIsViewed())
						.isDelivered(transaction.getIsDelivered()).build();
				transactionReponses.add(transactionResponse);
			});

			return transactionReponses;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Response addTransaction(Transaction transaction) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();

			if (transaction.getSupplierId() == null || transaction.getSupplierId() <= 0) {
				return Response.builder().status(400).message("Invalid supplier Id").timestamp(LocalDateTime.now())
						.build();
			}
			if (transaction.getFarmerId() == null || transaction.getFarmerId() <= 0) {
				return Response.builder().status(400).message("Invalid farmer Id").timestamp(LocalDateTime.now())
						.build();
			}
			if (transaction.getOfferId() == null || transaction.getOfferId() <= 0) {
				return Response.builder().status(400).message("Invalid offer Id").timestamp(LocalDateTime.now())
						.build();
			}

			Transaction _transaction = session
					.createQuery("FROM Transaction WHERE offerId = :offerId", Transaction.class)
					.setParameter("offerId", transaction.getOfferId()).uniqueResult();

			if (_transaction == null) {
				Transaction newTransaction = Transaction.builder().supplierId(transaction.getSupplierId())
						.farmerId(transaction.getFarmerId()).offerId(transaction.getOfferId())
						.acceptDate(LocalDate.now()).acceptTime(LocalTime.now()).status(true).isViewed(false)
						.isDelivered(false).build();

				session.persist(newTransaction);
			} else {
				_transaction.setStatus(true);
			}

			session.getTransaction().commit();

			return Response.builder().status(201).message("Transaction successfully created")
					.timestamp(LocalDateTime.now()).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Response updateTransaction(Long id, Transaction transaction) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();

			Transaction transac = session.get(Transaction.class, id);

			if (transaction.getPaidDate() != null) {
				transac.setPaidDate(transaction.getPaidDate());
			}
			if (transaction.getPaidTime() != null) {
				transac.setPaidTime(transaction.getPaidTime());
			}
			if (transaction.getDeliverDate() != null) {
				transac.setDeliverDate(transaction.getDeliverDate().plusDays(1));
			}
			if (transaction.getDeliveredDate() != null) {
				transac.setDeliveredDate(transaction.getDeliveredDate());
			}
			if (transaction.getDeliveredTime() != null) {
				transac.setDeliveredTime(transaction.getDeliveredTime());
			}

			if (transaction.getStatus() != null) {
				transac.setStatus(transaction.getStatus());
			}
			if (transaction.getIsViewed() != null) {
				transac.setIsViewed(transaction.getIsViewed());
			}
			if (transaction.getIsDelivered() != null) {
				transac.setIsDelivered(null);
			}

			session.getTransaction().commit();

			return Response.builder().status(201).message("Transaction successfully updated")
					.timestamp(LocalDateTime.now()).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Response deleteTransaction(Long id) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();

			Transaction transaction = session.get(Transaction.class, id);

			if (transaction != null) {
				session.delete(transaction);
			}

			session.getTransaction().commit();

			return Response.builder().status(201).message("Transaction successfully deleted")
					.timestamp(LocalDateTime.now()).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
