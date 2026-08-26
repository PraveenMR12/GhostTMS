package com.io.ghosttms.service;

import java.util.List;

import com.io.ghosttms.util.RequestObject;
import com.io.ghosttms.util.ResponseObject;
import com.io.ghosttms.util.UserDto;

public interface UserService {

	UserDto registerUser(UserDto userDto);

	ResponseObject loginUser(RequestObject requestObj);

	UserDto updateUser(UserDto user);
	
	UserDto getUserByEmail(String email);

	void deleteUser(String email);

	List<UserDto> getAllUser();	
	

}
