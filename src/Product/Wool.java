package Product;

import javafx.scene.image.Image;

public class Wool extends Product{
	protected static int WoolCount=0;
	protected static final int COST = 300;
	private Image img = new Image(ClassLoader.getSystemResource("wool.png").toString());
	
	
	public Wool() {
		this.price=COST;
	}

	public static int getWoolCount() {
		return WoolCount;
	}

	public static void addWool() {
		WoolCount++;
	}

	public int getAmount() {
		return WoolCount;
	}
	
	public void clear() {
		WoolCount=0;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}
}
