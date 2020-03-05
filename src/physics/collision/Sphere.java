package physics.collision;

import math.Vector3f;

public class Sphere implements Collision {

	float r;
	Vector3f pos;
	
	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	@Override
	public boolean isCollision(Collision obj) {
		
		if(obj instanceof Sphere){
			return isCollisionSphere((Sphere)obj);
		}else{
			//TODO:
		}
		
		return false;
	}
	
	public boolean isCollisionSphere(Sphere obj){
		float objr = obj.getR();
		Vector3f objpos = new Vector3f(obj.getPos());
		Vector3f pos = new Vector3f(this.pos);
		
		float l = objpos.add(pos.invert()).length();
		
		if(l<objr+r){				
			return true;
		}
		
		return false;
	}

	@Override
	public Vector3f getCollision() {
		return null;
	}

}
