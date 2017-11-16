package Logic;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class Hero {
	private static Rectangle unitblock;
	private static List<Rectangle> actionblock = new ArrayList<>();
	private final int[] inset = { 300, 300, 75, 75 };
	private final static double KEYBOARD_MOVEMENT_DELTA = 10;
	private static int acpos = 0;

	public Hero(Scene scene) {
		unitblock = new Rectangle(inset[0], inset[1], inset[2], inset[3]);
		unitblock.setFill(Color.AQUA);
		Rectangle abl = new Rectangle(inset[0] + unitblock.getWidth(), inset[1], inset[2], inset[3]);
		Rectangle abu = new Rectangle(inset[0], inset[1] - unitblock.getHeight(), inset[2], inset[3]);
		Rectangle abr = new Rectangle(inset[0] - unitblock.getWidth(), inset[1], inset[2], inset[3]);
		Rectangle abd = new Rectangle(inset[0], inset[1] + unitblock.getHeight(), inset[2], inset[3]);
		abl.setOpacity(0.1);
		abu.setOpacity(0.1);
		abd.setOpacity(0.1);
		abr.setOpacity(0.1);
		
		actionblock.add(abl);
		actionblock.add(abu);
		actionblock.add(abr);
		actionblock.add(abd);
		abl.setVisible(true);
		abu.setVisible(false);
		abr.setVisible(false);
		abd.setVisible(false);
		moveunitblockOnKeyPress(scene, unitblock, actionblock);
	}

	public static void moveunitblockOnKeyPress(Scene scene, Rectangle unit, List<Rectangle> actionblock2) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.UP)) {
					turnTo(1,actionblock2);
					moveUp(true, unit, actionblock2);

				} else if (event.getCode().equals(KeyCode.RIGHT)) {
					turnTo(0,actionblock2);
					moveRight(true, unit, actionblock2);

				} else if (event.getCode().equals(KeyCode.DOWN)) {
					turnTo(3,actionblock2);
					moveUp(false, unit, actionblock2);

				} else if (event.getCode().equals(KeyCode.LEFT)) {
					turnTo(2,actionblock2);
					moveRight(false, unit, actionblock2);

				}else if(event.getCode().equals(KeyCode.Z)) {
					ReceiveActionBlock.checkAction(getActiveBlock());
				}
			}
		});
	}

	class Delta {
		double x, y;
	}

	private static void moveUp(boolean up, Rectangle unit, List<Rectangle> actionblock2) {
		if (up == true) {
			unit.setLayoutY(unit.getLayoutY() - KEYBOARD_MOVEMENT_DELTA);
			for (Rectangle r : actionblock2) {
				r.setLayoutY(r.getLayoutY() - KEYBOARD_MOVEMENT_DELTA);
			}
			if (Environment.checkShapeIntersection(unit)) {
				unit.setLayoutY(unit.getLayoutY() + KEYBOARD_MOVEMENT_DELTA);
				for (Rectangle r : actionblock2) {
					r.setLayoutY(r.getLayoutY() + KEYBOARD_MOVEMENT_DELTA);
				}

			}
		} else {
			unit.setLayoutY(unit.getLayoutY() + KEYBOARD_MOVEMENT_DELTA);
			for (Rectangle r : actionblock2) {
				r.setLayoutY(r.getLayoutY() + KEYBOARD_MOVEMENT_DELTA);
			}
			if (Environment.checkShapeIntersection(unit)) {
				unit.setLayoutY(unit.getLayoutY() - KEYBOARD_MOVEMENT_DELTA);
				for (Rectangle r : actionblock2) {
					r.setLayoutY(r.getLayoutY() - KEYBOARD_MOVEMENT_DELTA);
				}
			}
		}
	}

	private static void moveRight(boolean right, Rectangle unit, List<Rectangle> actionblock2) {
		if (right == true) {
			unit.setLayoutX(unit.getLayoutX() + KEYBOARD_MOVEMENT_DELTA);
			for (Rectangle r : actionblock2) {
				r.setLayoutX(r.getLayoutX() + KEYBOARD_MOVEMENT_DELTA);
			}
			if (Environment.checkShapeIntersection(unit)) {
				unit.setLayoutX(unit.getLayoutX() - KEYBOARD_MOVEMENT_DELTA);
				for (Rectangle r : actionblock2) {
					r.setLayoutX(r.getLayoutX() - KEYBOARD_MOVEMENT_DELTA);
				}
			}
		} else {
			unit.setLayoutX(unit.getLayoutX() - KEYBOARD_MOVEMENT_DELTA);
			for (Rectangle r : actionblock2) {
				r.setLayoutX(r.getLayoutX() - KEYBOARD_MOVEMENT_DELTA);
			}
			if (Environment.checkShapeIntersection(unit)) {
				unit.setLayoutX(unit.getLayoutX() + KEYBOARD_MOVEMENT_DELTA);
				for (Rectangle r : actionblock2) {
					r.setLayoutX(r.getLayoutX() + KEYBOARD_MOVEMENT_DELTA);
				}
			}
		}
	}
	
	private static void turnTo(int pos,List<Rectangle> acb) {
		if(acpos != pos) {
			for(int i = 0 ;i < acb.size();i++) {
				if(i != pos) {
					acb.get(i).setVisible(false);
				}else {
					acb.get(i).setVisible(true);
				}
			}
			acpos = pos;
		}
	}

	public Rectangle getUnitblock() {
		return unitblock;
	}

	public static List<Rectangle> getActionblock() {
		return actionblock;
	}
	
	public static  Rectangle getActiveBlock() {
		Rectangle activeblock = null;
		for(Rectangle r : actionblock) {
			if (r.isVisible()) {
				activeblock = r;
			}
		}
		return activeblock;
	}
	
	
	
	
}
