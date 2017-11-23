package Tool;

import ComponentMap.Feild;
import ComponentMap.ActionByToolAble;

public class Hammer extends Tool{
	public Hammer() {
		this.name = "HAMMER";
		this.timeofuse = 0;
	}
	
	@Override
	public void Action(ActionByToolAble a) {
		// TODO Auto-generated method stub
		if(a instanceof Feild) {
			if(((Feild) a).getStone() != null) {
				((Feild) a).delStone();
			}
		}
		
	}
}
