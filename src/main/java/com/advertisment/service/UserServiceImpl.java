package com.advertisment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advertisment.constant.Constant;
import com.advertisment.dto.LoginRequestDto;
import com.advertisment.dto.LoginResponseDto;
import com.advertisment.entity.User;
import com.advertisment.exception.UserNotFoundException;
import com.advertisment.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * 
	 * @author PriyaDharshini S.
	 * @since 2020-02-17. This method will authenticate the user.
	 * @param loginDto - details of the user login
	 * @return LoginResponseDto which has status message,statusCode and user details
	 * @throws UserNotFoundException it will throw the exception if the user is not
	 *                               there.
	 * 
	 */
	@Override
	public LoginResponseDto authenticateUser(LoginRequestDto loginRequestDto) throws UserNotFoundException {
		Optional<User> user = userRepository.findByMobileAndPassword(loginRequestDto.getMobile(),
				loginRequestDto.getPassword());
		if (!user.isPresent()) {
			log.error("Exception occurred in UserServiceImpl authenticateUser method:" + Constant.USER_NOT_FOUND);
			throw new UserNotFoundException(Constant.USER_NOT_FOUND);
		}
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		loginResponseDto.setUserId(user.get().getUserId());
		loginResponseDto.setUserName(user.get().getUserName());
		loginResponseDto.setRole(user.get().getRole());
		log.info("Entering into UserServiceImpl authenticateUser method: Authentication Successful");
		return loginResponseDto;

	}

}
