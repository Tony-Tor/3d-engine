package gui;

import math.Vector3f;

public abstract class FrameGUI {

	Vector3f position;
	Vector3f size;
	
	public FrameGUI(Vector3f position, Vector3f size){
		this.position = position;
		this.size = size;
	}
	
	public void moveTo(Vector3f position){
		this.position = position;
	}
	
	public void size(Vector3f size){
		this.size = size;
	}
	
	public abstract void render();
}
