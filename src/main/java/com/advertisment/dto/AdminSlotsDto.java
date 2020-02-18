package com.advertisment.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.advertisment.common.AdvertismentManagementEnum.SlotStatus;

import lombok.Data;

@Data
public class AdminSlotsDto {

	private Integer slotId;
	private LocalDate date;
	private LocalTime fromTime;
	private LocalTime toTime;
	private String planName;
	private SlotStatus slotStatus;
	private Double totalCost;

}
