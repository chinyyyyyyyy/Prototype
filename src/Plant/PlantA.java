package Plant;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class PlantA extends Plant {
	private final int SEED_DURATION = 2;
	private final int SPOURT_DURATION = 4;
	public static final int SeedCost = 50;
	public static final int FruitCost = 80;
	protected static int PlantCount = 0;
	public static final ImagePattern seedstate = new ImagePattern(
			new Image(ClassLoader.getSystemResource("seedstate.png").toString()));
	public static final ImagePattern wateredseedstate = new ImagePattern(
			new Image(ClassLoader.getSystemResource("wateredseedstate.png").toString()));
	public static final ImagePattern sproutstate = new ImagePattern(
			new Image(ClassLoader.getSystemResource("radishsprout.png").toString()));
	public static final ImagePattern wateredsproutstate = new ImagePattern(
			new Image(ClassLoader.getSystemResource("wateredradishsprout.png").toString()));
	public static final ImagePattern matualstate = new ImagePattern(
			new Image(ClassLoader.getSystemResource("radishmatual.png").toString()));
	public static final Image radish = new Image(ClassLoader.getSystemResource("radish.png").toString());

	public PlantA() {
		dayofgrowth = 0;
		name = "Radish";
		this.price = FruitCost;
		this.harvestable=false;
	}

	public void Harvest() {
		PlantCount++;
		System.out.println("Harvest Success");
		this.harvestable=false;
	}

	public int getAmount() {
		return PlantCount;
	}

	public void clear() {
		PlantCount = 0;
	}

	@Override
	public ImagePattern checkState() {
		if (dayofgrowth > SPOURT_DURATION) {
			this.harvestable=true;
			return matualstate;
		} else if (dayofgrowth > SEED_DURATION) {
			return sproutstate;
		} else {
			return seedstate;
		}
	}

	@Override
	public ImagePattern getWateredstate() {
		if (dayofgrowth > SEED_DURATION) {
			return wateredsproutstate;
		} else {
			return wateredseedstate;
		}
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return radish;
	}
}
