package com.advertisment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advertisment.constant.Constant;
import com.advertisment.dto.LoginRequestDto;
import com.advertisment.dto.LoginResponseDto;
import com.advertisment.exception.UserNotFoundException;
import com.advertisment.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@CrossOrigin
@Slf4j
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * 
	 * @author PriyaDharshini S.
	 * @since 2020-02-11. This method will authenticate the user.
	 * @param loginDto - details of the user login
	 * @return LoginResponseDto which has status message,statusCode and user details
	 * @throws UserNotFoundException it will throw the exception if the user is not
	 *                               there.
	 * 
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto)
			throws UserNotFoundException {
		LoginResponseDto loginResponseDto = userService.authenticateUser(loginRequestDto);
		log.info("Entering into UserController authenticateUser method: calling userService");
		loginResponseDto.setStatusMessage(Constant.AUTHENTICATION_SUCCESSFUL);
		loginResponseDto.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
	}

}
