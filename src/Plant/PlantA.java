package Plant;

public class PlantA  extends Plant{
	private final int SEED_DURATION =  1;
	private final int SPOURT_DURATION =  2;
	
	public PlantA() {
		dayofgrowth = 0;
		name = "PlantA";
		}
	
	@Override
	public int checkState() {
		if (dayofgrowth >= SPOURT_DURATION) {
			return 2;
		}else if(dayofgrowth >= SEED_DURATION) {
			return 1;
		}else {
			return 0;
		}
	}
}
