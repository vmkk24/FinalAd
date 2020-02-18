package com.advertisment.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookSlotRequestDto {
	private Integer userId;
	private Integer slotId;
	private String fromTime;
	private String toTime;
	private LocalDate date;
	private String adName;
}
