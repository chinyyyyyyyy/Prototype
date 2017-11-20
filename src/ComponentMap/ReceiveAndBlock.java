package ComponentMap;

import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ReceiveAndBlock extends Rectangle{
	
	public ReceiveAndBlock(int x,int y ,int w ,int h ,Color c) {
		super(x,y,w,h);
		this.setFill(c);
	}
	
	public static boolean checkShapeIntersection(List<Environment> nodes,Shape block) {
	    boolean collisionDetected = false;
	    for (Shape static_bloc : nodes) {
	        Shape intersect = Shape.intersect(block, static_bloc);
	        if (intersect.getBoundsInLocal().getWidth() != -1) {
	          collisionDetected = true;
	        }
	      
	    }

	    if (collisionDetected) {
	      return true;
	    } else {
	      return false;
	    }
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
