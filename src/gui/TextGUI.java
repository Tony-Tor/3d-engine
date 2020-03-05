package gui;

import math.Vector3f;

public class TextGUI extends FrameGUI{

	String text;
	
	public TextGUI(Vector3f position, Vector3f size, String text){
		super(position, size);
		this.text = text;
	}

	@Override
	public void render() {
		
	}
}
