package com.advertisment.service;

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

import com.advertisment.entity.Plan;
import com.advertisment.exception.PlanNotFoundException;
import com.advertisment.repository.PlanRepository;

@RunWith(MockitoJUnitRunner.class)
public class PlanServiceTest {

	@InjectMocks
	PlanServiceImpl planServiceImpl;

	@Mock
	PlanRepository planRepository;

	Plan plan = new Plan();
	List<Plan> plans = new ArrayList<>();

	@Before
	public void init() {
		plan.setPlanId(1);
		plans.add(plan);
	}

	@Test
	public void testGetAllPlans() throws PlanNotFoundException {
		Mockito.when(planRepository.findAll()).thenReturn(plans);
		List<Plan> actual = planServiceImpl.getAllPlans();
		assertEquals(1, actual.size());
	}

	@Test(expected = PlanNotFoundException.class)
	public void testGetAllPlansForPlanNotFoundException() throws PlanNotFoundException {
		plans = new ArrayList<>();
		Mockito.when(planRepository.findAll()).thenReturn(plans);
		planServiceImpl.getAllPlans();
	}

}
