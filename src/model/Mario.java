package model;

import interfaces.Movable;

public class Mario extends GameCharacter implements Movable {
	
	private boolean jumping;
	
	public Mario(int x, int y, double w, double h) {
		super(x, y, w, h);
		jumping = false;
	}
	
	public boolean isJumping() {
		return jumping;
	}
	
	public void setJumping(boolean jump) {
		jumping = jump;
	}

	public void move() {
		
		x += vx;
		y += vy;
		vx += ax;
		vy += ay;
		
	}
	
	public boolean checkCollision(int x1, int y1, double w1, double h1) {
		
		if (x >= x1 && x <= x1 + w1 && y + height >= y1) 	
			return true;
		else
			return false;
		
	}
	
	
}
