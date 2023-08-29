package org.ssglobal.training.codes.response;

import java.time.LocalDate;
import java.time.LocalTime;

import org.ssglobal.training.codes.model.Advertisement;
import org.ssglobal.training.codes.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponse {
	private Long offerId;
	private User farmer;
	private User supplier;
	private Advertisement advertisement;
	private Integer quantity;
	private Double mass;
	private Double price;
	private LocalDate offerDate;
	private LocalTime offerTime;
	private Boolean isAccepted;
	private Boolean isViewed;	
}
