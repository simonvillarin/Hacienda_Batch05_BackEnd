package org.ssglobal.training.codes.response;

import java.time.LocalDate;
import java.time.LocalTime;

import org.ssglobal.training.codes.model.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
	private Long paymentId;
	private String orderIdRef;
	private Transaction transaction;
	private String paymentMode;
	private LocalDate paymentDate;
	private LocalTime paymentTime;
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
