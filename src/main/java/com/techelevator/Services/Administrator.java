package com.techelevator.Services;

import com.techelevator.Items.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

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
}
