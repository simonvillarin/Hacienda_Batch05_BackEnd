package org.ssglobal.training.codes.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.request.SMSRequest;
import org.ssglobal.training.codes.response.Response;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;

@Service
public class SMSService {
	
	// Simon phone number
	public Response sendAdminSMS(SMSRequest sms) {
		VonageClient client = VonageClient.builder()
				.apiKey("3660928d")
				.apiSecret("sQOYY705e34Swgn7")
				.build();
		
		TextMessage message = new TextMessage("Hacienda",
		        "639270445766",
		        sms.getMessage()
		);

		SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

		if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
		   return Response.builder()
				   .status(200)
				   .message("SMS sent successfully")
				   .timestamp(LocalDateTime.now())
				   .build();
		} else {
			 return Response.builder()
					   .status(400)
					   .message("SMS failed to sent")
					   .timestamp(LocalDateTime.now())
					   .build();
		}
	}
	
	// Ados phone number
	public Response sendFarmerSMS(SMSRequest sms) {
		VonageClient client = VonageClient.builder()
				.apiKey("ddb76f2d")
				.apiSecret("0IlFBPt83tSKKtSF")
				.build();
		
		TextMessage message = new TextMessage("Hacienda",
		        "639054883528",
		        sms.getMessage()
		);

		SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

		if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
		   return Response.builder()
				   .status(200)
				   .message("SMS sent successfully")
				   .timestamp(LocalDateTime.now())
				   .build();
		} else {
			 return Response.builder()
					   .status(400)
					   .message("SMS failed to sent")
					   .timestamp(LocalDateTime.now())
					   .build();
		}
	}
	
	//Clara phone number
	public Response sendSupplierSMS(SMSRequest sms) {
		VonageClient client = VonageClient.builder()
				.apiKey("2bfdc6cf")
				.apiSecret("76wGk41ZndD6uqzk")
				.build();
		
		TextMessage message = new TextMessage("Hacienda",
		        "639054883528",
		        sms.getMessage()
		);

		SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

		if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
		   return Response.builder()
				   .status(200)
				   .message("SMS sent successfully")
				   .timestamp(LocalDateTime.now())
				   .build();
		} else {
			 return Response.builder()
					   .status(400)
					   .message("SMS failed to sent")
					   .timestamp(LocalDateTime.now())
					   .build();
		}
	}
}
