package Components;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GUIList {
	private ArrayList<UIComponent> listOfGui;
	
	public GUIList()
	{
		listOfGui = new ArrayList<UIComponent>();
	}
	
	public void add(UIComponent getComponent)
	{
		listOfGui.add(getComponent);
	}

	public UIComponent get(String componentName)
	{
		for (UIComponent x : listOfGui)
		{
			if (x.getName().equals(componentName)) return x;
		}
		return null;
	}
	
	public void draw(Graphics g)
	{
		for (UIComponent x : listOfGui)
		{
			x.draw(g);
		}
	}
}
