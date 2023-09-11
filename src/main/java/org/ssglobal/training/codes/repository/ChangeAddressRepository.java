package org.ssglobal.training.codes.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.ssglobal.training.codes.model.ChangeAddress;
import org.ssglobal.training.codes.model.Transaction;
import org.ssglobal.training.codes.response.ChangeAddressResponse;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ChangeAddressRepository {
	private final SessionFactory sf;
	
	public ChangeAddress getChangeAddressById(Long id) {
		try (Session session = sf.openSession()) {
			return session.get(ChangeAddress.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<ChangeAddress> getAllChangeAddress() {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM ChangeAddress", ChangeAddress.class).list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public ChangeAddressResponse getChangeAddressByTransactionId(Long id) {
		try (Session session = sf.openSession()) {
			Query<ChangeAddress> query = session
					.createQuery("FROM ChangeAddress WHERE transactionId = :transactionId", ChangeAddress.class)
					.setParameter("transactionId", id);
			ChangeAddress changeAddress = query.uniqueResult();

			Transaction transaction = session.get(Transaction.class, changeAddress.getTransactionId());

			return ChangeAddressResponse.builder()
					.changeAddressId(changeAddress.getChangeAddressId())
					.transaction(transaction)
					.fullName(changeAddress.getFullName())
					.unit(changeAddress.getUnit())
					.street(changeAddress.getStreet())
					.village(changeAddress.getVillage())
					.barangay(changeAddress.getBarangay())
					.city(changeAddress.getCity())
					.province(changeAddress.getProvince())
					.region(changeAddress.getRegion())
					.contact(changeAddress.getContact())
					.status(changeAddress.getStatus())
					.build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Response addChangeAddress(ChangeAddress changeAddress) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();
			
			if (changeAddress.getTransactionId() == null || changeAddress.getTransactionId() <= 0) {
				return Response.builder().status(400).message("Invalid transaction Id").timestamp(LocalDateTime.now())
						.build();
			}
			
			if (changeAddress.getFullName() == null || changeAddress.getFullName().isEmpty()) {
				return Response.builder().status(400).message("Invalid full name").timestamp(LocalDateTime.now())
						.build();
			}
			
			if (changeAddress.getUnit() == null || changeAddress.getUnit().isEmpty()) {
				return Response.builder().status(400).message("Invalid unit").timestamp(LocalDateTime.now())
						.build();
			}
			
			if (changeAddress.getStreet() == null || changeAddress.getStreet().isEmpty()) {
				return Response.builder().status(400).message("Invalid street").timestamp(LocalDateTime.now())
						.build();
			}
			
			if (changeAddress.getVillage() == null || changeAddress.getVillage().isEmpty()) {
				return Response.builder().status(400).message("Invalid village").timestamp(LocalDateTime.now())
						.build();
			}
			
			if (changeAddress.getBarangay() == null || changeAddress.getBarangay().isEmpty()) {
				return Response.builder().status(400).message("Invalid barangay").timestamp(LocalDateTime.now())
						.build();
			}

			if (changeAddress.getCity() == null || changeAddress.getCity().isEmpty()) {
				return Response.builder().status(400).message("Invalid city").timestamp(LocalDateTime.now())
						.build();
			}
			
			if (changeAddress.getProvince() == null || changeAddress.getProvince().isEmpty()) {
				return Response.builder().status(400).message("Invalid province").timestamp(LocalDateTime.now())
						.build();
			}
			
			if (changeAddress.getRegion() == null || changeAddress.getRegion().isEmpty()) {
				return Response.builder().status(400).message("Invalid region").timestamp(LocalDateTime.now())
						.build();
			}
			
			if (changeAddress.getContact() == null || changeAddress.getContact().isEmpty()) {
				return Response.builder().status(400).message("Invalid contact").timestamp(LocalDateTime.now())
						.build();
			}
	
			ChangeAddress newChangeAddress = ChangeAddress.builder()
					.transactionId(changeAddress.getTransactionId())
					.fullName(changeAddress.getFullName())
					.unit(changeAddress.getUnit())
					.street(changeAddress.getStreet())
					.village(changeAddress.getVillage())
					.barangay(changeAddress.getBarangay())
					.city(changeAddress.getCity())
					.province(changeAddress.getProvince())
					.region(changeAddress.getRegion())
					.contact(changeAddress.getContact())
					.status(true).build(); 

			session.persist(newChangeAddress);

			session.getTransaction().commit();

			return Response.builder().status(201).message("Change Address successfully created")
					.timestamp(LocalDateTime.now()).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Response updateChangeAddress(Long id, ChangeAddress changeAddress) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();

			ChangeAddress existingChangeAddress = session.get(ChangeAddress.class, id);

			if (changeAddress.getFullName() != null) {
				existingChangeAddress.setFullName(changeAddress.getFullName());
			}

			if (changeAddress.getUnit() != null) {
				existingChangeAddress.setUnit(changeAddress.getUnit());
			}

			if (changeAddress.getStreet() != null) {
				existingChangeAddress.setStreet(changeAddress.getStreet());
			}
			
			if (changeAddress.getVillage() != null) {
				existingChangeAddress.setVillage(changeAddress.getVillage());
			}
			
			if (changeAddress.getBarangay() != null) {
				existingChangeAddress.setBarangay(changeAddress.getBarangay());
			}
			
			if (changeAddress.getCity() != null) {
				existingChangeAddress.setCity(changeAddress.getCity());
			}
			
			if (changeAddress.getProvince() != null) {
				existingChangeAddress.setProvince(changeAddress.getProvince());
			}

			if (changeAddress.getRegion() != null) {
				existingChangeAddress.setRegion(changeAddress.getRegion());
			}
			
			if (changeAddress.getContact() != null) {
				existingChangeAddress.setContact(changeAddress.getContact());
			}
			
			if (changeAddress.getStatus() != null) {
				existingChangeAddress.setStatus(changeAddress.getStatus());
			}

			session.update(existingChangeAddress);
			session.getTransaction().commit();

			return Response.builder().status(200).message("Change Address successfully updated")
					.timestamp(LocalDateTime.now()).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
