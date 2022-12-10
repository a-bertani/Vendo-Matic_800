package com.techelevator.Items;

import java.math.BigDecimal;

public class Gum extends Item{
	public Gum(String slotIdentifier, String name, double price, String type) {
		super(slotIdentifier, name, BigDecimal.valueOf(price), type);
	}

	@Override
	public String getMessage() {
		return "Chew Chew, Yum!";
	}
}
