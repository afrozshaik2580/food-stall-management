package com.afroz.foodstall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.afroz.foodstall.entities.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{

	@Query(value = "select * from orders where stall_id = ?1 and status = 0;",nativeQuery = true)
	List<Orders> findByStall_id(int id);

	@Query(value = "select * from orders where user_id = ?1 ;",nativeQuery = true)
	List<Orders> getByUserId(int id);

}
