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
public class AdvertisementRequest {
	private Long supplierId;
	private String name;
	private String category;
	private String description;
	private Integer quantity;
	private Double mass;
	private Double price;
	private String filename;
	private String mimeType;
	private byte[] data;
	private LocalDate postDate;
	private Boolean status;
	private Boolean isOffered;
}
