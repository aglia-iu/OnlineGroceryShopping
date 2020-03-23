
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
import java.util.ArrayList;

/**
 * 
 * HashTable implementation that uses an Array of ArrayLists in order to account
 * for collisions in the data structure. Every time a collision occurs, the data
 * type simply adds the element to the ArrayList within the Array at
 * hash_table[hash(key)] to avoid having to consider the collision.
 * 
 * @param <K> unique comparable identifier for each <K,V> pair, may not be null
 * @param <V> associated value with a key, value may be null
 */
public class GroceryHashTable {

	/** The load factor that is used if none is specified by user */
	static final double DEFAULT_LFR = 0.75;

	/** The initial capacity that is used if none is specified user */
	static final int DEF_CAPACITY = 101;

	/**
	 * Private Class for the ItemNode.
	 * 
	 * @author Anjali Gali
	 *
	 */
	private class ItemNode {
		private String key; // The key of the key value pair
		private Item value; // The value of the key value pair

		/**
		 * Getter method for the key.
		 * 
		 * @return key
		 */
		private String getKey() {
			return key;
		}

		/**
		 * Getter method for the value.
		 * 
		 * @return the value.
		 */
		private Item getValue() {
			return value;
		}

		/**
		 * Creates a new node, complete with it's respective key and value pairs.
		 * 
		 * @param key   The key.
		 * @param value The value.
		 */
		private ItemNode(String key, Item value) {
			this.key = key;
			this.value = value;
		}
	}

	/**
	 * This is the default constructor that uses default capacity and sets load
	 * factor threshold for the newly created hash table.
	 */
	public GroceryHashTable() {
		this(DEF_CAPACITY, DEFAULT_LFR);
	}

	private double loadFactorThreshold; // The load factor threshold of the HashTable.
	private int tableSize; // The length of the hash table.
	private int initCap; // The Initial Capacity of the HashTable
	private int totalKeys; // The number of filled keys in the HashTable.
	private ArrayList<ItemNode>[] table; // the newly created hash table in the method.

	/**
	 * Creates an empty hash table with the specified capacity and load factor.
	 * 
	 * @param initCap             number of elements table should hold at start.
	 * @param loadFactorThreshold the ratio of items/capacity that causes table to
	 *                            resize and rehash
	 */
	public GroceryHashTable(int initCap, double loadFactorThreshold) {
		this.initCap = initCap; // sets the initial capacity of the Hash table.
		this.loadFactorThreshold = loadFactorThreshold; // sets the loadFactorThreshold of the HashTable.
		this.totalKeys = 0; // This sets the number of keys in the Hash Table to be 0 initially.

		this.table = new ArrayList[initCap];
		for (int i = 0; i < initCap; i++) {
			table[i] = new ArrayList<ItemNode>();

		}
		this.tableSize = this.table.length;
	}

	/**
	 * Add the key,value pair to the data structure and increase the number of keys.
	 * If key is null, throw IllegalNullKeyException; If key is already in data
	 * structure, throw DuplicateKeyException();
	 * 
	 * @param key   The unique key to be inserted in the data structure.
	 * @param value The value to be inserted along with the Key.
	 */
	public void insert(String key, Item value) throws IllegalNullKeyException, DuplicateKeyException {
		// We first resize the hashtable before we add anyti=hing inside it.
		this.rehash();

		ItemNode item1 = new ItemNode(key, value);
		// If key is null, throw IllegalNullKeyException.
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		try {
			for (int i = 0; i < table[hashFunction(key)].size(); i++) {
				if (this.table[hashFunction(key)].get(i).getKey() == (key)) {
					// If the key already exists in the hashtable, throw a new
					// DuplicateKeyException().
					throw new DuplicateKeyException();
				}
			}
			lookup(key);
			// Try to get the key. If the method throws a KeyNotFoundException, this means
			// that the key is not in the hash table. So we can safely insert the key
			// without the fear of duplicates.
		} catch (KeyNotFoundException k) {
			table[hashFunction(key)].add(item1);
			totalKeys++;
		}

	}

	/**
	 * If key is found, remove the key,value pair from the data structure decrease
	 * number of keys. Return true. If key is null, throw IllegalNullKeyException.
	 * If key is not found, return false.
	 * 
	 * @param key The key of the value to remove.
	 * @return True if the element is safely removed. False otherwise.
	 */
	public boolean remove(String key) throws IllegalNullKeyException {
		// If key is null, throw IllegalNullKeyException.
		if (key == null) {
			throw new IllegalNullKeyException();
		}

		// try to get the key. If the KeynotFoundException is thrown return k.
		try {
			lookup(key);
		} catch (KeyNotFoundException k) {
			return false;
		}
		// Iterate through the so found arrayList. Upon finding element, remove and
		// decrement the value in numKeys, and return true.
		for (int i = 0; i < table[hashFunction(key)].size(); i++) {

			if (table[hashFunction(key)].get(i).getKey() == (key)) {
				table[hashFunction(key)].remove(i);

				totalKeys--;
				return true;
			}
		}
		// At this point, nothing has been removed, so return false.
		return false;
	}

	/**
	 * Returns the value associated with the specified key. Does not remove key or
	 * decrease number of keys. If key is null, throw IllegalNullKeyException. If
	 * key is not found, throw KeyNotFoundException().
	 * 
	 * @param key The key to get from the hash table.
	 * @return The i associated with the given key.
	 * @throws IllegalNullKeyException If key is null.
	 * @throws KeyNotFoundException()  If key is not found.
	 */
	public Item lookup(String key) throws IllegalNullKeyException, KeyNotFoundException {
		// If key is null, throw IllegalNullKeyException.
		int itemToReturn = -2;
		if (key == null) {
			throw new IllegalNullKeyException();
		} else {
			for (int i = 0; i < table[hashFunction(key)].size(); i++) {
				// If this item exists, return the value associated with the specified key.
				if (table[hashFunction(key)].get(i).getKey().equals(key)) {
					itemToReturn = i;
				}
			}
			// If we reach this point in the array, since -2 is not a valid key in the
			// arrayList, this means the key has not been found, so we throw a
			// KeyNotFoundException().
			if (itemToReturn == -2) {
				throw new KeyNotFoundException();
			}
		}
		// Otherwise, we are successfully able to obtain the value, so return it.
		return table[hashFunction(key)].get(itemToReturn).getValue();
	}

	/**
	 * Returns the number of key,value pairs in the data structure
	 * 
	 * @return total key value pairs.
	 */
	public int totalKeys() {
		return totalKeys;
	}

	/**
	 * This method returns the Capacity of the HashTable. Capacity is the size of
	 * the hash table array. This method returns the current capacity. initial
	 * capacity must be a positive integer, 1 or greater and is specified in the
	 * constructor. REQUIRED: When the load factor is reached, the capacity must
	 * increase to: 2 * capacity + 1. Once increased, the capacity never decreases
	 * 
	 * @return Capacity of the HashTable.
	 */
	public int getCapacity() {
		// The initial capacity must be a positive int > 1. Specified in the constructor
		int capacity = tableSize; // Capacity is the size of the hash table array.

		// Return the current capacity of the hash table.
		return capacity;
	}

	/**
	 * This method returns the hash function of the item in question. We know that
	 * in this particular hash table, the keys of the items are 12 digit strings
	 * that start with the unique digit 9780. So, the representation of each of
	 * these keys is:
	 * 
	 * 9 7 8 | _ _ _ | _ _ _ | _ _ _ |
	 * 
	 * A strategy would be to divide these String into parts and then weigh each
	 * part by multiplying it by a prime that is close to a power of 2 (example: 31)
	 * after adding all the integers in that part.
	 * 
	 * Step 1: Take the first four into three pieces, p0, p1, p2, and p3, and parse
	 * the integers in the String
	 * 
	 * Step 2: Multiply these integers with a power of 31 individually.
	 * 
	 * Step 3: Add them up, then obtain their remainder when dividing by the
	 * tableSize (modulo).
	 * 
	 * @param key The unique string key of each item
	 * @return The Hash Code.
	 */

	public int hashFunction(String key) {
		// Weighing each value individually by a power of 31.
		int hashCode = Math.abs((key.hashCode())) % (tableSize);
		// Return the hash code.
		return hashCode;

	}

	public void rehash() throws IllegalNullKeyException, DuplicateKeyException {
		this.resize();
	}

	/**
	 * This method is used to resize and then rehash the hash table. It resizes by
	 * first storing the array into a temporary array. Once the loadFactor has been
	 * reached, then we change the size of the array, and insert the appropriate
	 * number of ArrayLists to avoid resizing issues. Then we rehash the elements
	 * one by one from the temporary to the new array.
	 * 
	 * @throws IllegalNullKeyException If the key is null.
	 * @throws DuplicateKeyException   If the key has a duplicate.
	 */
	private void resize() throws IllegalNullKeyException, DuplicateKeyException {
		// When the load factor is reached, the capacity must increase to:
		// 2 * capacity + 1.
		Double loadFactor = (((double) totalKeys) / ((double) getCapacity()));
		// Copying all current elements of array into temporary array.
		ArrayList<ItemNode>[] temp = copyAllVals();

		if ((loadFactor).compareTo(loadFactorThreshold) > 0) {
			// Modifying the ArrayList.size, as well as the tableSize.
			table = new ArrayList[(2 * table.length) + 1];
			this.tableSize = (2 * tableSize) + 1;
			// Inserting a new ArrayList at every point in the Array.
			for (int i = 0; i < getCapacity(); i++) {
				table[i] = new ArrayList<ItemNode>();
			}
			// Rehashing the values back into the array from temp.
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] == null) {
					continue;
				}
				// Obtain the String and key value of the item in question from temp.
				for (int j = 0; j < temp[i].size(); j++) {
					String key = temp[i].get(j).getKey();
					Item value = temp[i].get(j).getValue();
					// Rehash the function appropriately.
					// hashFunction(key);
					try {
						// Reinsert the value.
						reinsert(key, value);
					} catch (DuplicateKeyException d) {
						throw new DuplicateKeyException();
					}
				}
			}
		}

	}

	// Modify the elements in the array by rehashing them. It would probably
	// be most helpful to create a new method for it.
	/**
	 * The method used to reinsert values into an hash table using the hash function
	 * after resizing the table.
	 * 
	 * @return ArrayList<item>[] The temp array containing all the elements in the
	 *         array currently.
	 */
	private ArrayList<ItemNode>[] copyAllVals() {
		ArrayList<ItemNode>[] temp = new ArrayList[table.length]; // New temp array
		if (table != null) {
			for (int i = 0; i < table.length; i++) {
				// While the hash table at (i) is not empty, nor null, insert
				// the existing arrayList into the temp(i). Otherwise, move to the
				// next element.
				if (!table[i].isEmpty() && table[i] != null) {
					ArrayList<ItemNode> element = table[i];
					temp[i] = (element);
				} else {
					continue;
				}
			}
		}
		// return the temp array.
		return temp;
	}

	/**
	 * This is the method that is used to reinsert the items that have been removed
	 * from the array after rehashing has occured. They are reinserted using the
	 * hashFunction. This is a private helper method.
	 * 
	 * @param key   The key to be inserted
	 * @param value The value to be inserted
	 * @throws IllegalNullKeyException
	 * @throws DuplicateKeyException
	 */
	private void reinsert(String key, Item value) throws IllegalNullKeyException, DuplicateKeyException {
		if (key == null) {
			throw new IllegalNullKeyException();
		}
		ItemNode newItem = new ItemNode(key, value);

		try {
			for (int i = 0; i < table[hashFunction(key)].size(); i++) {
				if (this.table[hashFunction(key)].get(i).getKey().equals(key)) {
					// If the key is the same as the key passed into the
					// method, throw a new DuplicateKeyException().
					throw new DuplicateKeyException();
				}
			}
			lookup(key);
			// Try to get the key. If the method throws a KeyNotFoundExcpetion, this means
			// that the key is not in the hash table. So we can safely insert the key
			// without the fear of duplicates.
		} catch (KeyNotFoundException k) {
			table[hashFunction(key)].add(newItem);
		}

	}

}