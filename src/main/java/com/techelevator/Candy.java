package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Item {
	public Candy(String slotIdentifier, String name, double price, String type) {
		super(slotIdentifier, name, BigDecimal.valueOf(price), type);
	}
@Override
	public String getMessage() {
		return "Munch Munch, Yum!";
	}
}
