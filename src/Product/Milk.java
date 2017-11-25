package Product;

public class Milk extends Product{
	
	protected static int MilkCount=0;
	protected static final int COST = 100;
	
	public Milk() {
	}

	public static int getMilkCount() {
		return MilkCount;
	}

	public static void addMilk() {
		MilkCount++;
	}
}
