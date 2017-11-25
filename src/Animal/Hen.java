package Animal;

import Product.Egg;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Hen extends Animal{

	protected String name;
	protected static int HenCount=0;
	protected int age;
	protected int love;
	
	public Hen(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		this.age=1;
		this.love=0;
	}
	
	public void cry() {
		System.out.println("Pokkkk Pokk Pokkk");
	}
	
	public void produce() {
		Egg.addEgg();
	}

	public void checkAction(Rectangle r) {
		Shape intersect = Shape.intersect(r, this);
		double wi = intersect.getBoundsInLocal().getWidth();
		double hi = intersect.getBoundsInLocal().getHeight();
		double wa = r.getWidth();
		double ha = r.getHeight();
		if (wi >= 0.9 * wa && hi >= 0.9 * ha) {
			cry();
			this.love++;
//			System.out.println(""+this.love);
//			produce();
//			System.out.println(Milk.getMilkCount());
		}
	}
	
	public static void addHen() {
		HenCount++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getHenCount() {
		return HenCount;
	}

	public int getAge() {
		return age;
	}

	public int getLove() {
		return love;
	}

}
