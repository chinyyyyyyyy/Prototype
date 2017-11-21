package Logic;

public class World {
	private static int days;
	
	public World() {
		days = 1;
	}
	
	public static void nextDay() {
		days+=1;
		System.out.println("Today is "+days);
	}
	
	public int getDay() {
		return days;
	}
}
