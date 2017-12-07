package Tool;

import Animal.Sheep;
import ComponentMap.ActionByToolAble;

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
			}else {
				System.out.println("You can't cut now.");
			}
				
		}
	}

	public void CheckUpgrade() {
	}
}
