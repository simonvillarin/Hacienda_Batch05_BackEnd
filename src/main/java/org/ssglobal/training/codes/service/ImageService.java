package org.ssglobal.training.codes.service;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.Image;
import org.ssglobal.training.codes.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {
	private final ImageRepository imageRepository;

	public Image getByFilename(String filename) {
		return imageRepository.getByFilename(filename);
	}
}
