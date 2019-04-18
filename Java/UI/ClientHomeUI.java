package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Components.*;
import Main.HttpRequests;

public class ClientHomeUI  implements UIFrame{

	HttpRequests http = new HttpRequests();
	GUIList frameComponents = new GUIList();
	GUIList alertComponents = new GUIList();
	User currentUser;
	boolean awaiting = false;

	ClientHomeUI(User getUser){
		currentUser = getUser;
//		ArrayList<UserBattles> temp = HttpRequests.getBattles(currentUser.getUserUID());
//		for (UserBattles x : temp) {
//			System.out.println(x.getID());
//		}
		frameComponents.add(new Shape("SQUARE", Color.WHITE, 0, 50, 800, 550, true));

		frameComponents.add(new Button("Home", 55, 0, 80, 50, "Home", "goto Home User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Shop", 135, 0, 80, 50, "Shop", "goto Shop User", new Color(232, 176, 175), Color.GRAY, 16));
		//		frameComponents.add(new Button("Gacha", 215, 0, 80, 50, "Gacha", "goto Gacha User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Guild", 215, 0, 80, 50, "Guild", "goto Guild User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Avatar", 295, 0, 80, 50, "Avatar", "goto Avatar User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("My Account", 580, 0, 80, 50, "My Account", "goto Account User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Logout", 680, 0, 80, 50, "Logout", "goto Logout User", new Color(232, 176, 175), Color.GRAY, 16));

		frameComponents.add(new Label("Page Title", 15, 70, "Welcome, " + currentUser.getFirstName(), 20));
		frameComponents.add(new Shape("SQUARE", Color.GRAY, 0, 100, 180, 450, true));
		frameComponents.add(new Shape("SQUARE", Color.BLACK, 10, 110, 160, 175, true));
		frameComponents.add(new Shape("SQUARE", Color.GRAY, 11, 111, 158, 173, true));
		frameComponents.add(new Label("User Name", 15, 130, currentUser.getUserName() + " #" + Integer.toString(currentUser.getTier()), 16));

		frameComponents.add(new Shape("SQUARE", Color.BLACK, 10, 305, 160, 235, true));
		frameComponents.add(new Shape("SQUARE", Color.GRAY, 11, 306, 158, 233, true));
		frameComponents.add(new Label("Coins", 15, 320, "Coins", 16));
		frameComponents.add(new Label("Coins", 55, 335, Integer.toString(currentUser.getCoins()), 14));
		frameComponents.add(new Label("Diamonds", 15, 360, "Diamonds", 16));
		frameComponents.add(new Label("Diamonds", 55, 375, Integer.toString(currentUser.getDiamonds()), 14));
		frameComponents.add(new Button("Message", 15, 410, 100, 35, "Messages", "goto Message User", Color.GRAY, Color.WHITE, 16));
		frameComponents.add(new Button("Other Avatar", 15, 450, 100, 35, "Other Avatars", "view other avatar", Color.GRAY, Color.WHITE, 16));
		frameComponents.add(new Button("Battles", 15, 490, 100, 35, "Check Battles", "goto Battle User", Color.GRAY, Color.WHITE, 16));
	}

	@Override
	public void draw(Graphics g) {
		frameComponents.draw(g);
		alertComponents.draw(g);
	}

	@Override
	public String clickAction(int mouseX, int mouseY) {
		String getAction = alertComponents.mouseSelect(mouseX, mouseY);		
		if (getAction == null) getAction = frameComponents.mouseSelect(mouseX, mouseY);
		if (getAction != null) {
			String[] splitAction = getAction.split(" ");
			if (splitAction[0].equals("Confirm")){
				if (splitAction[1].equals("Yes")) {
					if (splitAction[3].equals("Search")) {
						//						HttpRequests.purchaseReward(currentUser, getPurchase.getName());
						awaiting = false;
						alertComponents.remove("Search Avatar");
						return "goto Search avatar " + splitAction[2];
					}
				} else if (splitAction[1].equals("No")) {
					awaiting = false;
					alertComponents.remove("Search Avatar");
				}
			}
			if (splitAction[0].equals("view")) {		
				if (!awaiting) {
					alertComponents.add(new AlertBox("Search Avatar", 200, 200, "What avatar do you want to search for?", "Search", currentUser));
					awaiting = true;
				}
			} 
		}
		return getAction;
	}

	@Override
	public void keyPress(KeyEvent c) {
		frameComponents.keyPress(c);
		alertComponents.keyPress(c);
	}

	@Override
	public void tick() {
		frameComponents.tick();
	}
}
