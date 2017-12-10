package SpecialScene;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

public interface SpecialScene {
	
	static final Font header = Font.loadFont(ClassLoader.getSystemResource("fontja.ttf").toString(), 80);
	static final Font body = Font.loadFont(ClassLoader.getSystemResource("fontja.ttf").toString(), 40);
	Image Background = new Image(ClassLoader.getSystemResource("backgroundspecialscene.png").toString());
	
	public void update();
	

}
