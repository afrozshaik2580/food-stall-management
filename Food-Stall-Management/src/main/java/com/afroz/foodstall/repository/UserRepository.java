package com.afroz.foodstall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.afroz.foodstall.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(value = "select * from user where phone = ?1",nativeQuery = true)
	User findByPhone(String phone);
}
