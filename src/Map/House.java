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

public class House {
	private  Group root;
	public  Scene scene;
	private  Hero hero;
	private  List<Environment> e = new ArrayList<>();
	private  List<ReceiveAction> re = new ArrayList<>();
	

	public House(int starthx,int starthy) {
		root = new Group();
		scene = new Scene(root,1280,720);
		Canvas bg = new Canvas(1280,720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		gc.setFill(Color.gray(0.9));
		gc.fillRect(0, 0, 1280, 720);
		root.getChildren().add(bg);
		
		for(int i = 90;i < 800;i+=80) {
			for(int j = 220;j < 620;j+=80) {
				ReceiveAction r = new ReceiveAction(i,j, 80, 80,Color.DARKGOLDENROD);//feild
				r.setStroke(Color.BLACK);
				re.add(r);
			}
		}
		
		root.getChildren().addAll(re);
		
		e.add(new Environment(-10, 0, 10,720,Color.BLACK));//boarderLEFT
		e.add(new Environment(0, -10, 1280,10,Color.BLACK));//boarderTOP
		e.add(new Environment(1280, 0, 10,720,Color.BLACK));//boarderRIGHT
		e.add(new Environment(0,720, 1280,10,Color.BLACK));//boarderBOTTOM
		e.add(new Environment(0,0,495,270,Color.BLACK));//Blacksmith
		e.add(new Environment(1020,0,260,285,Color.BLACK));//Seed Shop
		e.add(new Environment(1020,300,260,285,Color.BLACK));//Animal Shop
		e.add(new Environment(630,300,225,225,Color.AQUA));//Fountain
		
		root.getChildren().addAll(e);
		
		

		hero = new Hero(scene,starthx,starthy,e,re);
		root.getChildren().addAll(hero.getUnitblock());
		for (Rectangle r : hero.getActionblock()) {
			root.getChildren().add(r);
		}
	}
	
	public Scene getTownScene() {
		return this.scene;
	}
	