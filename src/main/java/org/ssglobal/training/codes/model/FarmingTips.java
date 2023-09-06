package org.ssglobal.training.codes.model;

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
@Table(name = "farming_tips")
public class FarmingTips {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tip_id")
	private Long tipId;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "tip")
	private String tip;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "status")
	private Boolean status;
}
