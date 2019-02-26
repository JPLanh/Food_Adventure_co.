package UI;

import java.awt.Color;
import java.awt.Graphics;

import Components.*;

public class LoginUI  implements UIFrame{

	GUIList frameComponents = new GUIList();

	LoginUI(){
		frameComponents.add(new Shape("SQUARE", Color.WHITE, 250, 120, 250, 260, true));
		frameComponents.add(new Label("Login", 350, 150, "Login"));
		frameComponents.add(new TextField("Username", 300, 180, "username"));
		frameComponents.add(new TextField("password", 300, 220, "password"));
		frameComponents.add(new Button("Login", 300, 260, 150, 30, "LOGIN", "goto Login User"));
		frameComponents.add(new Label("Create Account", 265, 300, "Not registered?"));
		frameComponents.add(new LabelLink("Register", 345, 303, 150, 30, "Create an account", "goto Register Frame"));
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
