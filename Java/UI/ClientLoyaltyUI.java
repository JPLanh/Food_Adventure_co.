package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Components.*;
import Main.HttpRequests;

public class ClientLoyaltyUI  implements UIFrame{

	HttpRequests http = new HttpRequests();
	GUIList frameComponents = new GUIList();
	GUIList refreshComponents = new GUIList();
	GUIList alertComponents = new GUIList();
	User currentUser;
	int page = 0;
	private String status;
	private boolean awaiting;
	ArrayList<Reward> rewardList;

	ClientLoyaltyUI(User getUser){
		status = "Item";
		currentUser = getUser;
		rewardList = HttpRequests.getAllRewards();

		frameComponents.add(new Shape("SQUARE", Color.WHITE, 0, 50, 800, 550, true));
		frameComponents.add(new Button("Home", 55, 0, 80, 50, "Home", "goto Home User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Shop", 135, 0, 80, 50, "Shop", "goto Shop User", new Color(232, 176, 175), Color.GRAY, 16));
		//		frameComponents.add(new Button("Gacha", 215, 0, 80, 50, "Gacha", "goto Gacha User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Guild", 215, 0, 80, 50, "Guild", "goto Guild User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Avatar", 295, 0, 80, 50, "Avatar", "goto Avatar User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("My Account", 580, 0, 80, 50, "My Account", "goto Account User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Logout", 680, 0, 80, 50, "Logout", "goto Logout User", new Color(232, 176, 175), Color.GRAY, 16));

		frameComponents.add(new Shape("SQUARE", new Color(188, 201, 155), 0, 75, 200, 500, true));
		frameComponents.add(new Button("Coupon", 15, 100, 150, 30, "Coupon", "Coupon Account"));
		frameComponents.add(new Button("Item", 15, 140, 150, 30, "Item", "Item Account"));
		frameComponents.add(new Button("Reward", 15, 180, 150, 30, "My rewards", "Reward Account"));
		frameComponents.add(new Button("Purchase", 15, 220, 150, 30, "Get more diamonds", "Diamond Account"));
	}

	private void refresh() {
		refreshComponents = new GUIList();
		for (int x = 0 + (page*6); x < rewardList.size(); x++) {
			refreshComponents.add(new PurchaseItem(rewardList.get(x).getName(), 250 + ((160*x)) % (160*3), 110 + (int)(215*Math.floor((x)/(3))) - (430*page), 150, 150, rewardList.get(x).getCoin() + " coins", status + " " + rewardList.get(x).getName()));
		}	

		if (page > 0) refreshComponents.add(new Button("Previous Page", 210, 100, 40, 400, "<", "Previous Page"));
		if (rewardList.size() > 5) refreshComponents.add(new Button("Next Page", 720, 100, 40, 400, ">", "Next Page"));
	}

	@Override
	public void draw(Graphics g) {
		frameComponents.draw(g);
		refreshComponents.draw(g);
		alertComponents.draw(g);
	}
//Substring is off, for cat hat the substring ends up being "s t Hat"
	@Override
	public String clickAction(int mouseX, int mouseY) {
		String getAction = frameComponents.mouseSelect(mouseX, mouseY);
		if (getAction == null) getAction = refreshComponents.mouseSelect(mouseX, mouseY);
		if (getAction == null) getAction = alertComponents.mouseSelect(mouseX, mouseY);		
		if (getAction != null) {
			String[] splitAction = getAction.split(" ");
			if (splitAction[0].equals("Purchase")) {		
				if (!awaiting) {
					alertComponents.add(new AlertBox("Confirm purchase", 200, 200, "Are you sure you want to purchase " + getAction.substring(9) + "?", getAction.substring(9)));
					awaiting = true;
				}
			} else if (splitAction[0].equals("Refund")) {		
				if (!awaiting) {
					alertComponents.add(new AlertBox("Confirm refund", 200, 200, "Refund " + getAction.substring(7) + "?", getAction.substring(7)));
					awaiting = true;
				}
			} else if (splitAction[0].equals("Next")) {
				page++;
			} else if (splitAction[0].equals("Previous")) {
				page--;
			} else if (splitAction[0].equals("Item")) {
				status = "Item";
				rewardList = new ArrayList<Reward>();
				ArrayList<Reward> tempReward = HttpRequests.getAllRewards();
				for (Reward x : tempReward) {
					if (x.getType().equals("Item"))
						rewardList.add(x);
				}
			} else if (splitAction[0].equals("Coupon")) {
				status = "Coupon";
				rewardList = new ArrayList<Reward>();
				ArrayList<Reward> tempReward = HttpRequests.getAllRewards();
				for (Reward x : tempReward) {
					if (x.getType().equals("Coupon"))
						rewardList.add(x);
				}
			} else if (splitAction[0].equals("Reward")) {
				status = "Refund";
				rewardList = HttpRequests.getAllMyRewards(currentUser);
			} else if (splitAction[0].equals("Confirm")){
				if (splitAction[1].equals("Yes")) {
					if (status.equals("Purchase")) {
						HttpRequests.purchaseReward(currentUser, getAction.substring(12));
						HttpRequests.finishPurchase(currentUser, getAction.substring(12));
						alertComponents.remove("Confirm purchase");
					} else if (status.equals("Refund")){
						System.out.println(getAction.substring(8));
						HttpRequests.refundReward(currentUser, getAction.substring(10));
						HttpRequests.finishRefund(currentUser, getAction.substring(10));
						alertComponents.remove("Confirm refund");
					}
				} else if (splitAction[1].equals("No")) {
					if (status.equals("Purchase")) {
						alertComponents.remove("Confirm purchase");
					} else if (status.equals("Refund")) {
						alertComponents.remove("Confirm refund");
					}
				}
			} else {
				System.out.println(getAction);
				return getAction;
			}
		} 
		return null;
	}

	@Override
	public void keyPress(KeyEvent c) {
		System.out.println(c);
		frameComponents.keyPress(c);
	}

	@Override
	public void tick() {
		refresh();
		frameComponents.tick();
	}
}
