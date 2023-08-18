package org.ssglobal.training.codes.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.ssglobal.training.codes.model.Image;
import org.ssglobal.training.codes.model.User;
import org.ssglobal.training.codes.request.UserRequest;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {
	private final SessionFactory sf;
	private final PasswordEncoder passwordEncoder;
	
	public List<User> getAllUsers() {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM User", User.class)
					.list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<User> getAllFarmers() {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM User WHERE role = :role", User.class)
					.setParameter("role", "Farmer")
					.list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<User> getAllSuppliers() {
		try (Session session = sf.openSession()) {
			return session.createQuery("FROM User WHERE role = :role", User.class)
					.setParameter("role", "Supplier")
					.list();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Response updateUser(Long id, UserRequest user) {
		try (Session session = sf.openSession()) {
			User _user = session.get(User.class, id);
			if (user != null) {
				session.beginTransaction();
				
				if (user.getFirstName() != null && user.getFirstName() != "" ) {
					_user.setFirstName(user.getFirstName());
				}
				if (user.getMiddleName() != null && user.getMiddleName() != "") {
					_user.setMiddleName(user.getMiddleName());
				}
				if (user.getLastName() != null && user.getLastName() != "") {
					_user.setLastName(user.getLastName());
				}
				if (user.getSuffix() != null && user.getSuffix() != "") {
					_user.setSuffix(user.getSuffix());
				}
				if (user.getGender() != null && user.getGender() != "") {
					_user.setGender(user.getGender());
				}
				if (user.getBirthdate() != null) {
					_user.setBirthdate(user.getBirthdate());;
				}
				if (user.getUnit() != null && user.getUnit() != "") {
					_user.setUnit(user.getUnit());
				}
				if (user.getStreet() != null && user.getStreet() != "") {
					_user.setStreet(user.getStreet());
				}
				if (user.getVillage() != null && user.getVillage() != "") {
					_user.setVillage(user.getVillage());
				}
				if (user.getBarangay() != null && user.getBarangay() != "") {
					_user.setBarangay(user.getBarangay());
				}
				if (user.getCity() != null && user.getCity() != "") {
					_user.setCity(user.getCity());
				}
				if (user.getRegion() != null && user.getRegion() != "") {
					_user.setRegion(user.getRegion());
				}
				if (user.getZipCode() != null) {
					_user.setZipCode(user.getZipCode());
				}
				if (user.getContact() != null && user.getContact() != "") {
					_user.setContact(user.getContact());
				}
				if (user.getEmail() != null && user.getEmail() != "") {
					_user.setEmail(user.getEmail());
				}
				if (user.getPassword() != null && user.getPassword() != "") {
					_user.setPassword(passwordEncoder.encode(user.getPassword()));
				}
				if (user.getFilename() != null) {
					Query<Image> query = session.createQuery("FROM Image WHERE filename = :filename", Image.class)
							.setParameter("filename", user.getFilename());
					Image image = query.uniqueResult();
					if (image == null) {
						Image img = Image.builder()
								.filename(user.getFilename())
								.mimeType(user.getMimeType())
								.data(user.getData())
								.build();
						session.persist(img);
					}
					_user.setImage(createImageLink(user.getFilename()));
				}
				if (user.getStatus() != null) {
					_user.setStatus(user.getStatus());
				}
				
				session.getTransaction().commit();
				
				return Response.builder()
						.status(200)
						.message("User successfully updated")
						.timestamp(LocalDateTime.now())
						.build();
			} else {
				return Response.builder()
						.status(404)
						.message("User not found")
						.timestamp(LocalDateTime.now())
						.build();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private String createImageLink(String filename) {
		return ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/api/image/" + filename).toUriString();
	}
}
