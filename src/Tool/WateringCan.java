package Tool;

import ComponentMap.Feild;
import ComponentMap.Pond;
import ComponentMap.ActionByToolAble;

public class WateringCan extends Tool{
	private final int MAX_CAPACITY = 10;
	private int water_level ;
	
	public WateringCan() {
		this.name = "WATERINGCAN";
		this.timeofuse = 0;
		water_level = 0;
	}
	
	@Override
	public void Action(ActionByToolAble a) {
		// TODO Auto-generated method stub
		if(a instanceof Pond) {
			water_level = MAX_CAPACITY;
			System.out.println("Water level MAX");
		}else if(a instanceof Feild && water_level >0 &&  ((Feild)a).canwater()){
	
			((Feild) a).watering();
			water_level--;
		}
	}
	
	public void cry() {
		System.out.println(name + " has " + water_level );
	}
}
