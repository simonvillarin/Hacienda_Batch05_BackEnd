package org.ssglobal.training.codes.response;

import org.ssglobal.training.codes.model.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeAddressResponse {
	private Long changeAddressId;
	private Transaction transaction;
	private String fullName;
	private String unit;
	private String street;
	private String village;
	private String barangay;
	private String city;
	private String province;
	private String region;
	private String contact;
	private Boolean status;
}
