package Animal;

import Product.Milk;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Cow extends Animal{
	
	protected String name;
	protected static int CowCount=0;
	protected int age;
	protected int love;
	
	public Cow(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		this.age=1;
		this.love=0;
	}
	
	public void cry() {
		System.out.println("Mooooooooo");
	}
	
	public void produce() {
		Milk.addMilk();
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
	
	public static void addCow() {
		CowCount++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getCowCount() {
		return CowCount;
	}

	public int getAge() {
		return age;
	}

	public int getLove() {
		return love;
	}

}
