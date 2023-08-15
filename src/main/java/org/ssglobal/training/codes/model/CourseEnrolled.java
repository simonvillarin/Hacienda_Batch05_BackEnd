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
@Table(name = "course_enrolled")
public class CourseEnrolled {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enroll_id")
	private Long enrollId;
	
	@Column(name = "farmer_id")
	private Integer farmerId;
	
	@Column(name = "course_id")
	private Integer courseId;
	
	@Column(name = "enroll_date")
	private LocalDate enrollDate;
	
	@Column(name = "status")
	private Boolean status;
}
