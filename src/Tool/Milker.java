package Tool;

import Animal.Cow;
import Logic.SceneManager;
import Map.CowBarn;
import javafx.scene.image.Image;

public class Milker extends Tool {

	public static final int COST = 2000;
	public static final Image img = new Image(ClassLoader.getSystemResource("milker.png").toString());
	private static CowBarn scene = (CowBarn) SceneManager.getListMap().get(3);
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
				scene.chat("You can't milk now.");
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
