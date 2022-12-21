package com.techelevator.Items;

import java.math.BigDecimal;
import java.util.Stack;

public class Gum extends Item{
	public static Stack<String> idToBeUsed = new Stack<>();
	private static int nextStockId = 5;
	private static int stockLimit= 10;
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
