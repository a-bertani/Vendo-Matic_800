package com.techelevator;

import java.math.BigDecimal;

public class Item {
	private final String slotIdentifier;
	private final String name;
	private final BigDecimal price;

	private final String type;
	private int stock;
	private String message = "";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSlotIdentifier() {
		return slotIdentifier;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Item(String slotIdentifier, String name, BigDecimal price, String type) {
		this.slotIdentifier = slotIdentifier;
		this.name = name;
		this.price = price;
		this.type = type;
		this.stock = 5;
	}
}
