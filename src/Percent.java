import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Percent extends JFrame implements ActionListener, MouseListener
{
	static int[][] coor = new int[500][500];
	static Timer timer;
	static int interval = 10;

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		

	}

	public static void main (String[] args)
	{		
		Percent me = new Percent();
		InnerPanel jp = new InnerPanel();
		
		jp.setPreferredSize(new Dimension(500, 500));
		
		me.getContentPane().add(jp);

		me.addMouseListener(me);	// I am my own mouse listener

		me.popup();
	}
	
	public Percent()
	{
		timer = new Timer(interval, this);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		timer.start();
		coor[arg0.getX()][arg0.getY()] = 1;
	}
	

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	static class InnerPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			for (int x=0; x<coor.length; x++)
				for (int y=0; y<coor[0].length; y++)
					if (coor[x][y] == 1)
						g.drawRect(x, y, 1, 1);
		}
	}

}
