package org.ssglobal.training.codes.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sold_crop")
public class SoldCrop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sold_id")
	private Long soldId;
	
	@Column(name = "offer_id")
	private Integer offerId;
	
	@Column(name = "sold_crop_date")
	private LocalDate soldCropDate;
}
