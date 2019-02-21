package Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class TextField extends JPanel implements UIComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5011944768362330368L;
	private int xPos, yPos, width, height;
	private int lowX, highX, lowY, highY;
	private String name, string = "";
	private boolean hidden = false, active = false;

	public TextField(String getName, int getXPos, int getYPos)
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

	public void draw(Graphics g) 
	{
		g.setColor(Color.BLACK);
		g.fillRect(xPos-2, yPos-2, width+4, height+4);
		g.setColor(Color.white);
		g.fillRect(xPos, yPos, width, height);
		g.setColor(Color.BLACK);
		g.drawString(string, xPos+12, yPos+20);
	}

	public String getString() {
		return string;
	}

	@Override
	public String getName() 
	{
		return name;
	}

	public void keyPress(KeyEvent c)
	{
		if (c.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE && string.length() > 1) string = string.substring(0, string.length()-2) + "|";
		else if (Character.isLetterOrDigit(c.getKeyChar())) string = string.substring(0,string.length()-1) + c.getKeyChar() + "|";
	}
}
