package org.ssglobal.training.codes.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequest {
	private Integer farmerId;
	private Integer postId;
	private Integer quantity;
	private Integer price;
	private LocalDate offerDate;
}
