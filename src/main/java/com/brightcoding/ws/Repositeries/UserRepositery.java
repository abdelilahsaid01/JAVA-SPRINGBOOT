package com.brightcoding.ws.Repositeries;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.brightcoding.ws.Entities.UserEntity;

//public interface UserRepositery extends CrudRepository<UserEntity, Long> {
public interface UserRepositery extends PagingAndSortingRepository<UserEntity, Long> {	//Permet de récupérer une page de la base de donnée

	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
}
