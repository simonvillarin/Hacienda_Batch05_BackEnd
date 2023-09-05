package org.ssglobal.training.codes.response;

import java.time.LocalDate;

import org.ssglobal.training.codes.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintResponse {
	private Long complaintId;
	private User farmer;
	private String complaintType;
	private String complaintDetails;
	private LocalDate date;
	private String image;
	private Boolean status;
	private Boolean isDeleted;
}
