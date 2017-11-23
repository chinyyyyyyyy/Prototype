package ComponentMap;

import java.util.ArrayList;
import java.util.List;

import Map.setsceneable;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Pair;

public class SceneManager extends Rectangle{
	private static Stage primaryStage;
	private static List<setsceneable> listmap = new ArrayList<>();
	
	public SceneManager(Stage s,List<setsceneable> lm) {
		primaryStage =s;
		listmap = lm;
		primaryStage.setScene(lm.get(0).getScene());
		primaryStage.show();
		primaryStage.setTitle("Harvest Sun");
	}
	
	public static void warpTo(int mapno) {
		primaryStage.setScene(listmap.get(mapno).getScene());
	}
	
	public static int CheckOnWarpBlock(List<Pair<Rectangle,Integer>> WarpList,Rectangle unitblock) {
		int pos = -1;
		for (Pair<Rectangle,Integer> p : WarpList) {
			Rectangle r = p.getKey();
	        Shape intersect = Shape.intersect(r, unitblock);
	        double wi = intersect.getBoundsInLocal().getWidth();
	        double hi = intersect.getBoundsInLocal().getHeight();
	        double wr = unitblock.getWidth();
	        double hr = unitblock.getHeight();
	        double wa = r.getWidth();
	        double ha = r.getHeight();
	        if (wr < wa && hr < ha ) {
	        	if(wi == wr && hi == hr  ) pos = p.getValue();;
	        }else if (wr >= wa && hr < ha ) {
	        	if(wi >= 0.8*wa && hi == hr  ) pos = p.getValue();;
	        }else if (wr < wa && hr >= ha ) {
	        	if(wi == wr && hi >= 0.8*ha  ) pos = p.getValue();;
	        }else if (wr >= wa && hr >= ha ) {
	        	if(wi >= 0.8*wa && hi >= 0.8*ha ) pos = p.getValue();;
	        }
	    }
		return pos;
	        

	}	
	
}
