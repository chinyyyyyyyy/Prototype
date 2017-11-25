package Product;

public class Wool extends Product{
	protected static int WoolCount=0;
	protected static final int COST = 100;
	
	public Wool() {
	}

	public static int getWoolCount() {
		return WoolCount;
	}

	public static void addWool() {
		WoolCount++;
	}
}
