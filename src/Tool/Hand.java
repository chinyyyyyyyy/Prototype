package Tool;

import ComponentMap.ActionByToolAble;
import ComponentMap.Dropbox;
import ComponentMap.Feild;
import Logic.InBackpack;
import Plant.OnHandAble;

public class Hand implements InBackpack {
	private static OnHandAble onhand;
	private String name;

	public Hand() {
		onhand = null;
		this.name = "Hand";
	}

	@Override
	public void Action(ActionByToolAble a) {
		// TODO Auto-generated method stub
//		if (onhand == null && a instanceof Feild && ((Feild) a).getPlant() != null
//				&& ((Feild) a).getPlant().checkState() == 2) {
//			onhand = ((Feild) a).getPlant();
//			System.out.println(name + " is holding a " + onhand.getName());
//			((Feild) a).resetPlant();
//		}else 
		
		if(onhand != null && a instanceof Dropbox ) {
			Dropbox.addProduct(onhand) ;
			onhand = null;
		}
	}

	public void cry() {
		if (onhand == null) {
			System.out.println(name);
		} else {
			System.out.println(name + " is holding " + onhand.getName());
		}
	}
	
	public void CheckUpgrade() {
	}
	
	public static void setOnhand(OnHandAble  a) {
		onhand = a;
	}
}

