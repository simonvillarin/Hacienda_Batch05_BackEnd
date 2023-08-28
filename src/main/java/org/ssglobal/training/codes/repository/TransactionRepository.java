package org.ssglobal.training.codes.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
	
	public TransactionResponse getTransactionById(Long id) {
		try (Session session = sf.openSession()) {
			Transaction transaction = session.get(Transaction.class, id);
			
			Query<User> query = session.createQuery("FROM User WHERE userId = :userId", User.class)
					.setParameter("userId", transaction.getSupplierId());
			User supplier = query.uniqueResult();
			
			Query<User> query1 = session.createQuery("FROM User WHERE userId = :userId", User.class)
					.setParameter("userId", transaction.getFarmerId());
			User farmer = query1.uniqueResult();
			
			Offer offer = session.get(Offer.class, transaction.getOfferId());
			
			Query<Advertisement> query2 = session.createQuery("FROM Advertisement WHERE postId = :postId", Advertisement.class)
					.setParameter("postId", offer.getPostId());
			Advertisement advertisement = query2.uniqueResult();
			
			OfferResponse offerResponse = OfferResponse.builder()
					.farmer(farmer)
					.supplier(supplier)
					.advertisement(advertisement)
					.quantity(offer.getQuantity())
					.mass(offer.getMass())
					.price(offer.getPrice())
					.offerDate(offer.getOfferDate())
					.offerTime(offer.getOfferTime())
					.isViewed(offer.getIsViewed())
					.build();
			
			return TransactionResponse.builder()
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
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	public Response addTransaction(Transaction transaction) {
		try (Session session = sf.openSession()) {
            session.beginTransaction();

            if (transaction.getSupplierId() == null || transaction.getSupplierId() <= 0) {
                return Response.builder()
                        .status(400)
                        .message("Invalid supplier Id")
                        .timestamp(LocalDateTime.now())
                        .build();
            }
            if (transaction.getFarmerId() == null || transaction.getFarmerId() <= 0) {
                return Response.builder()
                        .status(400)
                        .message("Invalid farmer Id")
                        .timestamp(LocalDateTime.now())
                        .build();
            }
            if (transaction.getOfferId() == null || transaction.getFarmerId() <= 0) {
                return Response.builder()
                        .status(400)
                        .message("Invalid farmer Id")
                        .timestamp(LocalDateTime.now())
                        .build();
            }
            
            Transaction newTransaction = Transaction.builder()
            		.supplierId(transaction.getSupplierId())
            		.farmerId(transaction.getFarmerId())
            		.offerId(transaction.getOfferId())
            		.acceptDate(LocalDate.now())
            		.acceptTime(LocalTime.now())
            		.build();

            session.persist(newTransaction);

            session.getTransaction().commit();

            return Response.builder()
                    .status(201)
                    .message("Transaction successfully created")
                    .timestamp(LocalDateTime.now())
                    .build();
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
            	transac.setDeliverDate(transaction.getDeliverDate());
            }
            if (transaction.getDeliverTime() != null) {
            	transac.setDeliverTime(transaction.getDeliverTime());
            }
            if (transaction.getStatus() != null) {
            	transac.setStatus(transaction.getStatus());
            }
            if (transaction.getIsViewed() != null) {
            	transac.setIsViewed(transaction.getIsViewed());
            }

            session.getTransaction().commit();

            return Response.builder()
                    .status(201)
                    .message("Transaction successfully updated")
                    .timestamp(LocalDateTime.now())
                    .build();
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

            return Response.builder()
                    .status(201)
                    .message("Transaction successfully updated")
                    .timestamp(LocalDateTime.now())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
	}
}
