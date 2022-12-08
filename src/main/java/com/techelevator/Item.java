package com.techelevator;

public class Item {
	private final String slotIdentifier;
	private final String name;
	private final double price;

	private final String type;
	private final int stock;
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

	public double getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public int getStock() {
		return stock;
	}

	public Item(String slotIdentifier, String name, double price, String type) {
		this.slotIdentifier = slotIdentifier;
		this.name = name;
		this.price = price;
		this.type = type;
		this.stock = 5;
	}
}
