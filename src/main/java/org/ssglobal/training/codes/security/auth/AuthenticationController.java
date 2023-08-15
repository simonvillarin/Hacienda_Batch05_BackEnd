package org.ssglobal.training.codes.security.auth;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.ssglobal.training.codes.model.Users;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Component
@Path("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService authService;
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(@RequestBody Users user) {
		return authService.register(user);
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AuthenticationResponse login(@RequestBody AuthenticationRequest authRequest) {
		return authService.login(authRequest);
	}
}
