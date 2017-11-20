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

public class Town{
	private  Group root;
	private  Scene scene;
	private  Hero hero;
	private  List<Environment> e = new ArrayList<>();
	private  List<ReceiveAction> re = new ArrayList<>();
	

	public Town(int starthx,int starthy) {
		root = new Group();
		scene = new Scene(root,1280,720);
		Canvas bg = new Canvas(1280,720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		gc.setFill(Color.SPRINGGREEN);
		gc.fillRect(0, 0, 1280, 720);
		root.getChildren().add(bg);
		
		e.add(new Environment(0, 0, 0,720,Color.BLACK));//boarderLEFT
		e.add(new Environment(0, 0, 1280,0,Color.BLACK));//boarderTOP
		e.add(new Environment(1280, 0, 0,720,Color.BLACK));//boarderRIGHT
		e.add(new Environment(0,720, 1280,0,Color.BLACK));//boarderBOTTOM
		e.add(new Environment(0,0,495,270,Color.BLACK));//Blacksmith
		e.add(new Environment(1020,0,260,285,Color.BLACK));//Seed Shop
		e.add(new Environment(1020,300,260,285,Color.BLACK));//Animal Shop
		e.add(new Environment(630,300,225,225,Color.AQUA));//Fountain
		
		root.getChildren().addAll(e);
		
		
//		for(int i = 90;i < 800;i+=80) {
//			for(int j = 220;j < 620;j+=80) {
//				ReceiveAction r = new ReceiveAction(i,j, 80, 80,Color.DARKGOLDENROD);//feild
//				r.setStroke(Color.BLACK);
//				re.add(r);
//			}
//		}
//		
//		root.getChildren().addAll(re);
//		

		hero = new Hero(scene,starthx,starthy,e,re);
		root.getChildren().addAll(hero.getUnitblock());
		for (Rectangle r : Hero.getActionblock()) {
			root.getChildren().add(r);
		}
	}
	
	public Scene getTownScene() {
		return this.scene;
	}
	
}
