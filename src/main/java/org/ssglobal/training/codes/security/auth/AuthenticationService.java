package org.ssglobal.training.codes.security.auth;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.Admin;
import org.ssglobal.training.codes.model.Farmer;
import org.ssglobal.training.codes.model.Supplier;
import org.ssglobal.training.codes.model.Users;
import org.ssglobal.training.codes.repository.AdminRepository;
import org.ssglobal.training.codes.repository.FarmerRepository;
import org.ssglobal.training.codes.repository.SupplierRepository;
import org.ssglobal.training.codes.repository.UsersRepository;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.security.config.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UsersRepository userRepository;
	private final AdminRepository adminRepository;
	private final SupplierRepository supplierRepository;
	private final FarmerRepository farmerRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authManager;
	private final JwtService jwtService;
	
	public Response register(Users user) {
		var _user = Users.builder()
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
		userRepository.save(_user);
		
		var _user1 = userRepository.findTopByOrderByUserIdDesc();
		if ("Admin".equalsIgnoreCase(user.getRole().name())) {
			var admin = Admin.builder()
					.userId(_user1.getUserId())
					.build();
			adminRepository.save(admin);
		} else if ("Supplier".equalsIgnoreCase(user.getRole().name())) {
			var supplier = Supplier.builder()
					.userId(_user1.getUserId())
					.build();
			supplierRepository.save(supplier);
		} else {
			var farmer = Farmer.builder()
					.userId(_user1.getUserId())
					.build();
			farmerRepository.save(farmer);
		}
		
		return Response.builder()
				.status(200)
				.message("User successfully registered")
				.timestamp(LocalDateTime.now())
				.build();
	}
	
	public AuthenticationResponse login(AuthenticationRequest authRequest) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		
		String token;
		Optional<Users> user = userRepository.findByUsername(authRequest.getUsername());
		if (user.isPresent()) {
			token = jwtService.generateToken(user.get());
		} else {
			throw new RuntimeException("User not found");
		}
		
		return AuthenticationResponse.builder()
				.token(token)
				.id(user.get().getUserId())
				.role(user.get().getRole().name())
				.build();
	}
}
