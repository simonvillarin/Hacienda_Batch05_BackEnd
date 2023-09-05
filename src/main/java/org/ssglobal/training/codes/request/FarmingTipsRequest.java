package org.ssglobal.training.codes.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmingTipsRequest {
	private String tip;
	private String subject;
	private String filename;
	private String mimeType;
	private byte[] data;
	private Boolean status;
}
