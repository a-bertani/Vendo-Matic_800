package com.techelevator.Items;

import java.math.BigDecimal;

public class Chip extends Item{
	public Chip(String slotIdentifier, String name, double price) {
		super(slotIdentifier, name, BigDecimal.valueOf(price));
		super.setType("Chip");
	}
@Override
	public String getMessage() {
		return "Crunch Crunch, Yum!";
	}
}
