package Components;

import java.awt.Color;
import java.awt.Font;
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

	public TextField(String getName, int getXPos, int getYPos, String setString)
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
		string = setString;
	}

	public void draw(Graphics g) 
	{
//		g.setColor(Color.BLACK);
//		g.fillRect(xPos-2, yPos-2, width+4, height+4);
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 12)); 
		g.setColor(new Color(242, 242, 242));
		g.fillRect(xPos, yPos, width, height);
		if (string.equals(name)) g.setColor(new Color(188, 201, 155));
		else g.setColor(Color.BLACK);
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
//		System.out.println(c.getKeyCode());
		if (c.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE && string.length() > 1) string = string.substring(0, string.length()-2) + "|";
		else if (Character.toString(c.getKeyChar()).matches("[a-zA-Z0-9@.\\s]")) {  
				string = string.substring(0,string.length()-1) + c.getKeyChar() + "|";
		}
	}

	@Override
	public boolean isClickedOn(int mouseX, int mouseY)
	{ 
		if (mouseX < highX && mouseX > lowX && mouseY < highY && mouseY > lowY)	{
			if (string.equals(name)) string = "";
			string += "|";
			active = true;
			return true;
		}
		else {
			if (active)
			{
				string = string.substring(0,string.length()-1);
				if (string.equals("")) {
					string = name;
				}
			}
			active = false;
			return false;		
		}
	}

	@Override
	public String clickAction() {
		return "Activate";
	}
	@Override
	public boolean isActive() {
		return active;
	}
}
