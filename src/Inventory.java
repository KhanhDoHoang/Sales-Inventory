import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Inventory{
	private ArrayList<InventoryItem> inventory;
	private int count;

	public Inventory() {
		this.count = 0;
		this.inventory = new ArrayList<>();
	}

	public String toString() {
		// Display all data member
		String str = "";
		for (int i = 0; i < this.inventory.size(); i++) {
			System.out.println(this.inventory.get(i).toString());
		}
		return str;
	}

	public int alreadyExist(FoodItem item) {
		// ---Return the index of a FoodItem in the inventory array
		for (int i = 0; i < inventory.size() - 1; i++) {
			if (inventory.get(i).isEqual(item) == true) {

				return i; // existed
			}
		}
		return -1; // not existed
	}

	public boolean addItem(Scanner scanner) {
		//checking for v f and p
		
			// ----Adds an item to the inventory array
			scanner = new Scanner(System.in);
			String userOption;
			while (true) {
				try {
					System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)?");
					userOption = scanner.nextLine();
					break;
				} catch (InputMismatchException e) {
					System.out.println("Invalid entry");
					scanner.next();
					continue;
				}
			}

			while (userOption.equals("") || userOption.equals("\t")
					|| !userOption.equals("f") && !userOption.equals("p") && !userOption.equals("v")) {
				System.out.print("Invalid! Do you wish to add a fruit(f), vegetable(v) or a preserve(p)?");
				userOption = scanner.nextLine();
			}

			// Assigning values for specific classes

			if (userOption.equals("f")) {
				// inventory[count] = new Fruit();
				inventory.add(new Fruit()); // Adding new fruit class into the inven
				// count here is the iteration from the beginning till the end to control the
				// loop globally
				System.out.print("Enter the code for the item: ");
				this.inventory.get(count).inputCode(scanner);
				if (this.alreadyExist(inventory.get(count)) != -1) {
					System.out.println("The code already existed!");
					System.out.println("Error Encountered while reading the file, aborting...");
					return false;
				}
				inventory.get(count).addItem(scanner);
			} else if (userOption.equals("v")) {
				inventory.add(new Vegetable()); // Declare for the new vegetable
				System.out.print("Enter the code for the item: ");
				this.inventory.get(count).inputCode(scanner);
				if (this.alreadyExist(inventory.get(count)) != -1) {
					System.out.println("The code already existed!");
					System.out.println("Error Encountered while reading the file, aborting...");
					inventory.remove(count);
					return false;
				}
				inventory.get(count).addItem(scanner);
			} else {
				inventory.add(new Preserve()); // Declare for the new preserve
				System.out.print("Enter the code for the item: ");
				this.inventory.get(count).inputCode(scanner);
				if (this.alreadyExist(inventory.get(count)) != -1) {
					System.out.println("The code already existed!");
					System.out.println("Error Encountered while reading the file, aborting...");
					inventory.remove(count);
					return false;
				}
				inventory.get(count).addItem(scanner);
			}
			count += 1;
			// -----------------Sorting for the codes-----------------//
			// Using data structure
			// inventory.get(count).getItemCode()
			merge(inventory);
		

		// ---------------------------------------------------------------------------------------------------------------//
		
		return true;
	}

	/**
	 * This does the data structure sorting
	 * @param inventory2
	 */
	private void merge(ArrayList<FoodItem> inventory2) {
		if (inventory2.size() <= 1) { // small list don't need to be merged
			return;
		}
		int mid = inventory2.size() / 2;

		ArrayList<FoodItem> left = new ArrayList<FoodItem>();
		ArrayList<FoodItem> right = new ArrayList<FoodItem>();

		for (int i = 0; i < mid; i++) {
			left.add(inventory2.remove(0)); // put first half part in left
		}
		while (inventory2.size() != 0) {
			right.add(inventory2.remove(0)); // put the remainings in right
		}
		// Now a is nothing there
		merge(left); // Merging till really small
		merge(right);

		// MERGE PARTS
		while (left.size() != 0 && right.size() != 0) {
			// compare both heads, add the lesser into the result and
			// remove it from its list
			if (left.get(0).compareTo(right.get(0)) < 0) {
				inventory2.add(left.remove(0));
			} else {
				inventory2.add(right.remove(0));
			}
		}
		// Fill the result with what remains in left or right
		while (left.size() != 0) {
			inventory2.add(left.remove(0));
		}
		while (right.size() != 0) {
			inventory2.add(right.remove(0));
		}
		// Assign to the original one
		inventory = inventory2;
	}

	/**
	 * This for buying and selling
	 * @param scanner
	 * @param buyOrSell
	 * @return
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		// Checking the code if it is the same as older ones

		// this.inventory.add(new FoodItem());

		// Add one more to input and then check that with the others
		// Remember delete that
		// boolean fromFile = false;
		inventory.add(new FoodItem());
		if (buyOrSell == false) { // false for sell
			System.out.print("Enter valid item code:");
			inventory.get(count).inputCode(scanner);
			if (this.alreadyExist(inventory.get(count)) == -1) { // not existed
				System.out.println("Code not found in inventory...");
				System.out.println("Error...could not buy item");
				inventory.remove(count);
				return false;
			} else {
				while (true) {
					try {
						System.out.println("Enter valid quantity to sell");
						int amount = scanner.nextInt();
						amount = 0 - amount;
						inventory.get(this.alreadyExist(inventory.get(count))).updateItem(amount);
						inventory.remove(count);

						return true;
					} catch (InputMismatchException e) {
						System.out.println("Invalid quantity...");
						System.out.println("Error...could not sell item");
						scanner.next();
						inventory.remove(count);

						continue;
					}
				}
			}
		} else { // true for buy
			System.out.print("Enter valid item code:");
			inventory.get(count).inputCode(scanner);
			if (this.alreadyExist(inventory.get(count)) == -1) {
				System.out.println("Code not found in inventory...");
				System.out.println("Error...could not buy item");
				inventory.remove(count);

				return false;
			} else {
				while (true) {
					try {
						System.out.println("Enter valid quantity to buy");
						int amount = scanner.nextInt();
						inventory.get(this.alreadyExist(inventory.get(count))).updateItem(amount);
						inventory.remove(count);

						return true;
					} catch (InputMismatchException e) {
						System.out.println("Invalid quantity...");
						System.out.println("Error...could not buy item");
						scanner.next();
						inventory.remove(count);

						continue;
					}
				}

			}
		}
		// Read in an itemCode to update and quantity by the input in the inventory
		// array
		// The boolean parameter is used to denote
	}
	/**
	 * This searches item in the arraylist
	 * @param input
	 */
	public void searchingForItem(Scanner input) {
		int target = 0;
		while(true) {
			try {
				System.out.println("Please enter item code:");
				target = input.nextInt();
				break;
			}catch(InputMismatchException e) {
				System.out.println("Invalid");
				input.next();
				continue;
			}
		}
		int result = searchItem(inventory, 0, inventory.size() - 1, target); 
		if (result == -1) 
			System.out.println("Code not found in inventory..."); 
		else
			System.out.println(inventory.get(result).toString()); 
	}
	/**
	 * Doing recursive merge sort
	 * @param list2
	 * @param left
	 * @param right
	 * @param target
	 * @return
	 */
	private int searchItem(ArrayList<FoodItem> list2, int left, int right, int target) {
		if (left <= right) { 
			int mid = (left + right) / 2; 

			if (list2.get(mid).getItemCode() == target) {
				return mid; 		//find the code at this 
			} else if (list2.get(mid).getItemCode() > target) {	
				right = mid - 1;	//too high in the sorted array-->minus
			} else {
				left = mid + 1;		//too low for this -->add to iterate
			}
			return searchItem(list2, left, right, target); 
		} 

		// We reach here when element is not present 
		// in array 
		return -1; 
	}

	
}


