package com.techelevator.Services;


import com.techelevator.Items.Item;
import com.techelevator.Logger.Logger;

import java.math.BigDecimal;
import java.util.List;
/*
The service class is responsible for handling all transactions
 */
public class Service {
    private final Money money = new Money();
    private BigDecimal currentMoneyProvided = BigDecimal.valueOf(0.00);
    private List<Item> inventory;
    private final Logger logger = new Logger();
    /*
    The purchaseItem method is responsible for checking the validity of a purchase
     before dispensing the item.
     @param String slotIdentifier uses slot identifier to find item
     @return String shows the result of the attempt to purchase item
     */
    public String purchaseItem(String slotIdentifier) {
       Item result= inventory.stream()
                .filter(item -> item.getSlotIdentifier().equals(slotIdentifier) )
                .findFirst()
                .orElse(null);
       return dispenseItem(result);
    }
    /*
    The feedMoney method checks to see if the amount to add is equivalent to a valid
    money amount and also if the amount is >= 1 dollar
    If it is valid we add the amount to the currentMoneyProvided
    @param amountToAdd potential amount to add
    @return void
     */
    public void feedMoney(BigDecimal amountToAdd) {
        BigDecimal amount = money.moneyList.stream()
                .filter(a -> a.equals(amountToAdd))
                .findFirst()
                .orElse(null);

        if (amount == null || amountToAdd.compareTo(BigDecimal.ONE) <= 0 ){
            throw new IllegalArgumentException("Money must be a whole dollar amount");
        }

        currentMoneyProvided = currentMoneyProvided.add(amount);
        logger.printLog("FEED MONEY: ", amountToAdd, getCurrentMoneyProvided() );
    }

    /*
    Allows the customer to complete the transaction and receive any remaining change
    through calling the getChange method
    @return String result of the getChange method
    @see getChange()
     */
    public String finishTransaction() {
        logger.printLog("GIVE CHANGE: ", getCurrentMoneyProvided(), BigDecimal.ZERO);
        return getChange();
    }

    public String dispenseItem(Item result) {
        if(result != null){
            if (result.getPrice().compareTo(currentMoneyProvided) <=0) {
                if (result.getStock() > 0){
                    setCurrentMoneyProvided(getCurrentMoneyProvided().subtract(result.getPrice()));
                    result.setStock(result.getStock() - 1);
                    logger.printLog(result.getName() + " " + result.getSlotIdentifier(),
                                    result.getPrice(),getCurrentMoneyProvided()) ;
                    return result.getName() + " " + result.getPrice() + " " + currentMoneyProvided + "\n" + result.getMessage();
                } else {
                    return result.getName() + " is out of stock";
                }
            }else {
                return "Insufficient funds";
            }
        }
        return "Product code does not exist";
    }
    /*
    The get change uses the current money provided and calculates the change using
    the smallest amount of coins possible
    @return String lists the amount of quarters, dimes, nickels, provided in the change
    */
    public String getChange(){
        int amtOfQuarters = 0;
        int amtOfDimes = 0;
        int amtOfNickels = 0;
        while (getCurrentMoneyProvided().compareTo(BigDecimal.ZERO) > 0){
            if (getCurrentMoneyProvided().min(BigDecimal.valueOf(.25)).compareTo(BigDecimal.valueOf(.25))==0){
                amtOfQuarters++;
                currentMoneyProvided = currentMoneyProvided.subtract(BigDecimal.valueOf(.25));
            }    else if (getCurrentMoneyProvided().min(BigDecimal.valueOf(.1)).compareTo(BigDecimal.valueOf(.1))==0){
                amtOfDimes++;
                currentMoneyProvided = currentMoneyProvided.subtract(BigDecimal.valueOf(.1));
            }    else if (getCurrentMoneyProvided().min(BigDecimal.valueOf(.05)).compareTo(BigDecimal.valueOf(.05))==0){
                amtOfNickels++;
                currentMoneyProvided = currentMoneyProvided.subtract(BigDecimal.valueOf(.05));
            }
        }
        return String.format("Quarters: %d\n" +
                             "Dimes: %d\n" +
                             "Nickels: %d\n", amtOfQuarters, amtOfDimes, amtOfNickels);
    }
    public BigDecimal getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }

    public void setCurrentMoneyProvided(BigDecimal currentMoneyProvided) {
        this.currentMoneyProvided = currentMoneyProvided;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }
}
