package com.techelevator.view;

import com.techelevator.*;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

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

		try{
			Scanner scan = new Scanner(file);

			while(scan.hasNextLine()){
				String[] newItemArray = scan.nextLine().split("\\|");

				switch (newItemArray[3]){
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
		} catch(Exception exception){
			System.out.println("Problem Loading Inventory List.  This Program will now close.");
		}
		service.setInventory(itemList);

	}

}
