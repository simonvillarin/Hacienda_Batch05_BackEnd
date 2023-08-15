package org.ssglobal.training.codes.security.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.ssglobal.training.codes.security.auth.AuthenticationController;

@Configuration
public class JaxrsConfig extends ResourceConfig {
	
	public JaxrsConfig() {
		register(AuthenticationController.class);
	}
}
