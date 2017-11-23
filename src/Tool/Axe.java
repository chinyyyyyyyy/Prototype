package Tool;

import ComponentMap.Feild;
import Map.ActionByToolAble;

public class Axe extends Tool {
	public Axe() {
		this.name = "AXE";
		this.timeofuse = 0;
	}

	@Override
	public void Action(ActionByToolAble a) {
		// TODO Auto-generated method stub
		if(a instanceof Feild) {
			if(((Feild) a).getWood() != null) {
				((Feild) a).delWood();
			}
		}
		
	}
	


}
