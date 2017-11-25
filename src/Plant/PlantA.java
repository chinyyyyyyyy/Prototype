package Plant;

<<<<<<< HEAD
public class PlantA  extends Plant{
	private final int SEED_DURATION =  1;
	private final int SPOURT_DURATION =  2;
	
	public PlantA() {
		dayofgrowth = 0;
		name = "PlantA";
		}
	
=======
public class PlantA extends Plant {
	private final int SEED_DURATION = 2;
	private final int SPOURT_DURATION = 4;
	public static int SeedCount = 0;
	public static final int SeedCost = 50;

	public PlantA() {
		dayofgrowth = 0;
	}

>>>>>>> 639ce3b53fc963fdb513a38c2f3c2e8e785093cb
	@Override
	public int checkState() {
		if (dayofgrowth >= SPOURT_DURATION) {
			return 2;
<<<<<<< HEAD
		}else if(dayofgrowth >= SEED_DURATION) {
=======
		} else if (dayofgrowth > SEED_DURATION) {
>>>>>>> 639ce3b53fc963fdb513a38c2f3c2e8e785093cb
			return 1;
		} else {
			return 0;
		}
	}

	public static void addSeed() {
		SeedCount++;
	}
}
