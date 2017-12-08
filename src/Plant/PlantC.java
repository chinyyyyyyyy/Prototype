package Plant;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class PlantC extends Plant {
	private final int SEED_DURATION = 2;
	private final int SPOURT_DURATION = 4;
	public static final int SeedCost = 200;
	public static final ImagePattern seedstate = new ImagePattern(new Image(ClassLoader.getSystemResource("seedstate.png").toString()));
	public static final ImagePattern wateredseedstate = new ImagePattern(new Image(ClassLoader.getSystemResource("wateredseedstate.png").toString()));
	public static final ImagePattern sproutstate = new ImagePattern(new  Image(ClassLoader.getSystemResource("cucumbersprout.png").toString()));
	public static final ImagePattern wateredsproutstate= new ImagePattern( new Image(ClassLoader.getSystemResource("wateredcucumbersprout.png").toString()));
	public static final ImagePattern matualstate = new ImagePattern(new Image(ClassLoader.getSystemResource("cucumbermatual.png").toString()));

	public PlantC() {
		dayofgrowth = 0;
		name = "Cucumber";
		name = "PantC";
		this.price=SeedCost;
	}

	@Override
	public  ImagePattern checkState() {
		if (dayofgrowth > SPOURT_DURATION) {
			return matualstate;
		} else if (dayofgrowth > SEED_DURATION) {
			return sproutstate;
		} else {
			return seedstate;
		}
	}
	
	public  ImagePattern getWateredstate() {
		if (dayofgrowth > SEED_DURATION) {
			return wateredsproutstate;
		} else {
			return wateredseedstate ;
		}
	}

}
