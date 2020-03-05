package physics.main;

import java.util.ArrayList;

import math.Vector3f;

public class SimpleNarrowPhase implements NarrowPhaseInterface {

	ArrayList<Pair> pairs;
	
	@Override
	public void setImpulses() {
		for(Pair pair:pairs){
			Vector3f normal = Vector3f.add(pair.rigidbody1.pos, new Vector3f().invert(pair.rigidbody2.pos), new Vector3f()).normal();
			Vector3f speed1 = pair.rigidbody1.speed;
			Vector3f speed2 = pair.rigidbody2.speed;
			float l;
			if(speed1.length()!=0){
				l=speed1.length()*new Vector3f().normal(speed1).dot(normal);
			}else{l=0;}
			Vector3f v1 = Vector3f.setLength(normal, l, new Vector3f());
			Vector3f v2 = Vector3f.add(speed1, new Vector3f().invert(v1), new Vector3f());
			normal.invert();
			if(speed2.length()!=0){
				l=speed2.length()*new Vector3f().normal(speed2).dot(normal);
			}else{l=0;}
			Vector3f v3 = Vector3f.setLength(normal, l, new Vector3f());
			v2.add(v3);
			v1.add(Vector3f.add(speed2, new Vector3f().invert(v3), new Vector3f()));
			pair.rigidbody1.setImpuls(v2);
			pair.rigidbody2.setImpuls(v1);
		}

	}

	@Override
	public void setPairs(ArrayList<Pair> pairs) {
		this.pairs=pairs;
		
	}

}
