package Plant;

import javafx.scene.paint.Color;

public abstract class Plant {
	protected int dayofgrowth;
	
	public void addDayOfGrowth() {
		dayofgrowth+= 1;
		System.out.println(dayofgrowth);
	}
	

	public abstract int checkState();

}


	
