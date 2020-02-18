package com.advertisment.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.advertisment.common.AdvertismentManagementEnum.SlotStatus;

import lombok.Data;

@Entity
@Data
public class Slot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer slotId;
	private LocalDate date;
	private LocalTime fromTime;
	private LocalTime toTime;
	@ManyToOne
	@JoinColumn(name = "plan_id")
	private Plan plan;
	@Enumerated(EnumType.STRING)
	private SlotStatus slotStatus;
	private Double totalCost;
	@ManyToOne
	@JoinColumn(name="created_by")
	private User createdBy;
	private String adName;

}
