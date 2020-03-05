package physics.collision;

import math.Vector3f;

public interface Collision {

	public boolean isCollision(Collision obj);
	
	public Vector3f getCollision();

	public void setPos(Vector3f pos);
}
