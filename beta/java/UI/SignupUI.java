package UI;

import java.awt.Color;
import java.awt.Graphics;


import Components.Button;
import Components.GUIList;
import Components.Label;
import Components.LabelLink;
import Components.Shape;
import Components.TextField;
import Components.UIFrame;

public class SignupUI implements UIFrame{

	GUIList frameComponents = new GUIList();

	SignupUI(){
		frameComponents.add(new Shape("SQUARE", Color.WHITE, 250, 75, 250, 400, true));
		frameComponents.add(new Label("Sign up", 340, 150, "Sign Up"));
		frameComponents.add(new TextField("Username", 300, 180, "username"));
		frameComponents.add(new TextField("Email Address", 300, 220, "email address"));
		frameComponents.add(new TextField("password", 300, 260, "password"));
		frameComponents.add(new TextField("confirm", 300, 300, "confirm password"));
		frameComponents.add(new Button("SIGN UP", 300, 345, 150, 30, "SIGN UP", "goto Create User"));
		frameComponents.add(new Label("Already have", 275, 380, "Already have an account?"));
		frameComponents.add(new LabelLink("Login", 370, 383, 150, 30, "Login", "goto Login Frame"));
	}

	@Override
	public void draw(Graphics g) {
		frameComponents.draw(g);
	}


	@Override
	public String clickAction(int mouseX, int mouseY) {
		return frameComponents.mouseSelect(mouseX, mouseY);
	}
}
