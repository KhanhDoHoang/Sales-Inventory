
import java.util.Scanner;
/**
 * This is read file class
 * @author HoangDo
 *
 */
public class Fruit extends FoodItem{
	private String orchardName;
	public Fruit() {
		super();
		this.orchardName = "";
	}

	public boolean addItem(Scanner scanner) {
		//System.out.print("Enter the code for the item: ");

		//false for not from file		//true for from file

		super.addItem(scanner);	
		//not from file
		System.out.print("Enter the name of the orchar supplier: ");
		orchardName = scanner.nextLine();

		return true;
	}
	public String getOrchardName() {
		return orchardName;
	}

	public String toString() {
		return super.toString() + " orchard supplier: " + this.orchardName;
	}
}

