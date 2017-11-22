package Tool;

public class WateringCan extends Tool{
	private final int MAX_CAPACITY = 10;
	private int water_level ;
	
	public WateringCan() {
		this.name = "WATERINGCAN";
		this.timeofuse = 0;
		water_level = 0;
	}
}
