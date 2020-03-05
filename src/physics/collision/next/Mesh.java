package physics.collision.next;

import java.util.ArrayList;

import math.Vector3f;
import physics.collision.Collision;
import rendered.Vertex;

public class Mesh implements Collision {

	public ArrayList<Vertex> vert = new ArrayList<>();
	public ArrayList<Integer> i = new ArrayList<>();
	public ArrayList<Face> face = new ArrayList<>();
	
	@Override
	public boolean isCollision(Collision obj) {
		// TODO Auto-generated method stub
		return false;
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
