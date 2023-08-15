package org.ssglobal.training.codes.security.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.ssglobal.training.codes.security.auth.AuthenticationController;

@Configuration
@ApplicationPath("/api")
public class JaxrsConfig extends ResourceConfig {
	
	public JaxrsConfig() {
		register(AuthenticationController.class);
		packages("org.ssglobal.training.codes.controller");
	}
}
