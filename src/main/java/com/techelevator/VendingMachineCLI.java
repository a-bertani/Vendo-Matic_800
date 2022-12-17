package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT ="Sales Report";

	private static final String MAIN_MENU_OPTION_ADMIN_MENU = "Admin Menu";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
														MAIN_MENU_OPTION_PURCHASE,
														MAIN_MENU_OPTION_EXIT,
														MAIN_MENU_OPTION_SALES_REPORT,
														MAIN_MENU_OPTION_ADMIN_MENU};
	private static final String PURCHASE_PROCESS_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_PROCESS_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_PROCESS_MENU_OPTION_FINISH_TRANSACTION ="Finish Transaction";
	private static final String[] PURCHASE_PROCESS_MENU_OPTIONS = { PURCHASE_PROCESS_MENU_OPTION_FEED_MONEY,
																	PURCHASE_PROCESS_MENU_OPTION_SELECT_PRODUCT,
																	PURCHASE_PROCESS_MENU_OPTION_FINISH_TRANSACTION};
	private final String ADMIN_MENU_ADD_ITEM = "Add New Item";
	private final String ADMIN_MENU_REMOVE_ITEM = "Remove Item";
	private final String ADMIN_MENU_CHANGE_PRICE = "Change Price";
	private final String ADMIN_MENU_CHANGE_PASSWORD = "Change Password";
	private final String ADMIN_MENU_EXIT_ADMIN_MENU = "Exit Admin Menu";
	private final String [] ADMIN_MENU_OPTIONS = { ADMIN_MENU_ADD_ITEM,
												   ADMIN_MENU_REMOVE_ITEM,
												   ADMIN_MENU_CHANGE_PRICE,
												   ADMIN_MENU_EXIT_ADMIN_MENU};


	private final Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		menu.readInventory(new File("vendingmachine.csv"));

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				menu.displayVendingMachineItems();
				menu.displayCurrentMoneyProvided();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				handlePurchaseProcessMenu(this.menu,choice);
			}else if(choice.equals(MAIN_MENU_OPTION_EXIT)){
				menu.exitMenu();
			}else if(choice.equals(MAIN_MENU_OPTION_SALES_REPORT)){
				menu.getSalesReport();
			} else if (choice.equals(MAIN_MENU_OPTION_ADMIN_MENU)) {
				handleAdminMenu(this.menu,choice);
			}
		}
	}

	public static void handlePurchaseProcessMenu(Menu menu, String choice){
		menu.displayCurrentMoneyProvided();
		choice = (String) menu.getChoiceFromOptions(PURCHASE_PROCESS_MENU_OPTIONS);
		switch (choice) {
			case PURCHASE_PROCESS_MENU_OPTION_FEED_MONEY:
				menu.feedMoneySelected();
				handlePurchaseProcessMenu(menu, choice);
				break;
			case PURCHASE_PROCESS_MENU_OPTION_SELECT_PRODUCT:
				menu.selectProductSelected();
				handlePurchaseProcessMenu(menu, choice);
				break;
			case PURCHASE_PROCESS_MENU_OPTION_FINISH_TRANSACTION:
				menu.finishTransactionSelected();
				break;
		}
	}

	private void handleAdminMenu(Menu menu, String choice) {
		System.out.println("\nWelcome Admin!\nVending Machine will update with changes and fully restock upon exit.\nThank you.");
		choice = (String) menu.getChoiceFromOptions(ADMIN_MENU_OPTIONS);
		switch (choice) {
			case ADMIN_MENU_ADD_ITEM:
				System.out.println("This has not been implemented yet.");
				break;
			case ADMIN_MENU_REMOVE_ITEM:
				System.out.println("This has not been implemented yet.");
				break;
			case ADMIN_MENU_CHANGE_PRICE:
				System.out.println("This has not been implemented yet.");
				break;
			case ADMIN_MENU_CHANGE_PASSWORD:
				System.out.println("This has not been implemented yet.");
				break;
			case ADMIN_MENU_EXIT_ADMIN_MENU:
				System.out.println("Changes have been implemented and items have been restocked.\nThank you.");
				menu.readInventory(new File("vendingmachine.csv"));
				break;
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
