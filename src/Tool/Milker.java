package Tool;

import Animal.Cow;
import ComponentMap.ActionByToolAble;

public class Milker extends Tool {

	public static final int COST = 2000;

	public Milker() {
		this.name = "Milker";
	}

	@Override
	public void Action(ActionByToolAble a) {
		// TODO Auto-generated method stub
		if (a instanceof Cow) {
			if (((Cow) a).getProduceable()) {
				((Cow) a).produce();
				System.out.println("Milk Success");
			}else {
				System.out.println("You can't milk now.");
			}
		}
	}

	public void CheckUpgrade() {
	}

}
