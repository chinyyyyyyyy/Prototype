package Plant;

public class PlantC extends Plant {
	private final int SEED_DURATION = 2;
	private final int SPOURT_DURATION = 4;
	public static final int SeedCost = 200;
	public static final int FruitCost = 300;	
	protected static int PlantCount=0;

	public PlantC() {
		dayofgrowth = 0;
		name = "PantC";
		this.price = FruitCost;
	}
	public void Harvest() {
		PlantCount++;
		System.out.println("Harvest Success");
	}

	public int getAmount() {
		return PlantCount;
	}
	
	public void clear() {
		PlantCount = 0;
	}

	@Override
	public int checkState() {
		if (dayofgrowth > SPOURT_DURATION) {
			return 2;
		} else if (dayofgrowth > SEED_DURATION) {
			return 1;
		} else {
			return 0;
		}
	}

}
