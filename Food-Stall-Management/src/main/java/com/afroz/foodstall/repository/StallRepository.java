package com.afroz.foodstall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afroz.foodstall.entities.Stall;

public interface StallRepository extends JpaRepository<Stall, Integer>{

	Stall findByOwnerEmail(String email);

}
