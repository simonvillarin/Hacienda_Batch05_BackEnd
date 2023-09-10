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
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Long transactionId;
	
	@Column(name = "supplier_id")
	private Long supplierId;
	
	@Column(name = "farmer_id")
	private Long farmerId;
	
	@Column(name = "offer_id")
	private Long offerId;
	
	@Column(name = "accept_date")
	private LocalDate acceptDate;
	
	@Column(name = "accept_time")
	private LocalTime acceptTime;
	
	@Column(name = "paid_date")
	private LocalDate paidDate;
	
	@Column(name = "paid_time")
	private LocalTime paidTime;
	
	@Column(name = "deliver_date")
	private LocalDate deliverDate;
	
	@Column(name = "delivered_date")
	private LocalDate deliveredDate;
	
	@Column(name = "delivered_time")
	private LocalTime deliveredTime;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "is_viewed")
	private Boolean isViewed;
	
	@Column(name = "is_delivered")
	private Boolean isDelivered;
}
