package com.techelevator.view;

import com.techelevator.Item;

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

				if (Objects.equals(newItemArray[3], "Candy")){
					Item i = new Item(newItemArray[0], newItemArray[1], Double.parseDouble(newItemArray[2]), "Candy");
					i.setMessage("Munch Munch, Yum!");
					itemList.add(i);
				} else if (Objects.equals(newItemArray[3], "Drink")){
					Item i = new Item(newItemArray[0], newItemArray[1], Double.parseDouble(newItemArray[2]), "Drink");
					i.setMessage("Glug Glug, Yum!");
					itemList.add(i);
				} else if (Objects.equals(newItemArray[3], "Chip")) {
					Item i = new Item(newItemArray[0], newItemArray[1], Double.parseDouble(newItemArray[2]), "Chip");
					i.setMessage("Crunch Crunch, Yum!");
					itemList.add(i);
				} else if (Objects.equals(newItemArray[3], "Gum")) {
					Item i = new Item(newItemArray[0], newItemArray[1], Double.parseDouble(newItemArray[2]), "Gum");
					i.setMessage("Chew Chew, Yum!");
					itemList.add(i);
				}


			}

		} catch(Exception exception){
			System.out.println("Problem Loading Inventory List.  This Program will now close.");
		}

	}

}
