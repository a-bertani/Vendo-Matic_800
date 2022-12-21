package com.techelevator.Services;

import com.techelevator.view.Menu;
import junit.framework.TestCase;
import org.junit.Test;
import java.io.File;

public class AdministratorTest extends TestCase {

    @Test
    public void testSuccessfullAddItem() {
        Administrator administrator = new Administrator();
        Menu menu = new Menu(System.in, System.out);
        Service service = new Service();
        menu.readInventory(new File("vendingmachine.csv"));
        service.setInventory(menu.getService().getInventory());
        String name = "Lays";
        Double price = 3.00;
        String type = "Chip";
        assertEquals("\nPlease place item in id A4",
                    administrator.addItem(service,name,price,type));
    }

    @Test
    public void testFailureWrongTypeAddItem() {
        Administrator administrator = new Administrator();
        Menu menu = new Menu(System.in, System.out);
        Service service = new Service();
        menu.readInventory(new File("vendingmachine.csv"));
        service.setInventory(menu.getService().getInventory());
        String name = "Lays";
        Double price = 3.00;
        String type = "Chipp";
        assertEquals("Item Cannot Be Placed",
                administrator.addItem(service,name,price,type));
    }

    @Test
    public void testNoInputNullAddItem() {
        Administrator administrator = new Administrator();
        Menu menu = new Menu(System.in, System.out);
        Service service = new Service();
        menu.readInventory(new File("vendingmachine.csv"));
        service.setInventory(menu.getService().getInventory());
        String name = "";
        Double price = null;
        String type = "";
        assertEquals("Item Cannot Be Placed",
                administrator.addItem(service,name,price,type));
    }

    @Test
    public void testSuccessfulFindNextIdForType() {
        Administrator administrator = new Administrator();
        Menu menu = new Menu(System.in, System.out);
        Service service = new Service();
        menu.readInventory(new File("vendingmachine.csv"));
        service.setInventory(menu.getService().getInventory());
        String type = "Drink";
        assertEquals("C5", administrator.findNextIdForType(type));
    }

    @Test
    public void testFailureFindNextIdForType() {
        Administrator administrator = new Administrator();
        Menu menu = new Menu(System.in, System.out);
        Service service = new Service();
        menu.readInventory(new File("vendingmachine.csv"));
        service.setInventory(menu.getService().getInventory());
        String type = "Drinkk";
        assertEquals("Item Cannot Be Placed", administrator.findNextIdForType(type));
    }

    @Test
    public void testFailureNoInputFindNextIdForType() {
        Administrator administrator = new Administrator();
        Menu menu = new Menu(System.in, System.out);
        Service service = new Service();
        menu.readInventory(new File("vendingmachine.csv"));
        service.setInventory(menu.getService().getInventory());
        String type = "";
        assertEquals("Item Cannot Be Placed", administrator.findNextIdForType(type));
    }

    @Test
    public void testRemoveItem() {
        Administrator administrator = new Administrator();
        Menu menu = new Menu(System.in, System.out);
        Service service = new Service();
        menu.readInventory(new File("vendingmachine.csv"));
        service.setInventory(menu.getService().getInventory());
        String id = "A1";
        assertEquals("\nItem has been removed.",administrator.removeItem(service,id));
    }

    @Test
    public void testFailureRemoveItem() {
        Administrator administrator = new Administrator();
        Menu menu = new Menu(System.in, System.out);
        Service service = new Service();
        menu.readInventory(new File("vendingmachine.csv"));
        service.setInventory(menu.getService().getInventory());
        String id = "Q4";
        assertEquals("\nItem Cannot Be Found To Remove.",administrator.removeItem(service,id));
    }

    @Test
    public void testChangePrice() {
        Administrator administrator = new Administrator();
        Menu menu = new Menu(System.in, System.out);
        Service service = new Service();
        menu.readInventory(new File("vendingmachine.csv"));
        service.setInventory(menu.getService().getInventory());
        String id = "A1";
        Double price = 4.00;
        assertEquals("Items Price Has Been Changed", administrator.changePrice(service, id, price));
    }

    @Test
    public void testEdgeCaseChangePrice() {
        Administrator administrator = new Administrator();
        Menu menu = new Menu(System.in, System.out);
        Service service = new Service();
        menu.readInventory(new File("vendingmachine.csv"));
        service.setInventory(menu.getService().getInventory());
        String id = "Q1";
        Double price = 4.00;
        assertEquals("\nItem Cannot Be Found To Change Price", administrator.changePrice(service, id, price));
    }

    @Test
    public void testNoInputChangePrice() {
        Administrator administrator = new Administrator();
        Menu menu = new Menu(System.in, System.out);
        Service service = new Service();
        menu.readInventory(new File("vendingmachine.csv"));
        service.setInventory(menu.getService().getInventory());
        String id = "";
        Double price = null;
        assertEquals("\nItem Cannot Be Found To Change Price", administrator.changePrice(service, id, price));
    }
    @Test
    public void testStockTooHighAddStock() {
        Administrator administrator = new Administrator();
        Menu menu = new Menu(System.in, System.out);
        Service service = new Service();
        menu.readInventory(new File("vendingmachine.csv"));
        service.setInventory(menu.getService().getInventory());
        String id = "A1";
        int inventoryAdded = 2;
        assertEquals("Inventory too high to complete.", administrator.addStock(service, id, inventoryAdded));
    }
    @Test
    public void testSuccessfullAddStock() {
        Administrator administrator = new Administrator();
        Menu menu = new Menu(System.in, System.out);
        Service service = new Service();
        menu.readInventory(new File("vendingmachine.csv"));
        service.setInventory(menu.getService().getInventory());
        service.purchaseItem("A1");
        String id = "A1";
        int inventoryAdded = 1;
        assertEquals("Inventory too high to complete.", administrator.addStock(service, id, inventoryAdded));
    }

    @Test
    public void testFailureAddStock() {
        Administrator administrator = new Administrator();
        Menu menu = new Menu(System.in, System.out);
        Service service = new Service();
        menu.readInventory(new File("vendingmachine.csv"));
        service.setInventory(menu.getService().getInventory());
        String id = "Q1";
        int inventoryAdded = 1;
        assertEquals("\nItem Cannot Be Found To Add Inventory", administrator.addStock(service, id, inventoryAdded));
    }

    @Test
    public void testNoInputAddStock() {
        Administrator administrator = new Administrator();
        Menu menu = new Menu(System.in, System.out);
        Service service = new Service();
        menu.readInventory(new File("vendingmachine.csv"));
        service.setInventory(menu.getService().getInventory());
        String id = "";
        int inventoryAdded = 1;
        assertEquals("\nItem Cannot Be Found To Add Inventory", administrator.addStock(service, id, inventoryAdded));
    }
}