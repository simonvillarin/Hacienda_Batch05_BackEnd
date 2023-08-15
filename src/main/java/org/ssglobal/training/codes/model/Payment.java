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
@Table(name = "payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Long paymentId;
	
	@Column(name = "order_id_ref")
	private Integer orderIdRef;
	
	@Column(name = "offer_id")
	private Integer offerId;
	
	@Column(name = "payment_mode")
	private String paymentMode;
	
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	
	@Column(name = "status")
	private Boolean status;
}
