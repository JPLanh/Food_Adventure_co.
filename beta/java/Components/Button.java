package Components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Button extends JPanel implements UIComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6017453879784009002L;
	private int xPos, yPos, width, height;
	private int lowX, highX, lowY, highY;
	private String name, string, action = "";
	private boolean hidden = false;

	public Button(String getName, int getXPos, int getYPos, int getWidth, int getHeight, String getString)
	{
		name = getName;
		xPos = getXPos;
		yPos = getYPos;
		width = getWidth;
		height = getHeight;
		lowX = xPos;
		highX = xPos+getWidth;
		lowY = yPos;
		highY = yPos+getHeight;
		string = getString;
	}

	public Button(String getName, int getXPos, int getYPos, int getWidth, int getHeight, String getString, String getAction)
	{
		name = getName;
		xPos = getXPos;
		yPos = getYPos;
		width = getWidth;
		height = getHeight;
		lowX = xPos;
		highX = xPos+getWidth;
		lowY = yPos;
		highY = yPos+getHeight;
		string = getString;
		action = getAction;
	}
	@Override
	public void draw(Graphics g) 
	{
		g.setColor(Color.BLACK);
		g.fillRect(xPos-1, yPos-1, width+2, height+2);
		g.setColor(Color.white);
		g.fillRect(xPos, yPos, width, height);
		g.setColor(Color.BLACK);
		g.drawString(string, xPos+(width/2)-(string.length()*3), yPos+(height/2)+2);
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String getName() 
	{
		return name;
	}
}
