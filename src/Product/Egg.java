package Product;

import javafx.scene.image.Image;

public class Egg extends Product{

	protected static int EggCount=0;
	protected static final int COST = 100;
	private Image img = new Image(ClassLoader.getSystemResource("egg.png").toString());
	
	public Egg() {
		this.price=COST;
	}

	public static int getEggCount() {
		return EggCount;
	}

	public static void addEgg() {
		EggCount++;
	}
	
	public int getAmount() {
		return EggCount;
	}
	
	public void clear() {
		EggCount=0;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}
	
}
