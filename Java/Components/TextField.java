package Components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class TextField extends JPanel implements UIComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5011944768362330368L;
	private int xPos, yPos, width, height;
	private int lowX, highX, lowY, highY, counter;
	private String name, string = "";
	private ArrayList<String> text = new ArrayList<String>();
	private boolean hidden = false, active = false, flag = false;

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

	public TextField(String getName, int getXPos, int getYPos, String setString, int getLength, int getHeight, boolean flag)
	{
		name = getName;
		this.flag = flag;
		xPos = getXPos;
		yPos = getYPos;
		width = getLength;
		height = getHeight;
		lowX = xPos;
		highX = xPos+width;
		lowY = yPos;
		highY = yPos+height;
		string = setString;
	}

	public void draw(Graphics g) 
	{
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 12)); 
		g.setColor(new Color(242, 242, 242));
		g.fillRect(xPos, yPos, width, height);
		if (string.equals(name)) g.setColor(new Color(188, 201, 155));
		else g.setColor(Color.BLACK);
		for (int x = 0; x < text.size(); x++) {
			g.drawString(text.get(x), xPos+12, yPos+20 + (x*20));
		}
		g.drawString(string, xPos+12, yPos+20 + (text.size()*20));
	}

	public String getString() {
		String tempstring = "";
		for (String x : text) {
			tempstring += x + "\n";
		}
		tempstring += string;
		if (tempstring.charAt(tempstring.length()-1) == '|') tempstring = tempstring.substring(0, tempstring.length()-1);
		return tempstring;
	}

	@Override
	public String getName() 
	{
		return name;
	}

	public void keyPress(KeyEvent c)
	{
		if (c.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
			if (string.length() > 1) string = string.substring(0, string.length()-2) + "|";
			else {
				if (text.size()>0) string = text.remove(text.size()-1) + "|";
			}
		} else {
			if (c.getKeyCode() == 10) {
				text.add(string.substring(0,string.length()-1));
				string = "|";
			}
			else if (c.getKeyCode() != 16) {
				if (string.length() > 120) {
					text.add(string.substring(0,string.length()-1)+c.getKeyChar());
					string = "|";
				} else {
					string = string.substring(0,string.length()-1) + c.getKeyChar() + "|";
				}
			}
		}
		//		System.out.println(c.getKeyCode());
		//		if (c.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE && string.length() > 1) string = string.substring(0, string.length()-2) + "|";
		//		else if (Character.toString(c.getKeyChar()).matches("[a-zA-Z0-9@.\\s]")) {  
		//				string = string.substring(0,string.length()-1) + c.getKeyChar() + "|";
		//		}
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
