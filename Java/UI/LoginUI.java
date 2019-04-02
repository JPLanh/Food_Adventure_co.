package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Components.*;
import Main.HttpRequests;

public class LoginUI  implements UIFrame{

	HttpRequests http = new HttpRequests();
	GUIList frameComponents = new GUIList();
	GUIList animationComponents = new GUIList();

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
		animationComponents.draw(g);
	}

	@Override
	public String clickAction(int mouseX, int mouseY) {
		String getAction = frameComponents.mouseSelect(mouseX, mouseY);
		if (getAction != null) {
			if (getAction.equals("goto Login User")) {
				User checkUser = HttpRequests.getUser(0, ((TextField)frameComponents.get("Username")).getString(), ((TextField)frameComponents.get("Password")).getString());
				if (checkUser == null) {
					animationComponents.add(new Label("Invalid User", 280, 375, "Invalid Username or Password", true));
					return null;
				}
				else return getAction +  " " +  ((TextField)frameComponents.get("Username")).getString() + " " + ((TextField)frameComponents.get("Password")).getString();
			}
			return getAction;
		}
		return null;
	}

	@Override
	public void keyPress(KeyEvent c) {
		frameComponents.keyPress(c);
	}

	@Override
	public void tick() {
		animationComponents.tick();
	}
}
