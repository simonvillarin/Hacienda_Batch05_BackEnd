package org.ssglobal.training.codes.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.Image;
import org.ssglobal.training.codes.repository.ImageRepository;
import org.ssglobal.training.codes.service.ImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
	private final ImageRepository imageRepository;
	
	@Override
	public Image getImage(String filename) {
		Optional<Image> image = imageRepository.findByFilename(filename);
		if (image.isPresent()) {
			return image.get();
		} else {
			throw new RuntimeException("Image not found");
		}
	}

}
