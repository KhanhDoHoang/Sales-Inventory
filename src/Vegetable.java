import java.util.Formatter;
import java.util.Scanner;
/**
 * This is read file class
 * @author HoangDo
 *
 */
public class Vegetable extends FoodItem{
	private String farmname;
	public Vegetable() {
		super();
		this.farmname = "";
	}

	public boolean addItem(Scanner scanner) {
		//System.out.print("Enter the code for the item: ");

		//false for not from file		//true for from file

		super.addItem(scanner);	
		//not from file
		System.out.print("Enter the name of the orchar supplier: ");
		farmname = scanner.nextLine();

		return true;
	}
	public String getFarmName() {
		return farmname;
	}

	public String toString() {
		return super.toString() + " farm supplier: " + this.farmname;
	}
	
}
