package org.ssglobal.training.codes.security.auth;

import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.ssglobal.training.codes.model.Admin;
import org.ssglobal.training.codes.model.Farmer;
import org.ssglobal.training.codes.model.Image;
import org.ssglobal.training.codes.model.Supplier;
import org.ssglobal.training.codes.model.User;
import org.ssglobal.training.codes.request.UserRequest;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.security.config.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final SessionFactory sf;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authManager;
	private final JwtService jwtService;
	
	public Response register(UserRequest user) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();
			
			User _user = new User();
			
			if (user.getFirstName() != null) {
				_user.setFirstName(user.getFirstName());
			}
			if (user.getMiddleName() != null) {
				_user.setMiddleName(user.getMiddleName());
			}
			if (user.getLastName() != null) {
				_user.setLastName(user.getLastName());
			}
			if (user.getSuffix() != null) {
				_user.setSuffix(user.getSuffix());
			}
			if (user.getGender() != null) {
				_user.setGender(user.getGender());
			}
			if (user.getBirthdate() != null) {
				_user.setBirthdate(user.getBirthdate());
			}
			if (user.getUnit() != null) {
				_user.setUnit(user.getUnit());
			}
			if (user.getStreet() != null) {
				_user.setStreet(user.getStreet());
			}
			if (user.getVillage() != null) {
				_user.setVillage(user.getVillage());
			}
			if (user.getBarangay() != null) {
				_user.setBarangay(user.getBarangay());
			}
			if (user.getCity() != null) {
				_user.setCity(user.getCity());
			}
			if (user.getProvince() != null) {
				_user.setProvince(user.getProvince());
			}
			if (user.getRegion() != null) {
				_user.setRegion(user.getRegion());
			}
			if (user.getIdType() != null) {
				_user.setIdType(user.getIdType());
			}
			if (user.getIdNumber() != null) {
				_user.setIdNumber(user.getIdNumber());
			}
			if (user.getPassword() != null) {
				_user.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			if (user.getRole() != null) {
				_user.setRole(user.getRole());
			}
			if (user.getStatus() != null) {
				_user.setStatus(user.getStatus());
			}
			if (user.getContact() != null) {
				User usr = session.createQuery("FROM User WHERE contact = :contact", User.class)
						.setParameter("contact", user.getContact())
						.uniqueResult();
				if (usr != null) {
					return Response.builder()
							.status(409)
							.message("Contact number already exists")
							.timestamp(LocalDateTime.now())
							.build();
				}
				_user.setContact(user.getContact());
			}
			if (user.getEmail() != null) {
				User usr = session.createQuery("FROM User WHERE email = :email", User.class)
						.setParameter("email", user.getEmail())
						.uniqueResult();
				if (usr != null) {
					return Response.builder()
							.status(403)
							.message("Email already exists")
							.timestamp(LocalDateTime.now())
							.build();
				}
				_user.setEmail(user.getEmail());
			}
			if (user.getUsername() != null) {
				User usr = session.createQuery("FROM User WHERE username = :username", User.class)
						.setParameter("username", user.getUsername())
						.uniqueResult();
				if (usr != null) {
					return Response.builder()
							.status(403)
							.message("Username already exists")
							.timestamp(LocalDateTime.now())
							.build();
				}
				_user.setUsername(user.getUsername());
			}
			if (user.getFilename1() != null) {
				Image image = session.createQuery("FROM Image WHERE filename = :filename", Image.class)
						.setParameter("filename", user.getFilename1())
						.uniqueResult();
				if (image == null) {
					Image img = Image.builder()
							.filename(user.getFilename1())
							.mimeType(user.getMimeType1())
							.data(user.getData1())
							.build();
					session.persist(img);
				}
				_user.setIdFront(createImageLink(user.getFilename1()));
			}
			if (user.getFilename2() != null) {
				Image image = session.createQuery("FROM Image WHERE filename = :filename", Image.class)
						.setParameter("filename", user.getFilename2())
						.uniqueResult();
				if (image == null) {
					Image img = Image.builder()
							.filename(user.getFilename2())
							.mimeType(user.getMimeType2())
							.data(user.getData2())
							.build();
					session.persist(img);
				}
				_user.setIdBack(createImageLink(user.getFilename2()));
			}
			if (user.getSelfie() != null) {
				_user.setSelfie(user.getSelfie());
			}
			
			session.persist(_user);
			
			Query<User> query = session.createQuery("FROM User u ORDER BY u.userId DESC", User.class);
			query.setMaxResults(1);
			User usr = query.uniqueResult();
			
			if ("Admin".equalsIgnoreCase(user.getRole().name())) {
				Admin admin = Admin.builder()
						.userId(usr.getUserId())
						.build();
				session.persist(admin);
			} else if ("Supplier".equalsIgnoreCase(user.getRole().name())) {
				Supplier supplier = Supplier.builder()
						.userId(usr.getUserId())
						.build();
				session.persist(supplier);
			} else {
				Farmer farmer = Farmer.builder()
						.userId(usr.getUserId())
						.build();
				session.persist(farmer);
			}
			
			session.getTransaction().commit();
		}
		
		return Response.builder()
				.status(200)
				.message("User successfully registered")
				.timestamp(LocalDateTime.now())
				.build();
	}
	
	public AuthenticationResponse login(AuthenticationRequest authRequest) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		
		try (Session session = sf.openSession()) {
			User user = session.createQuery("FROM User u WHERE u.username = :username and status = :status", User.class)
					.setParameter("username", authRequest.getUsername())
					.setParameter("status", "Active")
					.uniqueResult();
			 
			String token;
			if (user != null) {
				token = jwtService.generateToken(user);
			} else {
				throw new RuntimeException("User not found");
			}
			return AuthenticationResponse.builder()
					.token(token)
					.id(user.getUserId())
					.role(user.getRole().name())
					.build();
		}
	}
	
	private String createImageLink(String filename) {
		return ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/api/image/" + filename).toUriString();
	}
}
