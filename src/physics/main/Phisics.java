package physics.main;

import java.util.ArrayList;

import math.Vector3f;
import physics.body.RigidBody;

public class Phisics implements Runnable{

	private ArrayList<RigidBody> rigidsBody = new ArrayList<>();
	private BroadPhaseInterface broadphase;
	private NarrowPhaseInterface narrowphase;
	
	private boolean phisics_start = true;
	
	public Phisics(BroadPhaseInterface broadphase,NarrowPhaseInterface narrowphase){
		this.broadphase=broadphase;
		this.narrowphase=narrowphase;
	}
	
	public void addRigidBody(RigidBody rigidbody){
		rigidsBody.add(rigidbody);
	}

	public void setImpulseRigidBody(int i,Vector3f v){
		rigidsBody.get(i).setImpuls(v);
	}
	
	public void phisicsStop(){
		phisics_start = false;
	}
	
	private void phisicsWait(){
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			System.err.println("Error thread");
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(phisics_start){
			for(RigidBody r:rigidsBody){
				if(!r.isStatic){//TODO:boolean to int
					r.update();
				}
			}
		
			broadphase.setRigidBody(rigidsBody);
			narrowphase.setPairs(broadphase.getBroadPhasePair());
			narrowphase.setImpulses();
		
			phisicsWait();
		}
	}
	
}
