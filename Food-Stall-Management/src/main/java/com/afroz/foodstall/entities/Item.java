package com.afroz.foodstall.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "name should not be empty")
	private String name;
	@Size(min = 5, message = "description should be minimum 5 characters")
	private String description;
	@Positive(message = "cannot be null")
	private double price;

	@ManyToOne
	private Stall stall;
	
	@ManyToMany(mappedBy = "items")
	private List<Orders> orders;


	@ManyToMany(mappedBy = "cart")
	private List<User> users = new ArrayList<>();


	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Item(@NotEmpty(message = "name should not be empty") String name,
			@Size(min = 5, message = "description should be minimum 5 characters") String description,
			@Positive(message = "cannot be null") double price, Stall stall, List<Orders> orders, List<User> users) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.stall = stall;
		this.orders = orders;
		this.users = users;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Stall getStall() {
		return stall;
	}


	public void setStall(Stall stall) {
		this.stall = stall;
	}


	public List<Orders> getOrder() {
		return orders;
	}


	public void setOrder(List<Orders> orders) {
		this.orders = orders;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}

}
