package Product;

public class Wool extends Product{
	protected static int WoolCount=0;
	protected static final int COST = 100;
	
	public Wool() {
		this.price=COST;
	}

	public static int getWoolCount() {
		return WoolCount;
	}

	public static void addWool() {
		WoolCount++;
	}

	public int getAmount() {
		return WoolCount;
	}
	
	public void clear() {
		WoolCount=0;
	}
}
