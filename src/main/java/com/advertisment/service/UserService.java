package com.advertisment.service;

import com.advertisment.dto.LoginRequestDto;
import com.advertisment.dto.LoginResponseDto;
import com.advertisment.exception.UserNotFoundException;

public interface UserService {

	LoginResponseDto authenticateUser(LoginRequestDto loginRequestDto) throws UserNotFoundException;

}
