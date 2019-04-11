package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Components.*;
import Main.HttpRequests;

public class ClientGuildUI  implements UIFrame{

	HttpRequests http = new HttpRequests();
	GUIList frameComponents = new GUIList();
	User currentUser;
	private String state = ""; 
	ArrayList<Guild> myGuilds, guildList;
	GUIList alertComponents = new GUIList();
	Guild currentGuild;
	boolean awaiting = false;

	ClientGuildUI(User currentUser){
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

		if (currentGuild != null) {
			ArrayList<User> users = HttpRequests.getGuildMates(currentGuild.getName());
			User leader;
			frameComponents.add(new Label("Guild Name", 15, 130, currentGuild.getName() + " #" + Integer.toString(currentGuild.getRank()), 16));
			frameComponents.add(new Label("Members", 15, 300, "Members", 16));
			frameComponents.add(new Button("Leave Guild", 200, 500, 80, 50, "Leave Guild", "Leave Guild", new Color(232, 176, 175), Color.GRAY, 16));
			for (int x = 0; x < users.size(); x++) {
				if (state.equals("")) {
					frameComponents.add(new Label(users.get(x).getUserName(), 15, 320 + (30*x), users.get(x).getUserName(), 16));
					if (users.get(x).isLeader()) {
						leader = users.get(x);
						if (users.get(x).getUserName().equals(currentUser.getUserName())) 
							frameComponents.add(new Button("Leader", 55, 500, 80, 50, "Modify Guild", "Change Leader", new Color(232, 176, 175), Color.GRAY, 16));
					}
				} else if (state.equals("Change Leader")) {
					frameComponents.add(new Button("Kick Member " + x, 200, 320 + (30*x), 80, 50, "Kick", "Kicking " + users.get(x).getUserName() + " "  + users.get(x).getUserUID(), new Color(232, 176, 175), Color.GRAY, 16));
					frameComponents.add(new Button(users.get(x).getUserName(), 15, 320 + (30*x), 150, 30, users.get(x).getUserName(), "Promote " + users.get(x).getUserName() + " " + users.get(x).getUserUID(), new Color(232, 176, 175), Color.GRAY, 16));
				}
			}
		} else {
			if (state.equals("")) {
				myGuilds = HttpRequests.getMyGuilds(currentUser);
				frameComponents.add(new Button("New Guild", 20, 130, 120, 50, "New Guild", "Guild new", Color.GRAY, Color.WHITE, 16));
				frameComponents.add(new Button("Join Guild", 20, 190, 120, 50, "Join Guild", "Guild join", Color.GRAY, Color.WHITE, 16));						
//				frameComponents.add(new Button("New Guild", 20, 130, 120, 50, "New Guild", "Guild new", new Color(232, 176, 175), Color.GRAY, 16));
//				frameComponents.add(new Button("Join Guild", 20, 190, 120, 50, "Join Guild", "Guild join", new Color(232, 176, 175), Color.GRAY, 16));		
				for (int i = 0; i < myGuilds.size(); i++) {
					frameComponents.add(new Button("Guild " + myGuilds.get(i).getName(), 250, 100 + (60*i), 200, 50, myGuilds.get(i).getName(), "Check " + myGuilds.get(i).getName(), Color.GRAY, Color.WHITE, 16));				
				}
			} else if (state.equals("New Guild")){
				frameComponents.add(new Label("Guild", 295, 75, "Guild Name: ", 16));
				frameComponents.add(new TextField("Guild Search", 295, 100, "Guild Search"));
				frameComponents.add(new Button("Create Guild", 495, 75, 200, 50, "Create Guild", "Guild create", new Color(232, 176, 175), Color.GRAY, 16));
			}  else if (state.equals("Join Guild")){
				guildList = HttpRequests.getGuildList();
				for (int i = 0; i < guildList.size(); i++) {
					frameComponents.add(new Button("Guild " + guildList.get(i).getName(), 250, 100 + (60*i), 200, 50, guildList.get(i).getName(), "Join " + guildList.get(i).getName(), Color.GRAY, Color.WHITE, 16));				
				}
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
		String getAction = alertComponents.mouseSelect(mouseX, mouseY);		
		if (getAction == null) getAction = frameComponents.mouseSelect(mouseX, mouseY);
		if (getAction != null) {
			String[] takeAction = getAction.split(" ");
			if (takeAction[0].equals("Confirm")) {
				if (takeAction[1].equals("Yes")) {
					if (takeAction[2].equals("Leave")) {
						HttpRequests.leaveGuild(currentUser.getUserUID(), currentGuild.getName());
						state = "";
						currentGuild = null;
					} else if (takeAction[2].equals("Kick")) {
						HttpRequests.leaveGuild(Integer.parseInt(takeAction[4]), currentGuild.getName());	
					} else if (takeAction[2].equals("Promote")) {
						HttpRequests.promoteUser(currentUser.getUserName(), takeAction[3], currentGuild.getName());
					}
				}
				awaiting = false;	
				alertComponents.remove("Leave Guild");
				alertComponents.remove("Promote");
			}

			if (getAction.substring(0, 4).equals("Join")) {
				String leaderName = HttpRequests.JoinGuild(currentUser, getAction.substring(5));
				leaderName = leaderName.split("\r")[0];
				HttpRequests.sendMessage(leaderName, "FATco", currentUser.getUserName() + " wants to join " + getAction.substring(5) , "Dear " + leaderName + "\n\n" + currentUser.getUserName() + " would like to join the guild " + getAction.substring(5));
			} 
			else if (getAction.substring(0, 4).equals("goto")) {
				return getAction;
			} 
			else if (getAction.substring(0, 7).equals("Kicking")) {
				if (!awaiting) {
					String[] tempString = getAction.split(" ");
					alertComponents.add(new AlertBox("Leave Guild", 200, 200, "Leave Guild?", "Kick "  + tempString[1] + " " + tempString[2], currentUser, false));
					awaiting = true;
				}
			}
			else if (getAction.substring(0, 5).equals("Check")) {
				for (Guild x : myGuilds) {
					if (x.getName().equals(getAction.substring(6))){
						currentGuild = x;
					}
				}
			}
			else if (getAction.substring(0, 7).equals("Promote")) {
				if (!awaiting) {
					String[] tempString = getAction.split(" ");
					alertComponents.add(new AlertBox("Promote", 200, 200, "Promote " + tempString[1] + "?", "Promote " + tempString[1] + " " + tempString[2], currentUser, false));
					awaiting = true;
				}
			}
			if (getAction.equals("Guild new")) {
				state = "New Guild";
			} 
			else if (getAction.equals("Guild join")) {
				state = "Join Guild";
			} 
			else if (getAction.equals("Leave Guild")) {
				if (!awaiting) {
					alertComponents.add(new AlertBox("Leave Guild", 200, 200, "Leave Guild?", "Leave Guild", currentUser, false));
					awaiting = true;
				}
			} 
			else if (getAction.equals("Guild create")) {
				HttpRequests.createGuild(currentUser, ((TextField) frameComponents.get("Guild Search")).getString());
				refreshFrame();
				state = "";
			}  
			else if (getAction.equals("Change Leader")) {
				state = "Change Leader";
			}
			else if (getAction.equals("Activate")) {
				return getAction;
			}
		}
		refreshFrame();
		return "Guild Frame";
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
