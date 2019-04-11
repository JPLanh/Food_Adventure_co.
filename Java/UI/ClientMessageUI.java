package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Components.*;
import Main.HttpRequests;


public class ClientMessageUI  implements UIFrame{

	HttpRequests http = new HttpRequests();
	GUIList frameComponents = new GUIList();
	GUIList msgComponents = new GUIList();
	GUIList msgFrameComponents = new GUIList();
	User currentUser, userCheck;
	Message currentMsg;
	Boolean compose = false;

	ClientMessageUI(User getUser){
		currentUser = getUser;
		frameComponents.add(new Shape("SQUARE", Color.WHITE, 0, 50, 800, 550, true));
		frameComponents.add(new Shape("SQUARE", Color.GRAY, 49, 130, 682, 420, false));

		frameComponents.add(new Button("Home", 55, 0, 80, 50, "Home", "goto Home User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Shop", 135, 0, 80, 50, "Shop", "goto Shop User", new Color(232, 176, 175), Color.GRAY, 16));
		//		frameComponents.add(new Button("Gacha", 215, 0, 80, 50, "Gacha", "goto Gacha User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Guild", 215, 0, 80, 50, "Guild", "goto Guild User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Avatar", 295, 0, 80, 50, "Avatar", "goto Avatar User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("My Account", 580, 0, 80, 50, "My Account", "goto Account User", new Color(232, 176, 175), Color.GRAY, 16));
		frameComponents.add(new Button("Logout", 680, 0, 80, 50, "Logout", "goto Logout User", new Color(232, 176, 175), Color.GRAY, 16));
		//		frameComponents.add(new mbbEta(49, 130, "Test"));
		//		public MessageBox(String getName, int getInitX, int getInitY, String getString, String getAction, User getUser) {


	}

	@Override
	public void draw(Graphics g) {
		frameComponents.draw(g);
		msgComponents.draw(g);
		msgFrameComponents.draw(g);
	}

	@Override
	public String clickAction(int mouseX, int mouseY) {
		String getAction = null;
		getAction = msgFrameComponents.mouseSelect(mouseX, mouseY);
		if (getAction == null) getAction = msgComponents.mouseSelect(mouseX, mouseY);
		if (getAction == null) getAction = frameComponents.mouseSelect(mouseX, mouseY);
		if (getAction != null) {
			//			System.out.println(getAction);
			String[] takeAction = getAction.split(" ");
			if (getAction.equals("Back Messages")) {
				currentMsg = null;
			}
			if (getAction.equals("New Messages")) {
				compose = true;
				msgFrameComponents = new GUIList();
				msgFrameComponents.add(new Button("Back Message", 50, 75, 150, 50, "Cancel", "Cancel Messages", new Color(232, 176, 175), Color.GRAY, 16));
				msgFrameComponents.add(new Button("Reply Message", 210, 75, 150, 50, "Send", "Send Messages", new Color(232, 176, 175), Color.GRAY, 16));
				msgFrameComponents.add(new TextField("Recepient", 55, 140, "Recepient", 200, 30, false));
				msgFrameComponents.add(new TextField("Subject", 55, 180, "Subject", 650, 30, false));
				msgFrameComponents.add(new TextField("Body", 55, 220, "Body", 650, 260, true));
			}
			if (getAction.equals("Reply Messages")) {
				compose = true;
				msgComponents = new GUIList();
				msgFrameComponents = new GUIList();
				msgFrameComponents.add(new Button("Back Message", 50, 75, 150, 50, "Cancel", "Cancel Messages", new Color(232, 176, 175), Color.GRAY, 16));
				msgFrameComponents.add(new Button("Reply Message", 210, 75, 150, 50, "Send", "Reply send", new Color(232, 176, 175), Color.GRAY, 16));
				msgFrameComponents.add(new Label("Recepient", 55, 140, currentMsg.getFrom()));
				msgFrameComponents.add(new TextField("Subject", 55, 180, "Re: " + currentMsg.getSubject(), 650, 30, false));
				msgFrameComponents.add(new TextField("Body", 55, 220, "Body", 650, 260, true));
			}
			if (getAction.equals("Delete Messages")) {
				HttpRequests.removeMessage(currentMsg.getId());
				currentMsg = null;
				compose = false;
			}
			if (getAction.equals("Cancel Messages")) {
				msgFrameComponents = new GUIList();
				compose = false;
			}
			if (takeAction[0].equals("Accept") && takeAction[1].equals("Offer")) {
				HttpRequests.admitGuild(takeAction[2], getAction.split(takeAction[2])[1], "Admit");			
				currentMsg = null;
				compose = false;
			}
			if (takeAction[0].equals("Decline") && takeAction[1].equals("Offer")) {
				HttpRequests.admitGuild(takeAction[2], getAction.split(takeAction[2])[1], "Deny");
				currentMsg = null;
				compose = false;
			}
			if (getAction.equals("Send Messages")) { 
				HttpRequests.sendMessage(
					((TextField)msgFrameComponents.get("Recepient")).getString(), 
					currentUser.getUserName(),
					((TextField)msgFrameComponents.get("Subject")).getString(), 
					((TextField)msgFrameComponents.get("Body")).getString());				
				msgComponents = new GUIList();
				msgFrameComponents = new GUIList();
			currentMsg = null;
			compose = false;
			}
			if (getAction.equals("Reply send")) {
				HttpRequests.sendMessage(
						currentMsg.getFrom(), 
						currentUser.getUserName(),
						((TextField)msgFrameComponents.get("Subject")).getString(), 
						((TextField)msgFrameComponents.get("Body")).getString());		
				msgFrameComponents = new GUIList();		
				currentMsg = null;
				compose = false;
			}
			if (getAction.equals("Test Send")) HttpRequests.sendMessage("JPLanh", "FATco", "new User to guild", "There's a new user who wants to join the guild");
			if (takeAction[0].equals("read"))
			{
				currentMsg = HttpRequests.getMessages(currentUser.getUserName()).get(Integer.parseInt(takeAction[2]));
			}
			return getAction;
		}
		return null;
	}

	@Override
	public void keyPress(KeyEvent c) {
		frameComponents.keyPress(c);
		msgFrameComponents.keyPress(c);
	}

	@Override
	public void tick() {
		msgComponents = new GUIList();
		if (currentMsg == null) {
			if (!compose) {
				msgComponents.add(new Button("New Message", 50, 75, 150, 50, "New Messages", "New Messages", new Color(232, 176, 175), Color.GRAY, 16));
//				msgComponents.add(new Button("Read Message", 210, 75, 150, 50, "Read Messages", "Test Send", new Color(232, 176, 175), Color.GRAY, 16));
				ArrayList<Message> getMsgs = HttpRequests.getMessages(currentUser.getUserName());
				//		ArrayList<Message> getMsgs = HttpRequests.getMessages(currentUser.getUserName());
				for (int i = 0; i < getMsgs.size(); i++) {
					msgComponents.add(new MessageBox("Msg " + i, 49, 130+75*i , getMsgs.get(i), "msg " + i, null));
				}
			} else {

			}
		} else {
			if (!compose) {
				msgComponents.add(new Button("Back Message", 50, 75, 150, 50, "Back", "Back Messages", new Color(232, 176, 175), Color.GRAY, 16));
				msgComponents.add(new Button("Reply Message", 210, 75, 150, 50, "Reply", "Reply Messages", new Color(232, 176, 175), Color.GRAY, 16));
				msgComponents.add(new Button("Delete Message", 370, 75, 150, 50, "Delete", "Delete Messages", new Color(232, 176, 175), Color.GRAY, 16));
				msgComponents.add(new Label("Sender", 55, 140, currentMsg.getFrom()));
				msgComponents.add(new Label("Subject", 55, 180, currentMsg.getSubject()));
				String msgChk = currentMsg.getBodyMsg();
				int msgCntr = 0;
				while (msgChk.length() > 120) {
					msgComponents.add(new Label("Subject", 55, 220+18*msgCntr, msgChk.substring(0, 120)));
					msgChk = msgChk.substring(120);
					msgCntr++;
				}
				msgComponents.add(new Label("Subject", 55, 220+18*msgCntr, msgChk));
				if (currentMsg.getFrom().equals("FATco")) {
					msgComponents.add(new Button("Accept Offer", 50, 450, 150, 50, "Accept", "Accept Offer " + currentMsg.getSubject().split(" ")[0] + " " + currentMsg.getSubject().split("join")[1], new Color(232, 176, 175), Color.GRAY, 16));
					msgComponents.add(new Button("Decline Offer", 250, 450, 150, 50, "Decline", "Decline Offer " + currentMsg.getSubject().split(" ")[0] + " " + currentMsg.getSubject().split("join")[1], new Color(232, 176, 175), Color.GRAY, 16));
				}
			}
		}
		frameComponents.tick();
	}
}
