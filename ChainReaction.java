/*************************************************************
 * File Name:		ChainReaction.java
 * Description:	This class contains the main game logic.
 *************************************************************/

import java.util.ArrayDeque;

public class ChainReaction extends Game {
	private int numPlayers;						//Number of players
	private int curTurn;							//Current player
	private int[] playerSlots;					//Number of slots occupied by a player
	private ArrayDeque<Slot> updateList;	//List of slots to update
	private boolean needsUpdate;				//Whether the board needs to be updated
	private boolean isFirstRound;				//Whether it is the first round

	public ChainReaction (int width, int height, int numPlayers) {
		super(width, height);
		this.numPlayers = numPlayers;
		reset();
	} //ChainReaction constructor
	
	//switches turns
	private void nextTurn () {
		if (isFirstRound) {
			curTurn++;
			
			if (curTurn >= numPlayers) {
				isFirstRound = false;
				curTurn %= numPlayers;
			} //if structure
		} else {
			do {
				curTurn = (curTurn + 1) % numPlayers;
			} while (playerSlots[curTurn] == 0);
		} //if structure
	} //nextTurn method
	
	//Make a move
	public void play (int col, int row) {
		try {
			Slot curSlot = board.getSlot(col, row);
			
			//Check if a move can be made at this location
			if (curSlot.getSize() == 0 || curSlot.getTeam() == curTurn) {
				//Add slot to update list
				updateList.add(curSlot);
				needsUpdate = true;
			} //if structure
		} catch (Exception e) {
			//e.printStackTrace();
		} //try-catch structure
	} //play method
	
	//Update board
	public void update () {
		ArrayDeque<Slot> newUpdateList = new ArrayDeque<Slot>();
		
		if (!updateList.isEmpty() && (getWinner() == -1 || getWinner() != curTurn)) {
			while (!updateList.isEmpty() && (getWinner() == -1 || getWinner() != curTurn)) {
				Slot curSlot = updateList.pop();
				
				//Check if slot is empty
				if (curSlot.getSize() == 0) {
					//Update number of slots occupied
					playerSlots[curTurn]++;
				//Check if slot belongs to the current player
				} else if (curSlot.getTeam() != curTurn) {
					//Update number of slots occupied
					playerSlots[curTurn]++;
					playerSlots[curSlot.getTeam()]--;
				} //if structure
				
				//Update team of slot
				curSlot.setTeam(curTurn);
			
				//Add to existing slot and update adjacent slots if exploded
				if (curSlot.add()) {
					playerSlots[curSlot.getTeam()]--;
				
					int col = curSlot.getCol();
					int row = curSlot.getRow();
				
					try {
						newUpdateList.add(board.getSlot(col - 1, row));
					} catch (ArrayIndexOutOfBoundsException e) {
					} //try-catch structure
					
					try {
						newUpdateList.add(board.getSlot(col + 1, row));
					} catch (ArrayIndexOutOfBoundsException e) {
					} //try-catch structure
					
					try {
						newUpdateList.add(board.getSlot(col, row - 1));
					} catch (ArrayIndexOutOfBoundsException e) {
					} //try-catch structure
					
					try {
						newUpdateList.add(board.getSlot(col, row + 1));
					} catch (ArrayIndexOutOfBoundsException e) {
					} //try-catch structure
				} //if structure
			} //while loop
			
			updateList = newUpdateList;
			needsUpdate = !updateList.isEmpty();
			
			//Switch turns
			if (!needsUpdate()) {
				nextTurn();
			} //if structure
		} //if structure
	} //update method
	
	public boolean needsUpdate () {
		return needsUpdate;
	} //if structure
	
	public void reset () {
		board.empty();
		curTurn = 0;
		playerSlots = new int[numPlayers];
		updateList = new ArrayDeque<Slot>();
		needsUpdate = false;
		isFirstRound = true;
	} //reset method
	
	//Check if the game is over
	public boolean gameOver () {
		int playersAlive = 0;
		
		if (isFirstRound) {
			return false;
		} //if structure
		
		for (int i = 0; i < numPlayers && playersAlive <= 1; i++) {
			if (playerSlots[i] > 0) {
				playersAlive++;
			} //if structure
		} //for loop
		
		return playersAlive <= 1;
	} //gameOver method
	
	//Check for a winner
	public int getWinner () {
		int playersAlive = 0;
		int winner = -1;
		
		if (isFirstRound) {
			return -1;
		} //if structure
		
		for (int i = 0; i < numPlayers && playersAlive <= 1; i++) {
			if (playerSlots[i] > 0) {
				winner = i;
				playersAlive++;
			} //if structure
		} //for loop
		
		if (playersAlive <= 1) {
			return winner;
		} else {
			return -1;
		} //if structure
	} //getWinner method
} //ChainReaction class