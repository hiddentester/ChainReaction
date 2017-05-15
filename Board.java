/*******************************************************************************
 * File Name:     Board.java
 * Description:   This class implements a game board.
 *******************************************************************************/

public class Board {
	private int height, width;		//Dimensions of board
	private Slot[][] board;			//Board
	
	public Board (int height, int width) {
		this.height = height;
		this.width = width;
		board = new Slot[width][height];
		empty();
	} //Board constructor
	
	//Accessors
	public int getHeight () {
		return height;
	} //getHeight method
	
	public int getWidth () {
		return width;
	} //getWidth method
	
	public Slot getSlot (int col, int row) {
		return board[col][row];
	} //getSlot method
	
	public void setSlot (int col, int row, int team, int size) {
		int max = 0;
		
		//Determine number of adjacent slots
		if (col > 0) {
			max++;
		} //if structure
		
		if (col < width - 1) {
			max++;
		} //if structure
		
		if (row > 0) {
			max++;
		} //if structure
		
		if (row < height - 1) {
			max++;
		} //if structure
	
		board[col][row] = new Slot(col, row, team, size, max);
	} //setSlot method
	
	public void empty () {
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height; row++) {
				setSlot(col, row, 0, 0);
			} //for loop
		} //for loop
	} //empty method
} //Board class