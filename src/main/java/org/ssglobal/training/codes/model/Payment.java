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
@Table(name = "payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Long paymentId;
	
	@Column(name = "order_id_ref")
	private String orderIdRef;
	
	@Column(name = "transaction_id")
	private Long transactionId;
	
	@Column(name = "payment_mode")
	private String paymentMode;
	
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	
	@Column(name = "payment_time")
	private LocalTime paymentTime;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "unit")
	private String unit;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "village")
	private String village;
	
	@Column(name = "barangay")
	private String barangay;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "province")
	private String province;
	
	@Column(name = "region")
	private String region;
	
	@Column(name = "contact")
	private String contact;
	
	@Column(name = "status")
	private Boolean status;
}
