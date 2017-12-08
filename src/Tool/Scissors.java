package Tool;

import Animal.Sheep;
import ComponentMap.ActionByToolAble;
import ComponentMap.SceneManager;

public class Scissors extends Tool {
	
	public static final int COST = 1000;

	public Scissors() {
		this.name = "Scissors";
	}

	@Override
	public void Action(ActionByToolAble a) {
		// TODO Auto-generated method stub
		if (a instanceof Sheep) {
			if (((Sheep) a).getProduceable()) {
				((Sheep) a).produce();
				System.out.println("Cut Success");
				SceneManager.warpTo(SceneManager.getSceneNumber());
			}else {
				System.out.println("You can't cut now.");
			}
				
		}
	}

	public boolean CheckUpgrade() {
		return false;
	}
}
