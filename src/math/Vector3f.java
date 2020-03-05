package math;

public class Vector3f {

	/*TODO:Comments, proxy, interface*/
	public final static Vector3f ZERO = new Vector3f(0,0,0);
	public final static Vector3f UNIT_X = new Vector3f(1,0,0);
	public final static Vector3f UNIT_Y = new Vector3f(0,1,0);
	public final static Vector3f UNIT_Z = new Vector3f(0,0,1);
	
	public float x;
	public float y;
	public float z;
	
	public Vector3f(){
		set(ZERO);
	}
	
	public Vector3f(Vector3f vector){
		set(vector);
	}
	
	public Vector3f(float x,float y,float z){
		set(x, y, z);
	}
	
	public Vector3f add(Vector3f vector){
		x+=vector.x;
		y+=vector.y;
		z+=vector.z;
		return this;
	}
	
	public Vector3f add(Vector3f vector1, Vector3f vector2){
		return add(vector1, vector2, this);
	}
	
	public static Vector3f add(Vector3f vector1, Vector3f vector2, Vector3f dust){
		dust.x=vector1.x+vector2.x;
		dust.y=vector1.y+vector2.y;
		dust.z=vector1.z+vector2.z;
		return dust;
	}
	
	public Vector3f add(float a){
		x+=a;
		y+=a;
		z+=a;
		return this;
	}
	
	public Vector3f add(Vector3f vector, float a){
		return add(vector, a, this);
	}
	
	public static Vector3f add(Vector3f vector, float a, Vector3f dust){
		dust.x=vector.x+a;
		dust.y=vector.y+a;
		dust.z=vector.z+a;
		return dust;
	}
	
	public Vector3f invert(){
		x=-x;
		y=-y;
		z=-z;
		return this;
	}
	
	public Vector3f invert(Vector3f vector){
		return invert(vector, this);
	}
	
	public static Vector3f invert(Vector3f vector, Vector3f dust){
		dust.x=-vector.x;
		dust.y=-vector.y;
		dust.z=-vector.z;
		return dust;
	}
	
	public float length(){
		return (float) Math.sqrt(x*x+y*y+z*z);
	}
	
	public Vector3f normal(){
		float l = length();
		if(l==0){
			System.err.println("Error: Vector = 0,0,0");
			x=y=z=1;
			l=length();
		}
		x/=l;
		y/=l;
		z/=l;
		return this;
	}
	
	public Vector3f normal(Vector3f vector){
		return normal(vector, this);
	}
	
	public static Vector3f normal(Vector3f vector, Vector3f dust){
		float l = vector.length();
		if(l==0){
			System.err.println("Error: Vector = 0,0,0");
			dust.x=dust.y=dust.z=1;
			l=dust.length();
			dust.x/=l;
			dust.y/=l;
			dust.z/=l;
			return dust;
		}
		dust.x=vector.x/l;
		dust.y=vector.y/l;
		dust.z=vector.z/l;
		return dust;
	}
	
	public float dot(Vector3f vector){
		return x*vector.x+y*vector.y+z*vector.z;
	}
	
	public Vector3f dot(float a){
		x*=a;
		y*=a;
		z*=a;
		return this;
	}
	
	public Vector3f dot(Vector3f vector, float a){
		return dot(vector, a, this);
	}
	
	public static Vector3f dot(Vector3f vector, float a, Vector3f dust){
		dust.x=vector.x*a;
		dust.y=vector.y*a;
		dust.z=vector.z*a;
		return dust;
	}
	
	public Vector3f cross(Vector3f vector){
		float x=this.x;
		float y=this.y;
		float z=this.z;
		
		this.x=y*vector.z-z*vector.y;
		this.y=z*vector.x-x*vector.z;
		this.z=x*vector.y-y*vector.x;
		return this;
	}
	
	public Vector3f cross(Vector3f vector1, Vector3f vector2){
		return cross(vector1, vector2, this);
	}
	
	public static Vector3f cross(Vector3f vector1, Vector3f vector2, Vector3f dust){
		dust.x=vector1.y*vector2.z-vector1.z*vector2.y;
		dust.y=vector1.z*vector2.x-vector1.x*vector2.z;
		dust.z=vector1.x*vector2.y-vector1.y*vector2.x;
		return dust;
	}
	
	public Vector3f setLength(float l){
		normal();
		dot(l);
		return this;
	}
	
	public Vector3f setLength(Vector3f vector, float l){
		return setLength(vector, l, this);
	}
	
	public static Vector3f setLength(Vector3f vector, float l, Vector3f dust){
		dust.normal(vector);
		dust.dot(l);
		return dust;
	}
	
	public void set(float x,float y,float z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public void set(Vector3f vector){
		this.x=vector.x;
		this.y=vector.y;
		this.z=vector.z;
	}
	
	public float[] get(){
		float[] a = {x,y,z};
		return a;
	}
}
