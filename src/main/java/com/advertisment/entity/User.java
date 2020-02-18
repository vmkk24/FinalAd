package com.advertisment.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.advertisment.common.AdvertismentManagementEnum.Role;

import lombok.Data;

@Entity
@Data
public class User {
	
	@Id
	private Integer userId;
	private String userName;
	private String password;
	private String email;
	private Long mobile;
	@Enumerated(EnumType.STRING)
	private Role role;
	

}
