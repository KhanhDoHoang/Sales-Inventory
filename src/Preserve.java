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

	public boolean addItem(Scanner scanner, boolean fromFile) {
		//System.out.print("Enter the code for the item: ");

		//false for not from file		//true for from file
		if(fromFile == true) {
			super.addItem(scanner, true);

			while(scanner.hasNextLine()) {
				String line = scanner.next();
				//System.out.println(line);

				if (line.equals("charsize")) {
					//System.out.println(line);

					int temp = scanner.nextInt();
					//System.out.println(temp);
					while(intSize < 0) {
						System.out.println("Invalid! Cannot read...");
						return false;
					}
					intSize = temp;
					return true;
				}
			}
		} else {
			//not from file
			super.addItem(scanner, false);
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
		}
		return true;
	}
	public int getIntSize() {
		return intSize;
	}
	public String toString() {
		return super.toString() + " size: " + this.intSize;
	}
	public void outputItem(Formatter writer) {
		writer.format("%s\n", "p");
		super.outputItem(writer);
		writer.format("charsize %d\n", this.intSize);
	}
}
