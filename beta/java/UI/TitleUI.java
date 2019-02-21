package UI;

import java.awt.Color;
import java.awt.Graphics;


import Components.Button;
import Components.GUIList;
import Components.Label;
import Components.TextField;
import Components.UIFrame;

public class TitleUI implements UIFrame{

	GUIList frameComponents = new GUIList();

	TitleUI(){
		frameComponents.add(new Button("New User", 250, 180, 110, 40, "New User", "goto New User"));
		frameComponents.add(new TextField("New Name", 50, 230, "My Name"));
		frameComponents.add(new Label("Testing Label", 100, 180, "Hello: "));
	}

	@Override
	public void draw(Graphics g) {
		frameComponents.draw(g);
	}
}
