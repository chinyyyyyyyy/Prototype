package Tool;

import Animal.Animal;
import ComponentMap.ActionByToolAble;
import ComponentMap.StackAble;
import Logic.Backpack;
import Logic.InBackpack;
import javafx.scene.image.Image;

public class Hay implements InBackpack, StackAble {
	public static final Image img = new Image(ClassLoader.getSystemResource("hay.png").toString());
	protected static int HayCount = 0;

	public Hay() {
	}

	public void cry() {
		System.out.println(this.getClass().getSimpleName());
	}

	public static int getHayCount() {
		return HayCount;
	}

	public static void addHay() {
		HayCount++;
	}

	public void Action(ActionByToolAble a) {
		if (a instanceof Animal) {
			if (((Animal) a).getFeedable()) {
				((Animal) a).eat();
				HayCount--;
				System.out.println("Feed Success");
				if(HayCount==0) Backpack.deleteItem();
			} else
				System.out.println("You already feed it.");
		}
	}

	public int getAmount() {
		return HayCount;
	}
	
	public void clear() {
		HayCount=0;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}

}
