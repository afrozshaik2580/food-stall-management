package com.afroz.foodstall.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "name must not be empty")
	private String name;
	@NotEmpty(message = "phone must not be empty")
	@Size(min = 10,message = "phone must be minimum 10 charracters")
	private String phone;
	@NotEmpty(message = "email must not be empty")
	@Email(message = "not acorrect format")
	private String email;
	@NotEmpty(message = "password must not be empty")
	@Size(min=4,message = "password must be minimum 5 characters")
	private String password;
	private double balance;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "cart", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<Item> cart = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<Orders> orders = new ArrayList<>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(@NotEmpty(message = "name must not be empty") String name,
			@NotEmpty(message = "phone must not be empty") @Size(min = 10, message = "phone must be minimum 10 charracters") String phone,
			@NotEmpty(message = "email must not be empty") @Email(message = "not acorrect format") String email,
			@NotEmpty(message = "password must not be empty") @Size(min = 4, message = "password must be minimum 5 characters") String password,
			double balance, List<Item> cart, List<Orders> orders) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.balance = balance;
		this.cart = cart;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Item> getCart() {
		return cart;
	}

	public void setCart(List<Item> cart) {
		this.cart = cart;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	
}
