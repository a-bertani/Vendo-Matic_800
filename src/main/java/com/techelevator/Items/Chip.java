package com.techelevator.Items;

import java.math.BigDecimal;
import java.util.Stack;

public class Chip extends Item {
	static Stack<String> idToBeUsed = new Stack<>();
	static int nextStockId = 4;
	static int stockLimit= 10;
	public Chip(String slotIdentifier, String name, double price) {
		super(slotIdentifier, name, BigDecimal.valueOf(price), "Chip");
	}

@Override
	public String getMessage() {
		return "Crunch Crunch, Yum!";
	}

	public static String findNextId() {
		if(!idToBeUsed.isEmpty()){
			return "A" + idToBeUsed.pop();
		}
		if(nextStockId <= stockLimit) {
			String currentId = "A" + nextStockId;
			nextStockId++;
			return currentId;
		}
		return "No More Space Available to Add New Drink";
	}
}
