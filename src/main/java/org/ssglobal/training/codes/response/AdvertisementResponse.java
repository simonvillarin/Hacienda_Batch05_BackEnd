package org.ssglobal.training.codes.response;

import java.time.LocalDate;

import org.ssglobal.training.codes.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementResponse {
	private Long postId;
	private User supplier;
	private String name;
	private String category;
	private String description;
	private String measurement;
	private Double value;
	private Double price;
	private String image;
	private LocalDate postDate;
	private Boolean status;
	private Integer numOfOffers;
	private Boolean isOffered;
}
