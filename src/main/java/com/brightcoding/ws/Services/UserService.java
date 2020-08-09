package com.brightcoding.ws.Services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.brightcoding.ws.Shared.Dato.UserDto;

public interface UserService extends UserDetailsService {
	
	UserDto createUser(UserDto userDto);
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(String Id, UserDto userDto);
	void deleteUser(String Id);
	List<UserDto> getUsers(int page, int limit);
}
