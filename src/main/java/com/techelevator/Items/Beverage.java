package com.techelevator.Items;

import java.math.BigDecimal;

public class Beverage extends Item {

	public Beverage(String slotIdentifier, String name, double price) {
		super(slotIdentifier, name, BigDecimal.valueOf(price));
		super.setType("Drink");
	}

@Override
	public String getMessage() {
		return "Glug Glug, Yum!";
	}

}
