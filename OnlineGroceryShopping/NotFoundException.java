
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
 * Checked exception thrown when a non existent key is specified for get.
 */

/*
 * @SuppressWarnings instruct the compiler to ignore or suppress, specified
 * compiler warning in annotated element and all program elements inside that
 * element. (Learned in CS400 Office Hours)
 */
@SuppressWarnings("serial")
public class NotFoundException extends Exception {

}
