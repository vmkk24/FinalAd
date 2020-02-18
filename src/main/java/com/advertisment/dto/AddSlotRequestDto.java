package com.advertisment.dto;

import java.time.LocalDate;

import com.advertisment.common.AdvertismentManagementEnum.SlotStatus;

import lombok.Data;

@Data
public class AddSlotRequestDto {

	private LocalDate date;
	private String fromTime;
	private String toTime;
	private Integer planId;
	private SlotStatus slotStatus;
	private Integer userId;
}