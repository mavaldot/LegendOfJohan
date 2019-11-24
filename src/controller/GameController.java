package controller;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Game;
import model.Mario;
import panes.CharacterPane;
import thread.GameThread;
import view.Main;

public class GameController implements Initializable {

	private HashMap<KeyCode, Boolean> keys = new HashMap<>();
	
	private Mario player;
	private Game game;
	
	Image image = new Image(getClass().getResourceAsStream("mario.png"));
	
	ImageView imgV = new ImageView(image);
	
	private Group mario;
	
	boolean moving = true;
	
	Image MARIO = new Image(Main.class.getResourceAsStream("MARIOIDLE.png"));
	Image MARIORUN1 = new Image(Main.class.getResourceAsStream("MARIORUN1.png"));
	Image MARIORUN2 = new Image(Main.class.getResourceAsStream("MARIORUN2.png"));
	Image MARIORUN3 = new Image(Main.class.getResourceAsStream("MARIORUN3.png"));
	
	
	private final int levelWidth = 1500;
	
	@FXML
	AnchorPane ap;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		player = new Mario(0, 0, 1, 1);
		game = new Game(player);
		
	}
	
	public void update() {
		move();
	}
	
	public void move() {
		
	}
	
	public void change(ActionEvent e) {
		moving = !moving;
	}
	
	public void begin() {
		
		Scene scene = ap.getScene();
		
		scene.setOnKeyPressed( (event) -> {
			keys.put(event.getCode(), true);
		});
		
		scene.setOnKeyReleased( (event) -> {
			keys.put(event.getCode(), false);
		});
		

		
		final ImageView mario1 = new ImageView(MARIO);
		ImageView mariorun1 = new ImageView(MARIORUN1);
		ImageView mariorun2 = new ImageView(MARIORUN2);
		ImageView mariorun3 = new ImageView(MARIORUN3);
		
		mariorun1.setPreserveRatio(true);
		mariorun2.setPreserveRatio(true);
		mariorun3.setPreserveRatio(true);
		
		mariorun1.setFitHeight(100);
		mariorun1.setFitWidth(100);
		
		mariorun2.setFitHeight(100);
		mariorun2.setFitWidth(100);
		
		mariorun3.setFitHeight(100);
		mariorun3.setFitWidth(100);
		
		mario = new Group(mario1);
		
		mario.translateXProperty().addListener( (obs, old, newVal) -> {
			
			int offset = newVal.intValue();
			
			if (offset > 640 && offset < levelWidth - 640) 
				ap.setLayoutX(-(offset - 640));
			
		});
		
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
		
		t.play();
		
		Timeline t2 = new Timeline();
		t2.setCycleCount(Timeline.INDEFINITE);
		
		t2.getKeyFrames().add(new KeyFrame(
				Duration.millis(100),
				(ActionEvent event) -> {
					mario.getChildren().setAll(mario1);
				}
		));
		
		ap.getChildren().addAll(mario);
		
		new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				
				if (moving) {
					t2.stop();
					t.play();
				}
				else {
					t.stop();
					t2.play();
				}
				
				mario.setTranslateX(player.getX());
				
			}
			
		}.start();
		
		new GameThread(game).start();
		
	}
	
}
