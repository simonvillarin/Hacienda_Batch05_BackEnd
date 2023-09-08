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
public class PaymentDetailsResponse {
	private Long paymentDetailsId;
	private String paymentIdRef;
	private Transaction transaction;
	private Long paymentAccountId;
	private String paymentMode;
	private Long accountNumber;
	private String accountName;
	private Boolean status;
}
