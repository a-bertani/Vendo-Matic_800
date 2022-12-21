package com.techelevator.Items;

import java.math.BigDecimal;
import java.util.Stack;

public class Chip extends Item {
	public static Stack<String> idToBeUsed = new Stack<>();
	private static int nextStockId = 4;
	private static int stockLimit= 10;
	public Chip(String slotIdentifier, String name, double price) {
		super(slotIdentifier, name, BigDecimal.valueOf(price), "Chip");
	}

@Override
	public String getMessage() {
		return "Crunch Crunch, Yum!";
	}

	public static String findNextId() {
		if(!idToBeUsed.isEmpty()){
			return idToBeUsed.pop();
		}
		if(nextStockId <= stockLimit) {
			String currentId = "A" + nextStockId;
			nextStockId++;
			return currentId;
		}
		return "No More Space Available to Add New Drink";
	}
}
