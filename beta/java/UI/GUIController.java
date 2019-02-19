package UI;

import java.awt.Graphics;
import javax.swing.*;
import Components.UIFrame;

public class GUIController extends JPanel{

	UIFrame currentFrame;
	int UIwidth, UIheight;
	
	public GUIController(){
		//currentFrame = initialize your page here
	}

	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		currentFrame.draw(g);
	}
}
