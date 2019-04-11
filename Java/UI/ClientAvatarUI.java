package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Components.*;
import Main.HttpRequests;

public class ClientAvatarUI  implements UIFrame{

	HttpRequests http = new HttpRequests();
	GUIList frameComponents = new GUIList();
	Avatar myAvatar;
	User currentUser;
	private String state = "", notFound = "";
	private boolean self = false;

	ClientAvatarUI(User getUser){
		currentUser = getUser;
		self = true;
		refreshFrame();
	}

	ClientAvatarUI(User getUser, String getAvatar){
		currentUser = getUser;
//		refreshFrame();
		self = false;
		myAvatar = HttpRequests.searchAvatar(getAvatar);
		if (myAvatar == null) {
			notFound = getAvatar;
			state = "Not Found";
		}
		refreshFrame();

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

		if (myAvatar != null) {
			if (self) {
				if (!myAvatar.isMain()) frameComponents.add(new Button("Default Avatar", 45, 300, 100, 50, "Make Default", "Default Avatar", Color.WHITE, Color.GRAY, 16 ));
				frameComponents.add(new Button("Remove Avatar", 45, 350, 100, 50, "Remove Avatar", "Remove Avatar", Color.WHITE, Color.GRAY, 16 ));
			}
			frameComponents.add(new Label("Avatar Display Name", 250, 100, myAvatar.getName(), 16));
			frameComponents.add(new Label("Avatar Display Level", 250, 100 + 30, "Level: " + myAvatar.getAttributes().get("Level").toString()));
			frameComponents.add(new Label("Avatar Display Health", 250, 100 + 60, "Health: " + myAvatar.getAttributes().get("Health").toString()));				
			int i = 0;
			for (String x : myAvatar.getAttributes().keySet()) {
				if (!x.equals("Level") && !x.equals("Health")) {
					frameComponents.add(new Label("Avatar Display " + x, 250, 240 + 30*i, x+ ": " + myAvatar.getAttributes().get(x).toString()));				
					i+=1;
				}
			}

			frameComponents.add(new Button("Return", 45, 400, 100, 50, "Back", "Return", Color.WHITE, Color.GRAY, 16 ));
		} else {
			if (state.equals("Not Found")) {
				frameComponents.add(new Label("Avatar Display Name", 250, 100, notFound + " not found", 16));
			} else if (state.equals("")) {
				ArrayList<Avatar> listOfAvatars = HttpRequests.getAllUserAvatar(currentUser);
				for (int i = 0; i < listOfAvatars.size(); i++) {
					if (i == 0) {
						frameComponents.add(new Shape("CIRCLE", Color.GRAY, 50, 350, 100, 50, true));
						frameComponents.add(new Label(listOfAvatars.get(i).getName(), 50, 300, listOfAvatars.get(i).getName(), 16));
						frameComponents.add(new Button("Select Avatar " + i, 50, 400, 80, 50, "Select Avatar", "Select Avatar " + i, Color.WHITE, Color.GRAY, 16));
						if (listOfAvatars.get(i).isMain()) 
							frameComponents.add(new Label("Avatar " + i + " default", 50, 250, "Default", 16));
					} else if (i == 1) {
						frameComponents.add(new Shape("CIRCLE", Color.GRAY, 180, 300, 100, 50, true));
						frameComponents.add(new Label(listOfAvatars.get(i).getName(), 180, 250, listOfAvatars.get(i).getName(), 16));
						frameComponents.add(new Button("Select Avatar " + i, 180, 350, 80, 50, "Select Avatar", "Select Avatar " + i, Color.WHITE, Color.GRAY, 16));
						if (listOfAvatars.get(i).isMain()) 
							frameComponents.add(new Label("Avatar " + i + " default", 180, 200, "Default", 16));
					} else if (i == 2) {
						frameComponents.add(new Shape("CIRCLE", Color.GRAY, 310, 350, 100, 50, true));
						frameComponents.add(new Label(listOfAvatars.get(i).getName(), 300, 300, listOfAvatars.get(i).getName(), 16));
						frameComponents.add(new Button("Select Avatar " + i, 310, 400, 80, 50, "Select Avatar", "Select Avatar " + i, Color.WHITE, Color.GRAY, 16));
						if (listOfAvatars.get(i).isMain()) 
							frameComponents.add(new Label("Avatar " + i + " default", 310, 250, "Default", 16));
					} else if (i == 3) {
						frameComponents.add(new Shape("CIRCLE", Color.GRAY, 440, 300, 100, 50, true));
						frameComponents.add(new Label(listOfAvatars.get(i).getName(), 440, 250, listOfAvatars.get(i).getName(), 16));
						frameComponents.add(new Button("Select Avatar " + i, 440, 350, 80, 50, "Select Avatar", "Select Avatar " + i, Color.WHITE, Color.GRAY, 16));
						if (listOfAvatars.get(i).isMain()) 
							frameComponents.add(new Label("Avatar " + i + " default", 440, 200, "Default", 16));
					} else if (i == 4) {
						frameComponents.add(new Shape("CIRCLE", Color.GRAY, 570, 350, 100, 50, true));
						frameComponents.add(new Label(listOfAvatars.get(i).getName(), 570, 300, listOfAvatars.get(i).getName(), 16));
						frameComponents.add(new Button("Select Avatar " + i, 570, 400, 80, 50, "Select Avatar", "Select Avatar " + i, Color.WHITE, Color.GRAY, 16));
						if (listOfAvatars.get(i).isMain()) 
							frameComponents.add(new Label("Avatar " + i + " default", 570, 250, "Default", 16));
					}
				}
				for (int i = listOfAvatars.size(); i< 5; i++) {
					if (i == 0) {
						frameComponents.add(new Shape("CIRCLE", Color.GRAY, 50, 350, 100, 50, true));
						frameComponents.add(new Button("New Avatar 0", 50, 300, 80, 50, "Create New Avatar", "Create New Avatar", Color.WHITE, Color.GRAY, 16));
					} else if (i == 1) {
						frameComponents.add(new Shape("CIRCLE", Color.GRAY, 180, 300, 100, 50, true));
						frameComponents.add(new Button("New Avatar 1", 180, 250, 80, 50, "Create New Avatar", "Create New Avatar", Color.WHITE, Color.GRAY, 16));
					} else if (i == 2) {
						frameComponents.add(new Shape("CIRCLE", Color.GRAY, 310, 350, 100, 50, true));
						frameComponents.add(new Button("New Avatar 2", 310, 300, 80, 50, "Create New Avatar", "Create New Avatar", Color.WHITE, Color.GRAY, 16));
					} else if (i == 3) {
						frameComponents.add(new Shape("CIRCLE", Color.GRAY, 440, 300, 100, 50, true));
						frameComponents.add(new Button("New Avatar 3", 440, 250, 80, 50, "Create New Avatar", "Create New Avatar", Color.WHITE, Color.GRAY, 16));
					} else if (i == 4) {
						frameComponents.add(new Shape("CIRCLE", Color.GRAY, 570, 350, 100, 50, true));
						frameComponents.add(new Button("New Avatar 4", 570, 300, 80, 50, "Create New Avatar", "Create New Avatar", Color.WHITE, Color.GRAY, 16));
					}
				}

			} else if (state.equals("Create New Avatar")) {
				myAvatar = new Avatar();
				frameComponents.add(new Shape("CIRCLE", Color.GRAY, 50, 350, 100, 50, true));				
				frameComponents.add(new Button("Hair Style Previous", 200, 150, 40, 40, "<", "Previous Hair Style", Color.WHITE, Color.GRAY, 16));
				frameComponents.add(new Button("Hair Style Next", 400, 150, 40, 40, ">", "Next Hair Style", Color.WHITE, Color.GRAY, 16));
				frameComponents.add(new Label("Hair Style", 300, 150, "Hair Style ", 16));

				frameComponents.add(new Button("Hair Color Previous", 200, 200, 40, 40, "<", "Previous Hair Color", Color.WHITE, Color.GRAY, 16));
				frameComponents.add(new Button("Hair Color Next", 400, 200, 40, 40, ">", "Next Hair Color", Color.WHITE, Color.GRAY, 16));
				frameComponents.add(new Label("Hair Color", 300, 200, "Hair Color ", 16));

				frameComponents.add(new Button("Eye Color Previous", 200, 250, 40, 40, "<", "Previous Eye Color", Color.WHITE, Color.GRAY, 16));
				frameComponents.add(new Button("Eye Color Next", 400, 250, 40, 40, ">", "Next Eye Color", Color.WHITE, Color.GRAY, 16));
				frameComponents.add(new Label("Eye Color", 300, 250, "Eye Color ", 16));

				frameComponents.add(new Button("Fur Color Previous", 200, 300, 40, 40, "<", "Previous Fur Color", Color.WHITE, Color.GRAY, 16));
				frameComponents.add(new Button("Fur Color Next", 400, 300, 40, 40, ">", "Next Fur Color", Color.WHITE, Color.GRAY, 16));
				frameComponents.add(new Label("Fur Color", 300, 300, "Fur Color ", 16));

				frameComponents.add(new Button("Animal Previous", 200, 350, 40, 40, "<", "Previous Animal", Color.WHITE, Color.GRAY, 16));
				frameComponents.add(new Button("Animal Next", 400, 350, 40, 40, ">", "Next Animal", Color.WHITE, Color.GRAY, 16));
				frameComponents.add(new Label("Animal", 300, 350, "Animal ", 16));

				frameComponents.add(new TextField("Avatar Name", 295, 100, "Avatar Name"));
				frameComponents.add(new Button("Create Avatar", 495, 75, 200, 50, "Create Avatar", "Create Avatar", new Color(232, 176, 175), Color.GRAY, 16));
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
		if (getAction != null) {
			String[] takeAction = getAction.split(" ");
			if (takeAction[0].equals("Create")) {
				if (getAction.equals("Create New Avatar")) {
					state = "Create New Avatar";
					refreshFrame();
					return null;
				} else if (getAction.equals("Create Avatar")) {
					myAvatar.setName(((TextField)frameComponents.get("Avatar Name")).getString());
					HttpRequests.createAvatar(myAvatar, currentUser);
				}
			} else if (takeAction[0].equals("Previous")) {
				if (getAction.substring(9).equals("Hair Color")) myAvatar.setHairColor((myAvatar.getHairColor()-1)%10);
				if (getAction.substring(9).equals("Hair Style")) myAvatar.setHairStyle((myAvatar.getHairStyle()-1)%10);
				if (getAction.substring(9).equals("Eye Color")) myAvatar.setEyeColor((myAvatar.getEyeColor()-1)%10);
				if (getAction.substring(9).equals("Fur Color")) myAvatar.setFurColor((myAvatar.getFurColor()-1)%10);
				if (getAction.substring(9).equals("Animal")) myAvatar.setAnimal((myAvatar.getAnimal()-1)%10);
			} else if (takeAction[0].equals("Next")) {
				if (getAction.substring(5).equals("Hair Color")) myAvatar.setHairColor((myAvatar.getHairColor()+1)%10);
				if (getAction.substring(5).equals("Hair Style")) myAvatar.setHairStyle((myAvatar.getHairStyle()+1)%10);
				if (getAction.substring(5).equals("Eye Color")) myAvatar.setEyeColor((myAvatar.getEyeColor()+1)%10);
				if (getAction.substring(5).equals("Fur Color")) myAvatar.setFurColor((myAvatar.getFurColor()+1)%10);
				if (getAction.substring(5).equals("Animal")) myAvatar.setAnimal((myAvatar.getAnimal()+1)%10);
			} else if (takeAction[0].equals("Select")) {
				ArrayList<Avatar> listOfAvatars = HttpRequests.getAllUserAvatar(currentUser);
				myAvatar = listOfAvatars.get(Integer.parseInt(takeAction[2]));
				refreshFrame();
			}
			else if (takeAction[0].equals("Return")) {
				myAvatar = null;
				refreshFrame();
			}
			else if (takeAction[0].equals("Default")) {
				HttpRequests.updateAvatar(currentUser.getUserUID(), myAvatar.getName());
				refreshFrame();
			}
			else if (takeAction[0].equals("Remove")) {
				HttpRequests.deleteAvatar(myAvatar.getName());
				myAvatar = null;
				refreshFrame();
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
