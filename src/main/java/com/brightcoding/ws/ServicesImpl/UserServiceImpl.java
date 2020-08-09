package com.brightcoding.ws.ServicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.brightcoding.ws.Entities.UserEntity;
import com.brightcoding.ws.Repositeries.UserRepositery;
import com.brightcoding.ws.Response.UserResponse;
import com.brightcoding.ws.Services.UserService;
import com.brightcoding.ws.Shared.Utils;
import com.brightcoding.ws.Shared.Dato.UserDto;

//import com.brightcoding.ws.Shared.Utils;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepositery userRepositery;

//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utils utils;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
		UserEntity checkUser = userRepositery.findByEmail(user.getEmail());
		if (checkUser != null)
			throw new RuntimeException("User Already Exist !");
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		// userEntity.setEncreptedPassword("test PassWord");
		// userEntity.setUserId("user test Id");
		userEntity.setEncreptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(utils.generateUserId(32));
		UserEntity newUser = userRepositery.save(userEntity);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(newUser, userDto);
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepositery.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(), userEntity.getEncreptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepositery.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepositery.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		UserEntity userEntity = userRepositery.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		UserEntity updateUser = userRepositery.save(userEntity);
		UserDto user = new UserDto();
		BeanUtils.copyProperties(updateUser, user);
		return user;
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepositery.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		userRepositery.delete(userEntity);
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		if(page>0) page -= 1;	//affecter -1 à la valeur de la page pour eviter la page 0
		List<UserDto> usersDto = new ArrayList<>();	//Déclaser une list de type UserDto
		Pageable pageableRequest = PageRequest.of(page, limit);	//Pegeable contien la page et la limit(size)
		Page<UserEntity> userPage= userRepositery.findAll(pageableRequest);	//Récupérer une page
		List<UserEntity> users= userPage.getContent();	//récupérer la list d'une page
		for (UserEntity userEntity:users) {
		UserDto user = new UserDto();
		BeanUtils.copyProperties(userEntity, user);
		usersDto.add(user);		
	} 
		return usersDto;

		}
}
	
	

