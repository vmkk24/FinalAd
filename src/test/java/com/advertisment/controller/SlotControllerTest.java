package com.advertisment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.advertisment.constant.Constant;
import com.advertisment.dto.AddSlotRequestDto;
import com.advertisment.dto.AddSlotResponseDto;
import com.advertisment.dto.AdminSlotsDto;
import com.advertisment.dto.BookSlotRequestDto;
import com.advertisment.dto.BookSlotResponseDto;
import com.advertisment.entity.User;
import com.advertisment.exception.PlanNotFoundException;
import com.advertisment.exception.SlotExisistException;
import com.advertisment.exception.SlotNotFoundException;
import com.advertisment.exception.UserNotFoundException;
import com.advertisment.service.SlotService;

@RunWith(MockitoJUnitRunner.class)
public class SlotControllerTest {

	@InjectMocks
	SlotController slotController;

	@Mock
	SlotService slotService;

	User user = new User();
	AddSlotRequestDto addSlotRequestDto = new AddSlotRequestDto();
	AddSlotResponseDto addSlotResponseDto = new AddSlotResponseDto();
	BookSlotRequestDto bookSlotRequestDto = new BookSlotRequestDto();

	@Before
	public void init() {
		user.setUserId(1);
		addSlotRequestDto.setUserId(1);
		addSlotRequestDto.setPlanId(1);
		addSlotResponseDto.setMessage(Constant.SUCCESS_MESSAGE);
		addSlotResponseDto.setStatusCode(Constant.STATUS_CODE);
	}

	@Test
	public void testGetAllSlotsByUser() throws UserNotFoundException, SlotNotFoundException {
		Mockito.when(slotService.getSlotsByUser(1)).thenReturn(new ArrayList<>());
		ResponseEntity<List<AdminSlotsDto>> actual = slotController.getSlotsByUser(1);
		assertEquals(HttpStatus.OK, actual.getStatusCode());
	}

	@Test(expected = UserNotFoundException.class)
	public void testGetAllSlotsByUserForUserNotFoundException() throws UserNotFoundException, SlotNotFoundException {
		user.setUserId(null);
		slotController.getSlotsByUser(null);
	}

	@Test
	public void testAddSlot() throws UserNotFoundException, PlanNotFoundException, SlotExisistException {
		Mockito.when(slotService.addSlot(addSlotRequestDto)).thenReturn(addSlotResponseDto);
		ResponseEntity<AddSlotResponseDto> actual = slotController.addSlot(addSlotRequestDto);
		assertEquals(HttpStatus.OK, actual.getStatusCode());
	}
	
	@Test
	public void testGetAvailableSlots() throws SlotNotFoundException {
		Mockito.when(slotService.getAvailableSlots()).thenReturn(new ArrayList<>());
		ResponseEntity<List<AdminSlotsDto>> actual = slotController.getAvailableSlots();
		assertEquals(HttpStatus.OK, actual.getStatusCode());
	}
	
	@Test
	public void testGetBookedSlotsByUser() throws SlotNotFoundException, UserNotFoundException {
		Mockito.when(slotService.getBookedSlotsByUser(1)).thenReturn(new ArrayList<>());
		ResponseEntity<List<AdminSlotsDto>> actual = slotController.getBookedSlotsByUser(1);
		assertEquals(HttpStatus.OK, actual.getStatusCode());
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testGetBookedSlotsByUserForUserNotFoundException() throws SlotNotFoundException, UserNotFoundException {
		user.setUserId(null);
		slotController.getBookedSlotsByUser(null);
	}
	
	@Test
	public void testBookSlots() throws SlotNotFoundException, UserNotFoundException {
		Mockito.when(slotService.bookSlot(bookSlotRequestDto)).thenReturn(new BookSlotResponseDto());
		ResponseEntity<BookSlotResponseDto> actual = slotController.bookSlot(bookSlotRequestDto);
		assertEquals(HttpStatus.OK, actual.getStatusCode());
	}

	
	

}
