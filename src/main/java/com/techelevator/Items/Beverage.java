package com.techelevator.Items;

import java.math.BigDecimal;
import java.util.Stack;

public class Beverage extends Item {

	static Stack<String> idToBeUsed = new Stack<>();
	static int nextStockId = 5;
	static int stockLimit= 10;

	public Beverage(String slotIdentifier, String name, double price) {
		super(slotIdentifier, name, BigDecimal.valueOf(price), "Drink");
	}

	@Override
	public String getMessage() {
		return "Glug Glug, Yum!";
	}


	public static String findNextId() {
		if(!idToBeUsed.isEmpty()){
			return "C" + idToBeUsed.pop();
		}
		if(nextStockId <= stockLimit) {
			String currentId = "C" + nextStockId;
			nextStockId++;
			return currentId;
		}
		return "No More Space Available to Add New Drink";
	}

}
