package com.techelevator.Items;

import java.math.BigDecimal;
import java.util.Stack;

//The beverage class was created to cover one type of item in the vending machine.
//It has implemented the methods that are provided from the super class.
public class Beverage extends Item {

	public static Stack<String> idToBeUsed = new Stack<>();
	private static int nextStockId = 5;
	private static int stockLimit= 10;

	public Beverage(String slotIdentifier, String name, double price) {
		super(slotIdentifier, name, BigDecimal.valueOf(price), "Drink");
	}

	@Override
	public String getMessage() {
		return "Glug Glug, Yum!";
	}


	public static String findNextId() {
		if(!idToBeUsed.isEmpty()){
			return idToBeUsed.pop();
		}
		if(nextStockId <= stockLimit) {
			String currentId = "C" + nextStockId;
			nextStockId++;
			return currentId;
		}
		return "No More Space Available to Add New Drink";
	}

}
