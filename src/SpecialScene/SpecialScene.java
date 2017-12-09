package SpecialScene;

import javafx.scene.image.Image;

public interface SpecialScene {
	
	public void update();
	Image Background = new Image(ClassLoader.getSystemResource("backgroundspecialscene.png").toString());

}
