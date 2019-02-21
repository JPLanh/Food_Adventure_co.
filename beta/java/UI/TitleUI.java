package UI;

import java.awt.Graphics;


import Components.Button;
import Components.GUIList;
import Components.Label;
import Components.UIFrame;

public class TitleUI implements UIFrame{

	GUIList frameComponents = new GUIList();

	TitleUI(){
		frameComponents.add(new Button("Create Session", 250, 180, 110, 40, "Create Session", "goto Create Session"));
		frameComponents.add(new Label("Testing Label", 100, 180, "Hello: "));
	}

	@Override
	public void draw(Graphics g) {
		frameComponents.draw(g);
	}
}
