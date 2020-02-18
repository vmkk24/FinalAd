package com.advertisment.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Plan {
	
	@Id
	private Integer planId;
	private String planName;
	private Double planCost;

}
