package org.ssglobal.training.codes.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "complaint")
public class Complaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "complaint_id")
	private Long complaintId;
	
	@Column(name = "farmer_id")
	private Integer farmerId;
	
	@Column(name = "complaint_type")
	private String complaintType;
	
	@Column(name = "complaint_details")
	private String complaintDetails;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "status")
	private Boolean status;
}
