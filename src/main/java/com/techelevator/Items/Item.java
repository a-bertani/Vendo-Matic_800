package com.techelevator.Items;

import java.math.BigDecimal;

//Item class is the super class responsible for hosting the methods that are utilized by
// children classes: Candy, Chips, Beverages, & Gum
public class Item implements Comparable<Item>{
	private final String name;
	private final String slotIdentifier;
	private BigDecimal price;
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

	public void setPrice(BigDecimal price) {
		this.price = price;
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
