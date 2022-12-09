package com.techelevator;

public class Chip extends Item{
	public Chip(String slotIdentifier, String name, double price, String type) {
		super(slotIdentifier, name, price, type);
	}
@Override
	public String getMessage() {
		return "Crunch Crunch, Yum!";
	}
}
