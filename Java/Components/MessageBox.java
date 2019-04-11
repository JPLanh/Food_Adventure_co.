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

public class MessageBox implements UICompound{
	GUIList compoundComponents = new GUIList();
	int posx, posy, lowX, lowY, highX, highY; 
	boolean alive = true;
	String name, action;
	private User user;
	private Message msg;

	public MessageBox(String getName, int getInitX, int getInitY, Message getMsg, String getAction, User getUser) {
		msg = getMsg;
		user = getUser;
		posx = getInitX;
		posy = getInitY;
		lowX = posx;
		highX = posx+682;
		lowY = posy;
		highY = posy+75;
		name = getName;
		action = getAction;
		compoundComponents.add(new Shape("SQUARE", Color.GRAY, posx, posy, 682, 75));
		compoundComponents.add(new Label("Subject", posx + 140, posy + 5, "Subject: " + getMsg.getSubject(), 16));
		String prevMsg;
		if (getMsg.getBodyMsg().length() < 40) prevMsg = getMsg.getBodyMsg();
		else prevMsg = getMsg.getBodyMsg().substring(0, 39);
		compoundComponents.add(new Label("Message", posx + 140, posy + 35, prevMsg, 14));
		compoundComponents.add(new Label("From", posx + 5, posy + 15, getMsg.getFrom(), 14));
		compoundComponents.add(new Label("Date", posx + 495, posy + 5, getMsg.getDate().substring(0, 10) + ",   " + getMsg.getDate().substring(11, 16), 14));
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
		return action;	
	}


	@Override
	public String clickAction(int mouseX, int mouseY) {
		return "read " + action;
//		System.out.println("Test");
//		String temp = compoundComponents.mouseSelect(mouseX, mouseY);
//		if (temp != null) {
//			String[] splitText = temp.split(" ");
//			if (splitText[0].equals("Check")) {
//				if (splitText[1].equals("Yes")) {
//						return "Confirm Yes " + ((TextField)compoundComponents.get("Email")).getString() + " " + action;
//				} else if (splitText[1].equals("No")){
//					return "Confirm No";
//				}
//			} 
//		}
//		return temp;
		//		return compoundComponents.mouseSelect(mouseX, mouseY);
	}
}
