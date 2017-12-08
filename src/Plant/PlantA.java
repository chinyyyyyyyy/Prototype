package Plant;

public class PlantA extends Plant {
	private final int SEED_DURATION = 2;
	private final int SPOURT_DURATION = 4;
	public static final int SeedCost = 50;
	public static final int FruitCost = 80;
	protected static int PlantCount=0;

	public PlantA() {
		dayofgrowth = 0;
		name = "PlantA";
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
		if (dayofgrowth >= SPOURT_DURATION) {
			return 2;
		} else if (dayofgrowth >= SEED_DURATION) {
			return 1;
		} else {
			return 0;
		}
	}
}
