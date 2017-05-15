/*************************************************************
 * File Name:		DrawingScheduler.java
 * Description:	This class determines when things are drawn.
 *************************************************************/

public class DrawingScheduler extends Thread {
	private Drawing draw;
	
	public DrawingScheduler (Drawing draw) {
		this.draw = draw;
	} //DrawingScheduler constructor

	@Override
	public void run () {
		while (true) {
			draw.repaint();
		} //while loop
	} //draw method
} //DrawingScheduler class