package org.ssglobal.training.codes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.User;
import org.ssglobal.training.codes.repository.UserRepository;
import org.ssglobal.training.codes.request.UserRequest;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}
	
	public List<User> getAllFarmers() {
		return userRepository.getAllFarmers();
	}
	
	public List<User> getAllSuppliers() {
		return userRepository.getAllSuppliers();
	}
	
	@Transactional
	public Response updateUser(Long id, UserRequest user) {
		return userRepository.updateUser(id, user);
	}
}
