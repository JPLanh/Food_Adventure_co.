package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Components.*;
import Main.HttpRequests;

public class AdminHomeUI  implements UIFrame{

	HttpRequests http = new HttpRequests();
	GUIList frameComponents = new GUIList();
	User searchedUser;
	Reward searchedReward;

	AdminHomeUI(){
		refreshFrame();
	}
	
	private void refreshFrame() {
		frameComponents = new GUIList();
		frameComponents.add(new Shape("SQUARE", Color.WHITE, 0, 50, 800, 550, true));
		frameComponents.add(new TextField("User UID", 125, 10, "8163"));
		frameComponents.add(new Button("Search", 5, 10, 80, 40, "Search User", "Search User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new TextField("Reward", 500, 10, "Reward"));
		frameComponents.add(new Button("New Reward", 405, 10, 80, 40, "Reward", "Reward New", new Color(232, 176, 175), Color.GRAY, 16));
		if (searchedUser != null) {
			frameComponents.add(new Label("Username", 50, 100, searchedUser.getUserName(), 16));			
			frameComponents.add(new Label("Coins", 50, 125, "Coins: " + searchedUser.getCoins(), 16));
			frameComponents.add(new TextField("Coins Value", 50, 150, "Coins Value"));
			frameComponents.add(new Button("Coin Increase", 225, 150, 80, 30, "Add", "Increase Coin", new Color(232, 176, 175), Color.GRAY, 16));	
			frameComponents.add(new Button("Coin Decrease", 325, 150, 80, 30, "Minus", "Decrease Coin", new Color(232, 176, 175), Color.GRAY, 16));			
			frameComponents.add(new Label("Diamonds", 50, 200, "Diamonds: "+ searchedUser.getDiamonds(), 16));			
			frameComponents.add(new TextField("Diamond Value", 50, 225, "Diamond Value"));
			frameComponents.add(new Button("Diamond Increase", 225, 225, 80, 30, "Add", "Increase Diamond", new Color(232, 176, 175), Color.GRAY, 16));	
			frameComponents.add(new Button("Diamond Decrease", 325, 225, 80, 30, "Minus", "Decrease Diamond", new Color(232, 176, 175), Color.GRAY, 16));	
			frameComponents.add(new Label("Tier", 50, 275, "Tier: " + + searchedUser.getTier(), 16));			
			frameComponents.add(new Button("Tier Increase", 50, 300, 80, 30, "Add", "Increase Tier", new Color(232, 176, 175), Color.GRAY, 16));	
			frameComponents.add(new Button("Tier Decrease", 150, 300, 80, 30, "Minus", "Decrease Tier", new Color(232, 176, 175), Color.GRAY, 16));	
			frameComponents.add(new Button("Submit", 50, 375, 80, 40, "Submit", "Submit", new Color(232, 176, 175), Color.GRAY, 16));
		}
		if (searchedReward != null) {
			frameComponents.add(new Label("Reward Name", 50, 100, searchedReward.getName(), 16));			
			frameComponents.add(new Label("Reward Coins", 50, 125, "Current Coins: " + searchedReward.getCoin(), 16));
			frameComponents.add(new TextField("New Coins Value", 50, 150, "New Coins Value"));
			frameComponents.add(new Button("Set Coin", 225, 150, 80, 30, "Set Coin", "Set Reward Coin", new Color(232, 176, 175), Color.GRAY, 16));				
			frameComponents.add(new Label("Reward Diamonds", 50, 200, "Current Diamonds: "+ searchedReward.getDiamond(), 16));			
			frameComponents.add(new TextField("New Diamond Value", 50, 225, "New Diamond Value"));
			frameComponents.add(new Button("Set Diamond", 225, 225, 80, 30, "Set", "Set Reward Diamond", new Color(232, 176, 175), Color.GRAY, 16));		
			frameComponents.add(new Label("Reward Tier", 50, 275, "Current Tier: " + + searchedReward.getTier(), 16));			
			frameComponents.add(new TextField("New Tier Value", 50, 300, "New Tier Value"));
			frameComponents.add(new Button("Set Tier", 225, 300, 80, 30, "Set", "Set Reward Tier", new Color(232, 176, 175), Color.GRAY, 16));		
			frameComponents.add(new Button("Submit", 50, 375, 80, 40, "Submit", "Submit", new Color(232, 176, 175), Color.GRAY, 16));
		}
	}

	@Override
	public void draw(Graphics g) {
		frameComponents.draw(g);
	}

	@Override
	public String clickAction(int mouseX, int mouseY) {
		String getAction = frameComponents.mouseSelect(mouseX, mouseY);
		if (getAction.equals("Search User")) {
			searchedUser = HttpRequests.searchUser(Integer.parseInt(((TextField)frameComponents.get("User UID")).getString()));
			searchedReward = null;
		} 
		else if (getAction.equals("Reward New"))
		{
			searchedReward = HttpRequests.searchReward(new Reward(((TextField)frameComponents.get("Reward")).getString()));
			searchedUser = null;
		}
		else if (getAction.equals("Set Reward Coin")) {
			searchedReward.setCoin(searchedReward.getCoin() + Integer.parseInt(((TextField)frameComponents.get("New Coins Value")).getString()));
		}
		else if (getAction.equals("Set Reward Diamond")) {
			searchedReward.setDiamond(searchedReward.getDiamond() + Integer.parseInt(((TextField)frameComponents.get("New Diamond Value")).getString()));
		}
		else if (getAction.equals("Set Reward Tier")) {
			searchedReward.setTier(searchedReward.getTier() + Integer.parseInt(((TextField)frameComponents.get("New Tier Value")).getString()));
		}
		else if (getAction.equals("Increase Coin")) {
			searchedUser.setCoins(searchedUser.getCoins() + Integer.parseInt(((TextField)frameComponents.get("Coins Value")).getString()));
		}
		else if (getAction.equals("Decrease Coin")) {
			searchedUser.setCoins(searchedUser.getCoins() - Integer.parseInt(((TextField)frameComponents.get("Coins Value")).getString()));
			
		}
		else if (getAction.equals("Increase Diamond")) {
			searchedUser.setDiamonds(searchedUser.getDiamonds() + Integer.parseInt(((TextField)frameComponents.get("Diamond Value")).getString()));			
		}
		else if (getAction.equals("Decrease Diamond")) {
			searchedUser.setDiamonds(searchedUser.getDiamonds() - Integer.parseInt(((TextField)frameComponents.get("Diamond Value")).getString()));
			
		}
		else if (getAction.equals("Increase Tier")) {
			searchedUser.setTier(searchedUser.getTier() + 1);			
		}
		else if (getAction.equals("Decrease Tier")) {
			searchedUser.setTier(searchedUser.getTier() - 1);
			
		}
		else if (getAction.equals("Submit")) {
			if (searchedUser != null)
				HttpRequests.updateUser(searchedUser);
			if (searchedReward != null)
				HttpRequests.updateReward(searchedReward);
		}
		else if (getAction.equals("Activate")) {
			return getAction;
		}
		refreshFrame();
		return "Admin Frame";
//		else return frameComponents.mouseSelect(mouseX, mouseY);
	}

	@Override
	public void keyPress(KeyEvent c) {
		frameComponents.keyPress(c);
	}

}