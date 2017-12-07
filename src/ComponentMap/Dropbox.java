package ComponentMap;

import java.util.ArrayList;
import java.util.List;

import Logic.Backpack;
import Plant.OnHandAble;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Dropbox extends ReceiveAction implements ActionByToolAble{
	private List<OnHandAble> listindropbox = new ArrayList<>();

	public Dropbox(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		// TODO Auto-generated constructor stub
	}
	
	public void addProduct(OnHandAble item) {
		listindropbox.add(item);
		System.out.println(listindropbox);
	}
	
	@Override
	public void checkAction(Rectangle r) {
		Shape intersect = Shape.intersect(r, this);
		double wi = intersect.getBoundsInLocal().getWidth();
		double hi = intersect.getBoundsInLocal().getHeight();
		double wr = this.getWidth();
		double hr = this.getHeight();
		double wa = r.getWidth();
		double ha = r.getHeight();
		if (wi >= 0.9 * wa && hi >= 0.9 * ha) {
			Backpack.CheckItemOnHand().Action(this);
		}
	}

}
