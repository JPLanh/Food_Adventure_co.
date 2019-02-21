package UI;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import Components.UIFrame;

public class GUIController extends JPanel{

	UIFrame currentFrame;
	int UIwidth, UIheight;
	
	public GUIController(){
		setBackground(new Color(232, 176, 175));
		currentFrame = new TitleUI();
	}

	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		currentFrame.draw(g);
	}
}
