import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * This is read file class
 * @author HoangDo
 *
 */
public class Assign2 {
	private static Scanner keyboard = new Scanner(System.in);
	/**
	 * @param args
	 */
	public static void main (String[]args) {

		//FoodItem foodItem = new FoodItem();
		String choice = ""; 
		Inventory inventory = new Inventory(); //Initialize inventory

		do {
			Scanner input = new Scanner(System.in);
			displayMenu();

			choice = keyboard.nextLine();
			while(choice.equals(null) || choice.equals("") || choice.equals("\t") || !choice.equals("1") && !choice.equals("2") 
					&& !choice.equals("3") && !choice.equals("4") && !choice.equals("5") && !choice.equals("6")
					&& !choice.equals("7") && !choice.equals("8")) {
				System.out.println("Incorrect value entered! Please enter 1-8: ");
				choice = keyboard.nextLine();
				continue;
			}

			if (choice.equals("1")) {
				//Add items
				boolean files = false;
				inventory.addItem(input, files);
			} else if (choice.equals("2")) {
				System.out.println("Inventory: ");
				//Display Current Inventory
				inventory.toString();

			} else if (choice.equals("3")) {
				//Buy Items
				inventory.updateQuantity(input, true);
			} else if (choice.equals("4")) {
				//Sell Items
				inventory.updateQuantity(input, false);
			} else if (choice.equals("5")) {
				//Search for items
				inventory.searchingForItem(input);
			} else if (choice.equals("6")) {
				//Save Inventory to Files
				//inventory.writeToFile();
				try {
					inventory.writeToFile(input);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (choice.equals("7")) {
				//Read Inventory to Files
				//inventory.readFromFile();
				try {
					Scanner input1 = new Scanner(System.in);
					inventory.readFromFile(input1);
				} catch (NoSuchElementException e) {
					System.out.println();
					//break;
				}
				
			} else {
				continue;
			}
		}while(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5") || choice.equals("6") || choice.equals("7"));
		System.out.println("Exitting...");

	}
	/**
	 * This is main menu displaying the choices for user
	 * @param args
	 */
	public static void displayMenu() {
		System.out.println("\nPlease select one of the following: ");
		System.out.println("1: Add Item to Inventory");
		System.out.println("2: Display Current Inventory");
		System.out.println("3: Buy Item(s)");
		System.out.println("4: Sell Item(s)");
		System.out.println("5: Search for Item");
		System.out.println("6: Save Inventory to Files");
		System.out.println("7: Read Inventory to Files");
		System.out.println("8: To Exit");
	}
}
