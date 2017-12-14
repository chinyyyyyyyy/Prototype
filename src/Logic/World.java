package Logic;

import java.util.ArrayList;
import java.util.List;
import ComponentMap.Dropbox;
import ComponentMap.Hero;
import Map.HasAnimal;
import Map.SetsSeneable;
import SpecialScene.BuyScene;
import javafx.scene.media.AudioClip;

public class World {
	private static boolean nextday=false;
	private static int days;
	private static List<updateEveryday> listupdate = new ArrayList<>();
	private static int money;
	private static boolean Buyable;
	private static List<String> season = new ArrayList<>();
	private static int ss = 0;
	public static AudioClip soundeffect = new AudioClip(ClassLoader.getSystemResource("roastersound.mp3").toString());
	
	public World() {
		days = 1;
		money = 50000;
		Buyable = true;
		season.add("Spring");
		season.add("Summer");
		season.add("Autumn");
		season.add("Winter");
	}

	public static void nextDay() {
		for (updateEveryday i : listupdate) {
			i.updateafterendday();
		}
	
		for(SetsSeneable i : SceneManager.getListMap()) {
			if(i instanceof HasAnimal) {
				((HasAnimal) i).update();
			}
		}
		SceneManager.warpTo(19);
	}
	
	public static void Tomorrow() {
		days += 1;
		if (days > 30) {
			ss = (ss + 1) % 4;
			days = 1;
		}	
		soundeffect.play();
		setMoney(money+Dropbox.getTotalsell());
		System.out.println("Good Morning " + Hero.getName());
		System.out.println("Today is day " + World.getDay());
		Dropbox.clear();
		setNextday(true);
	}

	public static int getDay() {
		return days;
	}

	public static List<updateEveryday> getListUpdate() {
		return listupdate;
	}

	public static String getSeason() {
		return season.get(ss);
	}

	public static int getMoney() {
		return money;
	}

	public static void setMoney(int x) {
		if (x < 0) {
			System.out.println("You don't have enough money.");
			System.out.println("You have " + money + "$");
			Buyable = false;
		} else {
			money = x;
			Buyable = true;
			System.out.println("You have " + money + "$");
		}
	}

	public static boolean getBuyable() {
		return Buyable;
	}

	public static boolean isNextday() {
		return nextday;
	}

	public static void setNextday(boolean nextday) {
		World.nextday = nextday;
	}
}
