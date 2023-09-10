package org.ssglobal.training.codes.response;

import org.ssglobal.training.codes.model.PaymentAccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetailsResponse1 {
	private Long paymentDetailsId;
	private String paymentIdRef;
	private TransactionResponse transaction;
	private PaymentAccount paymentAccount;
	private String paymentMode;
	private Long accountNumber;
	private String accountName;
	private Boolean status;
}
