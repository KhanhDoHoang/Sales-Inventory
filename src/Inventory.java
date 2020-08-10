import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Inventory{
	private ArrayList<InventoryItem> inventory;
	private int count;

	public Inventory() {
		this.count = 0;
		this.inventory = new ArrayList<>();
	}
	/**
	 * toString to print out
	 */
	public String toString() {			//Works
		// Display all data member
		String str = "";
		for (int i = 0; i < this.inventory.size(); i++) {
			System.out.println(this.inventory.get(i).toString());
		}
		return str;
	}
	/**
	 * 
	 * @param inventoryItem
	 * @return
	 */
	public int alreadyExist(InventoryItem inventoryItem) {		//Works
		// ---Return the index of a FoodItem in the inventory array
		//----Link to InventoryItem to check fo
		for (int i = 0; i < inventory.size() - 1; i++) {
			if (inventory.get(i).getItemCode() == inventoryItem.getItemCode()) {
//				System.out.println(inventory.get(i).getItemCode());
//				System.out.println(inventoryItem.getItemCode());
				return i; // existed
			}
		}
		return -1; // not existed
	}
	/**
	 * print Expiry based on LocalDate
	 */
	//Fixed!!!!!!!!!!!!-----------------------------------------------------
	public void printExpiry() {
		Scanner scanner = new Scanner(System.in);		
		inventory.add(new InventoryItem(new FoodItem())); // Adding new fruit class into the inven
		// count here is the iteration from the beginning till the end to control the
		// loop globally
		System.out.print("Enter the code for the item: ");
		this.inventory.get(count).inputCode(scanner);
		if (this.alreadyExist(inventory.get(count)) == -1) {	//not link to inventory item yet
			System.out.println("Code not found in inventory...");
			System.out.println("Error...could not find item");
			inventory.remove(count);
			return;
		} else {
			//inventory.get(this.alreadyExist(inventory.get(count))).toString();
			System.out.println(inventory.get(this.alreadyExist(inventory.get(count))).toString());
			inventory.get(this.alreadyExist(inventory.get(count))).printExpiry();
			inventory.remove(count);
		}
	}
	//Fixed!!!!!!!!!!!!-----------------------------------------------------
		
	/**
	 * 
	 * @param scanner
	 * @return
	 */
	public boolean addItem(Scanner scanner) {		//Works
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
			inventory.add(new InventoryItem(new Fruit())); // Adding new fruit class into the inven
			// count here is the iteration from the beginning till the end to control the
			// loop globally
			System.out.print("Enter the code for the item: ");
			this.inventory.get(count).inputCode(scanner);
			if (this.alreadyExist(inventory.get(count)) != -1) {	//not link to inventory item yet
				System.out.println("The code already existed!");
				inventory.remove(count);
				return false;
			}
			inventory.get(count).addItem(scanner);
		} else if (userOption.equals("v")) {
			inventory.add(new InventoryItem(new Vegetable())); // Declare for the new vegetable
			System.out.print("Enter the code for the item: ");
			this.inventory.get(count).inputCode(scanner);
			if (this.alreadyExist(inventory.get(count)) != -1) {
				System.out.println("The code already existed!");
				inventory.remove(count);
				return false;
			}
			inventory.get(count).addItem(scanner);
		} else {
			inventory.add(new InventoryItem(new Preserve())); // Declare for the new preserve
			System.out.print("Enter the code for the item: ");
			this.inventory.get(count).inputCode(scanner);
			if (this.alreadyExist(inventory.get(count)) != -1) {
				System.out.println("The code already existed!");
				inventory.remove(count);
				return false;
			}
			inventory.get(count).addItem(scanner);
		}
		count += 1;
		// -----------------Sorting for the codes-----------------//

		Collections.sort(inventory); //Sorting the codes with collection sort
		//Using Comparable in InventoryItem

		// ---------------------------------------------------------------------------------------------------------------//

		return true;
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
		inventory.add(new InventoryItem(new FoodItem()));
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
						inventory.get(this.alreadyExist(inventory.get(count))).updateQuantity(scanner, amount);
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
						inventory.get(this.alreadyExist(inventory.get(count))).updateQuantity(scanner, amount);
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
	public void searchingForItem(Scanner input) {	//Works
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
	 * @param inventory2
	 * @param left
	 * @param right
	 * @param target
	 * @return
	 */
	private int searchItem(ArrayList<InventoryItem> inventory2, int left, int right, int target) {	//Works
		if (left <= right) { 
			int mid = (left + right) / 2; 

			if (inventory2.get(mid).getItemCode() == target) {
				return mid; 		//find the code at this 
			} else if (inventory2.get(mid).getItemCode() > target) {	
				right = mid - 1;	//too high in the sorted array-->minus
			} else {
				left = mid + 1;		//too low for this -->add to iterate
			}
			return searchItem(inventory2, left, right, target); 
		} 

		// We reach here when element is not present 
		// in array 
		return -1; 
	}

	public void removeExpiredItems(LocalDate today) {			//Works
		for(int i = 0; i < this.inventory.size(); i++) {
			this.inventory.get(i).removeExpiredItems(today);
			//System.out.println("Im in inventory: " + i);
		}	
		System.out.println("Done removing!");
	}






}


