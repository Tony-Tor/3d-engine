package physics.main;

import java.util.ArrayList;

import physics.body.RigidBody;

public interface BroadPhaseInterface {

	public ArrayList<Pair> getBroadPhasePair();
	
	public void setRigidBody(ArrayList<RigidBody> rigidsBody);
}
