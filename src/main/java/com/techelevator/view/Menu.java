package com.techelevator.view;

import com.techelevator.Items.*;
import com.techelevator.Services.Service;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class Menu {

	private final PrintWriter out;
	private final Scanner in;

	private final Service service = new Service();

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < 3; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	//Accepts a file and converts lines in the file into Item Objects and adds them to and Item Object List
	public void readInventory(File file){

		List<Item> itemList = new ArrayList<Item>();

		if(file != null) {
			try {
				Scanner scan = new Scanner(file);

				while (scan.hasNextLine()) {
					String[] newItemArray = scan.nextLine().split("\\|");

					switch (newItemArray[3]) {
						case "Candy":
							Candy candy = new Candy(newItemArray[0], newItemArray[1], Double.parseDouble(newItemArray[2]));
							itemList.add(candy);
						case "Drink":
							Beverage beverage = new Beverage(newItemArray[0], newItemArray[1], Double.parseDouble(newItemArray[2]));
							itemList.add(beverage);
							break;
						case "Chip":
							Chip chip = new Chip(newItemArray[0], newItemArray[1], Double.parseDouble(newItemArray[2]));
							itemList.add(chip);
							break;
						case "Gum":
							Gum gum = new Gum(newItemArray[0], newItemArray[1], Double.parseDouble(newItemArray[2]));
							itemList.add(gum);
					}

				}
			} catch (Exception exception) {
				System.out.println("Problem Loading Inventory List.  This Program will now close.");
			}
			service.setInventory(itemList);
		}

	}
//  Displays item choices including type,
public void displayVendingMachineItems() {
	String line = "*********************************************************\n";
	String separator = " | ";
	System.out.printf("%s******************** ITEMS FOR SALE *********************\n%s**** %5s%sID%s%18s%sPRICE%s%-5s ****\n",
			line, line, StringUtils.center("TYPE", 5), separator, separator, StringUtils.center("ITEM NAME", 18), separator, separator, "STOCK");

	if (service.getInventory() != null) {
		for (Item x : service.getInventory()) {
			System.out.printf("**** %s%s%s%s%s%s$%.2f%s%s ****\n",
					StringUtils.center(x.getType(), 5), separator, x.getSlotIdentifier(), separator, StringUtils.center(x.getName(), 18),
					separator, x.getPrice(), separator, StringUtils.center(String.valueOf(x.getStock()), 5));
		}

		System.out.printf("%s%s", line, line);
	}
}

	public void getSalesReport() {
		BigDecimal totalSales = BigDecimal.valueOf(0.00);
		service.getInventory().forEach(item -> System.out.printf("%-19s| %s\n",item.getName(),(5 - item.getStock())));
		List<Item> itemsThatSold = service.getInventory().stream()
				.filter(item -> (5 - item.getStock()) > 0)
				.collect(Collectors.toList());
		for (Item item : itemsThatSold) {
			totalSales = totalSales.add(item.getPrice().multiply(BigDecimal.valueOf(5 - item.getStock())));
		}
		System.out.printf("\n**TOTAL SALES** $%s", totalSales);
	}
	public Service getService() {
		return service;
	}

	public void feedMoneySelected() {
		System.out.println("\nPlease Enter Whole Dollar Amount To Feed: ");
		String response = "";
		Double amount;
		try {
			response = in.nextLine();
			amount = Double.parseDouble(response);
			System.out.println(service.feedMoney(BigDecimal.valueOf(amount)));
		} catch (NumberFormatException e) {
			System.out.println("Please Enter A Real Number");
		}
	}
	public void selectProductSelected(){
		System.out.println("\nPlease Enter Item Id: ");
		String slotIdentifier = in.nextLine();
		System.out.println(service.purchaseItem(slotIdentifier));
	}
	public void finishTransactionSelected(){
		System.out.println("\nCurrent Money Provided: 0.00\n");
		System.out.println("Thank You For Your Service!");
		System.out.println(service.finishTransaction());
	}
	public void displayCurrentMoneyProvided(){
		System.out.printf("\nCurrent Money Provided: $%.02f\n", service.getCurrentMoneyProvided());
	}
	public void exitMenu() {
		System.exit(0);
	}
}
