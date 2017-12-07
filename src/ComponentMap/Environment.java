package ComponentMap;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Environment extends Rectangle {

	public Environment(int x, int y, int w, int h, Color c) {
		super(x, y, w, h);
		this.setFill(c);
	}

	public boolean checkShapeIntersection(Rectangle block) {
		boolean collisionDetected = false;
		Shape intersect = Shape.intersect(block, this);
		if (intersect.getBoundsInLocal().getWidth() != -1) {
			collisionDetected = true;

		}

		return collisionDetected;
	}


}
