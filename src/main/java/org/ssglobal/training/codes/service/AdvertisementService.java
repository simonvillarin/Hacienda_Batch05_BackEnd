package org.ssglobal.training.codes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.Advertisement;
import org.ssglobal.training.codes.repository.AdvertisementRepository;
import org.ssglobal.training.codes.request.AdvertisementRequest;
import org.ssglobal.training.codes.response.AdvertisementResponse;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdvertisementService {
	private final AdvertisementRepository advertisementRepository;
	
	public List<AdvertisementResponse> getAllAdvertisement(Long id) {
		return advertisementRepository.getAllAdvertisement(id);
	}
	
	public List<AdvertisementResponse> getAdvertisementBySupplierId(Long id) {
		return advertisementRepository.getAdvertisementBySupplierId(id);
	}
	
	public Advertisement getAdvertisementById(Long id) {
		return advertisementRepository.getAdvertisementById(id);
	}
	
	@Transactional
	public Response addAdvertisement(AdvertisementRequest advertisement) {
		return advertisementRepository.addAdvertisement(advertisement);
	}
	
	@Transactional
	public Response updateAdvertisement(Long id, AdvertisementRequest advertisement) {
		return advertisementRepository.updateAdvertisement(id, advertisement);
	}

}
