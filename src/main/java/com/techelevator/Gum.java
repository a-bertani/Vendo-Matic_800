package com.techelevator;

public class Gum extends Item{
	public Gum(String slotIdentifier, String name, double price, String type) {
		super(slotIdentifier, name, price, type);
	}

	@Override
	public String getMessage() {
		return "Chew Chew, Yum!";
	}
}
