
/*	NAME OF PROJECT: Online Grocery Shopping
 *  INCLUDED FILES: GroceryHashTable.java, Item.java, UserShopping.java, DuplicateKeyException.java, 
 *  				IllegalNullKeyException.java, KeyNotFoundException.java
 *  AUTHOR: Anjali Gali
 *  
 *  BEGAN: 3/16/2020
 *  ENDED: 3/22/2020
 *  
 *  Uses the java.util.* option to use all available data types in java.util.				
 * 	
 *  ABOUT: The Online Grocery Shopping is a response to the COVID-19 pandemic, which 
 *  resulted in a national quarantine response. Because of this quarantine, 
 *  many people were in a position where they did not have access to fresh food 
 *  and groceries. Since the demand for these items was so high, many stores 
 *  ran out of food and water very quickly and were unable to purchase these items
 *  at all, especially hand sanitizer, face masks, soap, toilet paper, and most fresh food
 *  importantly, fresh food. 	
 *  
 *  This is an online project that combats the demand for fresh produce and supplies, while
 *  also abiding by the quarantine. A user can choose certain items from this online grocery
 *  and purchase them as required, with the intention of these items being delivered to their 
 *  house. Users can then ship or have these items delivered to them, or ship these items to 
 *  the donation service.
 *  
 *  This project is still in its initial stages. I hope to be able to implement a User Interface 
 *  for this project, so that users can use it on their phones. I would like to implement a 
 *  donation service where users may also opt to donate items using the donate option.I would also 
 *  like to implement this as a delivery service where addresses may be stored in a PostGresSQL 
 *  Database.
 *  
 *  * * * * * * * * * * * CREDITING OUTSIDE HELP
 *  Idea: Anjali Gali
 *  
 *  In-Person Sources: NONE
 *  
 *  Online Sources: NONE 
 *  
 * Copyright Anjali Gali
 */

import java.util.*;

/**
 * The User shopping is a shopping application that represents the items a user
 * orders at a store. Since it is from the users perspective, the User also has
 * the opportunity to donate items, which are then inserted into Grocery Store
 * 
 * @author Anjali Gali
 */

public class UserShopping extends GroceryHashTable {
	// These are the static constant declarations.
	private static final int USER_SELECTION = 2; // The starting capacity of the hashtable.
	private static final double LOAD_FACTOR_THRESHOLD = 0.75; // The starting loadfactor threshold.

	static GroceryHashTable GROCERY_ITEMS; // Contains the items that are available in the grocery.
											// Making this variable a static variable is essential to
											// properly access the grocery items.
	private static final int MAXSIZE = 15; // The user may only order a minimum of 15 items at a time.

	/**
	 * Insert Items into the GROCERY_ITEMS.
	 * 
	 * @param hashtable The hash table into which items are to be inserted. These
	 *                  are just a sample of the items that can be created and
	 *                  inserted, and we can expand on the program to insert many
	 *                  more items.
	 * @return A hash table with the inserted elements.
	 */
	public static GroceryHashTable tableInsert(GroceryHashTable hashtable) {
		// Creating some new items to insert into the hashtable.
		Item item1 = new Item(1, "Apples", "2.00");
		Item item2 = new Item(2, "Strawberries", "2.00");
		Item item3 = new Item(4, "Bread", "2.00");
		Item item4 = new Item(5, "Butter", "2.00");
		Item item5 = new Item(6, "Meat", "2.00");
		Item item6 = new Item(7, "Cereal", "2.00");
		Item item7 = new Item(9, "Medicine", "2.00");
		Item item8 = new Item(10, "Milk", "2.00");
		Item item9 = new Item(11, "Hand Sanitizer", "2.00");
		Item item10 = new Item(12, "Soap", "2.00");
		Item item11 = new Item(13, "Surgical Masks", "2.00");

		try {
			// Insert the items into the hash table.
			hashtable.insert("1", item1);
			hashtable.insert("2", item2);
			hashtable.insert("3", item3);
			hashtable.insert("4", item4);
			hashtable.insert("5", item5);
			hashtable.insert("6", item6);
			hashtable.insert("7", item7);
			hashtable.insert("8", item8);
			hashtable.insert("9", item9);
			hashtable.insert("10", item10);
			hashtable.insert("11", item11);

			// If the key is null or a duplicate, display appropriate message.
		} catch (IllegalNullKeyException e) {
			System.err.print("These keys display null.");
		} catch (DuplicateKeyException e) {
			System.err.print("This key is a duplicate.");
		}

		return hashtable;
	}

	/**
	 * Returns the index of an item within the User Items. This is a private helper
	 * method.
	 * 
	 * @param item          name
	 * @param selectedItems User Items
	 * @param count         number of items present in the User Items
	 * @return index of the item within the User Items, else a negative number.
	 */
	private static int indexOfItem(String item, ArrayList<Item> userSelected) {

		int itemIndex = 0; // the index of that item.
		Item itemToBeCompared = null; // This is the item that we want to compare it to.
		//
		for (int i = 0; i < userSelected.size(); i++) {
			itemToBeCompared = userSelected.get(i);
		}
		if (itemToBeCompared.getName().equals(item)) {
			itemIndex = itemToBeCompared.getKey();
			return itemIndex;
		}
		return -2;
	}

	/**
	 * Takes the itemName and returns the corresponding price of the item as a
	 * double. This is a private helper method.
	 * 
	 * @param itemName the item in the userSelection whose price we want to find
	 * @return the item Price (as a double.)
	 */
	private static double getPriceOfItem(Item itemName) {

		double price = 0.0; // This is the item's price stored as a boolean.
		String lookupPrice = null;

		try {
			lookupPrice = itemName.getPrice();
			lookupPrice = lookupPrice.substring(0);

			price = Double.valueOf(lookupPrice);

			// If we find a number format exception, then we could not find the requested
			// key. Catch any possible NumberFormat and IllegalNullKey exceptions
		} catch (NumberFormatException e) {
			System.err.print("Could not find requested key.");
		}

		return price;
	}

	/**
	 * A method used to check the number of arguments in each user command. This is
	 * a private helper method.
	 * 
	 * @param reqArgs These are the required number of arguments in the method
	 * @param args    These are the actual arguments in the method.
	 * @throws IllegalArgumentException
	 */
	private static void argsChecker(int reqArgs, String[] args) throws IllegalArgumentException {
		// Here we check to see if the length of the args array is equal to the required
		// number of args.If not, we will throw an IllegalArgumentException.
		if (args.length != reqArgs) {
			throw new IllegalArgumentException();
		} else {
			return;
		}

	}

	/**
	 * This method adds the item with the given identifier index at the end of the
	 * userSelections. This method employs the use of the private helper method
	 * getItemDescription.
	 * 
	 * @param index          of the item within the groceryItems array
	 * @param userSelections User Items
	 * @return the number of items present in the userSelections after the item with
	 *         identifier index is added
	 */

	public static int addItemToUserSelection(int index, ArrayList<Item> userSelections) {

		if (MAXSIZE == userSelections.size()) { // if the userSelection is full.
			System.out.println("You cannot add new item.");
			return userSelections.size();
		} else if (userSelections.size() < MAXSIZE) {
			Item itemToAdd; // initializing the item to add to the userSelection as a string.
			try {
				itemToAdd = GROCERY_ITEMS.lookup("" + index + "");
				userSelections.add(itemToAdd); // add this item to the end of the userItems
			} catch (IllegalNullKeyException e) {
				System.out.println("This item returns an illegal null exception.");
			} catch (KeyNotFoundException e) {
				System.out.println("");
			}
		}
		return userSelections.size();
	}

	/**
	 * Removes the itemToRemoveand returns the number of items in the userSelections
	 * after remove operation is completed. Return a negative num. if not found item
	 * has not been found.
	 * 
	 * @param itemToRemove   The item to be removed from the userSelections.
	 * @param userSelections The userSelections from which the item is to be removed
	 * @return the number of items in the userSelections after removing the
	 *         itemToRemove.
	 */

	public static int removeItemFromUserSelections(String itemToRemove, ArrayList<Item> userSelections) {

		int foundAtIndex = 0; // this holds the position of the first item found that is to be removed.
		if (userSelections.size() == 0) {
			return userSelections.size();
		}
		// Getting the position of the first item found that is to be removed.
		foundAtIndex = indexOfItem(itemToRemove, userSelections);

		if (foundAtIndex != -2) {
			userSelections.remove(foundAtIndex - 1);
		} else {
			System.out.println("WARNING: " + itemToRemove + " not found in the User Items.");
		}
		return userSelections.size();
	}

	/**
	 * Returns the cost of the items in the User Items. We employ the use of the
	 * helper method getItemPrice.
	 * 
	 * @param userSelections the User Items
	 * @return the total cost
	 */

	public static double getCheckoutPrice(ArrayList<Item> userSelections) {

		double totalCost = 0.0;

		for (int i = 0; i < userSelections.size(); i++) {
			totalCost = getPriceOfItem(userSelections.get(i)) + totalCost;
		}

		return totalCost;
	}

	/**
	 * Displays the userSelectionItems content.
	 * 
	 * @param userSelectionItems the User Items
	 */

	public static void userSelectionItemsPrint(ArrayList<Item> userSelectionItems) {
		System.out.print("List of User Selections: ");
		// goes through every element in the userItems and prints it out
		for (int i = 0; i < userSelectionItems.size(); i++) {
			System.out.print(userSelectionItems.get(i).getName() + ", ");
		}
	}

	/**
	 * prints the grocery items. Uses the GROCERY_ITEMS array to print out the item
	 * description and price.
	 * 
	 * @return void
	 */
	public static void printGroceryItems() {

		System.out.println("");
		System.out.println("BARCODE" + "\t + " + "NAME" + "\t " + "PRICE");
		System.out.println("");

		// We iterate through every item in the GROCERY_ITEMS array.
		for (int i = 0; i < GROCERY_ITEMS.getCapacity(); i++) {

			try {
				System.out.println(i + "\t" + GROCERY_ITEMS.lookup("" + i + "").getName() + "\t "
						+ GROCERY_ITEMS.lookup("" + i + "").getPrice());
			} catch (IllegalNullKeyException e) {
				System.err.print("Could not find requested key.");
			} catch (KeyNotFoundException e) {
				System.out.print("");
			}
		}
		System.out.println("");
	}

	/**
	 * The main method is the driver of the application and is where everything is
	 * accomplished.
	 * 
	 * @param args (none)
	 *
	 */
	public static void main(String[] args) {
		String[] userArray; // Splits the array into an array using the identifier " "
		String userChar; // extracts the first non-whitespace character of the string the user enters
		int userInt = 0; // extracts the integer by the user.
		Scanner scnr = new Scanner(System.in); // Creating the Scanner object to hold user input
		int count = 0; // the User Items is empty
		ArrayList<Item> userSelections = new ArrayList<Item>(15);
		String userInput = ""; // holds the user input as a string; it is initialized to empty

		GROCERY_ITEMS = new GroceryHashTable(USER_SELECTION, LOAD_FACTOR_THRESHOLD); // Assigning our grocery Items.

		GROCERY_ITEMS = tableInsert(GROCERY_ITEMS); // Inserting items into the grocery.

		System.out.println("               ONLINE GROCERY APPLICATION                 ");
		System.out.println();
		System.out.println();

		// We use a Do-While Loop to print out the Input at least once.
		do {
			// Resetting all the variables before using them once again. This is to prevent
			// any input from being incorrectly used.
			userInput = "";
			userArray = new String[0];
			userChar = "";
			userInt = 0;
			
			System.out.println("MENU:");
			System.out.println(" P - print the items available in the grocery");
			System.out.println(" A - add an item to the selected items");
			System.out.println(" R - remove an item from the selected items");
			System.out.println(" D - display the items in the selected items");
			System.out.println(" C - checkout");
			System.out.println(" Q - Quit");
			System.out.println();
			System.out.print("PLEASE INPUT KEY: ");

			userInput = scnr.next();
			userInput = userInput.trim();
			userChar = userInput.substring(0, 1);

			// The options that are available for the user to enact
			if (userInput.equalsIgnoreCase("p")) {
				userArray = userInput.split(" ");
				try {
					argsChecker(1, userArray);
				} catch (IllegalArgumentException k) {
					System.out.println("Incorrect Syntaxt, please try again.");
				}
				// prints the Items
				printGroceryItems();
				System.out.println();

			} else if (userInput.equalsIgnoreCase("a")) {
				// adds an item to the selected items
				try {
					userInt = scnr.nextInt(); // read in next input as int which is grocery index of item to add
				} catch (InputMismatchException e) {
					System.out.println("Please insert the index of the item.");
					continue;
				}

				if ((userInt < 0) || (userInt > GROCERY_ITEMS.totalKeys())) {
					System.out.println("Please insert a valid index.");
					continue;
				}
				addItemToUserSelection(userInt, userSelections);
				System.out.println();

			} else if (userInput.equalsIgnoreCase("c")) {
				userArray = userInput.split(" ");
				try {
					argsChecker(1, userArray);
				} catch (IllegalArgumentException k) {
					System.out.println("Incorrect Syntaxt, please try again.");
					continue;
				}
				// checkout (finds the total cost of all the items in the selected items)
				double totalPrice = getCheckoutPrice(userSelections); // total cost without tax

				System.out
						.println("#items: " + userSelections.size() + " TOTAL: $" + String.format("%.2f", totalPrice));

				System.out.println();

				System.out.println("WOULD YOU LIKE TO FINISH PURCHASE? Enter [y] for Yes, or [n] for No.");
				userChar = scnr.next();

				if (userChar.equalsIgnoreCase("y")) {

					System.out.println("Thank you for your purchase. Your items will be delivered soon.");
					System.out.println();
					System.out.println("             Thank you for Shopping               ");
					System.out.println();
					break;

				} else if (userChar.equalsIgnoreCase("n")) {
					continue;
				} else {
					System.out.println("Please Enter [y] for YES or [n] for NO.");
				}

			} else if (userInput.equalsIgnoreCase("d")) {
				// Displaying the selected items Content
				userArray = userInput.split(" ");
				try {
					argsChecker(1, userArray);
				} catch (IllegalArgumentException k) {
					System.out.println("Incorrect Syntaxt, please try again.");
					continue;
				}
				userSelectionItemsPrint(userSelections);
				System.out.println();
				System.out.println();

			} else if (userInput.equalsIgnoreCase("r")) {
				// Removes an item from the User Items
				try {
					userInt = scnr.nextInt(); // read in next input as int which is grocery index of item to add
				} catch (InputMismatchException e) {
					System.out.println("Please insert the index of the item.");
					continue;
				}

				if ((userInt < 0) || (userInt > GROCERY_ITEMS.totalKeys())) {
					System.out.println("Please insert a valid index.");
					continue;
				}

				String itemToRemove = null;
				// Handle Exception
				try {
					itemToRemove = GROCERY_ITEMS.lookup("" + userInt + "").getName();
				} catch (IllegalNullKeyException e) {
					System.err.println("Could not find requested key.");
				} catch (KeyNotFoundException e) {
					System.err.println("");
				}
				removeItemFromUserSelections(itemToRemove, userSelections);
				System.out.println();
			} else if (userInput.equalsIgnoreCase("q")) {
				userArray = userInput.split(" ");
				try {
					argsChecker(1, userArray);
				} catch (IllegalArgumentException k) {
					System.out.println("Incorrect Syntaxt, please try again.");
				}
				// User is finished shopping.
				System.out.println("             Thank you for Shopping               ");

				break;
			} else {
				System.out.println("Incorrect Input, please try again.");
			}

		} while (!userInput.equalsIgnoreCase("Q"));
		scnr.close();
	}

}
