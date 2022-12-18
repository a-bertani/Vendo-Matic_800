package com.techelevator.Items;

import java.math.BigDecimal;
import java.util.Stack;

public class Gum extends Item{
	static int nextStockId = 5;
	static int stockLimit= 10;
	public static Stack<String> idToBeUsed = new Stack<>();
	public Gum(String slotIdentifier, String name, double price) {
		super(slotIdentifier, name, BigDecimal.valueOf(price), "Gum");
	}

	@Override
	public String getMessage() {
		return "Chew Chew, Yum!";
	}

	public static String findNextId() {
		if(!idToBeUsed.isEmpty()){
			return idToBeUsed.pop();
		}
		if(nextStockId <= stockLimit) {
			String currentId = "D" + nextStockId;
			nextStockId++;
			return currentId;
		}
		return "No More Space Available to Add New Drink";
	}
}
