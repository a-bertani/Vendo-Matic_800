package com.techelevator.view;

import com.techelevator.Items.*;
import com.techelevator.Services.Administrator;
import com.techelevator.Services.Service;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

//Menu handles all the display outputs to the VendingMachineCLI
public class Menu {
	private final PrintWriter out;
	private final Scanner in;
	private final Service service = new Service();
	private final Administrator administrator = new Administrator();

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
				if (options[i] == "Sales Report" || options[i] == "Admin Menu"){
					continue;
				}
				out.println(optionNum + ") " + options[i]);
			}
			out.print(System.lineSeparator() + "Please choose an option >>> ");
			out.flush();
		}

	//Accepts a file and converts lines in the file into Item Objects and adds them to and Item Object List
	public void readInventory (File file){
		List<Item> itemList = new ArrayList<Item>();

		if (file != null) {
			try {
				Scanner scan = new Scanner(file);

				while (scan.hasNextLine()) {

					String[] newItemArray = scan.nextLine().split("\\|");
					switch (newItemArray[3]) {
						case "Candy":
							Candy candy = new Candy(newItemArray[0], newItemArray[1], Double.parseDouble(newItemArray[2]));
							itemList.add(candy);
							break;
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
							break;
					}
				}
			} catch (Exception exception) {
				System.out.println("Problem Loading Inventory List.  This Program will now close.");
			}
			Collections.sort(itemList);
			service.setInventory(itemList);
		}
	}
	//  Displays item choices including type, id, name, price, & stock
	public void displayVendingMachineItems () {
		String line = "************************************************************\n";
		String separator = " | ";
		System.out.printf("%s********************** ITEMS FOR SALE **********************\n%s**** %5s%sID%s%18s%sPRICE%s%-5s ****\n",
				line, line, StringUtils.center("TYPE", 5), separator, separator,
				StringUtils.center("ITEM NAME", 18), separator, separator, StringUtils.center("STOCK", 8));
		//Converts inventory item list into a stream that sorts items by id
		service.getInventory().stream()
				.sorted()
				.forEach(x -> System.out.printf("**** %s%s%s%s%s%s$%.2f%s%s ****\n",
						StringUtils.center(x.getType(), 5), separator, x.getSlotIdentifier(), separator,
						StringUtils.center(x.getName(), 18), separator, x.getPrice(), separator,
						x.getStock() > 0 ? StringUtils.center(String.valueOf(x.getStock()), 8) : StringUtils.center("SOLD OUT", 8)));

			System.out.printf("%s%s", line, line);
	}

	public void getSalesReport () {
		BigDecimal totalSales = BigDecimal.valueOf(0.00);
		service.getInventory().forEach(item -> System.out.printf("%-19s| %s\n", item.getName(), (5 - item.getStock())));
		List<Item> itemsThatSold = service.getInventory().stream()
				.filter(item -> (5 - item.getStock()) > 0)
				.collect(Collectors.toList());
		for (Item item : itemsThatSold) {
			totalSales = totalSales.add(item.getPrice().multiply(BigDecimal.valueOf(5 - item.getStock())));
		}
		System.out.printf("\n**TOTAL SALES** $%.02f", totalSales);
	}
	public Service getService () {
		return service;
	}

	//collects the customer input of money and calls the service to handle transaction
	public void feedMoneySelected () {
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
	//collects the customer input of the item they want
	public void selectProductSelected () {
		displayVendingMachineItems();
		System.out.println("\nPlease Enter Item Id: ");
		String slotIdentifier = in.nextLine();
		System.out.println(service.purchaseItem(slotIdentifier));
	}
	//exits the purchase process menu
	public void finishTransactionSelected () {
		System.out.println("\nCurrent Money Provided: 0.00\n");
		System.out.println("Thank You For Your Service!");
		System.out.println(service.finishTransaction());
	}
	//displays customers provided money
	public void displayCurrentMoneyProvided () {
		System.out.printf("\nCurrent Money Provided: $%.02f\n", service.getCurrentMoneyProvided());
	}
	//exits the program
	public void exitMenu () {
		System.out.println("\nThank You! Please Come Again!");
		System.exit(0);
	}

	public void welcomeArt(){
		System.out.printf(
				"\n\t\t\t\t$$\\    $$\\                           $$\\                  $$\\      $$\\           $$\\     $$\\                  $$$$$$\\   $$$$$$\\   $$$$$$\\ \n" +
						"\t\t\t\t$$ |   $$ |                          $$ |                 $$$\\    $$$ |          $$ |    \\__|                $$  __$$\\ $$$ __$$\\ $$$ __$$\\\n" +
						"\t\t\t\t$$ |   $$ | $$$$$$\\  $$$$$$$\\   $$$$$$$ | $$$$$$\\         $$$$\\  $$$$ | $$$$$$\\$$$$$$\\   $$\\  $$$$$$$\\       $$ /  $$ |$$$$\\ $$ |$$$$\\ $$ |\n" +
						"\t\t\t\t\\$$\\  $$  |$$  __$$\\ $$  __$$\\ $$  __$$ |$$  __$$\\$$$$$$\\ $$\\$$\\$$ $$ | \\____$$\\_$$  _|  $$ |$$  _____|       $$$$$$  |$$\\$$\\$$ |$$\\$$\\$$ |\n" +
						"\t\t\t\t \\$$\\$$  / $$$$$$$$ |$$ |  $$ |$$ /  $$ |$$ /  $$ \\______|$$ \\$$$  $$ | $$$$$$$ |$$ |    $$ |$$ /            $$  __$$< $$ \\$$$$ |$$ \\$$$$ |\n" +
						"\t\t\t\t  \\$$$  /  $$   ____|$$ |  $$ |$$ |  $$ |$$ |  $$ |       $$ |\\$  /$$ |$$  __$$ |$$ |$$\\ $$ |$$ |            $$ /  $$ |$$ |\\$$$ |$$ |\\$$$ |\n" +
						"\t\t\t\t   \\$  /   \\$$$$$$$\\ $$ |  $$ |\\$$$$$$$ |\\$$$$$$  |       $$ | \\_/ $$ |\\$$$$$$$ |\\$$$$  |$$ |\\$$$$$$$\\       \\$$$$$$  |\\$$$$$$  /\\$$$$$$  /\n" +
						"\t\t\t\t    \\_/     \\_______|\\__|  \\__| \\_______| \\______/        \\__|     \\__| \\_______| \\____/ \\__| \\_______|       \\______/  \\______/  \\______/\n");
	}

	// ******************* ADMIN METHODS *******************
	// ******************* ADMIN METHODS *******************
	// ******************* ADMIN METHODS *******************

	public void welcomeAdmin() {
		System.out.println("\nWELCOME ADMIN!");
	}
	public boolean securityProtocol() {
		System.out.println("PLEASE ENTER PASSWORD: ");
		String input = in.nextLine();
		if (input.equals(administrator.getPASSWORD())) {
			System.out.println("\nACCESS GRANTED");
			return true;
		}
		System.out.println("\nACCESS DENIED");
		return false;
	}
	public void addItemSelected() {
		System.out.println("PLEASE ENTER ITEM TYPE: ");
		String type = in.nextLine();
		System.out.println("PLEASE ENTER ITEM NAME: ");
		String name = in.nextLine();
		System.out.println("PLEASE ENTER ITEM PRICE: ");
		String priceInput = in.nextLine();
		Double price = Double.parseDouble(priceInput);
		System.out.println(administrator.addItem(service, name, price, type));
	}

	public void removeItemSelected() {
		System.out.println("PLEASE ENTER ITEM ID TO REMOVE: ");
		String input = in.nextLine();
		System.out.println(administrator.removeItem(service, input));
	}

	public void changePriceSelected() {
		System.out.println("PLEASE ENTER ID OF WHICH ITEMS PRICE WILL CHANGE: ");
		String item = in.nextLine();
		System.out.println("PLEASE ENTER NEW PRICE: ");
		String input = in.nextLine();
		Double price = Double.parseDouble(input);
		System.out.println(administrator.changePrice(service, item, price));
	}

	public void changePasswordSelected() {
		System.out.println("PLEASE ENTER CURRENT PASSWORD: ");
		String currPassword = in.nextLine();
		String newPassword;
		if(currPassword.equals(administrator.getPASSWORD())) {
			System.out.println("PLEASE ENTER NEW PASSWORD: ");
			newPassword = in.nextLine();
			administrator.setPASSWORD(newPassword);
			System.out.println("\nPASSWORD CHANGED");
		} else {
			System.out.println("WRONG PASSWORD ENTERED");
		}
	}
	public void addStockSelected() {
		System.out.println("PLEASE ENTER ITEM ID YOU WILL ADD STOCK TO: ");
		String itemId = in.nextLine();
		System.out.println("PLEASE ENTER HOW MANY ITEMS YOU ADDED: ");
		String input = in.nextLine();
		Integer amountAdded = Integer.parseInt(input);
		System.out.println(administrator.addStock(service, itemId, amountAdded));
	}


	public void printAuditFile() {
		File auditFile = new File("Log.txt");
		try {
			Scanner scanner = new Scanner(auditFile);
			while (scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
		} catch (Exception e) {
			System.out.println("Audit file not found");
		}
	}

}
