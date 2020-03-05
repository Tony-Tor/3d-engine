package math;

public class Vector2f {

	public float x;
	public float y;
	
	public Vector2f(){
		x=y=0;
	}
	
	public Vector2f(float x,float y){
		this.x=x;
		this.y=y;
	}
	
	public Vector2f(Vector2f v){
		x=v.x;
		y=v.y;
	}

	public void set(Vector2f v) {
		x=v.x;
		y=v.y;
	}
}
