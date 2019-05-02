package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Components.*;
import Main.HttpRequests;

public class AdminHomeUI  implements UIFrame{

	HttpRequests http = new HttpRequests();
	GUIList frameComponents = new GUIList();
	GUIList alertComponents = new GUIList();
	User searchedUser;
	Reward searchedReward;
	String status;
	boolean awaiting = false;

	AdminHomeUI(){
		refreshFrame();
	}

	private void refreshFrame() {
		frameComponents = new GUIList();
		frameComponents.add(new Shape("SQUARE", Color.WHITE, 0, 50, 800, 550, true));
		frameComponents.add(new TextField("User UID", 125, 10, "User UID"));
		frameComponents.add(new Button("Search", 5, 10, 100, 30, "Search User", "Search User", new Color(188, 201, 155), Color.WHITE, 12));
		frameComponents.add(new TextField("Reward", 530, 10, "Reward"));
		frameComponents.add(new Button("New Reward", 405, 10, 110, 30, "Search Reward", "Reward New", new Color(188, 201, 155), Color.WHITE, 12));
//		frameComponents.add(new TextField("Customer ID", 250, 150, "Customer ID"));
//		frameComponents.add(new TextField("Currency", 250, 200, "Currency"));
//		frameComponents.add(new Button("Exchange", 250, 250, 80, 40, "Buy", "Exchange", Color.WHITE , Color.WHITE, 16));
		if (searchedUser != null) {
			frameComponents.add(new Shape("SQUARE", new Color(188, 201, 155), 55, 90, 200, 30, true));
			frameComponents.add(new Label("Username", 50, 90, searchedUser.getUserName(), 16));			
			frameComponents.add(new Label("Coins", 50, 125, "Coins: " + searchedUser.getCoins(), 16));
			frameComponents.add(new TextField("Coins Value", 50, 150, "Coins Value"));
			frameComponents.add(new Button("Coin Increase", 225, 150, 80, 30, "Add", "Increase Coin", new Color(188, 201, 155), Color.WHITE, 16));	
			frameComponents.add(new Button("Coin Decrease", 325, 150, 80, 30, "Minus", "Decrease Coin", new Color(188, 201, 155), Color.WHITE, 16));			
			frameComponents.add(new Label("Diamonds", 50, 200, "Diamonds: "+ searchedUser.getDiamonds(), 16));			
			frameComponents.add(new TextField("Diamond Value", 50, 225, "Diamond Value"));
			frameComponents.add(new Button("Diamond Increase", 225, 225, 80, 30, "Add", "Increase Diamond", new Color(188, 201, 155), Color.WHITE, 16));	
			frameComponents.add(new Button("Diamond Decrease", 325, 225, 80, 30, "Minus", "Decrease Diamond", new Color(188, 201, 155), Color.WHITE, 16));	
			frameComponents.add(new Label("Tier", 50, 275, "Tier: " + + searchedUser.getTier(), 16));			
			frameComponents.add(new Button("Tier Increase", 50, 300, 80, 30, "Add", "Increase Tier", new Color(188, 201, 155), Color.WHITE, 16));	
			frameComponents.add(new Button("Tier Decrease", 150, 300, 80, 30, "Minus", "Decrease Tier", new Color(188, 201, 155), Color.WHITE, 16));	
			frameComponents.add(new Button("Submit", 50, 375, 80, 40, "Submit", "Submit", new Color(188, 201, 155), Color.WHITE, 16));
		}
		if (searchedReward != null) {
			frameComponents.add(new Label("Reward Name", 50, 50, searchedReward.getName(), 16));			
			if (searchedReward.getType() == null) {
				frameComponents.add(new Button("Set as Item", 130, 80, 80, 30, "Item", "Set as Item", new Color(188, 201, 155), Color.WHITE, 16));		
				frameComponents.add(new Label("Reward Type", 50, 80, "Type: ", 16));			
				frameComponents.add(new Button("Set as Coupon", 225, 80, 80, 30, "Coupon", "Set as Coupon", new Color(188, 201, 155), Color.WHITE, 16));		
			} else {
				if (searchedReward.getType().equals("Item")) {
					frameComponents.add(new Label("Reward Coins", 50, 125, "Current Coins: " + searchedReward.getCoin(), 16));
					frameComponents.add(new TextField("New Coins Value", 50, 150, "New Coins Value"));
					frameComponents.add(new Button("Set Coin", 225, 150, 80, 30, "Set Coin", "Set Reward Coin", new Color(188, 201, 155), Color.WHITE, 16));				
					frameComponents.add(new Label("Reward Diamonds", 50, 200, "Current Diamonds: "+ searchedReward.getDiamond(), 16));			
					frameComponents.add(new TextField("New Diamond Value", 50, 225, "New Diamond Value"));
					frameComponents.add(new Button("Set Diamond", 225, 225, 80, 30, "Set", "Set Reward Diamond", new Color(188, 201, 155), Color.WHITE, 16));		
					frameComponents.add(new Label("Reward Tier", 50, 275, "Current Tier: " + + searchedReward.getTier(), 16));			
					frameComponents.add(new TextField("New Tier Value", 50, 300, "New Tier Value"));
					frameComponents.add(new Button("Set Tier", 225, 300, 80, 30, "Set", "Set Reward Tier", new Color(188, 201, 155), Color.WHITE, 16));		


//					frameComponents.add(new Button("Upload Image", 225, 375, 80, 30, "Upload", "Upload Image", new Color(188, 201, 155), Color.WHITE, 16));		
//					frameComponents.add(new Label("Image", 50, 375, "", 16));			

					frameComponents.add(new Label("Current Strength", 375, 100, "Current Strength: " + searchedReward.getStrength(), 16));
					frameComponents.add(new TextField("Strength", 375, 125, "Strength"));
					frameComponents.add(new Button("Set Strength", 550, 125, 80, 30, "Set", "Set Reward Strength", new Color(188, 201, 155), Color.WHITE, 16));				

					frameComponents.add(new Label("Current Agility", 375, 175, "Current Agility: " + searchedReward.getAgility(), 16));
					frameComponents.add(new TextField("Agility", 375, 200, "Agility"));
					frameComponents.add(new Button("Set Agility", 550, 200, 80, 30, "Set", "Set Reward Agility", new Color(188, 201, 155), Color.WHITE, 16));				

					frameComponents.add(new Label("Current Intellect", 375, 250, "Current Intellect: " + searchedReward.getIntellect(), 16));
					frameComponents.add(new TextField("Intellect", 375, 275, "Intellect"));
					frameComponents.add(new Button("Set Intellect", 550, 275, 80, 30, "Set", "Set Reward Intellect", new Color(188, 201, 155), Color.WHITE, 16));				

					frameComponents.add(new Label("Current Dexterity", 375, 325, "Current Dexterity: " + searchedReward.getDexterity(), 16));
					frameComponents.add(new TextField("Dexterity", 375, 350, "Dexterity"));
					frameComponents.add(new Button("Set Dexterity", 550, 350, 80, 30, "Set", "Set Reward Dexterity", new Color(188, 201, 155), Color.WHITE, 16));				

					frameComponents.add(new Label("Current Stamina", 375, 400, "Current Stamina: " + searchedReward.getStamina(), 16));
					frameComponents.add(new TextField("Stamina", 375, 425, "Stamina"));
					frameComponents.add(new Button("Set Stamina", 550, 425, 80, 30, "Set", "Set Reward Stamina", new Color(188, 201, 155), Color.WHITE, 16));				

					frameComponents.add(new Label("Current Health", 375, 475, "Current Bonus Health: " + searchedReward.getHealth(), 16));
					frameComponents.add(new TextField("Health", 375, 500, "Health"));
					frameComponents.add(new Button("Set Health", 550, 500, 80, 30, "Set", "Set Reward Health", new Color(188, 201, 155), Color.WHITE, 16));				

					frameComponents.add(new Button("Submit", 50, 450, 150, 40, "Submit", "Submit", new Color(188, 201, 155), Color.WHITE, 16));
				} else if (searchedReward.getType().equals("Coupon")) {
					frameComponents.add(new Label("Reward Coins", 50, 125, "Current Coins: " + searchedReward.getCoin(), 16));
					frameComponents.add(new TextField("New Coins Value", 50, 150, "New Coins Value"));
					frameComponents.add(new Button("Set Coin", 225, 150, 80, 30, "Set Coin", "Set Reward Coin", new Color(188, 201, 155), Color.WHITE, 16));				
					frameComponents.add(new Label("Reward Diamonds", 50, 200, "Current Diamonds: "+ searchedReward.getDiamond(), 16));			
					frameComponents.add(new TextField("New Diamond Value", 50, 225, "New Diamond Value"));
					frameComponents.add(new Button("Set Diamond", 225, 225, 80, 30, "Set", "Set Reward Diamond", new Color(188, 201, 155), Color.WHITE, 16));		
					frameComponents.add(new Label("Reward Tier", 50, 275, "Current Tier: " + + searchedReward.getTier(), 16));			
					frameComponents.add(new TextField("New Tier Value", 50, 300, "New Tier Value"));
					frameComponents.add(new Button("Set Tier", 225, 300, 80, 30, "Set", "Set Reward Tier", new Color(188, 201, 155), Color.WHITE, 16));		
					
					frameComponents.add(new Button("Upload Image", 225, 375, 80, 30, "Upload", "Upload Image", new Color(188, 201, 155), Color.WHITE, 16));		
					frameComponents.add(new Label("Image", 50, 375, "", 16));			

					frameComponents.add(new Label("Current Discount Type", 375, 100, "Current Discount Type: " + searchedReward.getDiscountType(), 16));
					frameComponents.add(new Button("Set Absolute Type", 550, 125, 80, 30, "Dollar", "Set Absolute Type", new Color(188, 201, 155), Color.WHITE, 16));
					frameComponents.add(new Button("Set Percent Type", 450, 125, 80, 30, "Percent", "Set Percent Type", new Color(188, 201, 155), Color.WHITE, 16));				

					frameComponents.add(new Label("Current Discount", 375, 175, "Current Discount: " + searchedReward.getDiscount(), 16));
					frameComponents.add(new TextField("Discount", 375, 200, "Discount"));
					frameComponents.add(new Button("Set Discount", 550, 200, 80, 30, "Set", "Set Reward Discount", new Color(188, 201, 155), Color.WHITE, 16));	

					frameComponents.add(new Button("Submit", 50, 450, 150, 40, "Submit", "Submit", new Color(188, 201, 155), Color.WHITE, 16));
				}
			}
			//			frameComponents.add(new Button("Coupon Submit", 50, 500, 150, 40, "Coupon Submit", "Submit Coupon", new Color(188, 201, 155), Color.WHITE, 16));
		}
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
			String[] takeAction = getAction.split(" ");
			if (takeAction[0].equals("Confirm")) {
				if (takeAction[1].equals("Yes")) {
					if (takeAction[2].equals("not")) {
						alertComponents.remove("Warning");
						awaiting = false;
					}
				}
			}
			if (getAction.equals("Search User")) {
				searchedUser = HttpRequests.searchUser(Integer.parseInt(((TextField)frameComponents.get("User UID")).getString()));
				searchedReward = null;
			}
			else if (getAction.equals("Exchange")) {
				User upUser = HttpRequests.searchUser(Integer.parseInt(((TextField)frameComponents.get("Customer ID")).getString()));
//				upUser.setCoins(upUser.getCoins() + );
				HttpRequests.updateUser(upUser);
			}
			else if (getAction.equals("Reward New"))
			{
				status = null;
				searchedReward = HttpRequests.searchReward(new Reward(((TextField)frameComponents.get("Reward")).getString()));
				System.out.println(searchedReward);
				if (searchedReward.getType() != null) {
					if (searchedReward.getType().equals("Item")) status = "Item";
					if (searchedReward.getType().equals("Coupon")) status = "Coupon";
				}
				searchedUser = null;
			}
			else if (getAction.equals("Set Reward Coin")) {
				searchedReward.setCoin(searchedReward.getCoin() + Integer.parseInt(((TextField)frameComponents.get("New Coins Value")).getString()));
			}
			else if (getAction.equals("Set as Item")) {
				searchedReward.setType("Item");
			}
			else if (getAction.equals("Set as Coupon")) {
				searchedReward.setType("Coupon");
			}
			else if (getAction.equals("Set Reward Discount")) {
				searchedReward.setDiscount(Integer.parseInt(((TextField)frameComponents.get("Discount")).getString()));
			}
			else if (getAction.equals("Set Absolute Type")) {
				searchedReward.setDiscountType("Dollar");
			}
			else if (getAction.equals("Set Percent Type")) {
				searchedReward.setDiscountType("Percent");
			}
			else if (getAction.equals("Set Reward Diamond")) {
				searchedReward.setDiamond(searchedReward.getDiamond() + Integer.parseInt(((TextField)frameComponents.get("New Diamond Value")).getString()));
			}
			else if (getAction.equals("Set Reward Tier")) {
				searchedReward.setTier(searchedReward.getTier() + Integer.parseInt(((TextField)frameComponents.get("New Tier Value")).getString()));
			}
			else if (getAction.equals("Set Reward Strength")) {
				searchedReward.setStrength(searchedReward.getStrength() + Integer.parseInt(((TextField)frameComponents.get("Strength")).getString()));
			}
			else if (getAction.equals("Set Reward Agility")) {
				searchedReward.setAgility(searchedReward.getAgility() + Integer.parseInt(((TextField)frameComponents.get("Agility")).getString()));
			}
			else if (getAction.equals("Set Reward Intellect")) {
				searchedReward.setIntellect(searchedReward.getIntellect() + Integer.parseInt(((TextField)frameComponents.get("Intellect")).getString()));
			}
			else if (getAction.equals("Set Reward Dexterity")) {
				searchedReward.setDexterity(searchedReward.getDexterity() + Integer.parseInt(((TextField)frameComponents.get("Dexterity")).getString()));
			}
			else if (getAction.equals("Set Reward Stamina")) {
				searchedReward.setStamina(searchedReward.getStamina() + Integer.parseInt(((TextField)frameComponents.get("Stamina")).getString()));
			}
			else if (getAction.equals("Set Reward Health")) {
				searchedReward.setHealth(searchedReward.getHealth() + Integer.parseInt(((TextField)frameComponents.get("Health")).getString()));
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
			else if (getAction.equals("Submit Item")) {
				searchedReward.setType("Item");
				HttpRequests.updateReward(searchedReward);			
			} 
			else if (getAction.equals("Submit Coupon")) {
				searchedReward.setType("Coupon");		
			}
			else if (getAction.equals("Submit")) {
				if (searchedUser != null)
					HttpRequests.updateUser(searchedUser);							
					if (!awaiting) {
						alertComponents.add(new AlertBox("Warning", 200, 200, "User Updated", "not confirm"));
						awaiting = true;
					}
				if (searchedReward != null) {
					HttpRequests.updateReward(searchedReward);	
					if (!awaiting) {
						alertComponents.add(new AlertBox("Warning", 200, 200, "Reward Updated", "not confirm"));
						awaiting = true;
					}
				}
			}
			else if (getAction.equals("Activate")) {
				return getAction;
			}
			refreshFrame();
			return "Admin Frame";
			//		else return frameComponents.mouseSelect(mouseX, mouseY);
		}
		return null;
	}

	@Override
	public void keyPress(KeyEvent c) {
		frameComponents.keyPress(c);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}