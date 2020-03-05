package physics.main;

import java.util.ArrayList;

import physics.body.RigidBody;

public class SimpleBroadPhase implements BroadPhaseInterface {

	ArrayList<RigidBody> rigidsBody;
	
	@Override
	public ArrayList<Pair> getBroadPhasePair() {
		ArrayList<Pair> pairs = new ArrayList<>();
		
		for(int i=0;i<rigidsBody.size();i++){
			RigidBody obj1 = rigidsBody.get(i);
			for(int j=i+1;j<rigidsBody.size();j++){
				RigidBody obj2 = rigidsBody.get(j);
				if(obj1.getCollision(0).isCollision(obj2.getCollision(0))){
					pairs.add(new Pair(obj1,obj2));
					System.out.println("collision");
				}
			}
		}
		
		return pairs;
	}

	@Override
	public void setRigidBody(ArrayList<RigidBody> rigidsBody) {
		this.rigidsBody=rigidsBody;
		
	}

}
