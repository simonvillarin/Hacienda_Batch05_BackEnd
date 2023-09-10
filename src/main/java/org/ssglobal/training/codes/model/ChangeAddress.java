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
@Table(name = "change_address")
public class ChangeAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "change_address_id")
	private Long changeAddressId;
	
	@Column(name = "transaction_id")
	private Long transactionId;
	
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
	
	@Column (name="status")
	private Boolean status;
}
