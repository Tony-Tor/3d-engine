package rendered;

import math.Quaternion;
import math.Vector3f;

public abstract class Position {

	Vector3f position;
	Quaternion quaternion;
	
	public Position(Vector3f position, Quaternion quaternion){
		this.position = position;
		this.quaternion = quaternion;
	}
	
	public void move(Vector3f vector){
		this.position.add(vector);
	}
	
	public void moveTo(Vector3f position){
		this.position = position;
	}
	
	public void rotate(Quaternion quaternion){
		this.quaternion = quaternion;
	}
	
	public void rotateAround(){
		//TODO:
	}
	
	public void lookAt(Vector3f position){
		this.quaternion.lookAt(this.position, position);
	}
	
	public abstract void update();
}
