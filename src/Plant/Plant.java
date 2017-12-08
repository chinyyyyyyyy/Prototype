package Plant;

<<<<<<< HEAD
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

=======
>>>>>>> aa7b0763d113a7f8f87e2716088d974a0a001958
public abstract class Plant implements OnHandAble {
	protected int dayofgrowth;
	protected String name;
	protected int amount;
	protected int price;
	
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
	
	public int getAmount() {
		return amount;
	}
	
	public int getPrice() {
		return price;
	}

}


	
