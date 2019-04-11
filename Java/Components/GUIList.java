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
//		listOfGui.add(getComponent);
		if (getComponent instanceof AlertBox) {
			listOfGui.add(0, getComponent);
		}
		else listOfGui.add(getComponent);
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
	public String mouseSelect(int mouseX, int mouseY)
	{
		for (UIComponent x : listOfGui)
		{
			if (x.isClickedOn(mouseX, mouseY))
			{
				if (x instanceof UICompound) {
					return ((UICompound) x).clickAction(mouseX, mouseY);
				}
				if (x instanceof UIComponent) {
					return x.clickAction();
				}
			}
		}
		return null;
	}
	
	public void keyPress(KeyEvent keyPress)
	{
		for (UIComponent x : listOfGui)
		{
			if (x instanceof AlertBox) {
				((AlertBox) x).keyPress(keyPress);
			}
			if (x.isActive())
			{
				if (x instanceof TextField) ((TextField) x).keyPress(keyPress);
			}
		}
	}

	public void remove(String componentName){
		UIComponent getUI = null;
		for (UIComponent x : listOfGui)
		{
			if (x.getName().equals(componentName)) getUI = x;
			break;
		}
		listOfGui.remove(getUI);
	}	
	public void tick() {
		UIComponent removal = null;
		for (UIComponent x : listOfGui) {
			if (x instanceof Label) {
				if (((Label) x).getLifeSpan() > 0) {
					((Label) x).setLifeSpan(((Label) x).getLifeSpan()-1);
				} else if (((Label) x).getLifeSpan() == 0) {
					removal = x;
				}
			}
		}
		if (removal != null) {
			listOfGui.remove(removal);
		}
	}
}
