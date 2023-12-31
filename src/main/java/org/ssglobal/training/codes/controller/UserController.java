package org.ssglobal.training.codes.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.ssglobal.training.codes.model.User;
import org.ssglobal.training.codes.request.UserRequest;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@Path("/farming")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GET
	@Path("/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("id") Long id) {
		return userService.getUserById(id);
	}
	
	@GET
	@Path("/farmers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllFarmers() {
		return userService.getAllFarmers();
	}
	
	@GET
	@Path("/suppliers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllSuppliers() {
		return userService.getAllSuppliers();
	}
	
	
	@PUT
	@Path("/user/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") Long id, @RequestBody UserRequest user) {
		return userService.updateUser(id, user);
	}
}
