package physics.main;

import physics.body.RigidBody;

public class Pair {

	RigidBody rigidbody1;
	RigidBody rigidbody2;
	
	public Pair(RigidBody rigidbody1, RigidBody rigidbody2) {
		this.rigidbody1 = rigidbody1;
		this.rigidbody2 = rigidbody2;
	}
	
}
