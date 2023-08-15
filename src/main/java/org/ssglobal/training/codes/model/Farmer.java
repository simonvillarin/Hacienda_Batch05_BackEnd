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
@Table(name = "farmer")
public class Farmer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "farmer_id")
	private Long farmerId;
	
	@Column(name = "user_id")
	private Long userId;
}
