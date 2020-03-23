
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
 *  also abiding by the quarantine. A user can choose certain items from this online market
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
/**
 * This is the Item Class. It is used to store information about the Items that
 * are being bought and donated to the Grocery Store.
 * 
 * The Item Class contains the Barcode of the item, the name of the item and the
 * price of the item. In the <key, value> pairs in the GroceryHashTable, the
 * Barcode acts as the key, while the item itself acts as the value.
 * 
 * @author Anjali Gali
 *
 */
public class Item {
	private int barcode; // Barcode of Item
	private String name; // Name of the Item
	private String price; // Price of the Item

	/**
	 * A constructor method for the Item. Uses the This operator to assign barcode,
	 * name and price to the variables in the method.
	 * 
	 * @param barcode The Items Barcode
	 * @param name    The Items Name
	 * @param price   The Items Price
	 */
	public Item(int barcode, String name, String price) {
		this.barcode = barcode;
		this.name = name;
		this.price = price;
	}

	/**
	 * This method returns the names of the item.
	 * 
	 * @return The Name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the item.
	 * 
	 * @param name New Name of Item.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method returns the price of the item.
	 * 
	 * @return The Price.
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * Sets the price of the item.
	 * 
	 * @param price The New Price of the Item
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * This method returns the bar code of the item.
	 * 
	 * @return The bar code.
	 */
	public int getKey() {
		return this.barcode;
	}

	/**
	 * Sets the bar code of the item.
	 * 
	 * @param barcode The New Bar Code of the Item
	 */
	public void setKey(int barcode) {
		this.barcode = barcode;
	}

	/**
	 * This is the to String() method of the Item object. This is overridden so that
	 * we may define our own toString() method.
	 */
	@Override
	public String toString() {
		return "Barcode: " + this.barcode + "; NAME: " + this.name + ", Price: " + this.price;
	}
}
