package thread;

import model.Game;

public class GameThread extends Thread {

	private Game game;
	
	public GameThread(Game g) {
		game = g;
		
		this.setDaemon(true);
	}
	
	public void run() {
		
		while (true) {
			
			game.update();
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}
