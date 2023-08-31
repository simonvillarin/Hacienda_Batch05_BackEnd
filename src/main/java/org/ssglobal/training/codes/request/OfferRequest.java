package org.ssglobal.training.codes.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequest {
	private Long farmerId;
	private Long supplierId;
	private Long postId;
	private String measurement;
	private Double value;
	private Double price;
	private Boolean isAccepted;
	private Boolean isViewed;
}
