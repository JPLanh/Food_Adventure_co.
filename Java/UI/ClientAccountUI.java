package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Components.*;
import Main.HttpRequests;

public class ClientAccountUI  implements UIFrame{

	HttpRequests http = new HttpRequests();
	GUIList frameComponents = new GUIList();

	ClientAccountUI(){
		frameComponents.add(new Shape("SQUARE", Color.WHITE, 0, 0, 800, 40, true));
		frameComponents.add(new Button("Edit Account", 15, 265, 150, 30, "Edit Account", "goto Edit Account"));
		frameComponents.add(new Button("View Account", 15, 300, 150, 30, "View Account", "goto View Account"));
		frameComponents.add(new Button("Terminate Account", 15, 335, 150, 30, "Terminate Account", "goto Terminate Account"));
		frameComponents.add(new Button("Back Home", 15, 370, 150, 30, "Back to home", "goto Home User"));
	}

	@Override
	public void draw(Graphics g) {
		frameComponents.draw(g);
	}

	@Override
	public String clickAction(int mouseX, int mouseY) {
		return frameComponents.mouseSelect(mouseX, mouseY);
	}

	@Override
	public void keyPress(KeyEvent c) {
		frameComponents.keyPress(c);
	}

}
