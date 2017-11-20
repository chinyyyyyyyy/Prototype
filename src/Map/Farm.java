package Map;

import java.util.ArrayList;
import java.util.List;

import ComponentMap.Environment;
import ComponentMap.Hero;
import ComponentMap.ReceiveAction;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Farm{
	private  Group root;
	private  Scene scene;
	private  Hero hero;
	private  List<Environment> e = new ArrayList<>();
	private  List<ReceiveAction> re = new ArrayList<>();
	

	public Farm(int starthx,int starthy) {
		root = new Group();
		scene = new Scene(root,1280,720);
		Canvas bg = new Canvas(1280,720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		gc.setFill(Color.FORESTGREEN);
		gc.fillRect(0, 0, 1280, 720);
		root.getChildren().add(bg);
		
		e.add(new Environment(0, 0, 10,720,Color.BLACK));//boarderLEFT
		e.add(new Environment(10, 0, 1270,10,Color.BLACK));//boarderTOP
		e.add(new Environment(1270, 10, 10,710,Color.BLACK));//boarderRIGHT
		e.add(new Environment(10,710, 1270,10,Color.BLACK));//boarderBOTTOM
		e.add(new Environment(10,10,300,120,Color.BLACK));//Hen
		e.add(new Environment(350,10,300,120,Color.BLACK));//Barn
		e.add(new Environment(680,40,100,90,Color.BLACK));//DropBox
		
		root.getChildren().addAll(e);
		
		
		for(int i = 90;i < 800;i+=80) {
			for(int j = 220;j < 620;j+=80) {
				ReceiveAction r = new ReceiveAction(i,j, 80, 80,Color.DARKGOLDENROD);//feild
				r.setStroke(Color.BLACK);
				re.add(r);
			}
		}
		
		root.getChildren().addAll(re);
		

		hero = new Hero(scene,starthx,starthy,e,re);
		root.getChildren().addAll(hero.getUnitblock());
		for (Rectangle r : Hero.getActionblock()) {
			root.getChildren().add(r);
		}
	}
	
	public Scene getFarmScene() {
		return this.scene;
	}
	
}
