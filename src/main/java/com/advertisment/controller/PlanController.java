package com.advertisment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advertisment.entity.Plan;
import com.advertisment.exception.PlanNotFoundException;
import com.advertisment.service.PlanService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/plans")
@CrossOrigin
@Slf4j
public class PlanController {

	@Autowired
	PlanService planService;

	/**
	 * 
	 * @author PriyaDharshini S.
	 * @since 2020-02-17. This method will get all the plans.
	 * @return list of plans. which has the plan list.
	 * @throws PlanNotFoundException it will throw the exception if the plan is not
	 *                               there.
	 * 
	 */
	@GetMapping
	public ResponseEntity<List<Plan>> getAllPlans() throws PlanNotFoundException {
		log.info("Entering into PlanController getAllPlans methd: calling planService");
		return new ResponseEntity<>(planService.getAllPlans(), HttpStatus.OK);
	}

}
