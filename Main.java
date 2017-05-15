/*************************************************************
 * File Name:		Main.java
 * Name:				hiddentester
 * Version:			1.0.170514a
 * Description:	This class runs the game.
 *************************************************************/

public class Main {
	private Game game;

	public static void main (String [] args) {
		Game game = new ChainReaction(6, 8, 4);
	   Window window = new Window(game);
		
		while (true) {
			try {
				Thread.sleep(100);
				((ChainReaction)game).update();
			} catch (Exception ex) {
			
			} //try-catch structure
		} //while loop
	} //main method
} //Main class