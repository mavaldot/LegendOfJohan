package model;

public class Mario extends GameCharacter {

	
	
	public Mario(int x, int y, int vx, int vy) {
		super(x, y, vx, vy);
		// TODO Auto-generated constructor stub
	}

	public void update() {
		x += 1;
	}
	
}
