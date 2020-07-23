import java.util.Formatter;
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

	public boolean addItem(Scanner scanner, boolean fromFile) {
		//System.out.print("Enter the code for the item: ");

		//false for not from file		//true for from file
		if(fromFile == true) {
			super.addItem(scanner, true);	

			while(scanner.hasNextLine()) {
				String line = scanner.next();
				//System.out.println(line);

				if (line.equals("orchardname")) {
					//System.out.println(line);
					orchardName = scanner.nextLine();
					//System.out.println(orchardName);
					return true;
				}
			}
		}else {
			super.addItem(scanner, false);	
			//not from file
			System.out.print("Enter the name of the orchar supplier: ");
			orchardName = scanner.nextLine();
		}
		return true;
	}
	public String getOrchardName() {
		return orchardName;
	}

	public String toString() {
		return super.toString() + " orchard supplier: " + this.orchardName;
	}
	public void outputItem(Formatter writer) {
		writer.format("%s\n", "f");
		super.outputItem(writer);
		writer.format("orchardname %s\n", this.orchardName);
		//System.out.println("Hello");
	}
}
