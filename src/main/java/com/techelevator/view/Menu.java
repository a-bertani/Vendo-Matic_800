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

public class Menu {

	private final PrintWriter out;
	private final Scanner in;

	private Service service = new Service();

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
		for (int i = 0; i < options.length; i++) {
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
							Candy candy = new Candy(newItemArray[0], newItemArray[1], Double.parseDouble(newItemArray[2]), "Candy");
							itemList.add(candy);
							break;
						case "Drink":
							Beverage beverage = new Beverage(newItemArray[0], newItemArray[1], Double.parseDouble(newItemArray[2]), "Drink");
							itemList.add(beverage);
							break;
						case "Chip":
							Chip chip = new Chip(newItemArray[0], newItemArray[1], Double.parseDouble(newItemArray[2]), "Chip");
							itemList.add(chip);
							break;
						case "Gum":
							Gum gum = new Gum(newItemArray[0], newItemArray[1], Double.parseDouble(newItemArray[2]), "Gum");
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
		String starter = "*********************************************************";
		String begin = "****";
		String end = "****";
		String separator = " | ";
		System.out.println(starter + "\n******************** ITEMS FOR SALE *********************\n" + starter);
		System.out.println(begin + " TYPE  | ID |      ITEM NAME     | PRICE | STOCK " + end);
		if (service.getInventory() != null) {
			for (Item x : service.getInventory()) {
				int n = 18 - (x.getName().length());
				char[] spaces = new char[n];
				Arrays.fill(spaces, ' ');

				int o = 5 - (x.getType().length());
				char[] typeSpaces = new char[o];
				Arrays.fill(typeSpaces, ' ');


				System.out.print(begin + " " + x.getType() + new String(typeSpaces) + " | " + x.getSlotIdentifier() + separator + x.getName() + new String(spaces) + separator + "$");
				System.out.printf("%.2f", x.getPrice());
				System.out.print(separator + "  " + x.getStock() + "   " + end + "\n");
			}
			System.out.print(starter + "\n");

			System.out.print(starter);
		}
	}
<<<<<<< HEAD
=======


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

>>>>>>> 94c8a417a659023094d17f8a4ed709112b05fb6c
	public Service getService() {
		return service;
	}
}
