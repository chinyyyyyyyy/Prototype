package Tool;

import ComponentMap.Feild;
import ComponentMap.Pond;
import ComponentMap.ActionByToolAble;

public class Hoe extends Tool{
	public Hoe() {
		this.name = "HOE";
		this.timeofuse = 0;
	}
	
	@Override
	public void Action(ActionByToolAble a) {
		// TODO Auto-generated method stub
		if(a instanceof Feild &&  ((Feild)a).canshovel()) {
			((Feild)a).shovel();
		}
	}
}
