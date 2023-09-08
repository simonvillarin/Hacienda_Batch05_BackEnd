package org.ssglobal.training.codes.model;

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
@Table(name = "payment_details")
public class PaymentDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_details_id")
	private Long paymentDetailsId;
	
	@Column(name = "payment_id_ref")
	private String paymentIdRef;
	
	@Column(name = "transaction_id")
	private Long transactionId;
	
	@Column(name = "payment_account_id")
	private Long paymentAccountId;
	
	@Column(name = "payment_mode")
	private String paymentMode;
	
	@Column(name = "account_number")
	private Long accountNumber;
	
	@Column(name = "account_name")
	private String accountName;
	
	@Column(name = "status")
	private Boolean status;
}
