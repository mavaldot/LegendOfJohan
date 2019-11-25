package model;

public class Block {

	private int x;
	private int y;
	private double width;
	private double height;
	private Block next;
	private Block prev;
	
	public Block(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
	
	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public Block getNext() {
		return next;
	}

	public void setNext(Block next) {
		this.next = next;
	}

	public Block getPrev() {
		return prev;
	}

	public void setPrev(Block prev) {
		this.prev = prev;
	}
	
	public boolean checkCollision(int x1, int y1, int w1, int h1) {
		
		if (x1 > x && x1 + w1 < x + width && y1 + h1 >= y1)
			return true;
		
		if (next != null) 
			return next.checkCollision(x1, y1, w1, h1);
		
		return false;
	}
	
	
}
