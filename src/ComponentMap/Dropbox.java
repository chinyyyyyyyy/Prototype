package ComponentMap;

import java.util.ArrayList;
import java.util.List;
import Logic.Backpack;
import Logic.InBackpack;
import Logic.updateEveryday;
import Plant.OnHandAble;
import Product.Egg;
import Product.Milk;
import Product.Product;
import Product.Wool;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Pair;

public class Dropbox extends ReceiveAction implements ActionByToolAble,updateEveryday {
	private static List<Pair<OnHandAble, Integer>> listindropbox = new ArrayList<>();

	private static int totalsell=0;

	public Dropbox(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		// TODO Auto-generated constructor stub
		listindropbox.add(new Pair<OnHandAble, Integer>(new Egg(),3));
		listindropbox.add(new Pair<OnHandAble, Integer>(new Wool(),4));
		listindropbox.add(new Pair<OnHandAble, Integer>(new Milk(),5));
		listindropbox.add(new Pair<OnHandAble, Integer>(new Egg(),3));
		listindropbox.add(new Pair<OnHandAble, Integer>(new Wool(),4));
		listindropbox.add(new Pair<OnHandAble, Integer>(new Milk(),5));
	}

	public static void addProduct(OnHandAble item) {
		boolean found = false;
		for (Pair<OnHandAble, Integer> i : listindropbox) {
			if (i.getKey().getName().equals(item.getName())) {
				int value = i.getValue();
				listindropbox.remove(i);
				listindropbox.add(new Pair<OnHandAble, Integer>(item, value + item.getAmount()));
				found = true;
				break;
			}
		}
		if (!found)
			listindropbox.add(new Pair<OnHandAble, Integer>(item,item.getAmount()));
		System.out.println(listindropbox);
	}

	@Override
	public void checkAction(Rectangle r) {
		Shape intersect = Shape.intersect(r, this);
		double wi = intersect.getBoundsInLocal().getWidth();
		double hi = intersect.getBoundsInLocal().getHeight();
		double wa = r.getWidth();
		double ha = r.getHeight();
		if (wi >= 0.9 * wa && hi >= 0.9 * ha) {
			InBackpack item = Backpack.CheckItemOnHand();
			if (item instanceof Product) {
				((Product) item).Action(this);
			}
		}
	}

	public void updateafterendday() {
		for (Pair<OnHandAble, Integer> i : listindropbox) {
			setTotalsell(getTotalsell() + i.getValue()*i.getKey().getPrice());
		}
	}
	
	public static void clear() {
		listindropbox.clear();	
		setTotalsell(0);
	}

	public static int getTotalsell() {
		return totalsell;
	}

	public static void setTotalsell(int totalsell) {
		Dropbox.totalsell = totalsell;
	}
	
	public static List<Pair<OnHandAble, Integer>> getListindropbox() {
		return listindropbox;
	}
}
