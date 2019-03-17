package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Components.Button;
import Components.GUIList;
import Components.Label;
import Components.LabelLink;
import Components.Shape;
import Components.TextField;
import Components.UIFrame;
import Main.HttpRequests;

public class SignupUI implements UIFrame{

	GUIList frameComponents = new GUIList();

	SignupUI(){
		frameComponents.add(new Shape("SQUARE", Color.WHITE, 250, 120, 250, 350, true));
		frameComponents.add(new Label("Sign up", 340, 150, "Sign Up"));
		frameComponents.add(new TextField("User UID", 300, 180, "User UID"));
		frameComponents.add(new TextField("Username", 300, 220, "Username"));
		frameComponents.add(new TextField("Password", 300, 260, "Password"));
		frameComponents.add(new TextField("Confirm Password", 300, 300, "Confirm Password"));
		frameComponents.add(new Button("SIGN UP", 300, 345, 150, 30, "SIGN UP", "goto Sign User"));
		frameComponents.add(new Label("Already have", 275, 380, "Already have an account?"));
		frameComponents.add(new LabelLink("Login", 370, 383, 150, 30, "Login", "goto Login Frame"));
	}

	@Override
	public void draw(Graphics g) {
		frameComponents.draw(g);
	}


	@Override
	public String clickAction(int mouseX, int mouseY) {
		String getAction = frameComponents.mouseSelect(mouseX, mouseY);
		if (getAction != null) {
			return getAction + " " + ((TextField)frameComponents.get("User UID")).getString() + " " + ((TextField)frameComponents.get("Username")).getString() + " " + ((TextField)frameComponents.get("Password")).getString();
		}
		return null;
	}

	@Override
	public void keyPress(KeyEvent c) {
		frameComponents.keyPress(c);
		
	}
}
