package org.ssglobal.training.codes.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.ssglobal.training.codes.service.impl.ImageServiceImpl;

import lombok.RequiredArgsConstructor;

@Component
@Path("/image")
@RequiredArgsConstructor
public class ImageController {
	private final ImageServiceImpl imageServiceImpl;
	
	@GET
	@Path("/{filename}")
	public void retrieve(@PathParam("filename") String filename, @Context HttpServletResponse response)
			throws IOException {
		var image = imageServiceImpl.getImage(filename);
		byte[] imageData = image.getData();
		String mimeType = image.getMimeType();

		response.setHeader(HttpHeaders.CONTENT_TYPE, mimeType);
		response.getOutputStream().write(imageData);
		response.getOutputStream().flush();
	}
}
