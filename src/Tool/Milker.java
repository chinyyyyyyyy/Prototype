package Tool;

import Animal.Cow;
import ComponentMap.ActionByToolAble;
import ComponentMap.SceneManager;
import javafx.scene.image.Image;

public class Milker extends Tool {

	public static final int COST = 2000;
	public static final Image img = new Image(ClassLoader.getSystemResource("milker.png").toString());
	public Milker() {
		this.name = "Milker";
	}

	@Override
	public void Action(ActionByToolAble a) {
		// TODO Auto-generated method stub
		if (a instanceof Cow) {
			if (((Cow) a).getProduceable()) {
					((Cow) a).produce();
					SceneManager.warpTo(SceneManager.getSceneNumber());
			}else {
				System.out.println("You can't milk now.");
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
