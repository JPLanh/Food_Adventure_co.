package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Components.*;
import Main.HttpRequests;

public class ClientBattleUI  implements UIFrame{

	HttpRequests http = new HttpRequests();
	GUIList frameComponents = new GUIList();
	User currentUser;
	private String state = ""; 
	ArrayList<Guild> myGuilds, guildList;
	GUIList alertComponents = new GUIList();
//	Guild currentGuild;
	UserBattles currentBattle;
	boolean awaiting = false;

	ClientBattleUI(User currentUser){
		this.currentUser = currentUser;
		refreshFrame();
		//		frameComponents.add(new ImageButton(currentUser.getUserName(), 250, 150, 150, 150, null, "Guild member" + currentUser.getUserName()));
	}

	private void refreshFrame() {
		frameComponents = new GUIList();
		frameComponents.add(new Shape("SQUARE", Color.WHITE, 0, 50, 800, 550, true));

		frameComponents.add(new Button("Home", 55, 0, 80, 50, "Home", "goto Home User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Shop", 135, 0, 80, 50, "Shop", "goto Shop User", new Color(232, 176, 175), Color.GRAY, 16));
		//		frameComponents.add(new Button("Gacha", 215, 0, 80, 50, "Gacha", "goto Gacha User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Guild", 215, 0, 80, 50, "Guild", "goto Guild User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Avatar", 295, 0, 80, 50, "Avatar", "goto Avatar User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("My Account", 580, 0, 80, 50, "My Account", "goto Account User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Logout", 680, 0, 80, 50, "Logout", "goto Logout User", new Color(232, 176, 175), Color.GRAY, 16));

		frameComponents.add(new Shape("SQUARE", Color.GRAY, 0, 100, 180, 400, true));
		frameComponents.add(new Shape("SQUARE", Color.BLACK, 10, 110, 160, 175, true));
		frameComponents.add(new Shape("SQUARE", Color.GRAY, 11, 111, 158, 173, true));

		frameComponents.add(new Shape("SQUARE", Color.BLACK, 10, 305, 160, 175, true));
		frameComponents.add(new Shape("SQUARE", Color.GRAY, 11, 306, 158, 173, true));
		if (currentBattle != null) {
			
		} else {
			ArrayList<UserBattles> bats = HttpRequests.getBattles(currentUser.getUserUID());
			for (int i = 0; i < bats.size(); i++) {
				if (bats.get(i).isResult()) frameComponents.add(new Button("Battle " + bats.get(i).getRoom(), 250, 150 + (50*i), 200, 50, "Avatar: " + bats.get(i).getParticipant().getName() + ", Battle: " + bats.get(i).getRoom().getRoom() + ", Damage Done " + bats.get(i).getDamage() , "Check " + i, Color.WHITE, Color.GRAY, 16));				
				else frameComponents.add(new Button("Battle " + bats.get(i).getRoom(), 250, 150 + (50*i), 400, 50, "Avatar: " + bats.get(i).getParticipant().getName() + ", Battle: " + bats.get(i).getRoom().getRoom() + ", Damage Done " + bats.get(i).getDamage() , "Check " + i, Color.RED, Color.WHITE, 16));				
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		frameComponents.draw(g);
		alertComponents.draw(g);
	}

	@Override
	public String clickAction(int mouseX, int mouseY) {
		String getAction = frameComponents.mouseSelect(mouseX, mouseY);		
//		if (getAction == null) getAction = frameComponents.mouseSelect(mouseX, mouseY);
//		System.out.println(getAction);
//		String[] takeAction = getAction.split(" ");
//		if (takeAction[0].equals("Confirm")) {
//			if (takeAction[1].equals("Yes")) {
//				if (takeAction[2].equals("Leave")) {
//					HttpRequests.leaveGuild(currentUser.getUserUID(), currentGuild.getName());
//					state = "";
//					currentGuild = null;
//				} else if (takeAction[2].equals("Kick")) {
//					HttpRequests.leaveGuild(Integer.parseInt(takeAction[4]), currentGuild.getName());	
//				} else if (takeAction[2].equals("Promote")) {
//					HttpRequests.promoteUser(currentUser.getUserName(), takeAction[3], currentGuild.getName());
//				}
//			}
//			awaiting = false;	
//			alertComponents.remove("Leave Guild");
//			alertComponents.remove("Promote");
//		}
//
//		if (getAction.substring(0, 4).equals("Join")) {
//			String leaderName = HttpRequests.JoinGuild(currentUser, getAction.substring(5));
//			leaderName = leaderName.split("\r")[0];
//			HttpRequests.sendMessage(leaderName, "FATco", currentUser.getUserName() + " wants to join " + getAction.substring(5) , "Dear " + leaderName + "\n\n" + currentUser.getUserName() + " would like to join the guild " + getAction.substring(5));
//		} 
//		else if (getAction.substring(0, 4).equals("goto")) {
//			return getAction;
//		} 
//		else if (getAction.substring(0, 7).equals("Kicking")) {
//			if (!awaiting) {
//				String[] tempString = getAction.split(" ");
//				alertComponents.add(new AlertBox("Leave Guild", 200, 200, "Leave Guild?", "Kick "  + tempString[1] + " " + tempString[2], currentUser, false));
//				awaiting = true;
//			}
//		}
//		else if (getAction.substring(0, 5).equals("Check")) {
//			for (Guild x : myGuilds) {
//				if (x.getName().equals(getAction.substring(6))){
//					currentGuild = x;
//				}
//			}
//		}
//		else if (getAction.substring(0, 7).equals("Promote")) {
//			if (!awaiting) {
//				String[] tempString = getAction.split(" ");
//				alertComponents.add(new AlertBox("Promote", 200, 200, "Promote " + tempString[1] + "?", "Promote " + tempString[1] + " " + tempString[2], currentUser, false));
//				awaiting = true;
//			}
//		}
//		if (getAction.equals("Guild new")) {
//			state = "New Guild";
//		} 
//		else if (getAction.equals("Guild join")) {
//			state = "Join Guild";
//		} 
//		else if (getAction.equals("Leave Guild")) {
//			if (!awaiting) {
//				alertComponents.add(new AlertBox("Leave Guild", 200, 200, "Leave Guild?", "Leave Guild", currentUser, false));
//				awaiting = true;
//			}
//		} 
//		else if (getAction.equals("Guild create")) {
//			HttpRequests.createGuild(currentUser, ((TextField) frameComponents.get("Guild Search")).getString());
//			refreshFrame();
//			state = "";
//		}  
//		else if (getAction.equals("Change Leader")) {
//			state = "Change Leader";
//		}
//		else if (getAction.equals("Activate")) {
//			return getAction;
//		}
//		refreshFrame();
//		System.out.println(state);
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
