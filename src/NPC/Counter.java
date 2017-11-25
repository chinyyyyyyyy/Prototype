package NPC;

import java.util.ArrayList;
import java.util.List;
import Animal.Animal;
import ComponentMap.NPC;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Counter extends NPC {

	public static List<Animal> animal = new ArrayList<Animal>();
	public static List<Animal> hen = new ArrayList<Animal>();

	public Counter(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	public void Welcome() {
	}

	public void checkAction(Rectangle r) {
<<<<<<< HEAD
		Shape intersect = Shape.intersect(r, this);
		double wi = intersect.getBoundsInLocal().getWidth();
		double hi = intersect.getBoundsInLocal().getHeight();
		double wa = r.getWidth();
		double ha = r.getHeight();
		Scanner in = new Scanner(System.in);
		if (wi >= 0.5 * wa && hi >= 0.5 * ha) {
			System.out.println("What do you want Today?");
			try {
				System.out.println("How many cow do you want?");
				int numcow = Integer.parseInt(in.nextLine());
				BuyCow(numcow);
				System.out.println("Buying Success");
				System.out.println("You have "+Cow.getCowCount()+" Cow.");
			}catch(IndexOutOfBoundsException e) {
				System.out.println("Your barn is full !!");
			}
			
			try {
				System.out.println("How many Sheep do you want?");
				int numsheep = Integer.parseInt(in.nextLine());
				BuySheep(numsheep);
				System.out.println("Buying Success");
				System.out.println("You have "+Sheep.getSheepCount()+" Sheep.");
			}catch(IndexOutOfBoundsException e) {
				System.out.println("Your barn is full !!");
			}
			
			try {
				System.out.println("How many Hen do you want?");
				int numhen = Integer.parseInt(in.nextLine());
				BuyHen(numhen);
				System.out.println("Buying Success");
				System.out.println("You have "+Hen.getHenCount()+" Hen.");
			}catch(IndexOutOfBoundsException e) {
				System.out.println("Your Coop is full !!");
			}
		
		}
		//SceneManager.warpTo(8);
=======
>>>>>>> 639ce3b53fc963fdb513a38c2f3c2e8e785093cb
	}

}
