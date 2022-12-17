package com.techelevator.Items;

import java.math.BigDecimal;

public class Item implements Comparable<Item>{
	private final String slotIdentifier;
	private final String name;
	private final BigDecimal price;

	private String type;
	private int stock;

	public String getMessage() {
		return "";
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setType(String type) {this.type = type;}
	public String getType() {
		return type;
	}


	public Item(String slotIdentifier, String name, BigDecimal price, String type) {
		this.slotIdentifier = slotIdentifier;
		this.name = name;
		this.price = price;
		this.type = type;
		this.stock = 5;
	}
	@Override
	public int compareTo(Item o) {
		int compareInt = this.getSlotIdentifier().compareTo(o.slotIdentifier);
		return Integer.compare(compareInt, 0);
	}
}
