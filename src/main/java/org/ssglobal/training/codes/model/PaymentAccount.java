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
@Table(name = "payment_account")
public class PaymentAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_account_id")
	private Long paymentAccountId;
	
	@Column(name = "farmer_id")
	private Integer farmerId;
	
	@Column(name = "account_number")
	private Long accountNumber;
	
	@Column(name = "account_name")
	private String accountName;
	
	@Column(name = "status")
	private Boolean status;
}
