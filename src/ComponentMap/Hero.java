package ComponentMap;

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
import javafx.util.Pair;

public class Hero {
	private Rectangle unitblock;
	private List<Rectangle> actionblock = new ArrayList<>();
	private final static double KEYBOARD_MOVEMENT_DELTA = 0.00075;
	private int acpos = 0;
	private List<Environment> env = new ArrayList<>();
	private List<ReceiveAction> re = new ArrayList<>();
	private List<Pair<Rectangle,Integer>> WarpList = new ArrayList<>();

	public Hero(Scene scene, int startx, int starty, List<Environment> e, List<ReceiveAction> re) {

		unitblock = new Rectangle(startx, starty, 75, 75);
		unitblock.setFill(Color.AQUA);

		Rectangle abl = new Rectangle(startx + unitblock.getWidth(), starty, 75, 75);
		Rectangle abu = new Rectangle(startx, starty - unitblock.getHeight(), 75, 75);
		Rectangle abr = new Rectangle(startx - unitblock.getWidth(), starty, 75, 75);
		Rectangle abd = new Rectangle(startx, starty + unitblock.getHeight(), 75, 75);
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
		moveunitblockOnKeyPress(scene);

		env = e;
		this.re = re;
	}

	public void moveunitblockOnKeyPress(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.UP)) {
					turnTo(1);
					moveUp(true);

				} else if (event.getCode().equals(KeyCode.RIGHT)) {
					turnTo(0);
					moveRight(true);

				} else if (event.getCode().equals(KeyCode.DOWN)) {
					turnTo(3);
					moveUp(false);

				} else if (event.getCode().equals(KeyCode.LEFT)) {
					turnTo(2);
					moveRight(false);

				} else if (event.getCode().equals(KeyCode.Z)) {
					ReceiveAction.checkAction(re, getActiveBlock());
				}
			}
		});
	}

	class Delta {
		double x, y;
	}

	private void moveUp(boolean up) {
		if (up == true) {
			for (int i = 0; i < 10000; i++) {
				unitblock.setLayoutY(unitblock.getLayoutY() - KEYBOARD_MOVEMENT_DELTA);
				for (Rectangle r : actionblock) {
					r.setLayoutY(r.getLayoutY() - KEYBOARD_MOVEMENT_DELTA);
				}
			}
			
			if(SceneManager.CheckOnWarpBlock(WarpList,unitblock)!=-1){
				SceneManager.warpTo(SceneManager.CheckOnWarpBlock(WarpList,unitblock));
			}
			
			if (Environment.checkShapeIntersection(env, unitblock)) {
				for (int i = 0; i < 10000; i++) {
					unitblock.setLayoutY(unitblock.getLayoutY() + KEYBOARD_MOVEMENT_DELTA);
					for (Rectangle r : actionblock) {
						r.setLayoutY(r.getLayoutY() + KEYBOARD_MOVEMENT_DELTA);
					}
				}

			}
		} else {
			for (int i = 0; i < 10000; i++) {
				unitblock.setLayoutY(unitblock.getLayoutY() + KEYBOARD_MOVEMENT_DELTA);
				for (Rectangle r : actionblock) {
					r.setLayoutY(r.getLayoutY() + KEYBOARD_MOVEMENT_DELTA);
				}
			}
			
			if(SceneManager.CheckOnWarpBlock(WarpList,unitblock)!=-1){
				SceneManager.warpTo(SceneManager.CheckOnWarpBlock(WarpList,unitblock));
			}
			
			if (Environment.checkShapeIntersection(env,unitblock)) {
				for (int i = 0; i < 10000; i++) {
					unitblock.setLayoutY(unitblock.getLayoutY() - KEYBOARD_MOVEMENT_DELTA);
					for (Rectangle r : actionblock) {
						r.setLayoutY(r.getLayoutY() - KEYBOARD_MOVEMENT_DELTA);
					}
				}
			}
		}
	}

	private void moveRight(boolean right) {
		if (right == true) {
			for (int i = 0; i < 10000; i++) {
				unitblock.setLayoutX(unitblock.getLayoutX() + KEYBOARD_MOVEMENT_DELTA);
				for (Rectangle r : actionblock) {
					r.setLayoutX(r.getLayoutX() + KEYBOARD_MOVEMENT_DELTA);
				}
			}
			if(SceneManager.CheckOnWarpBlock(WarpList,unitblock)!=-1){
				SceneManager.warpTo(SceneManager.CheckOnWarpBlock(WarpList,unitblock));
			}
			
			
			if (Environment.checkShapeIntersection(env,unitblock)) {
				for (int i = 0; i < 10000; i++) {
					unitblock.setLayoutX(unitblock.getLayoutX() - KEYBOARD_MOVEMENT_DELTA);
					for (Rectangle r : actionblock) {
						r.setLayoutX(r.getLayoutX() - KEYBOARD_MOVEMENT_DELTA);
					}
				}
			}
			
		} else {
			for (int i = 0; i < 10000; i++) {
				unitblock.setLayoutX(unitblock.getLayoutX() - KEYBOARD_MOVEMENT_DELTA);
				for (Rectangle r : actionblock) {
					r.setLayoutX(r.getLayoutX() - KEYBOARD_MOVEMENT_DELTA);
				}
			}
				
			if(SceneManager.CheckOnWarpBlock(WarpList,unitblock)!=-1){
				SceneManager.warpTo(SceneManager.CheckOnWarpBlock(WarpList,unitblock));
			}
			
			if (Environment.checkShapeIntersection(env, unitblock)) {
				for (int i = 0; i < 10000; i++) {
					unitblock.setLayoutX(unitblock.getLayoutX() + KEYBOARD_MOVEMENT_DELTA);
					for (Rectangle r : actionblock) {
						r.setLayoutX(r.getLayoutX() + KEYBOARD_MOVEMENT_DELTA);
					}
				}
			}
		}
	}

	private void turnTo(int pos) {
		if (acpos != pos) {
			for (int i = 0; i < actionblock.size(); i++) {
				if (i != pos) {
					actionblock.get(i).setVisible(false);
				} else {
					actionblock.get(i).setVisible(true);
				}
			}
			acpos = pos;
		}
	}

	public Rectangle getUnitblock() {
		return unitblock;
	}

	public List<Rectangle> getActionblock() {
		return actionblock;
	}

	public Rectangle getActiveBlock() {

		Rectangle activeblock = null;
		for (Rectangle r : actionblock) {
			if (r.isVisible()) {
				activeblock = r;
			}
		}
		return activeblock;
	}

	public void setWarpBlockList(List<Pair<Rectangle,Integer>> wl) {
		WarpList = wl;
	}
}
