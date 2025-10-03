package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	@DecimalMin(value="0",message="Quantity cannot be less than zero")
	private int quantity;
	
	@Column(nullable=false)
	@DecimalMin(value="0",message="Price cannot be less than zero")
	private double price;
	
	@Column(nullable=false)
	private String category;
	
	public Product() {
		
	}

	public Product(Long id, String name, int quantity, double price,String category) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.category=category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}