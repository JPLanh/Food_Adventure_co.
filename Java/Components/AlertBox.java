package Components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import Components.Button;
import Components.GUIList;
import Components.Label;
import Components.Shape;
import Components.UIComponent;
import Components.UICompound;

public class AlertBox implements UICompound{
	GUIList compoundComponents = new GUIList();
	int posx, posy, lowX, lowY, highX, highY; 
	boolean alive = true, confirm = false;
	String string, name, action;
	private User user;

	public AlertBox(String getName, int getInitX, int getInitY, String getString, String getAction, User getUser, String inputMsg) {
		user = getUser;
		posx = getInitX;
		posy = getInitY;
		lowX = posx;
		highX = posx+200;
		lowY = posy;
		highY = posy+100;
		string = getString;
		name = getName;
		action = getAction;
		compoundComponents.add(new Shape("SQUARE", Color.WHITE, posx, posy, 400, 100, true));
		compoundComponents.add(new Shape("SQUARE", Color.GRAY, posx, posy, 400, 100));
		compoundComponents.add(new Label("Message", posx + 15, posy + 5, string));
		compoundComponents.add(new TextField(inputMsg, posx + 15, posy + 30, inputMsg, 300));
		compoundComponents.add(new Button("Yes", posx +20, posy+70, 50, 25, "Submit", "Check Yes " + action));
		compoundComponents.add(new Button("No", posx +120, posy+70, 50, 25, "Cancel", "Check No " + action));
	}
	
	public AlertBox(String getName, int getInitX, int getInitY, String getString, String getAction) {
		posx = getInitX;
		posy = getInitY;
		lowX = posx;
		highX = posx+200;
		lowY = posy;
		highY = posy+100;
		confirm = true;
		string = getString;
		name = getName;
		action = getAction;
		compoundComponents.add(new Shape("SQUARE", Color.WHITE, posx, posy, 400, 100, true));
		compoundComponents.add(new Shape("SQUARE", Color.GRAY, posx, posy, 400, 100));
		compoundComponents.add(new Label("Message", posx + 15, posy + 5, string));
		compoundComponents.add(new Button("Yes", posx +20, posy+70, 50, 25, "Ok", "Check Yes " + action));
	}

	public AlertBox(String getName, int getInitX, int getInitY, String getString, String getAction, User getUser, boolean messageGet) {
		user = getUser;
		posx = getInitX;
		posy = getInitY;
		lowX = posx;
		highX = posx+200;
		confirm = true;
		lowY = posy;
		highY = posy+100;
		string = getString;
		name = getName;
		action = getAction;
		compoundComponents.add(new Shape("SQUARE", Color.WHITE, posx, posy, 200, 100, true));
		compoundComponents.add(new Shape("SQUARE", Color.GRAY, posx, posy, 200, 100));
		compoundComponents.add(new Label("Message", posx + 15, posy + 5, string));
		compoundComponents.add(new Button("Yes", posx +20, posy+70, 50, 25, "Submit", "Check Yes " + action));
		compoundComponents.add(new Button("No", posx +120, posy+70, 50, 25, "Cancel", "Check No " + action));
	}
	
	@Override
	public void draw(Graphics g) {
		compoundComponents.draw(g);
	}

	@Override
	public boolean isClickedOn(int mouseX, int mouseY) {
		if (mouseX < highX && mouseX > lowX && mouseY < highY && mouseY > lowY)	return true;
		else return false;
	}

	@Override
	public String getName() {
		return name;
	}

	public void keyPress(KeyEvent c) {
		compoundComponents.keyPress(c);
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	@Override
	public String clickAction() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String clickAction(int mouseX, int mouseY) {
		String temp = compoundComponents.mouseSelect(mouseX, mouseY);
		if (temp != null) {
			String[] splitText = temp.split(" ");
			if (splitText[0].equals("Check")) {
				if (splitText[1].equals("Yes")) {
					if (confirm) return "Confirm Yes " + action;
					else return "Confirm Yes " + ((TextField)compoundComponents.get("Email")).getString() + " " + action;
				} else if (splitText[1].equals("No")){
					if (confirm) return "Confirm No " + action;
					else return "Confirm No " + ((TextField)compoundComponents.get("Email")).getString() + " " + action;
				}
			} 
		}
		return temp;
		//		return compoundComponents.mouseSelect(mouseX, mouseY);
	}
}
