package Product;

public class Egg extends Product{

	protected static int EggCount=0;
	protected static final int COST = 100;
	
	public Egg() {
	}

	public static int getEggCount() {
		return EggCount;
	}

	public static void addEgg() {
		EggCount++;
	}
}
