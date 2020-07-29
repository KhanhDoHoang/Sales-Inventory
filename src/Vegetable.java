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

	public boolean addItem(Scanner scanner, boolean fromFile) {
		//System.out.print("Enter the code for the item: ");

		//false for not from file		//true for from file
		if(fromFile == true) {
			super.addItem(scanner, true);	

			while(scanner.hasNextLine()) {
				String line = scanner.next();
				//System.out.println(line);

				if (line.equals("farmname")) {
					//System.out.println(line);
					farmname = scanner.nextLine();
					//System.out.println(farmname);
					return true;
				}
			}
		}else {
			super.addItem(scanner, false);	
			//not from file
			System.out.print("Enter the name of the orchar supplier: ");
			farmname = scanner.nextLine();
		}
		return true;
	}
	public String getFarmName() {
		return farmname;
	}

	public String toString() {
		return super.toString() + " farm supplier: " + this.farmname;
	}
	public void outputItem(Formatter writer) {
		writer.format("%s\n", "f");
		super.outputItem(writer);
		writer.format("farmname %s\n", this.farmname);
		//System.out.println("Hello");
	}
}
