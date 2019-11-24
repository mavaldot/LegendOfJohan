package panes;

import animation.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class CharacterPane extends Pane {

	ImageView imgV;
	int count = 3;
	
	int columns = 3;
	
	int delta = 32;
	
	int width = 16;
	int height = 32;
	
	int score = 0;
	
	SpriteAnimation animation;
	
	public CharacterPane(ImageView imageView) {
		imgV = imageView;
		imgV.setViewport(new Rectangle2D(delta, 0, width, height));
		animation = new SpriteAnimation(imgV, Duration.millis(200), count, 3, delta, width, height);
		getChildren().addAll(imgV);
		
		animation.play();
		
	}
	
	public void moveX(int x) {
		
		for (int i = 0; i < x; i++) {
			setTranslateX(getTranslateX() + 1);
		}
		
		
		
	}
	
	
}
