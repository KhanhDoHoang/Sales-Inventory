import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This is read file class
 * @author HoangDo
 *
 */
public class FoodItem implements Comparable<FoodItem>{

	private int itemCode;
	private String itemName;
	private float itemPrice;
	private int itemQuantityInStock;
	private float itemCost;

	public FoodItem() {
		this.itemCode = 0;
		this.itemName = "";
		this.itemPrice = 0;
		this.itemQuantityInStock = 0;
		this.itemCost = 0;
	}

	/**
	 * Update a quantity field
	 * @param int
	 * @return boolean
	 */
	public boolean updateItem(int amount) {
		//Update a quantity field, could be positive or negative
		int tempQuantity = itemQuantityInStock;

		tempQuantity += amount;				//inStock: 12 == sell: -8
		//inStock: 12 == buy : 8

		if(tempQuantity < 0) {
			System.out.println("Insufficient stock in inventory...");
			System.out.println("Error...could not sell item");
			return false;
		}
		itemQuantityInStock = tempQuantity;

		return true;
	}
	/**
	 * This take the item code
	 * @param scanner
	 * @param fromFile
	 * @return
	 */
	public boolean inputCode(Scanner scanner, boolean fromFile) {
		//---------------Check for itemCode-----------------------------
		//Must be an integer		
		//-----------READ FROM FILE------------//
		if(fromFile == true) {
			//System.out.println("Here is code");
			while(scanner.hasNextLine()) {
				String line = scanner.next();
				//System.out.println(line);
				
				if(line.equals("itemcode")) {
					//System.out.println(line);
					try {
						itemCode = scanner.nextInt();
						//System.out.println(itemCode);
						return true;
					}catch(InputMismatchException e) {
						System.out.println("Invalid entry");
						return false;
					}
				}
			}
		}else {
			while (true) { 
				try { 
					//not reading from file
					itemCode = scanner.nextInt();
					scanner.nextLine();
					break; 
				} 
				catch (InputMismatchException e) { 
					System.out.println("Invalid entry");
					System.out.println("Enter a valid code:");
					scanner.nextLine();
					continue; 
				}
			}
		}
		return true;
	}

	/**
	 * Check for valid item for buying or selling
	 * @param FoodItem
	 * @return boolean
	 */
	public boolean isEqual(FoodItem item) {
		//check through inventory
		if(this.itemCode == item.itemCode) {
			return true;
		}else {
			return false;
		}

	}

	/**
	 * Check for valid item from Fruit, Preserve or Vegetable
	 * @param Scanner
	 * @return boolean
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		//---------------------------READ FROM FILE ----------//
		//read from file

		if(fromFile == true) {
			//System.out.println("hello");
			while(scanner.hasNextLine()) {
				//System.out.println("hello");
				String line = scanner.next();
				//System.out.println(line);
				switch(line) {
				case "itemname":
					//System.out.println(line);
					this.itemName = scanner.nextLine();
					//System.out.println(itemName);
					break;
					//----------------------------------------//
				case "quantityinstock":
					//System.out.println(line);
					try {
						int temp = scanner.nextInt();
						//System.out.println(temp);
						//scanner.nextLine();
						while(itemQuantityInStock < 0) {
							System.out.println("Invalid quantity");
							return false;
						} 
						this.itemQuantityInStock = temp;
					} catch (InputMismatchException e) {
						System.out.println("Invalid quantity");
						//scanner.next(); 
						return false;
					}
					break;
					//-----------------------------------------//
				case "itemcost":
					//System.out.println(line);
					try {
						float temp = scanner.nextFloat();
						//System.out.println(temp);
						//scanner.nextLine();
						while(itemCost < 0) {
							System.out.println("Invalid cost");
							return false;
						} 
						this.itemCost = temp;
					} catch (InputMismatchException e) {
						System.out.println("Invalid cost");
						//scanner.next(); 
						return false;
					}
					break;
					//----------------------------------------//
				case "itemprice":
					//System.out.println(line);
					try {
						float temp = scanner.nextFloat();
						//System.out.println(temp);
						//scanner.nextLine();
						while(itemPrice < 0) {
							System.out.println("Invalid cost");
							return false;
						} 
						this.itemPrice = temp;
					} catch (InputMismatchException e) {
						System.out.println("Invalid cost");
						//scanner.next(); 
						return false;
					}
					return true;
				}

			}
		} else {
			//---------------Check for itemCode-----------------------------
			//Must be an integer		
			//inputCode(scanner);

			//------------------Check for itemName------------------------------------
			System.out.print("Enter the name for the item: "); //Can be any input
			this.itemName = scanner.nextLine();
			scanner.nextLine();
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

			//-----------Check for cost----------------------------------
			while (true) { 
				try { 
					System.out.print("Enter the cost of the item: ");
					itemCost = scanner.nextFloat();
					scanner.nextLine();
					while(itemCost < 0) {
						System.out.println("Invalid entry");
						System.out.print("Enter the cost of the item: ");
						itemCost = scanner.nextFloat();
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

			//-----------Check for price---------------------------------
			while (true) { 
				try { 
					System.out.print("Enter the price of the item: ");
					itemPrice = scanner.nextFloat();
					scanner.nextLine();
					while(itemPrice < 0) {
						System.out.println("Invalid entry");
						System.out.print("Enter the price of the item: ");
						itemPrice = scanner.nextFloat();
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
		}
		return true;
	}

	/**
	 * Display data members
	 * @return toString
	 */
	public String toString() {		

		return "Item code: " + this.itemCode + " Name: " + this.itemName + " In stock: " 
				+ this.itemQuantityInStock + " price: " + this.itemPrice
				+ " cost: " + this.itemCost + " ";
		//How to add fruit, vegie or preserve to this toString
	}

	/**
	 * This gets item code
	 * @return
	 */
	public int getItemCode() {
		return itemCode;
	}
	/**
	 * This method for formatting the output written to file
	 * @param writer
	 */
	public void outputItem(Formatter writer) {
		writer.format("itemcode %d\nitemname %s\nquantityinstock %d\nitemcost %f\nitemprice %f\n", 
				this.itemCode, this.itemName, this.itemQuantityInStock, this.itemCost, this.itemPrice);
		//writer.format("%s", "This is my code");
		//System.out.println("Hello");
	}
	/**
	 * This to compare for the merge
	 * @param item
	 * @return
	 */
	@Override
	public int compareTo(FoodItem item) {
		if(this.itemCode == item.itemCode) {
			return 0;
		} else if(this.itemCode > item.itemCode) {
			return 1;
		} else {
			return -1;
		}
	}


}
//String name = String.format("%s", this.itemName);
//String quantity = String.format("%d", this.itemQuantityInStock);
//String price = String.format("%d", this.itemPrice);
//String cost = String.format("%d", this.itemCost);
//System.out.println("item code " + code + "\nitem name " + name + "\nquantity of item"
//	+ quantity + "\nitem cost " + cost + "\nitem price " + price);
