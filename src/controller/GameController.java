package controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import animation.MarioAnimation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Block;
import model.Game;
import model.Mario;
import model.State;
import thread.GameThread;
import view.Main;

public class GameController implements Initializable {

	private HashMap<KeyCode, Boolean> keys = new HashMap<>();
	
	private Mario player;
	private Game game;
	private Block b;
	private boolean gameOver;
	
	private Stage stage;
	
	Image image = new Image(getClass().getResourceAsStream("mario.png"));
	
	ImageView imgV = new ImageView(image);
	
	private Rectangle blocks;
	private Group mario;
	
	boolean moving = true;
	
	MarioAnimation marioAnim;
	
	
	private final int levelWidth = 1500;
	
	@FXML
	Pane root;
	
	@FXML
	Pane gamePane;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		player = new Mario(0, 0, 50, 100);
		game = new Game(player, keys);
		
		gamePane = new Pane();
		gamePane.setPrefHeight(800);
		gamePane.setPrefWidth(2400);
	    gamePane.setStyle("-fx-background-color: deepskyblue;");
	}
	
	public void setStage(Stage s) {
		stage = s;
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
		
		mario = new Group();
		
		
		b = game.getBlock();			
		
		blocks = new Rectangle(0, 0, b.getWidth(), b.getHeight());
		blocks.setFill(Color.SIENNA);
		
		marioAnim = new MarioAnimation(mario);
		
		mario.translateXProperty().addListener( (obs, old, newVal) -> {
			
			int offset = newVal.intValue();
			
			if (offset > 400 && offset < levelWidth - 400) 
				gamePane.setLayoutX(-(offset - 400));
			
		});
		
		Scene scene = root.getScene();
		
		scene.setOnKeyPressed( (event) -> {
			keys.put(event.getCode(), true);
		});
		
		scene.setOnKeyReleased( (event) -> {
			keys.put(event.getCode(), false);
		});
		
		
		mario.translateXProperty().addListener( (obs, old, newVal) -> {
			
			int offset = newVal.intValue();
			
			if (offset > 400 && offset < levelWidth - 400) 
				gamePane.setLayoutX(-(offset - 400));
			
		});
		
		Timeline t = marioAnim.generateRunTimeline();
		
		Timeline t2 = marioAnim.generateIdleTimeline();
		
		Timeline t3 = marioAnim.generateJumpTimeline();
		
		blocks.setTranslateX(b.getX());
		blocks.setTranslateY(b.getY());
		
		gamePane.getChildren().addAll(mario, blocks);
		
		root.getChildren().addAll(gamePane);
		
		
		
		new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				
				State state = game.state;
				
				switch (state) {
				
				case IDLE:
					t3.stop();
					t.stop();
					t2.play();
					break;
					
				case RIGHT:
					mario.setScaleX(1);
					t3.stop();
					t2.stop();
					t.play();
					break;
				case LEFT:
					mario.setScaleX(-1);
					t3.stop();
					t2.stop();
					t.play();
					break;
				case JUMP:
					t.stop();
					t2.stop();
					t3.play();
					break;
					
				}
				
				mario.setTranslateX(player.getX());
				mario.setTranslateY(player.getY());
				
				if (game.isGameOver()) {
					
					try {
						loadMenu();
					} 
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					this.stop();
				}
			}
			
		}.start();
		
		new GameThread(game).start();
		
	}
	

	
	public void loadMenu() throws IOException {
		FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
		Parent menuPane = menuLoader.load();
		Scene menuScene = new Scene(menuPane, 800, 600);
		stage.setScene(menuScene);
		
	}
	
}
