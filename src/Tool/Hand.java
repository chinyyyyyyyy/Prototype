package Tool;

import ComponentMap.ActionByToolAble;
import ComponentMap.Dropbox;
import ComponentMap.Feild;
import Plant.OnHandAble;

public class Hand extends Tool {
	private OnHandAble onhand;

	public Hand() {
		onhand = null;
		this.name = "Hand";
		this.timeofuse = 0;
	}

	@Override
	public void Action(ActionByToolAble a) {
		// TODO Auto-generated method stub

		if (onhand == null && a instanceof Feild && ((Feild) a).getPlant() != null
				&& ((Feild) a).getPlant().checkState() == 2) {
			onhand = ((Feild) a).getPlant();
			System.out.println(name + " is holding a " + onhand.getName());
			((Feild) a).resetPlant();
		}else if(onhand != null && a instanceof Dropbox ) {
			((Dropbox) a).addProduct(onhand) ;
			onhand = null;
		}
	}

	public void cry() {
		if (onhand == null) {
			System.out.println(name);
		} else {
			System.out.println(name + " is holding a " + onhand.getName());
		}
	}
	
	public void CheckUpgrade() {
	}
}

