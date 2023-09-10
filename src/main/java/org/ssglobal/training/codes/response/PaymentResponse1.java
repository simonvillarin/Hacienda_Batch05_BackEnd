package org.ssglobal.training.codes.response;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse1 {
	private Long paymentId;
	private String orderIdRef;
	private PaymentDetailsResponse1 payment;
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
