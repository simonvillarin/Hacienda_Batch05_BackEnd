package org.ssglobal.training.codes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.SoldCrop;
import org.ssglobal.training.codes.repository.SoldCropRepository;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SoldCropService {
	private final SoldCropRepository cropRepository;

	public List<SoldCrop> getAllSoldCrop() {
		return cropRepository.getAllSoldCrop();
	}

	public SoldCrop getSoldCropById(Long id) {
		return cropRepository.getSoldCropById(id);
	}

	public List<SoldCrop> getSolfCropByOfferId(Integer id) {
		return cropRepository.getSolfCropByOfferId(id);
	}
	
	@Transactional
	public Response addSoldCrop(SoldCrop soldCrop) {
		return cropRepository.addSoldCrop(soldCrop);
	}
	
	@Transactional
	public Response updateSoldCrop(Long id, SoldCrop soldCrop) {
		return cropRepository.updateSoldCrop(id, soldCrop);
	}
}
