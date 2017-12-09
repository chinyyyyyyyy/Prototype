package Product;

import javafx.scene.image.Image;

public class Milk extends Product{
	
	protected static int MilkCount=0;
	protected static final int COST = 300;
	private Image img = new Image(ClassLoader.getSystemResource("milik.png").toString());
	
	public Milk() {
		this.price=COST;
	}

	public static int getMilkCount() {
		return MilkCount;
	}

	public static void addMilk() {
		MilkCount++;
	}
	
	public int getAmount() {
		return MilkCount;
	}
	
	public void clear() {
		MilkCount=0;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}
}
