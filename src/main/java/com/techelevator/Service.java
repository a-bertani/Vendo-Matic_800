package com.techelevator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private static final BigDecimal NICKEL = BigDecimal.valueOf(.05);
    private static final BigDecimal DIME = BigDecimal.valueOf(.10);
    private static final BigDecimal DOLLAR = BigDecimal.valueOf(1.00);
    private static final BigDecimal FIVE = BigDecimal.valueOf(5.00);
    private static final BigDecimal TEN = BigDecimal.valueOf(10.00);
    private static final BigDecimal TWENTY = BigDecimal.valueOf(20.00);
    private static BigDecimal currentMoneyProvided = BigDecimal.valueOf(0.00);
    //ADD INVENTORY  public Inventory inventory;
     List<Item> inventory;

    public String purchaseItem(String slotIdentifier) {
        //Converts inventory into Stream
        //Filters stream by slotIdentifier
        //Checks if currentMoneyProvided >= itemPrice
        //returns Logs transaction using printLog()
        return "";
    }

    public String feedMoney() {
        //updates currentMoneyProvided
        //returns Log transaction using printLog()
        return "";
    }

    public void finishTransaction() {
        //gives change back to customer (using smallest amount of coins possible)
        //updates currentMoneyProvided
        //returns Log transaction using printLog()
    }

    public static BigDecimal getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }

    public static void setCurrentMoneyProvided(BigDecimal currentMoneyProvided) {
        Service.currentMoneyProvided = currentMoneyProvided;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }
}
