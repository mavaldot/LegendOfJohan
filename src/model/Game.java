package model;

import java.util.HashMap;

import javafx.scene.input.KeyCode;

public class Game {

	private HashMap<KeyCode, Boolean> keys;
	
	private Mario mario;
	private Block block;
	private boolean gameOver;
	
	public State state;
	
	public Game(Mario m, HashMap<KeyCode, Boolean> key) {
		mario = m;
		keys = key;
		
		gameOver = false;
		
		state = State.IDLE;
		block = new Block(0, 500, 500, 200);
	}
	
	public void update() {
		
		
		if (isPressed(KeyCode.J)) {
			
			if (!mario.isJumping()) {
				mario.setJumping(true);
				mario.setY(mario.getY() - 10);
				mario.setVy(-20);
				checkCollisions();
			}

		}
		
		if (isPressed(KeyCode.D)) {
			mario.setVx(5);
			checkCollisions();
			state = State.RIGHT;
		}
		else if (isPressed(KeyCode.A)) {
			mario.setVx(-5);
			checkCollisions();
			state = State.LEFT;
		}
		else {
			checkCollisions();
			mario.setVx(0);
			state = State.IDLE;
		}
		
		if (mario.isJumping()) {
			state = State.JUMP;
		}
		
		mario.move();
		
		if (mario.getY() > 600) {
			gameOver = true;
		}
		
	}
	
	public boolean isPressed(KeyCode key) {
		
		return keys.getOrDefault(key, false);
		
	}

	public boolean isGameOver() {
		return gameOver;
	}
	
	public void checkCollisions() {
		
		if (mario.checkCollision(block.getX(), block.getY(), block.getWidth(), block.getHeight())) {
			mario.setY( (int) (block.getY() - mario.getHeight()));
			mario.setAy(0);
			mario.setVy(0);
			mario.setJumping(false);
		}
		else
			mario.setAy(3);
		
	}
	
	public Block getBlock() {
		
		return block;
		
	}
	
}
