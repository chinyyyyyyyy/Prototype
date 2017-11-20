package ComponentMap;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ReceiveAction extends Rectangle{
	
	public ReceiveAction(int x,int y ,int w ,int h ,Color c) {
		super(x,y,w,h);
		this.setFill(c);
	}
	
	public static void checkAction(List<ReceiveAction> re,Rectangle r) {
	    for (Shape receive : re) {
	        Shape intersect = Shape.intersect(r, receive);
	        if (intersect.getBoundsInLocal().getWidth()>50 && intersect.getBoundsInLocal().getHeight()>50) {
	        	System.out.println("Action is on" + re.indexOf(receive));
	        }
	      }

	}		

}
