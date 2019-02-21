package UI;

import java.awt.Graphics;
import javax.swing.*;
import Components.UIFrame;

public class GUIController extends JPanel{

	UIFrame currentFrame;
	int UIwidth, UIheight;
	
	public GUIController(){
		currentFrame = new TitleUI();
	}

	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		currentFrame.draw(g);
	}
}
