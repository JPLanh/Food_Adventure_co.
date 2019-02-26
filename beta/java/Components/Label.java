package Components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Label extends JPanel implements UIComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2570709091349969835L;
	private int xPos, yPos, width, height;
	private int lowX, highX, lowY, highY;
	private String name, string = "";
	private boolean hidden = false;

	public Label(String getName, int getXPos, int getYPos)
	{
		name = getName;
		xPos = getXPos;
		yPos = getYPos;
		width = 150;
		height = 30;
		lowX = xPos;
		highX = xPos+width;
		lowY = yPos;
		highY = yPos+height;
	}

	public Label(String getName, int getXPos, int getYPos, String getString)
	{
		name = getName;
		xPos = getXPos;
		yPos = getYPos;
		width = 150;
		height = 30;
		lowX = xPos;
		highX = xPos+width;
		lowY = yPos;
		highY = yPos+height;
		string = getString;
	}

	public void draw(Graphics g) 
	{
		g.setColor(Color.BLACK); 
		g.drawString(string, xPos+12, yPos+20);
	}

	public void setString(String getString) 
	{
		string = getString;
	}
	
	public String getName() 
	{
		return name;
	}

	@Override
	public boolean isClickedOn(int mouseX, int mouseY)
	{ 
		if (mouseX < highX && mouseX > lowX && mouseY < highY && mouseY > lowY)	return true;
		else return false;
	}

	@Override
	public String clickAction() {
		return null;
	}
}
