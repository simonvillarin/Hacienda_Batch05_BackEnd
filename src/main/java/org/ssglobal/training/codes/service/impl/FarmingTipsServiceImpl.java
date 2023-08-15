package org.ssglobal.training.codes.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.ssglobal.training.codes.model.FarmingTips;
import org.ssglobal.training.codes.model.Image;
import org.ssglobal.training.codes.repository.FarmingTipsRepository;
import org.ssglobal.training.codes.repository.ImageRepository;
import org.ssglobal.training.codes.request.FarmingTipsRequest;
import org.ssglobal.training.codes.response.Response;
import org.ssglobal.training.codes.service.FarmingTipsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FarmingTipsServiceImpl implements FarmingTipsService {
	private final FarmingTipsRepository farmingTipsRepository;
	private final ImageRepository imageRepository;

	@Override
	public List<FarmingTips> findAllFarmingTips() {
		return farmingTipsRepository.findAll();
	}

	@Override
	public FarmingTips findById(Long tipId) {
		return farmingTipsRepository.findById(tipId).orElse(null);
	}
	
	@Override
	public Response addTip(FarmingTips tip) {
		farmingTipsRepository.save(tip);
		
		return Response.builder()
				.status(201)
				.message("Tip successfully created")
				.timestamp(LocalDateTime.now())
				.build();
	}


	@Override
	public Response addTipWithImage(FarmingTipsRequest tip) {
		Optional<Image> image = imageRepository.findByFilename(tip.getFilename());
		FarmingTips farmTip = new FarmingTips();
		if (image.isEmpty()) {
			byte[] imageData = tip.getData();
			Image img = Image.builder()
						.filename(tip.getFilename())
						.mimeType(tip.getMimeType())
						.data(imageData)
						.build();
			imageRepository.save(img);
		} 
		farmTip.setTip(tip.getTip());
		farmTip.setImage(createImageLink(tip.getFilename()));
		farmTip.setStatus(true);
		farmingTipsRepository.save(farmTip);

		return Response.builder()
				.status(201)
				.message("Tip successfully created")
				.timestamp(LocalDateTime.now())
				.build();
	}

	@Override
	public Response updateTip(Long tipId, FarmingTips tip) {
		Optional<FarmingTips> farmingTips = farmingTipsRepository.findById(tipId);
		if (farmingTips.isPresent()) {
			var farmTip = farmingTips.get();		
			if(tip.getTip() != null) {
				farmTip.setTip(tip.getTip());
			}		
			if (tip.getStatus() != null) {
				farmTip.setStatus(tip.getStatus());
			}		
			farmingTipsRepository.save(farmTip);
			
			return Response.builder()
					.status(200)
					.message("Tip successfully updated")
					.timestamp(LocalDateTime.now())
					.build();
		} else {
			return Response.builder()
					.status(404)
					.message("Tip not found")
					.timestamp(LocalDateTime.now())
					.build();
		}

	}

	@Override
	public Response updateTipWithImage(Long tipId, FarmingTipsRequest tip) {
		Optional<FarmingTips> farmingTips = farmingTipsRepository.findById(tipId);
		if (farmingTips.isPresent()) {
			Optional<Image> image = imageRepository.findByFilename(tip.getFilename());
			if (image.isEmpty()) {
				byte[] imageData = tip.getData();
				Image img = Image.builder()
							.filename(tip.getFilename())
							.mimeType(tip.getMimeType())
							.data(imageData)
							.build();
				imageRepository.save(img);
			}
			var farmTip = farmingTips.get();			
			if(tip.getTip() != null) {
				farmTip.setTip(tip.getTip());
			}
			
			farmTip.setImage(createImageLink(tip.getFilename()));
			farmingTipsRepository.save(farmTip);
			
			return Response.builder()
					.status(200)
					.message("Tip successfully updated")
					.timestamp(LocalDateTime.now())
					.build();
		} else {
			return Response.builder()
					.status(404)
					.message("Tip not found")
					.timestamp(LocalDateTime.now())
					.build();
		}

	}

	private String createImageLink(String filename) {
		return ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/api/image/" + filename).toUriString();
	}
}
