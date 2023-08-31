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
@Table(name = "advertisement")
public class Advertisement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long postId;
	
	@Column(name = "supplier_id")
	private Long supplierId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "measurement")
	private String measurement;
	
	@Column(name = "value")
	private Double value;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "post_date")
	private LocalDate postDate;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "is_offered")
	private Boolean isOffered;
}
