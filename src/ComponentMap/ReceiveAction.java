package ComponentMap;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ReceiveAction extends Rectangle{
	
	public ReceiveAction(int x,int y ,int w ,int h ,Color c) {
		super(x,y,w,h);
		this.setFill(c);
	}
	
	public static void checkAction(List<ReceiveAction> re,Rectangle r) {
	    for (ReceiveAction receive : re) {
	        Shape intersect = Shape.intersect(r, receive);
	        double wi = intersect.getBoundsInLocal().getWidth();
	        double hi = intersect.getBoundsInLocal().getHeight();
	        double wr = receive.getWidth();
	        double hr = receive.getHeight();
	        double wa = r.getWidth();
	        double ha = r.getHeight();
	        if (wr < wa && hr < ha ) {
	        	if(wi == wr && hi == hr  ) System.out.println("Action is on" + re.indexOf(receive));
	        }else if (wr >= wa && hr < ha ) {
	        	if(wi >= 0.8*wa && hi == hr  ) System.out.println("Action is on" + re.indexOf(receive));
	        }else if (wr < wa && hr >= ha ) {
	        	if(wi == wr && hi >= 0.8*ha  ) System.out.println("Action is on" + re.indexOf(receive));
	        }else if (wr >= wa && hr >= ha ) {
	        	if(wi >= 0.8*wa && hi >= 0.8*ha ) System.out.println("Action is on" + re.indexOf(receive));
	        }
	    }
	        

	}		

}
