package com.advertisment.service;

import java.util.List;

import com.advertisment.entity.Plan;
import com.advertisment.exception.PlanNotFoundException;

public interface PlanService {
	
	List<Plan> getAllPlans() throws PlanNotFoundException;

}
