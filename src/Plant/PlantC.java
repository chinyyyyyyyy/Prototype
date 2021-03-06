package Plant;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class PlantC extends Plant {
	private final int SEED_DURATION = 3;
	private final int SPOURT_DURATION = 7;
	public static final int SeedCost = 100;
	private static final int FruitCost = 300;
	private static int PlantCount = 0;
	private static final ImagePattern seedstate = new ImagePattern(
			new Image(ClassLoader.getSystemResource("seedstate.png").toString()));
	private static final ImagePattern wateredseedstate = new ImagePattern(
			new Image(ClassLoader.getSystemResource("wateredseedstate.png").toString()));
	private static final ImagePattern sproutstate = new ImagePattern(
			new Image(ClassLoader.getSystemResource("cucumbersprout.png").toString()));
	private static final ImagePattern wateredsproutstate = new ImagePattern(
			new Image(ClassLoader.getSystemResource("wateredcucumbersprout.png").toString()));
	private static final ImagePattern matualstate = new ImagePattern(
			new Image(ClassLoader.getSystemResource("cucumbermatual.png").toString()));
	private static final Image cucumber = new Image(ClassLoader.getSystemResource("cucumber.png").toString());

	public PlantC() {
		dayofgrowth = 0;
		name = "Cucumber";
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
		return cucumber;
	}

}
