/*******************************************************************************
 * File Name:     Slot.java
 * Description:   This class implements a Slot.
 *******************************************************************************/

public class Slot {
	private int team;				//Team
	private int size;				//Number of atoms
	private int maxSize;			//Max number of atoms
	private int col, row;		//Position
	
	public Slot (int col, int row, int team, int size, int maxSize) {
		this.col = col;
		this.row = row;
		this.team = team;
		this.size = size;
		this.maxSize = maxSize;
	} //Slot constructor
	
	public int getTeam () {
		return team;
	} //getTeam method
	
	public void setTeam (int team) {
		this.team = team;
	} //setTeam method
	
	public int getSize () {
		return size;
	} //getSize method
	
	public void setSize (int size) {
		this.size = size;
	} //setSize method
	
	public int getMaxSize () {
		return maxSize;
	} //getSize method
	
	public int getCol () {
		return col;
	} //getCol method
	
	public int getRow () {
		return row;
	} //getRow method
	
	public boolean isEmpty () {
		return size == 0;
	} //isEmpty method
	
	//Returns true if exploded
	public boolean add () {
		boolean exploded = (size + 1) >= maxSize;
		
		size = (size + 1) % maxSize;
		
		return exploded;
	} //add method
} //Slot class