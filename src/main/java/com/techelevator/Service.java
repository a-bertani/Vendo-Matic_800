package com.techelevator;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private Money money;
    private BigDecimal currentMoneyProvided = BigDecimal.valueOf(0.00);
    //ADD INVENTORY  public Inventory inventory;
     List<Item> inventory;
    Logger logger = new Logger();
    public String purchaseItem(String slotIdentifier) {
        //Converts inventory into Stream
       Item result= inventory.stream()
                .filter(item -> item.getSlotIdentifier().equals(slotIdentifier) )
                .findFirst()
                .orElse(null);
       return dispenseItem(result);
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

    public String dispenseItem(Item result) {
        if(result != null){
            if (result.getPrice().compareTo(currentMoneyProvided) <=0) {
                if (result.getStock() > 0){
                    setCurrentMoneyProvided(getCurrentMoneyProvided().subtract(result.getPrice()));
                    result.setStock(result.getStock() - 1);
                    logger.printLog(result.getName() + " " + result.getSlotIdentifier(),
                                    result.getPrice(),getCurrentMoneyProvided());
                    return result.getName() + " " + result.getPrice() + " " + currentMoneyProvided;
                }
            }else {
                return "Insufficient funds";
            }
        }
        return "Product code does not exist";
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
