package com.techelevator.Items;

import java.math.BigDecimal;
import java.util.Stack;

public class Candy extends Item {
	public static Stack<String> idToBeUsed = new Stack<>();
	private static int nextStockId = 5;
	private static int stockLimit= 10;
	public Candy(String slotIdentifier, String name, double price) {
		super(slotIdentifier, name, BigDecimal.valueOf(price), "Candy");
	}
@Override
	public String getMessage() {
		return "Munch Munch, Yum!";
	}

	public static String findNextId() {
		if(!idToBeUsed.isEmpty()){
			return idToBeUsed.pop();
		}
		if(nextStockId <= stockLimit) {
			String currentId = "B" + nextStockId;
			nextStockId++;
			return currentId;
		}
		return "No More Space Available to Add New Drink";
	}

}
