package org.ssglobal.training.codes.service;

import java.util.List;

import org.ssglobal.training.codes.model.FarmingTips;
import org.ssglobal.training.codes.request.FarmingTipsRequest;
import org.ssglobal.training.codes.response.Response;

public interface FarmingTipsService {
	List<FarmingTips> findAllFarmingTips();
	FarmingTips findById(Long tipId);
	Response addTip(FarmingTips tip);
	Response addTipWithImage(FarmingTipsRequest tip);
	Response updateTip(Long tipId, FarmingTips tip);
	Response updateTipWithImage(Long tipId, FarmingTipsRequest tip);

}
