/*************************************************************
 * File Name:		Game.java
 * Description:	This class contains game logic.
 *************************************************************/

public abstract class Game {
	protected Board board;

	public Game (int width, int height) {
		board = new Board(width, height);
	} //Game constructor
	
	public Board getBoard () {
		return board;
	} //getBoard method
} //Game class