package model;

import java.util.HashMap;

import javafx.scene.input.KeyCode;

public class Game {

	private HashMap<KeyCode, Boolean> keys;
	
	private Mario mario;
	
	public Game(Mario m) {
		mario = m;
	}
	
	public void update() {
		
		mario.update();
		
		
	}
	
	public boolean isPressed(KeyCode key) {
		
		return keys.getOrDefault(key, false);
		
	}
	
}
