package Plant;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

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
	
	 public abstract ImagePattern checkState();
	 
	 public abstract ImagePattern getWateredstate();
	
	@Override 
	public String toString() {
		return name;
	}

}


	
