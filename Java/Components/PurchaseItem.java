package Components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class PurchaseItem extends JPanel implements UIComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6017453879784009002L;
	private int xPos, yPos, width, height, fontSize = 12;
	private int lowX, highX, lowY, highY;
	private String name, string, action = "";
	private Color fontColor = Color.BLACK, bkColor = new Color(188, 201, 155);
	private boolean hidden = false;
	private BufferedImage img, image;
	private Reward reward;

	public PurchaseItem(String getName, int getXPos, int getYPos, int getWidth, int getHeight, String getString, String getAction, Color getBKColor, Color getFontColor, int getSize)
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
		fontColor = getFontColor;
		bkColor = getBKColor;
		fontSize = getSize;
	}
	
	public PurchaseItem(String getName, int getXPos, int getYPos, int getWidth, int getHeight, String getString, String getAction, Color getBKColor, Color getFontColor)
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
		fontColor = getFontColor;
		bkColor = getBKColor;
		
	}

	public PurchaseItem(String getName, int getXPos, int getYPos, int getWidth, int getHeight, String getString)
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
	
	public PurchaseItem(Reward getReward, int getXPos, int getYPos, int getWidth, int getHeight, String getString, String getAction) {
		try {
			image = ImageIO.read(new File("img/bluehoodie.png"));
			img = resize(image, getHeight, getWidth);
//			img = new BufferedImage(getWidth, getHeight, BufferedImage.TYPE_INT_RGB);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reward = getReward;
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

	public PurchaseItem(String getName, int getXPos, int getYPos, int getWidth, int getHeight, String getString, String getAction)
	{
		try {
			image = ImageIO.read(new URL("http://35.235.118.188/img/" + getName.toLowerCase().replace(" ", "").replace("%", "%25")+".png"));
			img = resize(image, getHeight, getWidth);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	private static BufferedImage resize(BufferedImage img, int height, int width) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}
	@Override
	public void draw(Graphics g) 
	{
//		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, fontSize)); 
//		g.setColor(bkColor);
//		g.fillRect(xPos, yPos, width, height);
		g.drawImage(image, xPos, yPos, width, height, null);
		g.setColor(fontColor);
		if (reward != null) {
			g.drawString(reward.getName(), xPos+(width/2)-(string.length()*3), yPos-10);
			if (reward.getRedeemedDate() != null) {
				g.setColor(Color.BLACK);
				g.fillRect(xPos, yPos+(height/2)-10, width, 20);			
			}
		}
		else g.drawString(name, xPos+(width/2)-(string.length()*3), yPos-10);
		g.drawString(string, xPos+(width/2)-(string.length()*3), yPos+(height)+20);
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
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
		return action;
	}
	@Override
	public boolean isActive() {
		return false;
	}
}
