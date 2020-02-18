package com.advertisment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.advertisment.common.AdvertismentManagementEnum.SlotStatus;
import com.advertisment.constant.Constant;
import com.advertisment.dto.AddSlotRequestDto;
import com.advertisment.dto.AddSlotResponseDto;
import com.advertisment.dto.AdminSlotsDto;
import com.advertisment.dto.BookSlotRequestDto;
import com.advertisment.dto.BookSlotResponseDto;
import com.advertisment.entity.Plan;
import com.advertisment.entity.Slot;
import com.advertisment.entity.User;
import com.advertisment.exception.PlanNotFoundException;
import com.advertisment.exception.SlotExisistException;
import com.advertisment.exception.SlotNotFoundException;
import com.advertisment.exception.UserNotFoundException;
import com.advertisment.repository.PlanRepository;
import com.advertisment.repository.SlotRepository;
import com.advertisment.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SlotServiceTest {

	@InjectMocks
	SlotServiceImpl slotServiceImpl;

	@Mock
	SlotRepository slotRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	PlanRepository planRepository;

	User user = new User();
	Slot slot = new Slot();
	List<Slot> slots = new ArrayList<>();
	Plan plan = new Plan();
	AdminSlotsDto adminSlotsDto = new AdminSlotsDto();
	List<AdminSlotsDto> adminSlotsDtos = new ArrayList<>();
	AddSlotRequestDto addSlotRequestDto = new AddSlotRequestDto();
	AddSlotResponseDto addSlotResponseDto = new AddSlotResponseDto();
	BookSlotRequestDto bookSlotRequestDto = new BookSlotRequestDto();
	BookSlotResponseDto bookSlotResponseDto = new BookSlotResponseDto();

	@Before
	public void init() {
		user.setUserId(1);
		user.setMobile(1L);
		plan.setPlanId(1);
		plan.setPlanCost(200.8);
		plan.setPlanName("premium");
		slot.setSlotId(1);
		slot.setPlan(plan);
		slot.setSlotStatus(SlotStatus.BOOKED);
		slot.setCreatedBy(user);
		slots.add(slot);
		adminSlotsDto.setSlotId(1);
		adminSlotsDtos.add(adminSlotsDto);
		addSlotRequestDto.setDate(LocalDate.of(2019, 11, 02));
		addSlotRequestDto.setFromTime("22:11:11");
		addSlotRequestDto.setToTime("23:11:11");
		addSlotResponseDto.setMessage(Constant.SUCCESS_MESSAGE);
		addSlotResponseDto.setStatusCode(200);
		addSlotRequestDto.setPlanId(1);
		addSlotRequestDto.setUserId(1);
	}

	@Test
	public void testGetAllSlotsByUser() throws UserNotFoundException, SlotNotFoundException {
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
		Mockito.when(slotRepository.findAllByCreatedBy(user)).thenReturn(slots);
		List<AdminSlotsDto> actual = slotServiceImpl.getSlotsByUser(1);
		assertEquals(1, actual.size());
	}

	@Test(expected = UserNotFoundException.class)
	public void testGetAllSlotsByUserForUserNotFoundException() throws UserNotFoundException, SlotNotFoundException {
		Mockito.when(userRepository.findById(2)).thenReturn(Optional.of(user));
		slotServiceImpl.getSlotsByUser(1);
	}

	@Test(expected = SlotNotFoundException.class)
	public void testGetAllSlotsByUserForSlotNotFoundException() throws UserNotFoundException, SlotNotFoundException {
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
		slots = new ArrayList<>();
		Mockito.when(slotRepository.findAllByCreatedBy(user)).thenReturn(slots);
		slotServiceImpl.getSlotsByUser(1);
	}

	@Test
	public void testGetAvailableSlots() throws SlotNotFoundException {
		Mockito.when(slotRepository.findAllBySlotStatus(SlotStatus.AVAILABLE)).thenReturn(slots);
		List<AdminSlotsDto> actual = slotServiceImpl.getAvailableSlots();
		assertEquals(1, actual.size());
	}

	@Test(expected = SlotNotFoundException.class)
	public void testGetAvailableSlotsFor() throws SlotNotFoundException {
		slots = new ArrayList<>();
		Mockito.when(slotRepository.findAllBySlotStatus(SlotStatus.AVAILABLE)).thenReturn(slots);
		slotServiceImpl.getAvailableSlots();
	}

	@Test
	public void testGetAllBookedSlotsByUser() throws UserNotFoundException, SlotNotFoundException {
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
		Mockito.when(slotRepository.findAllByCreatedByAndSlotStatus(user, SlotStatus.BOOKED)).thenReturn(slots);
		List<AdminSlotsDto> actual = slotServiceImpl.getBookedSlotsByUser(1);
		assertEquals(1, actual.size());
	}

	@Test(expected = UserNotFoundException.class)
	public void testGetAllBookedSlotsByUserForUserNotFoundException()
			throws UserNotFoundException, SlotNotFoundException {
		Mockito.when(userRepository.findById(2)).thenReturn(Optional.of(user));
		slotServiceImpl.getBookedSlotsByUser(1);
	}

	@Test(expected = SlotNotFoundException.class)
	public void testGetAllBookedSlotsByUserForSlotNotFoundException()
			throws UserNotFoundException, SlotNotFoundException {
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
		slots = new ArrayList<>();
		Mockito.when(slotRepository.findAllByCreatedByAndSlotStatus(user, SlotStatus.BOOKED)).thenReturn(slots);
		slotServiceImpl.getBookedSlotsByUser(1);
	}

	@Test
	public void testAddSlot() throws UserNotFoundException, PlanNotFoundException, SlotExisistException {
		Mockito.when(slotRepository.SlotByDateFromTimeToTime(LocalDate.of(2019, 11, 02), LocalTime.of(21, 11, 12),
				LocalTime.of(22, 11, 12))).thenReturn(slots);
		Mockito.when(planRepository.findById(1)).thenReturn(Optional.of(plan));
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
		AddSlotResponseDto actual = slotServiceImpl.addSlot(addSlotRequestDto);
		assertEquals(addSlotResponseDto, actual);

	}


}
