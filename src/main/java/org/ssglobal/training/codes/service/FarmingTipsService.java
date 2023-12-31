package org.ssglobal.training.codes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.FarmingTips;
import org.ssglobal.training.codes.repository.FarmingTipsRepository;
import org.ssglobal.training.codes.request.FarmingTipsRequest;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FarmingTipsService {
	private final FarmingTipsRepository farmingTipsRepository;
	
	public List<FarmingTips> getAllFarmingTips() {
		return farmingTipsRepository.getAllFarmingTips();
	}
	
	public FarmingTips getFarmingTipsById(Long id) {
		return farmingTipsRepository.getFarmingTipsById(id);
	}
	
	@Transactional
	public Response addFarmingTip(FarmingTipsRequest farmingTip) {
		return farmingTipsRepository.addFarmingTip(farmingTip);
	}
	
	@Transactional
	public Response updateFarmingTip(Long id, FarmingTipsRequest farmingTip) {
		return farmingTipsRepository.updateFarmingTip(id, farmingTip);
	}
}
