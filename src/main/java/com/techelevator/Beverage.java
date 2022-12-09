package com.techelevator;

import com.techelevator.Item;

public class Beverage extends Item {

	public Beverage(String slotIdentifier, String name, double price, String type) {
		super(slotIdentifier, name, price, type);
	}

@Override
	public String getMessage() {
		return "Glug Glug, Yum!";
	}

}
