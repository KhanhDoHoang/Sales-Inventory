import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This is read file class
 * @author HoangDo
 *
 */
public class Preserve extends FoodItem{
	private int intSize;
	public Preserve() {
		super();
		this.intSize = 0;
	}

	public boolean addItem(Scanner scanner) {
		//System.out.print("Enter the code for the item: ");

		//false for not from file		//true for from file

		//not from file
		super.addItem(scanner);
		while (true) { 
			try { 
				System.out.println("Enter the size of the jar in millilitres: ");
				intSize = scanner.nextInt();
				while(intSize < 0) {
					System.out.print("Enter the quantity for the item: ");
					intSize = scanner.nextInt();
					continue;
				}
				break;
			} 
			catch (InputMismatchException e) { 
				System.err.println("Invalid entry");
				scanner.next(); 
				continue; 
			}
		}

		return true;
	}
	public int getIntSize() {
		return intSize;
	}
	public String toString() {
		return super.toString() + " size: " + this.intSize;
	}

}
