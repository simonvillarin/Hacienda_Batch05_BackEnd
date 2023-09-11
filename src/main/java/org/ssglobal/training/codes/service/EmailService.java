package org.ssglobal.training.codes.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.User;
import org.ssglobal.training.codes.request.EmailRequest;
import org.ssglobal.training.codes.request.UserRequest;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	private final SessionFactory sf;
	private final PasswordEncoder passwordEncoder;
	private final JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String sender;

	private Map<String, Integer> otpMap = new HashMap<>();
	private User user;

	public Integer sendEmail(EmailRequest email) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(sender);
			
			List<String> key = new ArrayList<>(otpMap.keySet());
			if (key.size() > 0) {
				message.setTo(key.get(0));
			} else {
				message.setTo(email.getEmail());
			}
			
			message.setSubject(email.getSubject());
			message.setText(email.getMessage());
			mailSender.send(message);

			otpMap.put(email.getEmail(), email.getCode()); 
			scheduleOtpExpiry(email.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email.getCode();
	}

	private void scheduleOtpExpiry(String email) {
		int expirationTime = 5 * 60 * 1000; // 5 minutes in milliseconds
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				otpMap.remove(email); // Remove the expired OTP code from the map
			}
		}, expirationTime);
	}
	
	public boolean isOtpExpired() {
		List<String> key = new ArrayList<>(otpMap.keySet());
	    return !otpMap.containsKey(key.get(0));
	}
	
	public Boolean checkOTP(EmailRequest email) {
		return otpMap.containsValue(email.getCode());
	}
	
	public Response checkEmail(EmailRequest email) {
		try (Session session = sf.openSession()) {
			user = session.createQuery("FROM User WHERE email = :email", User.class)
					.setParameter("email", email.getEmail())
					.uniqueResult();
			if (user == null) {
				return Response.builder()
						.status(400)
						.message("Email does not exists")
						.timestamp(LocalDateTime.now())
						.build();
			}

			return Response.builder()
					.status(200)
					.message(user.getFirstName())
					.timestamp(LocalDateTime.now())
					.build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Long getUserId() {
		return user.getUserId();
	}
	
	public Response updateUser(Long id, UserRequest user) {
		try (Session session = sf.openSession()) {
			User _user = session.get(User.class, id);
			if (user != null) {
				session.beginTransaction();

				if (user.getPassword() != null && user.getPassword() != "") {
					_user.setPassword(passwordEncoder.encode(user.getPassword()));
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
	
	public Response sendEmail1(EmailRequest email) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(sender);
			
			message.setSubject(email.getSubject());
			message.setText(email.getMessage());
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.builder()
				.status(404)
				.message("Email successfully sent")
				.timestamp(LocalDateTime.now())
				.build();
	}
}
