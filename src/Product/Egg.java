package Product;

public class Egg extends Product{

	protected static int EggCount=0;
	protected static final int COST = 100;
	
	public Egg() {
		this.price=COST;
	}

	public static int getEggCount() {
		return EggCount;
	}

	public static void addEgg() {
		EggCount++;
	}
	
	public int getAmount() {
		return EggCount;
	}
	
	public void clear() {
		EggCount=0;
	}
}
