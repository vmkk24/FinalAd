package com.advertisment.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advertisment.common.AdvertismentManagementEnum.SlotStatus;
import com.advertisment.constant.Constant;
import com.advertisment.dto.AddSlotRequestDto;
import com.advertisment.dto.AddSlotResponseDto;
import com.advertisment.dto.AdminSlotsDto;
import com.advertisment.dto.BookSlotRequest;
import com.advertisment.dto.BookSlotRequestDto;
import com.advertisment.dto.BookSlotResponseDto;
import com.advertisment.dto.ResponseDto;
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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SlotServiceImpl implements SlotService {

	@Autowired
	SlotRepository slotRepository;

	@Autowired
	UserRepository userRepository;
	@Autowired
	PlanRepository planRepository;

	/**
	 * 
	 * @author PriyaDharshini S.
	 * @since 2020-02-17. This method will get the slots for the user.
	 * @param userId - id of the user
	 * @return list of AdminSlotsDto which has the list of slot details.
	 * @throws UserNotFoundException it will throw the exception if the user is not
	 *                               there.
	 * @throws SlotNotFoundException it will throw the exception if the slot is not
	 *                               there.
	 * 
	 */
	public List<AdminSlotsDto> getSlotsByUser(Integer userId) throws UserNotFoundException, SlotNotFoundException {

		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			log.error("Exception occured in SlotServiceImpl getSlotsByUser method :" + Constant.USER_NOT_FOUND);
			throw new UserNotFoundException(Constant.USER_NOT_FOUND);
		}
		List<Slot> slots = slotRepository.findAllByCreatedBy(user.get());
		if (slots.isEmpty()) {
			log.error("Exception occured in SlotServiceImpl getSlotsByUser method :" + Constant.SLOT_NOT_FOUND);
			throw new SlotNotFoundException(Constant.SLOT_NOT_FOUND);
		}
		log.info("Entering into SlotServiceImpl getSlotsByUser method : getting slots");
		List<AdminSlotsDto> adminSlots = new ArrayList<>();
		slots.forEach(slot -> {
			AdminSlotsDto adminSlotsDto = new AdminSlotsDto();
			BeanUtils.copyProperties(slot, adminSlotsDto);
			adminSlotsDto.setPlanName(slot.getPlan().getPlanName());
			adminSlots.add(adminSlotsDto);
		});
		return adminSlots;
	}

	/**
	 * 
	 * @author PriyaDharshini S.
	 * @since 2020-02-17. This method will get the available slots for the user.
	 * @param userId - id of the user
	 * @return list of AdminSlotsDto which has the list of slot details.
	 * @throws SlotNotFoundException it will throw the exception if the slot is not
	 *                               there.
	 * 
	 */
	public List<AdminSlotsDto> getAvailableSlots() throws SlotNotFoundException {

		List<Slot> slots = slotRepository.findAllBySlotStatus(SlotStatus.AVAILABLE);
		if (slots.isEmpty()) {
			log.error("Exception occured in SlotServiceImpl getAvailableSlots method :" + Constant.SLOT_NOT_FOUND);
			throw new SlotNotFoundException(Constant.SLOT_NOT_FOUND);
		}
		log.info("Entering into SlotServiceImpl getAvailableSlots method : getting slots");
		List<AdminSlotsDto> adminSlots = new ArrayList<>();
		slots.forEach(slot -> {
			AdminSlotsDto adminSlotsDto = new AdminSlotsDto();
			BeanUtils.copyProperties(slot, adminSlotsDto);
			adminSlotsDto.setPlanName(slot.getPlan().getPlanName());
			adminSlots.add(adminSlotsDto);
		});
		return adminSlots;
	}

	/**
	 * 
	 * @author PriyaDharshini S.
	 * @since 2020-02-17. This method will get the booked slots for the user.
	 * @param userId - id of the user
	 * @return list of AdminSlotsDto which has the list of slot details.
	 * @throws UserNotFoundException it will throw the exception if the user is not
	 *                               there.
	 * @throws SlotNotFoundException it will throw the exception if the slot is not
	 *                               there.
	 * 
	 */
	public List<AdminSlotsDto> getBookedSlotsByUser(Integer userId)
			throws UserNotFoundException, SlotNotFoundException {

		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			log.error("Exception occured in SlotServiceImpl getBookedSlotsByUser method :" + Constant.USER_NOT_FOUND);
			throw new UserNotFoundException(Constant.USER_NOT_FOUND);
		}
		List<Slot> slots = slotRepository.findAllByCreatedByAndSlotStatus(user.get(), SlotStatus.BOOKED);
		if (slots.isEmpty()) {
			log.error("Exception occured in SlotServiceImpl getBookedSlotsByUser method :" + Constant.SLOT_NOT_FOUND);
			throw new SlotNotFoundException(Constant.SLOT_NOT_FOUND);
		}
		log.info("Entering into SlotServiceImpl getBookedSlotsByUser method : getting slots");
		List<AdminSlotsDto> adminSlots = new ArrayList<>();
		slots.forEach(slot -> {
			AdminSlotsDto adminSlotsDto = new AdminSlotsDto();
			BeanUtils.copyProperties(slot, adminSlotsDto);
			adminSlotsDto.setPlanName(slot.getPlan().getPlanName());
			adminSlots.add(adminSlotsDto);
		});
		return adminSlots;
	}

	/**
	 * @author Yoga
	 * @since 2020-02-17.
	 * 
	 *        This method will get list of the userScheme details by the scemeId.
	 * @param ADD Slot RequestDto
	 * @return list of message and statusCode.
	 * @throws SlotExisistException  it will throw the exception if the slot is
	 *                               there.
	 * @throws UserNotFoundException
	 * @throws PlanNotFoundException
	 * 
	 */
	@Override
	public AddSlotResponseDto addSlot(AddSlotRequestDto addSlotRequestDto)
			throws UserNotFoundException, PlanNotFoundException, SlotExisistException {
		log.info("Entering into SlotServiceImpl add slots method");
		List<Slot> slots = slotRepository.SlotByDateFromTimeToTime(addSlotRequestDto.getDate(),
				LocalTime.parse(addSlotRequestDto.getFromTime()), LocalTime.parse(addSlotRequestDto.getToTime()));
		if (slots.isEmpty()) {
			log.info("Entering into SlotServiceImpl slots is empty block");
			Slot slot = new Slot();
			BeanUtils.copyProperties(addSlotRequestDto, slot);
			slot.setDate(addSlotRequestDto.getDate());
			slot.setFromTime(LocalTime.parse(addSlotRequestDto.getFromTime()));
			slot.setToTime(LocalTime.parse(addSlotRequestDto.getToTime()));
			Optional<Plan> plan = planRepository.findById(addSlotRequestDto.getPlanId());
			if (plan.isPresent()) {
				log.info("Entering into SlotServiceImpl plan is present block");
				slot.setPlan(plan.get());
			} else {
				throw new PlanNotFoundException(Constant.PLAN_NOT_FOUND);
			}
			Optional<User> user = userRepository.findById(addSlotRequestDto.getUserId());
			if (user.isPresent()) {
				slot.setCreatedBy(user.get());
			} else {
				throw new UserNotFoundException(Constant.USER_NOT_FOUND);
			}
			slot.setSlotStatus(addSlotRequestDto.getSlotStatus());
			LocalTime fromTime = LocalTime.parse(addSlotRequestDto.getFromTime());
			LocalTime toTime = LocalTime.parse(addSlotRequestDto.getToTime());
			Long time = ChronoUnit.SECONDS.between(fromTime, toTime);
			Double price = time * (plan.get().getPlanCost());
			Double totalCost = (double) Math.round(price);
			slot.setTotalCost(totalCost);
			slotRepository.save(slot);
			AddSlotResponseDto addSlotResponseDto = new AddSlotResponseDto();
			addSlotResponseDto.setMessage(Constant.SUCCESS_MESSAGE);
			addSlotResponseDto.setStatusCode(Constant.STATUS_CODE);
			return addSlotResponseDto;
		} else {
			throw new SlotExisistException(Constant.SLOT_EXISIST_EXCEPTION);
		}
	}

	

}
