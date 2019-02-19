import javax.swing.JFrame;

import UI.GUIController;

public class mainFrame extends JFrame{

	GUIController myUI = null;

	public mainFrame(int frameWidth, int frameHeight)
	{
		setSize(frameWidth, frameHeight);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(myUI = new GUIController());
		revalidate();
	}
}
