package com.advertisment.service;

import java.util.List;

import com.advertisment.dto.AddSlotRequestDto;
import com.advertisment.dto.AddSlotResponseDto;
import com.advertisment.dto.AdminSlotsDto;
import com.advertisment.dto.BookSlotRequest;
import com.advertisment.dto.BookSlotRequestDto;
import com.advertisment.dto.BookSlotResponseDto;
import com.advertisment.dto.ResponseDto;
import com.advertisment.exception.PlanNotFoundException;
import com.advertisment.exception.SlotExisistException;
import com.advertisment.exception.SlotNotFoundException;
import com.advertisment.exception.UserNotFoundException;

public interface SlotService {

	List<AdminSlotsDto> getSlotsByUser(Integer userId) throws UserNotFoundException, SlotNotFoundException;

	List<AdminSlotsDto> getAvailableSlots() throws SlotNotFoundException;

	List<AdminSlotsDto> getBookedSlotsByUser(Integer userId) throws UserNotFoundException, SlotNotFoundException;

	AddSlotResponseDto addSlot(AddSlotRequestDto addSlotRequestDto)
			throws UserNotFoundException, PlanNotFoundException, SlotExisistException;

	BookSlotResponseDto bookSlot(BookSlotRequestDto bookSlotRequestDto) throws SlotNotFoundException, UserNotFoundException;
	
	 ResponseDto bookSlots(BookSlotRequest bookSlotRequest) throws SlotNotFoundException;

}
