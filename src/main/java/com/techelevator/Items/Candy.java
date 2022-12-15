package com.techelevator.Items;

import java.math.BigDecimal;

public class Candy extends Item {
	public Candy(String slotIdentifier, String name, double price) {
		super(slotIdentifier, name, BigDecimal.valueOf(price), "Candy");
	}
@Override
	public String getMessage() {
		return "Munch Munch, Yum!";
	}
}
