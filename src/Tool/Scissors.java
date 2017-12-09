package Tool;

import Animal.Sheep;
import ComponentMap.ActionByToolAble;
import ComponentMap.SceneManager;
import javafx.scene.image.Image;

public class Scissors extends Tool {

	public static final int COST = 1000;
	public static final Image img = new Image(ClassLoader.getSystemResource("scissor.png").toString());

	public Scissors() {
		this.name = "Scissors";
	}

	@Override
	public void Action(ActionByToolAble a) {
		// TODO Auto-generated method stub
		if (a instanceof Sheep) {
			if (((Sheep) a).getProduceable()) {
					((Sheep) a).produce();
					SceneManager.warpTo(SceneManager.getSceneNumber());
			} else {
				System.out.println("You can't cut now.");
			}

		}
	}

	public boolean CheckUpgrade() {
		return false;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}
}
