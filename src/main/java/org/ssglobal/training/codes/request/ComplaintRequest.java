package org.ssglobal.training.codes.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintRequest {
	private Integer farmerId;
	private String complaintType;
	private String complaintDetails;
	private LocalDate date;
	private String filename;
	private String mimeType;
	private byte[] data;
	private Boolean status;
}
