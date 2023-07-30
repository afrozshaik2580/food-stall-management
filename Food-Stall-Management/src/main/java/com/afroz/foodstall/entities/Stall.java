package com.afroz.foodstall.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Stall {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "name must not be empty")
	@Size(min = 5,message = "name must have 5 characters")
	private String name;
	@NotEmpty(message = "owner must not be empty")
	private String ownerName;
	@NotEmpty(message = "email must not be empty")
	@Email(message = "must have a email format")
	private String ownerEmail;
	@NotEmpty(message = "phone must not be empty")
	private String ownerPhone;
	@NotEmpty(message = "password must not be empty")
	private String password;

	@OneToMany(mappedBy = "stall",fetch = FetchType.EAGER)
	private List<Item> menu = new ArrayList<>();

	@OneToMany(mappedBy = "stall")
	private List<Orders> orders = new ArrayList<>();

	public Stall() {
		super();
	}

	public Stall(
			@NotEmpty(message = "name must not be empty") @Size(min = 5, message = "name must have 5 characters") String name,
			@NotEmpty(message = "owner must not be empty") String ownerName,
			@NotEmpty(message = "email must not be empty") @Email(message = "must have a email format") String ownerEmail,
			@NotEmpty(message = "phone must not be empty") String ownerPhone,
			@NotEmpty(message = "password must not be empty") String password, List<Item> menu, List<Orders> orders) {
		super();
		this.name = name;
		this.ownerName = ownerName;
		this.ownerEmail = ownerEmail;
		this.ownerPhone = ownerPhone;
		this.password = password;
		this.menu = menu;
		this.orders = orders;
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

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Item> getMenu() {
		return menu;
	}

	public void setMenu(List<Item> menu) {
		this.menu = menu;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

}
