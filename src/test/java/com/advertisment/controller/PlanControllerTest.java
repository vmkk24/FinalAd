package com.advertisment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.advertisment.entity.Plan;
import com.advertisment.exception.PlanNotFoundException;
import com.advertisment.service.PlanService;

@RunWith(MockitoJUnitRunner.class)
public class PlanControllerTest {
	
	@InjectMocks
	PlanController planController;
	
	@Mock
	PlanService planService;
	
	@Test
	public void testGetAllPlans() throws PlanNotFoundException {
		Mockito.when(planService.getAllPlans()).thenReturn(new ArrayList<>());
		ResponseEntity<List<Plan>> actual = planController.getAllPlans();
		assertEquals(HttpStatus.OK, actual.getStatusCode());
	}

}
