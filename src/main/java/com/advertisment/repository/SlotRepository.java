package com.advertisment.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.advertisment.common.AdvertismentManagementEnum.SlotStatus;
import com.advertisment.entity.Slot;
import com.advertisment.entity.User;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer>{

	List<Slot> findAllByCreatedBy(User user);

	List<Slot> findAllBySlotStatus(SlotStatus available);

	List<Slot> findAllByCreatedByAndSlotStatus(User user, SlotStatus booked);
	
	@Query("select s from Slot s where s.date=:date and (( :fromTime between s.fromTime and s.toTime) or (:toTime between s.fromTime and s.toTime) or (:fromTime<s.fromTime and  :toTime >s.toTime))")
	List<Slot> SlotByDateFromTimeToTime(@NotNull @Param("date") LocalDate date,
			@NotNull @Param("fromTime") LocalTime fromTime, @NotNull @Param("toTime") LocalTime toTime);

	@Modifying(clearAutomatically = true)
	@Query("select s from Slot s where s.date=:date and (( :fromTime between s.fromTime and s.toTime) or (:toTime between s.fromTime and s.toTime) or (:fromTime<s.fromTime and  :toTime >s.toTime))")
	List<Slot> SlotByDateFromTimeToTimeStatus(@NotNull @Param("date") LocalDate date,
	@NotNull @Param ("fromTime") LocalTime fromTime, @NotNull @Param("toTime") LocalTime toTime);

	Optional<Slot> findBySlotId(Integer slotId);

}
