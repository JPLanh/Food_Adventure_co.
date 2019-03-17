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
	Guild currentGuild;

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
		frameComponents.add(new Button("Gacha", 215, 0, 80, 50, "Gacha", "goto Gacha User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Tournament", 295, 0, 80, 50, "Tournament", "goto Tournament User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("My Account", 580, 0, 80, 50, "My Account", "goto Account User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Logout", 680, 0, 80, 50, "Logout", "goto Logout User", new Color(232, 176, 175), Color.GRAY, 16));

		frameComponents.add(new Shape("SQUARE", Color.GRAY, 0, 100, 180, 400, true));
		frameComponents.add(new Shape("SQUARE", Color.BLACK, 10, 110, 160, 175, true));
		frameComponents.add(new Shape("SQUARE", Color.GRAY, 11, 111, 158, 173, true));

		frameComponents.add(new Shape("SQUARE", Color.BLACK, 10, 305, 160, 175, true));
		frameComponents.add(new Shape("SQUARE", Color.GRAY, 11, 306, 158, 173, true));

		if (currentGuild != null) {
			ArrayList<User> users = HttpRequests.getGuildMates(currentGuild.getName());
			frameComponents.add(new Label("Guild Name", 15, 130, currentGuild.getName() + " #" + Integer.toString(currentGuild.getRank()), 16));
			frameComponents.add(new Label("Members", 15, 300, "Members", 16));
			for (int x = 0; x < users.size(); x++) {
				if (state.equals("")) {
					if (users.get(x).getUserName().equals(currentUser.getUserName()) && users.get(x).isLeader()) 
						frameComponents.add(new Button("Leader", 55, 500, 80, 50, "Change Leader", "Change Leader", new Color(232, 176, 175), Color.GRAY, 16));
					frameComponents.add(new Label(users.get(x).getUserName(), 15, 320 + (25*x), users.get(x).getUserName(), 16));
				} else if (state.equals("Change Leader")) {
					frameComponents.add(new Button(users.get(x).getUserName(), 15, 320 + (25*x), 150, 30, users.get(x).getUserName(), "Promote " + users.get(x).getUserName(), new Color(232, 176, 175), Color.GRAY, 16));
				}
			}
		} else {
			if (state.equals("")) {
				myGuilds = HttpRequests.getMyGuilds(currentUser);
				frameComponents.add(new Button("New Guild", 295, 100, 120, 50, "New Guild", "Guild new", new Color(232, 176, 175), Color.GRAY, 16));
				frameComponents.add(new Button("Join Guild", 295, 160, 120, 50, "Join Guild", "Guild join", new Color(232, 176, 175), Color.GRAY, 16));		
				for (int i = 0; i < myGuilds.size(); i++) {
					frameComponents.add(new Button("Guild " + myGuilds.get(i).getName(), 250, 250 + (60*i), 200, 50, myGuilds.get(i).getName(), "Check " + myGuilds.get(i).getName(), new Color(232, 176, 175), Color.GRAY, 16));				
				}
			} else if (state.equals("New Guild")){
				frameComponents.add(new Label("Guild", 295, 75, "Guild Name: ", 16));
				frameComponents.add(new TextField("Guild Search", 295, 100, "Guild Search"));
				frameComponents.add(new Button("Create Guild", 495, 75, 200, 50, "Create Guild", "Guild create", new Color(232, 176, 175), Color.GRAY, 16));
			}  else if (state.equals("Join Guild")){
				//			frameComponents.add(new Label("Guild", 295, 75, "Guild Name: ", 16));
				//			frameComponents.add(new TextField("Guild Search", 295, 100, "Guild Search"));
				//			frameComponents.add(new Button("Search Guild", 495, 75, 200, 50, "Search Guild", "Guild search", new Color(232, 176, 175), Color.GRAY, 16));
				guildList = HttpRequests.getGuildList();
				for (int i = 0; i < guildList.size(); i++) {
					frameComponents.add(new Button("Guild " + guildList.get(i).getName(), 250, 150 + (60*i), 200, 50, guildList.get(i).getName(), "Join " + guildList.get(i).getName(), new Color(232, 176, 175), Color.GRAY, 16));				
				}
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		frameComponents.draw(g);
	}

	@Override
	public String clickAction(int mouseX, int mouseY) {
		String getAction = frameComponents.mouseSelect(mouseX, mouseY);
		if (getAction.substring(0, 4).equals("Join")) {
			HttpRequests.JoinGuild(currentUser, getAction.substring(5));
		}
		else if (getAction.substring(0, 5).equals("Check")) {
			for (Guild x : myGuilds) {
				if (x.getName().equals(getAction.substring(6))){
					currentGuild = x;
				}
			}
		}
		else if (getAction.substring(0, 7).equals("Promote")) {
			HttpRequests.promoteUser(currentUser.getUserName(), getAction.substring(8), currentGuild.getName());
			
		}
		if (getAction.equals("Guild new")) {
			state = "New Guild";
		} 
		else if (getAction.equals("Guild join")) {
			state = "Join Guild";
		} 
		else if (getAction.equals("Guild create")) {
			HttpRequests.createGuild(currentUser, ((TextField) frameComponents.get("Guild Search")).getString());
		}  
		else if (getAction.equals("Change Leader")) {
			state = "Change Leader";
		}
		else if (getAction.equals("Activate")) {
			return getAction;
		}
		refreshFrame();
		System.out.println(state);
		return "Guild Frame";
	}

	@Override
	public void keyPress(KeyEvent c) {
		frameComponents.keyPress(c);
	}

}
