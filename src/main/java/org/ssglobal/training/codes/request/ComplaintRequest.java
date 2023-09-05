package org.ssglobal.training.codes.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintRequest {
	private Long farmerId;
	private String complaintType;
	private String complaintDetails;
	private String filename;
	private String mimeType;
	private byte[] data;
	private Boolean status;
	private Boolean isDeleted;
}
