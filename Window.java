/*************************************************************
 * File Name:		Window.java
 * Description:	This class makes a window.
 *************************************************************/

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window implements MouseListener {
	//Program information
	private static final String TITLE = "Chain Reaction";
	private static final String VERSION = "0.0.170514a";
	
	//Program variables
	private Game game;
	private DrawingScheduler drawScheduler;
	private Drawing draw;
	JLabel titleBar = new JLabel();
	
	//Constructor
	public Window(Game game) {
		this.game = game;
		draw = new Drawing(this, game.getBoard());
		drawScheduler = new DrawingScheduler(draw);
		drawScheduler.start();
	
	   JFrame frame = new JFrame (TITLE + " " + VERSION);
		
	   //Initialize window properties
		frame.add(draw);
		draw.addMouseListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setMinimumSize(new Dimension(400, 350));
	   frame.setVisible(true);
		frame.setBackground(Color.white);
		frame.setAlwaysOnTop(false);
		
	   //Initialize title bar
	   frame.add(titleBar, "North");
	   titleBar.setText(TITLE + " " + VERSION);
	   titleBar.setFont(new Font("Serif", Font.BOLD,30));
	   titleBar.setForeground(Color.black);
	   titleBar.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Initialize button bar
		JPanel buttonBar = new JPanel();
		frame.add(buttonBar, "South");
		
		//Add buttons
		JButton button = new JButton("Reset");
		buttonBar.add(button);
		
		//Add action to button
		button.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent ae) {
					((ChainReaction)game).reset();
				} //actionPerformed method
			} //ActionListener
		); //restartButton action
	} //Window constructor
	
	//starting implementing MouseListener - it has 5 methods 
	
	public void mousePressed (MouseEvent e) {
		int col = (e.getX() - draw.getXShift()) / draw.getSquareSize();
		int row = (e.getY() - draw.getYShift()) / draw.getSquareSize();;
		
		if (!((ChainReaction)game).needsUpdate()) {
			((ChainReaction)game).play(col, row);
		} //if structure
	} //mousePressed method
      
	public void mouseReleased (MouseEvent e) {

	} //mouseReleased method
   
	public void mouseClicked (MouseEvent e) {
		
	} //mouseClicked method

	public void mouseEntered (MouseEvent e) {
	
	} //mouseEntered method

	public void mouseExited (MouseEvent e) {
	
	} //mouseExited method
	//finishing implementing MouseListener
} //Window class