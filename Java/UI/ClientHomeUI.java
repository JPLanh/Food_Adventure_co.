package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Components.*;
import Main.HttpRequests;

public class ClientHomeUI  implements UIFrame{

	HttpRequests http = new HttpRequests();
	GUIList frameComponents = new GUIList();

	ClientHomeUI(User currentUser){
		frameComponents.add(new Shape("SQUARE", Color.WHITE, 0, 50, 800, 550, true));

		frameComponents.add(new Button("Home", 55, 0, 80, 50, "Home", "goto Home User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Shop", 135, 0, 80, 50, "Shop", "goto Shop User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Gacha", 215, 0, 80, 50, "Gacha", "goto Gacha User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Guild", 295, 0, 80, 50, "Guild", "goto Guild User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Tournamnet", 375, 0, 80, 50, "Tournament", "goto Tournament User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("My Account", 580, 0, 80, 50, "My Account", "goto Account User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Logout", 680, 0, 80, 50, "Logout", "goto Logout User", new Color(232, 176, 175), Color.GRAY, 16));

		frameComponents.add(new Label("Page Title", 15, 70, "Welcome, " + currentUser.getFirstName(), 20));
		frameComponents.add(new Shape("SQUARE", Color.GRAY, 0, 100, 180, 400, true));
		frameComponents.add(new Shape("SQUARE", Color.BLACK, 10, 110, 160, 175, true));
		frameComponents.add(new Shape("SQUARE", Color.GRAY, 11, 111, 158, 173, true));
		frameComponents.add(new Label("User Name", 15, 130, currentUser.getUserName() + " #" + Integer.toString(currentUser.getTier()), 16));

		frameComponents.add(new Shape("SQUARE", Color.BLACK, 10, 305, 160, 175, true));
		frameComponents.add(new Shape("SQUARE", Color.GRAY, 11, 306, 158, 173, true));
		frameComponents.add(new Label("Coins", 15, 320, "Coins", 16));
		frameComponents.add(new Label("Coins", 55, 335, Integer.toString(currentUser.getCoins()), 14));
		frameComponents.add(new Label("Diamonds", 15, 360, "Diamonds", 16));
		frameComponents.add(new Label("Diamonds", 55, 375, Integer.toString(currentUser.getDiamonds()), 14));
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
