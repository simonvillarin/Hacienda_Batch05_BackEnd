package org.ssglobal.training.codes.response;

import java.time.LocalDate;
import java.time.LocalTime;

import org.ssglobal.training.codes.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
	private Long transactionId;
	private User supplier;
	private User farmer;
	private OfferResponse offer;
	private LocalDate acceptDate;
	private LocalTime acceptTime;
	private LocalDate paidDate;
	private LocalTime paidTime;
	private LocalDate deliverDate;
	private LocalTime deliverTime;
	private Boolean status;
	private Boolean isViewed;
}	
