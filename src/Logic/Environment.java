package Logic;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Environment {
	private static Rectangle[] nodes =
			{new Rectangle(0, 0, 10,720),
    		new Rectangle(10, 0, 1270,10),
    		new Rectangle(1270, 10, 10,710),
    		new Rectangle(10,710, 1270,10),
    		new Rectangle(635, 0, 10,360)
			};
	
	public static boolean checkShapeIntersection(Shape block) {
	    boolean collisionDetected = false;
	    for (Shape static_bloc : nodes) {
	      if (static_bloc != block) {
	        Shape intersect = Shape.intersect(block, static_bloc);
	        if (intersect.getBoundsInLocal().getWidth() != -1) {
	          collisionDetected = true;
	        }
	      }
	    }

	    if (collisionDetected) {
	      return true;
	    } else {
	      return false;
	    }
	  }
	
	public static Rectangle[] getEnvironment() {
		return nodes;
	}

	
}
