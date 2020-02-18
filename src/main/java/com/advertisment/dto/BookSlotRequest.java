package com.advertisment.dto;

import java.util.List;

import lombok.Data;

@Data
public class BookSlotRequest {
	private Integer userId;
	private Integer slotId;
	private List<BookSlotRequestDto> bookSlotRequestDtoList;
}
