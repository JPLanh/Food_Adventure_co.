package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Components.*;
import Main.HttpRequests;

public class ClientRefundUI  implements UIFrame{

	HttpRequests http = new HttpRequests();
	GUIList frameComponents = new GUIList();
	User currentUser;

	ClientRefundUI(User getUser){
//	ClientLoyaltyUI(){
		currentUser = getUser;
		ArrayList<Reward> rewardList = HttpRequests.getAllMyRewards(currentUser);
		frameComponents.add(new Shape("SQUARE", Color.WHITE, 0, 50, 800, 550, true));
		frameComponents.add(new Button("Home", 55, 0, 80, 50, "Home", "goto Home User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Shop", 135, 0, 80, 50, "Shop", "goto Shop User", new Color(232, 176, 175), Color.GRAY, 16));
//		frameComponents.add(new Button("Gacha", 215, 0, 80, 50, "Gacha", "goto Gacha User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Guild", 215, 0, 80, 50, "Guild", "goto Guild User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Avatar", 295, 0, 80, 50, "Avatar", "goto Avatar User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("My Account", 580, 0, 80, 50, "My Account", "goto Account User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Logout", 680, 0, 80, 50, "Logout", "goto Logout User", new Color(232, 176, 175), Color.GRAY, 16));

		frameComponents.add(new Shape("SQUARE", new Color(188, 201, 155), 0, 75, 200, 500, true));
		frameComponents.add(new Button("Coupon", 15, 100, 150, 30, "Coupon", "goto Coupon Account"));
		frameComponents.add(new Button("Item", 15, 140, 150, 30, "Item", "goto Item Account"));
		frameComponents.add(new Button("Reward", 15, 180, 150, 30, "My rewards", "goto Reward Account"));
		frameComponents.add(new Button("Purchase", 15, 220, 150, 30, "Get more diamonds", "goto Diamond Account"));

		for (int x = 0; x < rewardList.size(); x++) {
			frameComponents.add(new PurchaseItem(rewardList.get(x).getName(), 250 + ((160*x)) % (160*3), 110 + (int)(215*Math.floor((x)/(3))), 150, 150, rewardList.get(x).getCoin() + " coins", "Refund " + rewardList.get(x).getName()));
		}		
	}

	@Override
	public void draw(Graphics g) {
		frameComponents.draw(g);
	}

	@Override
	public String clickAction(int mouseX, int mouseY) {
		String getAction = frameComponents.mouseSelect(mouseX, mouseY);
		if (getAction != null) {
			String[] splitAction = getAction.split(" ");
			if (splitAction[0].equals("Refund")) {
				HttpRequests.refundReward(currentUser, getAction.substring(7));
				HttpRequests.finishRefund(currentUser, getAction.substring(7));
//				System.out.println(getAction.substring(9));
				return null;
				
			}
		}
		return getAction;
	}

	@Override
	public void keyPress(KeyEvent c) {
		frameComponents.keyPress(c);
	}

	@Override
	public void tick() {
		frameComponents.tick();
	}

}
