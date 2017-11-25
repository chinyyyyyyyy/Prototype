package Logic;

import java.util.ArrayList;
import java.util.List;

public class World {
	private static int days;
	private static List<updateEveryday> listupdate = new ArrayList<>();
	public World() {
		days = 1;
	}
	
	public static void nextDay() {
		days+=1;
		for(updateEveryday i : listupdate) {
			i.updateafterendday();
		}
		System.out.println("Today is "+days);
		
	}
	
	public int getDay() {
		return days;
	}
	
	public static List<updateEveryday> getListUpdate(){
		return listupdate;
	}
}
