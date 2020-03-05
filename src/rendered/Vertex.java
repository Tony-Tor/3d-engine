package rendered;

import math.Vector2f;
import math.Vector3f;

public class Vertex {

	public Vector3f pos;
	public Vector3f norm;
	public Vector2f tex;
	
	
	public Vertex(Vector3f pos,Vector3f norm,Vector2f tex){
		this.pos=pos;
		this.norm=norm;
		this.tex=tex;
	}
	
	//TODO:
	/*
	int[] bone;
	int[] weight;
	
	*/
}
