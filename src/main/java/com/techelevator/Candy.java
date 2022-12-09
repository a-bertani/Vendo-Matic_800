package com.techelevator;

public class Candy extends Item {
	public Candy(String slotIdentifier, String name, double price, String type) {
		super(slotIdentifier, name, price, type);
	}
@Override
	public String getMessage() {
		return "Munch Munch, Yum!";
	}
}
