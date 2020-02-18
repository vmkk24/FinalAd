package com.advertisment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.advertisment.dto.LoginRequestDto;
import com.advertisment.dto.LoginResponseDto;
import com.advertisment.entity.User;
import com.advertisment.exception.UserNotFoundException;
import com.advertisment.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;

	LoginRequestDto loginRequestDto = new LoginRequestDto();
	LoginResponseDto loginResponseDto = new LoginResponseDto();
	User user = new User();

	@Before
	public void init() {
		user.setUserId(1);
		loginRequestDto.setMobile(1L);
		loginRequestDto.setPassword("priya");
	}

	@Test
	public void testAuthenticateUser() throws UserNotFoundException {
		Mockito.when(userService.authenticateUser(loginRequestDto)).thenReturn(loginResponseDto);
		ResponseEntity<LoginResponseDto> actual = userController.authenticateUser(loginRequestDto);
		assertEquals(HttpStatus.OK, actual.getStatusCode());
	}

}
