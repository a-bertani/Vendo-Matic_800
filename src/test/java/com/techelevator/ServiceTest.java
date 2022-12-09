package com.techelevator;

import com.techelevator.view.Menu;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class ServiceTest extends TestCase {

    @Test
    public void testPurchaseItem() {
        Menu menu = new Menu(System.in, System.out);
        menu.readInventory(new File("vendingmachine.csv"));
        menu.service.setCurrentMoneyProvided(BigDecimal.valueOf(5.00));
        assertEquals("Potato Crisps 3.05 1.95", menu.service.purchaseItem("A1"));
    }
}