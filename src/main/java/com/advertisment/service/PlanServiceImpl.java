package com.advertisment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advertisment.constant.Constant;
import com.advertisment.entity.Plan;
import com.advertisment.exception.PlanNotFoundException;
import com.advertisment.repository.PlanRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlanServiceImpl implements PlanService {

	@Autowired
	PlanRepository planRepository;

	/**
	 * 
	 * @author PriyaDharshini S.
	 * @since 2020-02-17. This method will get all the plans.
	 * @return list of plans. which has the plan list.
	 * @throws PlanNotFoundException it will throw the exception if the plan is not
	 *                               there.
	 * 
	 */
	public List<Plan> getAllPlans() throws PlanNotFoundException {

		List<Plan> plans = planRepository.findAll();

		if (plans.isEmpty()) {
			log.error("Exception occured in PlanServiceImpl getAllPlans method" + Constant.PLAN_NOT_FOUND);
			throw new PlanNotFoundException(Constant.PLAN_NOT_FOUND);
		}
		log.info("Entered into PlanServiceImpl getAllPlans method: getting plans");
		return plans;

	}

}
