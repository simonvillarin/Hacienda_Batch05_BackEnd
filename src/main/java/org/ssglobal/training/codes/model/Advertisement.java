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
@Table(name = "advertisement")
public class Advertisement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long postId;
	
	@Column(name = "supplier_id")
	private Integer supplierId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "mass")
	private Double mass;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "status")
	private Boolean status;
}
