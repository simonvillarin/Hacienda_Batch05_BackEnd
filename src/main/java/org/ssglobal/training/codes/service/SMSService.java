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
	VonageClient client = VonageClient.builder()
			.apiKey("3660928d")
			.apiSecret("sQOYY705e34Swgn7")
			.build();
	
	public Response sendSMS(SMSRequest sms) {
		TextMessage message = new TextMessage("Hacienda",
		        "63" + sms.getToNumber(),
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
