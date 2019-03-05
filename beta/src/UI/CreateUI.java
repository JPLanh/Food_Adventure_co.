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

public class CreateUI implements UIFrame{

	GUIList frameComponents = new GUIList();

	CreateUI(){
		frameComponents.add(new Shape("SQUARE", Color.WHITE, 250, 120, 250, 350, true));
		frameComponents.add(new Label("Create", 310, 150, "Create an Account"));
		frameComponents.add(new TextField("First Name", 300, 180, "First Name"));
		frameComponents.add(new TextField("Last Name", 300, 220, "Last Name"));
		frameComponents.add(new TextField("Phone", 300, 260, "Phone"));
		frameComponents.add(new TextField("Email", 300, 300, "Email"));
		frameComponents.add(new TextField("Date of Birth", 300, 340, "Date of Birth"));
		frameComponents.add(new Button("SIGN UP", 300, 385, 150, 30, "SIGN UP", "goto Create User"));
	}

	@Override
	public void draw(Graphics g) {
		frameComponents.draw(g);
	}


	@Override
	public String clickAction(int mouseX, int mouseY) {
		String getAction = frameComponents.mouseSelect(mouseX, mouseY);
		if (getAction != null) {
			if (getAction.equals("goto Create User")) HttpRequests.createUser(
					((TextField)frameComponents.get("First Name")).getString(), ((TextField)frameComponents.get("Last Name")).getString(),
					((TextField)frameComponents.get("Email")).getString(), ((TextField)frameComponents.get("Phone")).getString(), 
					((TextField)frameComponents.get("Date of Birth")).getString()
					);
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
