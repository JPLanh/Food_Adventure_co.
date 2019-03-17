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
import Components.User;
import Main.HttpRequests;

public class ClientEditAccountUI implements UIFrame{

	GUIList frameComponents = new GUIList();
	User currentUser;
	
	ClientEditAccountUI(User getUser){
		currentUser = getUser;
		frameComponents.add(new Shape("SQUARE", Color.WHITE, 0, 50, 800, 550, true));
		frameComponents.add(new Shape("SQUARE", Color.GRAY, 49, 129, 682, 422, true));
		frameComponents.add(new Shape("SQUARE", Color.WHITE, 50, 130, 680, 420, true));
		

		frameComponents.add(new Button("Home", 55, 0, 80, 50, "Home", "goto Home User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Shop", 135, 0, 80, 50, "Shop", "goto Shop User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Gacha", 215, 0, 80, 50, "Gacha", "goto Gacha User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Tournament", 295, 0, 80, 50, "Tournament", "goto Tournament User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("My Account", 580, 0, 80, 50, "My Account", "goto Account User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Logout", 680, 0, 80, 50, "Logout", "goto Logout User", new Color(232, 176, 175), Color.GRAY, 16));

		frameComponents.add(new Button("Submit Account", 600, 100, 80, 50, "Submit Changes", "goto Submit User", Color.WHITE, Color.BLUE, 14));
		
		frameComponents.add(new Label("Page Title", 330, 100, "Account Info ", 20));
		
		frameComponents.add(new Label("User UID Label", 180, 150, "User UID: ", 16));
		frameComponents.add(new Label("User Name Label", 180, 215, "User Name: ", 16));
		frameComponents.add(new Label("Name Label", 180, 280, "Name: ", 16));
		frameComponents.add(new Label("Email Label", 180, 345, "Email: ", 16));
		frameComponents.add(new Label("Phone Label", 180, 410, "Phone: ", 16));
		frameComponents.add(new Label("Date Of Birth Label", 180, 475, "Date of Birth: ", 16));

		frameComponents.add(new Label("User UID", 180, 175, Integer.toString(getUser.getUserUID()), 14));
		frameComponents.add(new Label("User Name", 180, 240, getUser.getUserName(), 14));
		frameComponents.add(new Label("Name", 180, 305, getUser.getFirstName() + " " + getUser.getLastName(), 14));
		frameComponents.add(new TextField("Email", 180, 370, getUser.getEmail()));
		frameComponents.add(new TextField("Phone", 180, 435, getUser.getPhone()));
		frameComponents.add(new Label("Date Of Birth", 180, 500, getUser.getDateOfBirth(), 14));
	}

	@Override
	public void draw(Graphics g) {
		frameComponents.draw(g);
	}


	@Override
	public String clickAction(int mouseX, int mouseY) {
		String getAction = frameComponents.mouseSelect(mouseX, mouseY);
		if (getAction != null) {
			if (getAction.equals("goto Submit User")) {
				currentUser.setEmail(((TextField)frameComponents.get("Email")).getString());
				currentUser.setPhone(((TextField)frameComponents.get("Phone")).getString());
				HttpRequests.updateUser(currentUser);
			}
			return getAction;
		}
		return null;
	}

	@Override
	public void keyPress(KeyEvent c) {
		frameComponents.keyPress(c);
		
	}
}
