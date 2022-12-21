package com.techelevator.Services;

import com.techelevator.Items.*;
import java.math.BigDecimal;
import java.util.List;

public class Administrator {
    private String PASSWORD = "1234";
    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
    public String getPASSWORD() {
        return PASSWORD;
    }

    public String addItem(Service service, String name, Double price, String type){
        String id = findNextIdForType(type);
        if (id.equals("Item Cannot Be Placed")) {
            return id;
        }
        //getting inventory
        List<Item> addItemList = service.getInventory();
        //creating new item
        switch (type) {
            case "Drink":
                addItemList.add(new Beverage(id,name,price));
                break;
            case "Candy":
                addItemList.add(new Candy(id,name,price));
                break;
            case "Chip":
                addItemList.add(new Chip(id,name,price));
                break;
            case "Gum":
                addItemList.add(new Gum(id,name,price));
                break;
        }
        //setting inventory with new item
        service.setInventory(addItemList);
        //saying where to place id
        return "\nPlease place item in id " + id;
    }


    public String findNextIdForType(String type) {
        if(type.equals("Drink")) {
            return Beverage.findNextId();
        } else if (type.equals("Candy")) {
            return Candy.findNextId();
        } else if (type.equals("Chip")) {
            return Chip.findNextId();
        } else if (type.equals("Gum")) {
            return Gum.findNextId();
        }
        return  "Item Cannot Be Placed";
    }

    public String removeItem(Service service, String id) {
        Item itemToRemove = service.getInventory().stream()
                .filter(item -> item.getSlotIdentifier().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
        List<Item> newInventory = service.getInventory();
        if(itemToRemove == null) {
            return "\nItem Cannot Be Found To Remove.";
        }
        pushToStack(id);
        newInventory.remove(itemToRemove);
        service.setInventory(newInventory);
        return "\nItem has been removed.";
    }

    public void pushToStack(String id) {
        if (id.startsWith("A")){
            Chip.idToBeUsed.push(id);
        } else if (id.startsWith("B")) {
            Candy.idToBeUsed.push(id);
        } else if (id.startsWith("C")) {
            Beverage.idToBeUsed.push(id);
        } else if (id.startsWith("D")) {
            Gum.idToBeUsed.push(id);
        }
    }

    public String changePrice(Service service, String id, Double price) {
        Item itemToChange = service.getInventory().stream()
                .filter(item -> item.getSlotIdentifier().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
        List<Item> newInventory = service.getInventory();
        if(itemToChange == null) {
            return "\nItem Cannot Be Found To Change Price";
        }
        itemToChange.setPrice(BigDecimal.valueOf(price));
        return "Items Price Has Been Changed";
    }

    public String addStock(Service service, String id, int inventoryAdded) {
        Item itemToChange = service.getInventory().stream()
                .filter(item -> item.getSlotIdentifier().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
        List<Item> newInventory = service.getInventory();
        if(itemToChange == null) {
            return "\nItem Cannot Be Found To Add Inventory";
        }
        if (itemToChange.getStock() + inventoryAdded > 5) {
            return "Inventory too high to complete.";
        }
        itemToChange.setStock(itemToChange.getStock() + inventoryAdded);
        return "Inventory has been added to item successfully";
    }

}
