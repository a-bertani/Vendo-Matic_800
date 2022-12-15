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
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
														MAIN_MENU_OPTION_PURCHASE,
														MAIN_MENU_OPTION_EXIT,
														MAIN_MENU_OPTION_SALES_REPORT};
	private static final String PURCHASE_PROCESS_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_PROCESS_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_PROCESS_MENU_OPTION_FINISH_TRANSACTION ="Finish Transaction";
	private static final String[] PURCHASE_PROCESS_MENU_OPTIONS = { PURCHASE_PROCESS_MENU_OPTION_FEED_MONEY,
																	PURCHASE_PROCESS_MENU_OPTION_SELECT_PRODUCT,
																	PURCHASE_PROCESS_MENU_OPTION_FINISH_TRANSACTION};
	private Menu menu;

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
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
