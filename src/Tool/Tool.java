package Tool;

import Map.ActionByToolAble;

public abstract class Tool {
	protected String name; 
	protected int timeofuse; 
	
	
	public void cry() {
		System.out.println(name);
	}
	
	public abstract void Action(ActionByToolAble a);
	
}
