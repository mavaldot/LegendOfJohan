package animation;

import interfaces.Beginner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import view.Main;

public class MarioAnimation implements Beginner {

	private final Image MARIO = new Image(Main.class.getResourceAsStream("MARIOIDLE.png"));
	private final Image MARIORUN1 = new Image(Main.class.getResourceAsStream("MARIORUN1.png"));
	private final Image MARIORUN2 = new Image(Main.class.getResourceAsStream("MARIORUN2.png"));
	private final Image MARIORUN3 = new Image(Main.class.getResourceAsStream("MARIORUN3.png"));
	private final Image MARIOJUMP = new Image(Main.class.getResourceAsStream("MARIOJUMP.png"));
	
	private final ImageView mario1 = new ImageView(MARIO);
	private final ImageView mariorun1 = new ImageView(MARIORUN1);
	private final ImageView mariorun2 = new ImageView(MARIORUN2);
	private final ImageView mariorun3 = new ImageView(MARIORUN3);
	private final ImageView mariojump = new ImageView(MARIOJUMP);
	
	private Group mario;
	
	public MarioAnimation(Group m) {
		
		mario = m;
		begin();
	}
	
	public void begin() {
		
//		mario1.setPreserveRatio(true);
//		mariorun1.setPreserveRatio(true);
//		mariorun2.setPreserveRatio(true);
//		mariorun3.setPreserveRatio(true);

		mario1.setFitHeight(100);
		mario1.setFitWidth(50);
		
		mariorun1.setFitHeight(100);
		mariorun1.setFitWidth(50);
		
		mariorun2.setFitHeight(100);
		mariorun2.setFitWidth(50);
		
		mariorun3.setFitHeight(100);
		mariorun3.setFitWidth(50);
		
		mariojump.setFitHeight(100);
		mariojump.setFitWidth(50);
	}
	
	public Timeline generateRunTimeline() {
		
		Timeline t = new Timeline();
		t.setCycleCount(Timeline.INDEFINITE);
		
		t.getKeyFrames().add(new KeyFrame(
				Duration.millis(100),
				(ActionEvent event) -> {
					mario.getChildren().setAll(mariorun1);
				}
		));
		
		t.getKeyFrames().add(new KeyFrame(
				Duration.millis(200),
				(ActionEvent event) -> {
					mario.getChildren().setAll(mariorun2);
				}
		));
		
		t.getKeyFrames().add(new KeyFrame(
				Duration.millis(300),
				(ActionEvent event) -> {
					mario.getChildren().setAll(mariorun3);
				}
		));
	
		return t;
		
	}
	
	public Timeline generateIdleTimeline() {
		
		Timeline t2 = new Timeline();
		t2.setCycleCount(Timeline.INDEFINITE);
		
		t2.getKeyFrames().add(new KeyFrame(
				Duration.millis(100),
				(ActionEvent event) -> {
					mario.getChildren().setAll(mario1);
				}
		));
		
		return t2;
		
	}
	
	public Timeline generateJumpTimeline() {
		
		Timeline t3 = new Timeline();
		t3.setCycleCount(Timeline.INDEFINITE);
		
		t3.getKeyFrames().add(new KeyFrame(
				Duration.millis(100),
				(ActionEvent event) -> {
					mario.getChildren().setAll(mariojump);
				}
		));
		
		return t3;
	}
	
}
