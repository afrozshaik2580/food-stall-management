package com.afroz.foodstall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.afroz.foodstall.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

	@Query(value = "select * from item where stall_id= ?1",nativeQuery = true)
	List<Item> getItemsByStallid(Integer id);

	@Query(value = "select * from item where id= ?1",nativeQuery = true)
	Item findByIdoverride(Integer id);
}
