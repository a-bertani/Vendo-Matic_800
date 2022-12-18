package com.techelevator.Services;

import com.techelevator.Items.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Administrator {

    public String addItem(Service service, String name, Double price, String type){
        String id = findNextIdForType(type);
        if (id.equals("Item Cannot Be Placed")) {
            return id;
        }
        //getting inventory
        List<Item> addItemList = service.getInventory();
        //creating new item
        addItemList.add(new Item(id,name,BigDecimal.valueOf(price),type));
        //setting inventory with new item
        service.setInventory(addItemList);
        //saying where to place id
        return "\nPlease place item in id " + id;
    }

    public String findNextIdForType(String type) {
        if(type.equalsIgnoreCase("Drink")) {
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
}
