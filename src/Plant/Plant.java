package Plant;

import javafx.scene.paint.Color;

public abstract class Plant implements OnHandAble {
	protected int dayofgrowth;
	protected String name;
	
	public void addDayOfGrowth() {
		dayofgrowth+= 1;
		System.out.println(dayofgrowth);
	}
	
	public String getName() {
		return this.name;
	}
	
	public abstract int checkState();
	
	@Override 
	public String toString() {
		return name;
	}

}


	
