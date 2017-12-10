package SpecialScene;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

public interface SpecialScene {
	
	static final Font header = Font.loadFont(ClassLoader.getSystemResource("fonteiei.ttf").toString(), 70);
	static final Font body = Font.loadFont(ClassLoader.getSystemResource("fonteiei.ttf").toString(), 50);
	Image plus = new Image(ClassLoader.getSystemResource("plus.png").toString());
	Image minus = new Image(ClassLoader.getSystemResource("minus.png").toString());
	Image hand = new Image(ClassLoader.getSystemResource("pointing.png").toString());
	Image Background = new Image(ClassLoader.getSystemResource("backgroundspecialscene.png").toString());
	
	
	public void update();
	

}
