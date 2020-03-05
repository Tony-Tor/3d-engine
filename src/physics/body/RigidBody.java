package physics.body;

import math.*;
import physics.collision.Collision;
import physics.collision.Sphere;

public class RigidBody {

	public Vector3f pos;
	public Quaternion q;
	public Vector3f speed;
	public Quaternion rotation;
	public float invert_mass;
	public float elasticity;
	public boolean isStatic = true;
	Collision[] collision = new Collision[3];
	
	public RigidBody(Vector3f pos,/*Quaternion q,*/float invert_mass,float elasticity,float r){
		this.pos=pos;
		this.q=new Quaternion(new Vector3f(1,1,1),0);
		speed = new Vector3f(0,0,0);
		this.invert_mass=invert_mass;
		this.elasticity=elasticity;
		Sphere sphere = new Sphere();
		sphere.setPos(pos);
		sphere.setR(r);
		this.collision[0] = sphere;
	}
	
	public void setCollisionLevel(int level,Collision obj){
		collision[level] = obj;
	}
	
	public void setImpuls(Vector3f speed/*,Quaternion rotation*/){
		this.speed=speed;
		//this.rotation.mul(rotation);
		isStatic = false;
	}
	
	public void update(){
		pos.add(speed);
		if(speed.length()<0.01){
			speed = new Vector3f(0,0,0);
			isStatic = true;
		}
		collision[0].setPos(pos);
		//System.out.println(pos.x+" "+pos.y+" "+pos.z);
	}
	
	public Collision getCollision(int level){
		return collision[level];
	}
}
