package com.techelevator;

import com.techelevator.view.Menu;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;

public class ServiceTest extends TestCase {
    @Test
    public void testSinglePurchaseItem() {
        Menu menu = new Menu(System.in, System.out);
        menu.readInventory(new File("vendingmachine.csv"));
        menu.getService().setCurrentMoneyProvided(BigDecimal.valueOf(5.00));
        assertEquals("Stackers 1.45 3.55", menu.getService().purchaseItem("A2"));
    }

    @Test
    public void testMultiplePurchaseItem() {
        Menu menu = new Menu(System.in, System.out);
        menu.readInventory(new File("vendingmachine.csv"));
        menu.getService().setCurrentMoneyProvided(BigDecimal.valueOf(5.00));
        assertEquals("Potato Crisps 3.05 1.95", menu.getService().purchaseItem("A1"));
        assertEquals("Stackers 1.45 0.50", menu.getService().purchaseItem("A2"));
    }

    @Test
    public void testInsufficientFundsPurchaseItem() {
        Menu menu = new Menu(System.in, System.out);
        menu.readInventory(new File("vendingmachine.csv"));
        menu.getService().setCurrentMoneyProvided(BigDecimal.valueOf(1.00));
        assertEquals("Insufficient funds", menu.getService().purchaseItem("A1"));
    }

    @Test
    public void testOutOfStockPurchaseItem() {
        Menu menu = new Menu(System.in, System.out);
        menu.readInventory(new File("vendingmachine.csv"));
        menu.getService().feedMoney(BigDecimal.valueOf(20.00));
        assertEquals("Potato Crisps 3.05 16.95", menu.getService().purchaseItem("A1"));
        assertEquals("Potato Crisps 3.05 13.90", menu.getService().purchaseItem("A1"));
        assertEquals("Potato Crisps 3.05 10.85", menu.getService().purchaseItem("A1"));
        assertEquals("Potato Crisps 3.05 7.80", menu.getService().purchaseItem("A1"));
        assertEquals("Potato Crisps 3.05 4.75", menu.getService().purchaseItem("A1"));
        assertEquals("Potato Crisps is out of stock", menu.getService().purchaseItem("A1"));
    }

    @Test
    public void testProductCodeDoesNotExistPurchaseItem() {
        Menu menu = new Menu(System.in, System.out);
        menu.readInventory(new File("vendingmachine.csv"));
        menu.getService().setCurrentMoneyProvided(BigDecimal.valueOf(10.00));
        assertEquals("Product code does not exist", menu.getService().purchaseItem("E1"));
    }

    @Test
    public void testFeedMoney() {
        Menu menu = new Menu(System.in, System.out);
        menu.readInventory(new File("vendingmachine.csv"));
        menu.getService().feedMoney(BigDecimal.valueOf(5.00));
        menu.getService().feedMoney(BigDecimal.valueOf(5.00));
//        assertEquals("FEED MONEY: $5.00 $5.00", menu.service.feedMoney(BigDecimal.valueOf(5.00)));
    }

    public void testFinishTransaction() {
        Menu menu = new Menu(System.in, System.out);
        menu.readInventory(new File("vendingmachine.csv"));
        menu.getService().feedMoney(BigDecimal.valueOf(5.00));
        menu.getService().purchaseItem("A1");
        System.out.println( menu.getService().finishTransaction());
    }
}