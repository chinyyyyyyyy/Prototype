package SpecialScene;

public abstract class BuyScene {
	public static String getThank() {
		return "Thank you very much ~";
	}
	
	public static String getThank2() {
		return "Hope to see you soon !";
	}
	
	public static String getQuestion() {
		return "What do you want today?";
	}

	public abstract void chat(String string);
}
