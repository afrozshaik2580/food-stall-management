package com.afroz.foodstall.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

enum Status{PREPARING, COMPLETED}


@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Stall stall;
	
	private String username;
	private String mobile;
	private Status status;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "order_items", joinColumns = @JoinColumn(name="order_id"),inverseJoinColumns = @JoinColumn(name="item_id"))
	private List<Item> items = new ArrayList<>();

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(User user, Stall stall, List<Item> items) {
		super();
		this.user = user;
		this.stall = stall;
		this.items = items;
		this.username=user.getName();
		this.mobile=user.getPhone();
		this.status=status.PREPARING;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Stall getStall() {
		return stall;
	}

	public void setStall(Stall stall) {
		this.stall = stall;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(int val) {
		Status newStatus = null;
		if(val==1){
			newStatus=Status.COMPLETED;
		}
		this.status = newStatus;
	}

}
