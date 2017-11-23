package ComponentMap;

import Logic.Backpack;
import Logic.World;
import Map.ActionByToolAble;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Feild extends ReceiveAction implements ActionByToolAble {
	private Stone stone = null;
	private Wood wood = null;
	
	public Feild(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}
	
	public void setStone(Stone s) {
		this.stone = s;
		update();
	}
	
	public Stone getStone(){
		return stone;
	}
	
	public void delStone() {
		this.stone = null;
		update();
	}
	
	public void setWood(Wood w) {
		this.wood = w;
		update();
	}
	
	public Wood getWood(){
		return wood;
	}
	
	public void delWood() {
		this.wood = null;
		update();
	}
	
	public void update() {
		if(wood != null) {
			this.setFill(Color.SADDLEBROWN);
		}else if(stone != null) {
			this.setFill(Color.GRAY);
		}else {
			this.setFill(Color.DARKGOLDENROD);
		}
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
