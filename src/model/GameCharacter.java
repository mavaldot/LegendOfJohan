package model;

import interfaces.Movable;
import interfaces.Updatable;

public class GameCharacter {

	protected int x;
	protected int y;
	protected int vx;
	protected int vy;
	protected int ax;
	protected int ay;
	
	protected double width;
	protected double height;

	public GameCharacter(int x, int y, double width, double height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		vx = 0;
		vy = 0;
		ax = 0;
		ay = 0;
	}

	public double getHeight() {
		return height;
	}
	
	public double getWidth() {
		return width;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}
	
	public int getAx() {
		return ax;
	}

	public void setAx(int ax) {
		this.ax = ax;
	}

	public int getAy() {
		return ay;
	}

	public void setAy(int ay) {
		this.ay = ay;
	}
	
	
	
	
}
