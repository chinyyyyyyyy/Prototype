package ComponentMap;

import Logic.Backpack;
import Logic.updateEveryday;
import Map.Farm;
import Plant.Plant;
import ComponentMap.ActionByToolAble;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Feild extends ReceiveAction implements ActionByToolAble, updateEveryday {
	private Stone stone = null;
	private Wood wood = null;
	private Boolean isWaterToday = false;
	private Boolean isshovel = false;
	private Plant plant = null;
	private int xpos;
	private int ypos;
	private int width;
	private int height;
	private Environment env;
	private Image woodimg = new Image(ClassLoader.getSystemResource("single wood2.png").toString());
	private Image stoneimg = new Image(ClassLoader.getSystemResource("single stone2.png").toString());
	private Image soil1 = new Image(ClassLoader.getSystemResource("soil1.png").toString());

	//// --------------------Constructor-----------------------//

	public Feild(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
		xpos = x;
		ypos = y;
		width = w;
		height = h;
		update();

	}

	public Feild(int x, int y, int w, int h, Color c, Stone stone) {
		super(x, y, w, h, c);
		xpos = x;
		ypos = y;
		width = w;
		height = h;
		env = new Environment(x, y, w, h, Color.BISQUE);
		Farm.getFarmEnvList().add(env);
		this.stone = stone;
		update();
	}

	public Feild(int x, int y, int w, int h, Color c, Wood wood) {
		super(x, y, w, h, c);
		xpos = x;
		ypos = y;
		width = w;
		height = h;
		env = new Environment(x, y, w, h, Color.BISQUE);
		Farm.getFarmEnvList().add(env);
		this.wood = wood;
		update();
	}

	//// --------------------Method of obstacle-----------------------///

	public void setStone(Stone s) {
		this.stone = s;
		env = new Environment(xpos, ypos, width, height, Color.BISQUE);
		env.setVisible(false);
		Farm.getFarmEnvList().add(env);
		update();
	}

	public Stone getStone() {
		return stone;
	}

	public void delStone() {
		this.stone = null;
		Farm.getFarmEnvList().remove(env);
		env = null;
		update();
	}

	public void setWood(Wood wood) {
		this.wood = wood;
		env = new Environment(xpos, ypos, width, height, Color.BISQUE);
		env.setVisible(false);
		Farm.getFarmEnvList().add(env);
		update();
	}

	public Wood getWood() {
		return wood;
	}

	public void delWood() {
		this.wood = null;
		Farm.getFarmEnvList().remove(env);
		env = null;
		update();
	}

	//// --------------------Method of Watering-----------------------///

	public void watering() {
		isWaterToday = true;
		update();
	}

	public Boolean canwater() {
		if (stone == null && wood == null && plant != null && plant.isHarvestable() == false) {
			return true;
		}
		return false;
	}

	public void resetwaternewday() {
		isWaterToday = false;
		update();
	}

	//// --------------------Method of shovel-----------------------///
	public void shovel() {
		isshovel = true;
		update();
	}

	public Boolean canshovel() {
		if (stone == null && wood == null && plant == null) {
			return true;
		}
		return false;
	}

	//// --------------------Method of Plant-----------------------///
	public Plant getPlant() {
		return plant;
	}

	public boolean canplant() {
		if (plant == null && stone == null && isshovel)
			return true;
		return false;
	}

	public void setPlant(Plant p) {
		plant = p;
		isshovel = false;
		this.setFill(plant.checkState());
		System.out.println(plant.getName());

	}

	public void resetPlant() {
		plant = null;
		isshovel = false;
		update();
	}

	public void update() {
		if (wood != null) {
			this.setFill(new ImagePattern(woodimg));
		} else if (stone != null) {
			this.setFill(new ImagePattern(stoneimg));
		} else if (isWaterToday == true) {
			this.setOpacity(1);
			if(plant != null) {
				this.setFill(plant.getWateredstate());
			}
		} else if (isshovel == true) {
			this.setOpacity(1);
			this.setFill(new ImagePattern(soil1));
		} else {
			this.setOpacity(0);
		}
	}

	// -------------------update-------------------------//
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

	public void updateafterendday() {
		if (plant != null) {
			if (isWaterToday == true)
				plant.addDayOfGrowth();
			isWaterToday = false;
			this.setOpacity(1);
			this.setFill(plant.checkState());
			
		}
	}

}
