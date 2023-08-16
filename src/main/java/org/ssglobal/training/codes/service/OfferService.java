package org.ssglobal.training.codes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.Offer;
import org.ssglobal.training.codes.repository.OfferRepository;
import org.ssglobal.training.codes.request.OfferRequest;
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
	
	public List<Offer> getOfferByFarmerId(Integer id) {
		return offerRepository.getOfferByFarmerId(id);
	}
	
	public List<Offer> getOfferByPostId(Integer id) {
		return offerRepository.getOfferIdByPostId(id);
	}
	
	@Transactional
	public Response addOffer(OfferRequest offer) {
		return offerRepository.addOffer(offer);
	}
	
	@Transactional
	public Response updateOffer(Long id, OfferRequest offer) {
		return offerRepository.updateOffer(id, offer);
	}
}
