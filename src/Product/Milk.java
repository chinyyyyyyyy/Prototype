package Product;

public class Milk extends Product{
	
	protected static int MilkCount=0;
	protected static final int COST = 200;
	
	public Milk() {
		this.price=COST;
	}

	public static int getMilkCount() {
		return MilkCount;
	}

	public static void addMilk() {
		MilkCount++;
	}
	
	public int getAmount() {
		return MilkCount;
	}
	
	public void clear() {
		MilkCount=0;
	}
}
