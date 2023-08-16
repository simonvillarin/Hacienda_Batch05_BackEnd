package org.ssglobal.training.codes.security.auth;

import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.Admin;
import org.ssglobal.training.codes.model.Farmer;
import org.ssglobal.training.codes.model.Supplier;
import org.ssglobal.training.codes.model.User;
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
	
	public Response register(User user) {
		try (Session session = sf.openSession()) {
			session.beginTransaction();
			
			var _user = User.builder()
					.firstName(user.getFirstName())
					.middleName(user.getMiddleName())
					.lastName(user.getLastName())
					.suffix(user.getSuffix())
					.gender(user.getGender())
					.birthdate(user.getBirthdate())
					.unit(user.getUnit())
					.street(user.getStreet())
					.village(user.getVillage())
					.barangay(user.getBarangay())
					.city(user.getCity())
					.region(user.getRegion())
					.zipCode(user.getZipCode())
					.contact(user.getContact())
					.email(user.getEmail())
					.username(user.getUsername())
					.password(passwordEncoder.encode(user.getPassword()))
					.role(user.getRole())
					.status(true)
					.build();
			session.persist(_user);
			
			Query<User> query = session.createQuery("FROM User u ORDER BY u.userId DESC", User.class);
			query.setMaxResults(1);
			User user1 = query.uniqueResult();
			
			if ("Admin".equalsIgnoreCase(user.getRole().name())) {
				var admin = Admin.builder()
						.userId(user1.getUserId())
						.build();
				session.persist(admin);
			} else if ("Supplier".equalsIgnoreCase(user.getRole().name())) {
				var supplier = Supplier.builder()
						.userId(user1.getUserId())
						.build();
				session.persist(supplier);
			} else {
				var farmer = Farmer.builder()
						.userId(user1.getUserId())
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
			Query<User> query = session.createQuery("FROM User u WHERE u.username = :username", User.class);
			query.setParameter("username", authRequest.getUsername());
			User user = query.uniqueResult();
			 
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
}
