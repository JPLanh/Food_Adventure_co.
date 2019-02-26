package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import Components.UIFrame;

public class GUIController extends JPanel implements MouseListener{

	UIFrame currentFrame;
	int UIwidth, UIheight;
	
	public GUIController(){
		addMouseListener(this);
		setBackground(new Color(232, 176, 175));
		currentFrame = new SignupUI();
	}

	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		currentFrame.draw(g);
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		String getAction = currentFrame.clickAction(arg0.getX(), arg0.getY());
		if (getAction != null) {
			String[] takeAction = getAction.split(" ");
			if (takeAction[0].equals("goto"))
			{
				if (takeAction[1].equals("Register")) currentFrame = new SignupUI();
				else if (takeAction[1].equals("Login")) currentFrame = new LoginUI();
			}
		}
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
