package com.techelevator.Items;

import java.math.BigDecimal;

public class Beverage extends Item {

	public Beverage(String slotIdentifier, String name, double price, String type) {
		super(slotIdentifier, name, BigDecimal.valueOf(price), type);
	}

@Override
	public String getMessage() {
		return "Glug Glug, Yum!";
	}

}
