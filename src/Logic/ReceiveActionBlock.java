package Logic;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ReceiveActionBlock {
	private static  List<Rectangle> receieveactionblocks = new ArrayList<>();
	
	public ReceiveActionBlock() {
		Rectangle block0 = new Rectangle(340, 120, 75, 75);
		block0.setFill(Color.BISQUE);
		Rectangle block1 = new Rectangle(340, 195, 75,75);
		block1.setFill(Color.GRAY);
		Rectangle block2 = new Rectangle(340, 270, 75,75);
		block2.setFill(Color.ROYALBLUE);
		Rectangle block3 = new Rectangle(415,345, 75,75);
		block3.setFill(Color.TURQUOISE);
		
		receieveactionblocks.add(block0);
		receieveactionblocks.add(block1);
		receieveactionblocks.add(block2);
		receieveactionblocks.add(block3);
		
		
		
	}
	
	public static void checkAction(Rectangle r) {
	    for (Shape receive : receieveactionblocks) {
	        Shape intersect = Shape.intersect(r, receive);
	        if (intersect.getBoundsInLocal().getWidth()>50 && intersect.getBoundsInLocal().getHeight()>50) {
	        	System.out.println("Action is on" + receieveactionblocks.indexOf(receive));
	        }
	      }

	}
		
	public List<Rectangle> getActionableblock(){
		return receieveactionblocks;
	}
		
	
		

}
