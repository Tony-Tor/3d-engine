package physics.collision.next;

import math.Vector3f;
import physics.collision.Collision;

public class AABB implements Collision {

	Vector3f pos1;
	Vector3f pos2;
	
	public Vector3f getPos1() {
		return pos1;
	}

	public void setPos1(Vector3f pos1) {
		this.pos1 = pos1;
	}

	public Vector3f getPos2() {
		return pos2;
	}

	public void setPos2(Vector3f pos2) {
		this.pos2 = pos2;
	}

	@Override
	public boolean isCollision(Collision obj) {
		if(obj instanceof AABB){
			return isCollisionAABB((AABB)obj);
		}else{
			//TODO:
		}
		return false;
	}

	public boolean isCollisionAABB(AABB obj) {
		if(get(obj.pos1,obj.pos2)){		
			return true;
		}
		return false;
	}
	
	private boolean get(Vector3f pos1,Vector3f pos2){
		return ((this.pos1.x<pos1.x&&
				this.pos1.y<pos1.y&&
				this.pos1.z<pos1.z)&&
				(this.pos2.x>pos1.x&&
				this.pos2.y>pos1.y&&
				this.pos2.z>pos1.z))||
				((this.pos1.x<pos2.x&&
				this.pos1.y<pos2.y&&
				this.pos1.z<pos2.z)&&
				(this.pos2.x>pos2.x&&
				this.pos2.y>pos2.y&&
				this.pos2.z>pos2.z));
	}
	
	public void normal(){
		if(pos1.x>pos2.x&&pos1.y>pos2.y&&pos1.z>pos2.z){
			Vector3f v = pos1;
			pos1=pos2;
			pos2=v;
		}
	}

	@Override
	public Vector3f getCollision() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPos(Vector3f pos) {
		// TODO Auto-generated method stub
		
	}

}
