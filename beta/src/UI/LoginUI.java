package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Components.*;
import Main.HttpRequests;

public class LoginUI  implements UIFrame{

	HttpRequests http = new HttpRequests();
	GUIList frameComponents = new GUIList();

	LoginUI(){
		frameComponents.add(new Shape("SQUARE", Color.WHITE, 250, 120, 250, 260, true));
		frameComponents.add(new Label("Login", 350, 150, "Login"));
		frameComponents.add(new TextField("Username", 300, 180, "Username"));
		frameComponents.add(new TextField("Password", 300, 220, "Password"));
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
		String getAction = frameComponents.mouseSelect(mouseX, mouseY);
		if (getAction != null) {
			String getUser = ((TextField)frameComponents.get("username")).getString();
			if (getAction.equals("goto Login User")) HttpRequests.getUser(0, ((TextField)frameComponents.get("username")).getString(), ((TextField)frameComponents.get("password")).getString());
			else return getAction;
			return null;
		}
		return null;
	}

	@Override
	public void keyPress(KeyEvent c) {
		frameComponents.keyPress(c);
	}

}
