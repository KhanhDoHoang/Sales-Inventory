import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

	/**
	 * 
	 * @param item
	 */
	public InventoryItem(FoodItem item) {	//can have parameters if I wish!
		expires = new LinkedList<LocalDate>();
		this.item = item;
	}
	/**
	 * 
	 * @param scanner
	 * @return
	 */
	public boolean addItem(Scanner scanner) {
		item.addItem(scanner);		
		LocalDate date;
		//---Call the addItem of the child class which known from the Inventory
		while (true) { 
			try { 
				//--EXPIRY DATE for item new buy--//
				System.out.println("Please enter new date (yyyy-mm-dd):");
				String newDate = scanner.next();
				if(newDate.equals("none")) {
					date = LocalDate.MAX;
					break;
				}
				//---Checking for valid date---//
				DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				date = LocalDate.parse(newDate, dateformat);
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

		//expires.add(itemToAd==);

		for(int i = 0; i < this.itemQuantityInStock; i++) {
			expires.add(date);
			//System.out.println(expires);
		}


		return true;
	}
	/**
	 * 
	 * @return
	 */
	public int getItemCode() {
		//Get item code from the item got from inventory class and call the code from FoodItem
		//Use for inventory class
		return item.getItemCode();
	}
	/**
	 * input Code
	 * @param scanner
	 * @return
	 */
	public boolean inputCode(Scanner scanner) {
		//Need to fix this
		//How to link to food item to check "isEqual"
		//Or when call inventory// call here //then got here put it equal to the current code //Call the foodItem from here?
		this.item.inputCode(scanner);

		return true;
	}
	/**
	 * printExpiry for each localdate
	 */
	public void printExpiry() {
		//Print expiry but ask for item code also in the inventory class
		//To check expiry date for that specific code
		System.out.print("Item");
		item.toString();
		System.out.println();
		System.out.println("Expiry Details:");
		try {			
			Iterator<LocalDate> iterator1 = expires.iterator();
			LocalDate currentDate1 = LocalDate.MIN;
			int type = 0;
			LinkedList<LocalDate> dateNum = new LinkedList<LocalDate>(); 
			while(iterator1.hasNext()) {
				if(iterator1.next() != currentDate1) {
					currentDate1 = iterator1.next();
					dateNum.add(currentDate1);
					type++;
				}
			}
			//Based on type then know how many dates in expires
			//--------
			//LocalDate currentDate;
			
			for(int i = 0; i < type; i++) {
				int dateQty = 0;
				Iterator<LocalDate> iterator = expires.iterator();
				//currentDate = iterator.next();
				while(iterator.hasNext()) {
					if(iterator.next().equals(dateNum.get(i))) {
						dateQty++;
					}
				}
				System.out.println(dateNum.get(i) + " : " + dateQty);
			}


		}catch(NoSuchElementException e) {
			System.out.println("");
		}
	}
	/**
	 * remove expired item compared with current date
	 * @param today
	 */
	public void removeExpiredItems(LocalDate today) {
		//System.out.println("Hello Im in inventory item");
		//Remove expiry item based on today's date
		try {

			Iterator<LocalDate> iterator = expires.iterator();
			while(iterator.hasNext()) {
				//System.out.println("Hello Im in loop");
				if(today.isAfter(iterator.next())) {
					iterator.remove();
				}else {
					continue;
				}
			}

		}catch(NoSuchElementException e) {
			System.out.println("");
		}
	}
	/**
	 * 
	 * @param scanner
	 * @param amount
	 * @return
	 */
	public boolean updateQuantity(Scanner scanner, int amount) {
		//How to use queue to do first in first out for expiry 
		//choosing the item code
		LocalDate date = LocalDate.MIN;
		if(amount > 0) {
			while (true) { 
				try { 
					//--EXPIRY DATE for item new buy--//
					System.out.println("Please enter new date (yyyy-mm-dd):");
					String newDate = scanner.next();
					if(newDate.equals("none")) {
						date = LocalDate.MAX;
						break;
					}
					//---Checking for valid date---//
					DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					date = LocalDate.parse(newDate, dateformat);
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
			int tempQuantity = this.expires.size();
			//System.out.println(tempQuantity);
			tempQuantity += amount;				//inStock: 12 == sell: -8		//inStock: 12 == buy : 8
			//System.out.println(tempQuantity);
			if(tempQuantity < 0) {
				System.out.println("Insufficient stock in inventory...");
				System.out.println("Error...could not sell item");
				return false;
			}
			itemQuantityInStock = tempQuantity;

		}



		int tempQuantity = this.expires.size();
		//System.out.println(tempQuantity);
		tempQuantity += amount;				//inStock: 12 == sell: -8		//inStock: 12 == buy : 8
		//System.out.println(tempQuantity);
		if(tempQuantity < 0) {
			System.out.println("Insufficient stock in inventory...");
			System.out.println("Error...could not sell item");
			return false;
		}
		itemQuantityInStock = tempQuantity;
		//System.out.println(itemQuantityInStock);
		LocalDate smallestDate = LocalDate.MAX;
		//System.out.println(smallestDate);
		if(amount < 0) {

			Iterator<LocalDate> existedSell = expires.iterator();
			//System.out.println(existedSell);
			//			boolean existed = false;
			//			
			//			while(existedSell.hasNext()) {
			//				if(date.equals(existedSell.next())) {
			//					System.out.println("Founded date!");
			//					existed = true;
			//					break;
			//				}
			//			}
			//			if(existed == false) {
			//				System.out.println("Date not found!");
			//				return false;
			//			}

			while(existedSell.hasNext()) {
				if(smallestDate.isAfter(existedSell.next())) {
					smallestDate = existedSell.next();
				}
			}

			Iterator<LocalDate> iterator = expires.iterator();
			amount = 0 - amount;
			//System.out.println(amount);
			int count = 0;
			while(iterator.hasNext()) {
				if(smallestDate.equals(iterator.next())) {
					iterator.remove();
					//System.out.println(iterator.next());
				}
				if(count == (amount-1)) {
					break;
				}
				count++;
			}

			return true;
		}

		//System.out.println(amount);
		for(int i = 0; i < amount; i++) {
			expires.add(date);
		}

		return true;
	}
	/**
	 * toString()
	 */
	public String toString() {
		return item.toString() + " qty: " + this.expires.size() ;
	}
	/**
	 * compareTo for inventory
	 */
	@Override
	public int compareTo(InventoryItem o) {
		int compareCode=((InventoryItem)o).getItemCode();
		/* For Ascending order*/
		return this.getItemCode()-compareCode;
	}
}
//for(int i = 0; i < amount; i++) {
//if(date.equals(iterator.next())) {
//	iterator.remove();
//}
//}