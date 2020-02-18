package com.advertisment.dto;

import com.advertisment.common.AdvertismentManagementEnum.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto extends ResponseDto{
	private Role role;
	private Integer userId;
	private String userName;

}
