package UI;

import java.awt.Graphics;

import Components.GUIList;
import Components.UIFrame;

public class TitleUI implements UIFrame{

	GUIList frameComponents = new GUIList();

	TitleUI(){
		//Add components here
	}

	@Override
	public void draw(Graphics g) {
		frameComponents.draw(g);
	}
}
