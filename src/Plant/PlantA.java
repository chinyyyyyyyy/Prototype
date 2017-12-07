package Plant;

public class PlantA extends Plant {
	private final int SEED_DURATION = 2;
	private final int SPOURT_DURATION = 4;
	public static int SeedCount = 0;
	public static final int SeedCost = 50;

	public PlantA() {
		dayofgrowth = 0;
		name = "PlantA";
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

	public static void addSeed() {
		SeedCount++;
	}
}
