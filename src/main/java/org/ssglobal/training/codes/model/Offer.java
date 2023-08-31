package org.ssglobal.training.codes.model;

import java.time.LocalDate;
import java.time.LocalTime;

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
@Table(name = "offer")
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "offer_id")
	private Long offerId;
	
	@Column(name = "farmer_id")
	private Long farmerId;
	
	@Column(name = "supplier_id")
	private Long supplierId;
	
	@Column(name = "post_id")
	private Long postId;
	
	@Column(name = "measurement")
	private String measurement;
	
	@Column(name = "value")
	private Double value;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "offer_date")
	private LocalDate offerDate;
	
	@Column(name = "offer_time")
	private LocalTime offerTime;
	
	@Column(name = "is_accepted")
	private Boolean isAccepted;
	
	@Column(name = "is_viewed")
	private Boolean isViewed;
	
	@Column(name = "status")
	private Boolean status;
}
