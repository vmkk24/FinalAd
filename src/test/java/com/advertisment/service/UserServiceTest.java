package com.advertisment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.advertisment.common.AdvertismentManagementEnum.Role;
import com.advertisment.dto.LoginRequestDto;
import com.advertisment.dto.LoginResponseDto;
import com.advertisment.entity.User;
import com.advertisment.exception.UserNotFoundException;
import com.advertisment.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	LoginRequestDto loginRequestDto = new LoginRequestDto();
	LoginResponseDto loginResponseDto = new LoginResponseDto();
	User user = new User();

	@Before
	public void init() {
		user.setUserId(1);
		user.setMobile(1L);
		user.setPassword("priya");
		user.setRole(Role.ADMIN);
		loginRequestDto.setMobile(1L);
		loginRequestDto.setPassword("priya");
		loginResponseDto.setRole(Role.ADMIN);
	}

	@Test
	public void testAuthenticateUser() throws UserNotFoundException {
		Mockito.when(userRepository.findByMobileAndPassword(1L, "priya")).thenReturn(Optional.of(user));
		LoginResponseDto actual = userServiceImpl.authenticateUser(loginRequestDto);
		assertEquals(Role.ADMIN, actual.getRole());
	}

	@Test(expected = UserNotFoundException.class)
	public void testAuthenticateUserForUserNotFoundException() throws UserNotFoundException {
		Mockito.when(userRepository.findByMobileAndPassword(2L, "sri")).thenReturn(Optional.of(user));
		userServiceImpl.authenticateUser(loginRequestDto);
	}

}
