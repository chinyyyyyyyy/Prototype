package ComponentMap;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Environment extends Rectangle {
	
	public Environment(int x,int y ,int w ,int h ,Color c) {
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
	
	
}
