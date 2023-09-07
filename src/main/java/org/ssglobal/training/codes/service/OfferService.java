package org.ssglobal.training.codes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.Offer;
import org.ssglobal.training.codes.repository.OfferRepository;
import org.ssglobal.training.codes.response.OfferResponse;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfferService {
	private final OfferRepository offerRepository;
	
	public List<Offer> getAllOffer() {
		return offerRepository.getAllOffer();
	}
	
	public Offer getOfferById(Long id) {
		return offerRepository.getOfferById(id);
	}
	
	public List<OfferResponse> getOfferByFarmerId(Long id) {
		return offerRepository.getOfferByFarmerId(id);
	}
	
	public List<OfferResponse> getOfferBySupplierId(Long id) {
		return offerRepository.getOfferBySupplierId(id);
	}
	
	public List<OfferResponse> getOfferByPostId(Long id) {
		return offerRepository.getOfferByPostId(id);
	}
	
	@Transactional
	public Response addOffer(Offer offer) {
		return offerRepository.addOffer(offer);
	}
	
	@Transactional
	public Response updateOffer(Long id, Offer offer) {
		return offerRepository.updateOffer(id, offer);
	}
	
	@Transactional
	public Response deleteOffer(Long id) {
		return offerRepository.deleteOffer(id);
	}
}