package Components;

import java.awt.Graphics;

public interface UIComponent {
	
	public void draw(Graphics g);
	public String getName();
	public boolean isClickedOn(int mouseX, int mouseY);
	public String clickAction();
	public boolean isActive();
}
