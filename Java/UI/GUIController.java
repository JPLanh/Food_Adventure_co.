package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import Components.UIFrame;
import Components.User;
import Main.HttpRequests;

public class GUIController extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3149851920030223090L;
	UIFrame currentFrame;
	User currentUser;
	int UIwidth, UIheight;

	public GUIController(){
		addMouseListener(this);
		setBackground(new Color(232, 176, 175));
		currentFrame = new AdminHomeUI();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				currentFrame.keyPress(event);
				repaint();
			}      
		});

		new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tick();
				repaint();
			}
		}).start();
	}

	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		currentFrame.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		String getAction = currentFrame.clickAction(arg0.getX(), arg0.getY());
		if (getAction != null) {
			String[] takeAction = getAction.split(" ");
			if (takeAction[0].equals("goto"))
			{
				if (takeAction[1].equals("Register")) currentFrame = new SignupUI();
				else if (takeAction[1].equals("Login")) {
//					System.out.println(getAction);
					currentUser = HttpRequests.getUser(0, takeAction[3], takeAction[4]);
					currentFrame = new ClientHomeUI(currentUser);
				}
				else if (takeAction[1].equals("Home")) {
					currentUser = HttpRequests.getUser(0,  currentUser.getUserName(), currentUser.getPassword());
					currentFrame = new ClientHomeUI(currentUser);
				}
				else if (takeAction[1].equals("Create")) currentFrame = new CreateUI();
				else if (takeAction[1].equals("Logout")) currentFrame = new LoginUI();
				else if (takeAction[1].equals("Account")) currentFrame = new ClientViewAccountUI(currentUser);
				else if (takeAction[1].equals("View")) currentFrame = new ClientViewAccountUI(currentUser);
				else if (takeAction[1].equals("Edit")) currentFrame = new ClientEditAccountUI(currentUser);
				else if (takeAction[1].equals("Shop")) currentFrame = new ClientLoyaltyUI(currentUser);
				else if (takeAction[1].equals("Reward")) currentFrame = new ClientRefundUI(currentUser);
				else if (takeAction[1].equals("Guild")) currentFrame = new ClientGuildUI(currentUser);
				else if (takeAction[1].equals("Avatar")) currentFrame = new ClientAvatarUI(currentUser);
				else if (takeAction[1].equals("Message")) currentFrame = new ClientMessageUI(currentUser);
				else if (takeAction[1].equals("Item")) currentFrame = new ClientLoyaltyUI(currentUser);
				else if (takeAction[1].equals("Battle")) currentFrame = new ClientBattleUI(currentUser);
				else if (takeAction[1].equals("Terminate")) currentFrame = new LoginUI();
				else if (takeAction[1].equals("Sign")) {
					currentUser = HttpRequests.registerUser(Integer.parseInt(takeAction[3]), takeAction[4], takeAction[5]);
					currentUser = HttpRequests.getUser(Integer.parseInt(takeAction[3]), takeAction[4], takeAction[5]);
					currentFrame = new ClientHomeUI(currentUser);
				}
				else if (takeAction[1].equals("Search")) currentFrame = new ClientAvatarUI(currentUser, takeAction[3]);
			}
			else if (takeAction[0].equals("Activate"))
			{
				setFocusable(true);
				requestFocus();
			}
			else if (takeAction[0].equals("Admin"))
			{
				repaint();
			}
			else if (takeAction[0].equals("Create"))
			{
				if (takeAction.length == 2) {
					if (takeAction[1].equals("Avatar"))
						currentFrame = new ClientAvatarUI(currentUser);
				} else if (takeAction.length == 3) {
					if (takeAction[2].equals("User"))
						currentFrame = new StaffHomeUI();
				}
			}
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public void tick() {
		currentFrame.tick();
//		setFocusable(true);
//		requestFocus();
	}
}
