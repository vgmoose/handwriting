import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Percent extends JFrame implements ActionListener, MouseMotionListener, MouseListener
{
	static int[][] coor = new int[500][500];
	static int[][] target = new int[500][500];
	static float percent;

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		

	}
	
	public void makeSquare()
	{
		// top line
		for (int x=100; x<400; x++)
			target[x][100] = 1;
		
		// bottom line
		for (int x=100; x<400; x++)
			target[x][400] = 1;
		
		// left line
		for (int x=100; x<400; x++)
			target[100][x] = 1;
		
		// right line
		for (int x=100; x<400; x++)
			target[400][x] = 1;
		
				
	}

	public static void main (String[] args)
	{		
		Percent me = new Percent();
		InnerPanel jp = new InnerPanel();
		
		jp.setPreferredSize(new Dimension(500, 500));
		jp.repaint();
		
		me.getContentPane().add(jp);

		me.addMouseListener(me);	// I am my own mouse listener
		me.addMouseMotionListener(me);

		me.popup();
	}
	
	public Percent()
	{
		setVisible(false);
		makeSquare();
	}

	public void popup(){
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Get the size of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		// Determine the new location of the window
		int w = getSize().width;
		int h = getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;

		// Move the window
		setLocation(x, y);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}
	
	public void printOldSchool()
	{
		for (int x=0; x<coor.length; x++)
		{
			for (int y=0; y<coor[0].length; y++)
				if (coor[x][y] == 1)
					System.out.print("#");
				else
					System.out.print(" ");
			
			System.out.print("\n");
		}
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		streak(arg0.getX(), arg0.getY());
	}
	

	@Override
	public void mouseReleased(MouseEvent arg0) {
		percent = getPercent();
		repaint();

	}
	
	public float getPercent()
	{
		float count = 0; 
		float targetcount = 0;
		
		for (int x=0; x<500; x++)
			for (int y=0; y<500; y++)
				if (target[x][y]==1)
				{
					for (int i=0; i<20; i++)
						for (int j=0; j<20; j++)
							if (coor[x+i][y+j] == 1)
							{
								count++;
								i=99;
								break;
							}
					targetcount++;
				}
		
//		System.out.println(count/250000);
				
		return count/targetcount;
	}

	static class InnerPanel extends JPanel
	{
		InnerPanel()
		{
			super();
			setBackground(Color.black);
			
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			for (int x=0; x<coor.length; x++)
				for (int y=0; y<coor[0].length; y++)
				{
					g.setColor(Color.green);
					if (target[x][y] == 1)
						g.drawOval(x-5, y-30, 20, 20);
				}
			
			for (int x=0; x<coor.length; x++)
				for (int y=0; y<coor[0].length; y++)
				{
					g.setColor(Color.red);
					if (coor[x][y] == 1)
						g.fillOval(x-5, y-30, 20, 20);
				}
					
			Font f = new Font ("Helvetica", Font.BOLD, 14);
		      g.setFont(f);
		      
			g.setColor(Color.white);
			g.drawString("Matching: "+(int)(percent*100)+"%", 10, 20);
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		streak(arg0.getX(), arg0.getY());
		
	}
	
	public void streak(int x, int y)
	{
		coor[x][y] = 1;
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
