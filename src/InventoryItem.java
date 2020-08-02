import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

public class InventoryItem implements Comparable<InventoryItem>{

	private int itemQuantityInStock;
	private FoodItem item;
	private Queue<LocalDate> expires;

	public InventoryItem() {
		expires = new LinkedList<LocalDate>();
		this.itemQuantityInStock = 0;
		this.item = new FoodItem();
	}

	public InventoryItem(FoodItem item) {	//can have parameters if I wish!
		expires = new LinkedList<LocalDate>();
		this.item = item;
	}

	public boolean addItem(Scanner scanner) {
		item.addItem(scanner);		
		LocalDate date;
		//---Call the addItem of the child class which known from the Inventory
		while (true) { 
			try { 
				//--EXPIRY DATE for item new buy--//
				System.out.println("Please enter new date (yyyy-mm-dd):");
				String todayDate = scanner.next();
				//---Checking for valid date---//
				DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				date = LocalDate.parse(todayDate, dateformat);
				break; 
			} 
			catch (DateTimeParseException e) { 
				System.out.println("Invalid entry");
				System.out.println("Enter a valid date:");
				scanner.nextLine();
				continue; 
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid entry");
				System.out.println("Enter a valid date:");
				scanner.nextLine();
				continue; 
			}
		}
		//------------------Check for quantity------------------------------------
		//Must be positive
		while (true) { 
			try { 
				System.out.print("Enter the quantity for the item: ");
				itemQuantityInStock = scanner.nextInt();
				scanner.nextLine();
				while(itemQuantityInStock < 0) {
					System.out.println("Invalid entry");
					System.out.print("Enter the quantity for the item: ");
					itemQuantityInStock = scanner.nextInt();
					scanner.nextLine();
					continue;
				}
				break; 
			} 
			catch (InputMismatchException e) { 
				System.out.println("Invalid entry");
				scanner.next(); 
				continue; 
			}
		}
		//------Adding quantity and time to expires
		//String itemToAdd = date + " : " + this.itemQuantityInStock;

		//expires.add(itemToAdd);
		expires.add(date);


		return true;
	}

	//	class MyFormatter {
	//		private final String patterns;
	//
	//		public MyFormatter(String patterns){		//Vargargs taking input from multi sources
	//			this.patterns = patterns;
	//		}
	//
	//		public LocalDate parse(String text){
	//			for(int i = 0; i < patterns.length(); i++){
	//				try{
	//					return LocalDate.parse(text, DateTimeFormatter.ofPattern(text));
	//				}catch(DateTimeParseException excep){
	//					System.out.println("Cannot parse at index " + i);
	//				}
	//			}
	//			throw new IllegalArgumentException("Not able to parse the date for all patterns given");
	//		}
	//	}

	public int getItemCode() {
		//Get item code from the item got from inventory class and call the code from FoodItem
		//Use for inventory class
		return item.getItemCode();
	}

	public boolean inputCode(Scanner scanner) {
		//Need to fix this
		//How to link to food item to check "isEqual"
		//Or when call inventory// call here //then got here put it equal to the current code //Call the foodItem from here?
		this.item.inputCode(scanner);

		return true;
	}

	public void printExpiry() {
		//Print expiry but ask for item code also in the inventory class
		//To check expiry date for that specific code
		System.out.print("Item");
		item.toString();
		System.out.println();
		System.out.println("Expiry Details:");
		Iterator<LocalDate> iterator = expires.iterator();
		Iterator<Integer> iter = quantity.iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.hasNext() + " : " + iter.hasNext() + "\n");
		}
		System.out.println();
	}

	public void removeExpiredItems(LocalDate today) {

		//Remove expiry item based on today's date
		try {
			Iterator<LocalDate> iterator = expires.iterator();
			while(iterator.hasNext()) {
				if(today.isAfter(expires.peek())) {
					expires.remove();

				}
			}
		}catch(NoSuchElementException e) {
			System.out.println("");
		}

	}

	public boolean updateQuantity(Scanner scanner, int amount) {
		//How to use queue to do first in first out for expiry 
		//choosing the item code

		//Check if the input date is equal which expires
		Iterator<LocalDate> checkDate = expires.iterator();
		

		return true;
	}

	public String toString() {
		return item.toString() + " qty: " + this.itemQuantityInStock ;

	}

	@Override
	public int compareTo(InventoryItem o) {
		int compareCode=((InventoryItem)o).getItemCode();
		/* For Ascending order*/
		return this.getItemCode()-compareCode;
	}


}



