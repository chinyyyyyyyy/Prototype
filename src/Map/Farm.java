package Map;

import java.util.ArrayList;
import java.util.List;

import ComponentMap.Environment;
import ComponentMap.Feild;
import ComponentMap.Hero;
import ComponentMap.ReceiveAction;
import ComponentMap.Stone;
import ComponentMap.Wood;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class Farm implements setsceneable{
	private  Group root;
	private  Scene scene;
	private  Hero hero;
	private  List<Environment> e = new ArrayList<>();
	private  List<ReceiveAction> re = new ArrayList<>();
	private  List<Pair<Rectangle,Integer>> WarpList = new ArrayList<>();
	

	public Farm(int starthx,int starthy) {
		root = new Group();
		scene = new Scene(root,1280,720);
		Canvas bg = new Canvas(1280,720);
		GraphicsContext gc = bg.getGraphicsContext2D();
		gc.setFill(Color.FORESTGREEN);
		gc.fillRect(0, 0, 1280, 720);
		root.getChildren().add(bg);
		
		
		//CreateEnvironment
		e.add(new Environment(-10, 0, 10,720,Color.BLACK));//boarderLEFT
		e.add(new Environment(0, -10, 1280,10,Color.BLACK));//boarderTOP
		e.add(new Environment(1280, 0, 10,720,Color.BLACK));//boarderRIGHT
		e.add(new Environment(0,720, 1280,10,Color.BLACK));//boarderBOTTOM
		
		e.add(new Environment(0,0,90,140,Color.BLACK));//Hen
		e.add(new Environment(90,0,120,115,Color.BLACK));//Hen
		e.add(new Environment(210,0,100,140,Color.BLACK));//Hen
		Rectangle warpblocktohenbarn = new Rectangle(90,115,120,25);
		warpblocktohenbarn.setFill(Color.RED);
		root.getChildren().add(warpblocktohenbarn);
		Pair<Rectangle,Integer> tohenbarn = new Pair<Rectangle,Integer>(warpblocktohenbarn,4);
		WarpList.add(tohenbarn);
		
		
		e.add(new Environment(350,0,90,140,Color.BLACK));//Barn
		e.add(new Environment(440,0,120,115,Color.BLACK));//Barn
		e.add(new Environment(560,0,90,140,Color.BLACK));//Barn
		Rectangle warpblocktocowbarn = new Rectangle(440,115,120,25);
		warpblocktocowbarn.setFill(Color.RED);
		root.getChildren().add(warpblocktocowbarn);
		Pair<Rectangle,Integer> tocowbarn = new Pair<Rectangle,Integer>(warpblocktocowbarn,3);
		WarpList.add(tocowbarn);
		
		e.add(new Environment(910,0,125,200,Color.BLACK));//House
		e.add(new Environment(1035,0,120,175,Color.BLACK));//House
		e.add(new Environment(1155,0,125,200,Color.BLACK));//House
		Rectangle warpblocktohouse = new Rectangle(1035,175,120,25);
		warpblocktohouse.setFill(Color.RED);
		root.getChildren().add(warpblocktohouse);
		Pair<Rectangle,Integer> tocowhouse = new Pair<Rectangle,Integer>(warpblocktohouse,2);
		WarpList.add(tocowhouse);
		
		
		e.add(new Environment(710,40,120,100,Color.BURLYWOOD));//DropBox
		e.add(new Environment(910,420,280,200,Color.AQUAMARINE));//Ponds
		root.getChildren().addAll(e);
		

		
		for(int i = 90;i < 800;i+=80) {
			for(int j = 220;j < 620;j+=80) {
				ReceiveAction r = new ReceiveAction(i,j, 80, 80,Color.DARKGOLDENROD);//feild
				r.setStroke(Color.BLACK);
				re.add(r);
			}
		}
		//---------------------Add Feild-----------------------//
		Feild test = new Feild(800,620,80,80,Color.DARKGOLDENROD);
		Stone stone = new Stone();
		test.setStone(stone);
		test.update();
		re.add(test);
		Feild test2 = new Feild(720,620,80,80,Color.DARKGOLDENROD);
		Wood wood = new Wood();
		test2.setWood(wood);
		test2.update();
		re.add(test2);
		//---------------------Add Feild-----------------------//
		
		re.add(new ReceiveAction(710,40,100,100,Color.BURLYWOOD));//DropBox
		re.add(new ReceiveAction(910,420,280,200,Color.AQUAMARINE));//Ponds
		
		root.getChildren().addAll(re);
		
		
		Rectangle warpblocktotown = new Rectangle(1255,250,25,120);
		warpblocktotown.setFill(Color.RED);
		root.getChildren().addAll(warpblocktotown);
		Pair<Rectangle,Integer> totown = new Pair<Rectangle,Integer>(warpblocktotown,1);
		WarpList.add(totown);
		
		

		hero = new Hero(scene,starthx,starthy,e,re);
		root.getChildren().addAll(hero.getUnitblock());
		for (Rectangle r : hero.getActionblock()) {
			root.getChildren().add(r);
		}
		hero.setWarpBlockList(WarpList);
	}
	
	
	
	public Scene getScene() {
		return this.scene;
	}
	
}
