import java.time.LocalDate;
import java.util.Scanner;

public class InventoryItem {

	private int itemQuantityInStock;
	private FoodItem item;
	private LocalDate expires;
	
	public InventoryItem(FoodItem item) {	//can have parameters if I wish!
		this.itemQuantityInStock = 0;
		this.item = item;
		expires = LocalDate.now();
	}
	
	public boolean addItem(Scanner scanner) {
		
		return true;
	}

	public int getItemCode() {
		return item.getItemCode();
	}
	
	public boolean inputCode() {
		return true;
	}
	
	public void printExpiry() {
		
	}
	
	public void removeExpiredItems() {
		
	}
	
	public boolean updateQuality(Scanner scanner, int amount) {
		return true;
	}
	
	public String toString() {
		return "";
	}
}
