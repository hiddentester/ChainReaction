/*******************************************************************************
 * File Name:     Drawing.java
 * Description:   This class draws the board.
 *******************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.io.*;

//Drawing class
public class Drawing extends JComponent {
	private Board board;
	private Window window;
	private ImageIcon[] textures;			//Textures
	private int squareSize;					//Size of a square
	private int inPadding;					//Space between blocks
	private int outPadding;					//Space around board
	private int xShift, yShift;			//Board positioning variables
	private int[][] colourPrefs;			//Player colour preferences
	
	public Drawing (Window window, Board board) {
		super();
		this.window = window;
		this.board = board;
		inPadding = 5;
		outPadding = 20;
		colourPrefs = loadColours("preferences.txt");
	} //Drawing constructor
	
	//Texture loader
	public static ImageIcon[] getTextures (String folderName) {
      File folder = new File("./" + folderName + "/");
      File[] files = folder.listFiles();
		ImageIcon[] textures = new ImageIcon[files.length];
		
		for (int i = 0; i < textures.length; i++) {
			textures[i] = new ImageIcon(folderName + "/" + files[i].getName());
		} //for loop
		
		return textures;
   } //getTextures method
	
	//Player colour loader
	public int[][] loadColours (String fileName) {
		final int NUM_PARAMS = 3;
		int[][] colours;
	
      try {
      	BufferedReader in = new BufferedReader(new FileReader(fileName));
			int size = Integer.parseInt(in.readLine());
			
			colours = new int[size][NUM_PARAMS];
			
			for (int i = 0; i < size; i++) {
				String[] unparsed = in.readLine().split(",");
				
				for (int j = 0; j < NUM_PARAMS; j++) {
					colours[i][j] = Integer.parseInt(unparsed[j]);
				} //for loop
			} //for loop
			
			return colours;
		} catch (Exception e) {
			e.printStackTrace();
			return new int[0][0];
		} //try-catch structure
   } //getTextures method
	
	//Accessors
	public int getSquareSize () {
		return squareSize;
	} //squareSize accessor
	
	public int getInPadding () {
		return inPadding;
	} //inPadding accessor
	
	public int getOutPadding () {
		return outPadding;
	} //outPadding accessor
	
	public int getXShift () {
		return xShift;
	} //xShift accessor
	
	public int getYShift () {
		return yShift;
	} //yShift accessor
	
	private void updateScaling () {
		if ((double)board.getWidth() / board.getHeight() >
			((double)getWidth() - (2 * outPadding)) / (getHeight() - (2 * outPadding))) {
			
			squareSize = (getWidth() - (2 * outPadding)) / board.getWidth();		//Base block size on width
		} else {
			squareSize = (getHeight() - (2 * outPadding)) / board.getHeight();	//Base block size on height
		} //if structure
		
		xShift = (int)((getWidth() / 2) - ((double)board.getWidth() / 2 * squareSize));
		yShift = (int)((getHeight() / 2) - ((double)board.getHeight() / 2 * squareSize));
	} //updateScaling method
	
	public void paint(Graphics g) {
		updateScaling();
	
		//Fill background
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//Draw slots
		for (int col = 0; col < board.getWidth(); col++) {
			for (int row = 0; row < board.getHeight(); row++) {
				//Fill slot background
				g.setColor(Color.darkGray);
				g.fillRect(
					col * squareSize + xShift + (inPadding / 2),
					row * squareSize + yShift + (inPadding / 2),
					squareSize - (inPadding / 2),
					squareSize - (inPadding / 2)
				);
				
				//Choose colour
				Slot curSlot = board.getSlot(col, row);
				g.setColor(new Color(
					colourPrefs[curSlot.getTeam()][0],
					colourPrefs[curSlot.getTeam()][1],
					colourPrefs[curSlot.getTeam()][2],
					(int)(Math.min(curSlot.getSize(), curSlot.getMaxSize() - 1) / 1.0 / (curSlot.getMaxSize() - 1) * 255)
				));
				
				g.fillRect(
					col * squareSize + xShift + (inPadding / 2),
					row * squareSize + yShift + (inPadding / 2),
					squareSize - (inPadding / 2),
					squareSize - (inPadding / 2)
				);
				
				/*g.fillRect(
					col * squareSize + xShift + (inPadding / 2),
					row * squareSize + yShift + (inPadding / 2),
					Math.max(0, (int)(Math.min(curSlot.getSize(), curSlot.getMaxSize() - 1) / 1.0 / (curSlot.getMaxSize() - 1) * squareSize) - inPadding / 2),
					squareSize - (inPadding / 2)
				);*/
			} //for loop
		} //for loop
	} //paint method
} //Drawing class