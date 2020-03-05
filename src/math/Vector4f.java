package math;

public class Vector4f {

	public float x;
	public float y;
	public float z;
	public float w;
	
	public Vector4f(){
		x=y=z=0.0f;
		w=1.0f;
	}
	
	public Vector4f(Vector3f vector,float w){
		set(vector,w);
	}
	
	public Vector4f(Vector4f vector){
		set(vector);
	}
	
	public Vector4f(float x,float y,float z,float w){
		set(x, y, z);
	}
	
	public Vector4f add(Vector3f vector){
		x+=vector.x;
		y+=vector.y;
		z+=vector.z;
		return this;
	}
	
	public Vector4f add(float a){
		x+=a;
		y+=a;
		z+=a;
		return this;
	}
	
	public Vector4f invert(){
		x=-x;
		y=-y;
		z=-z;
		return this;
	}
	
	public float length(){
		return (float) Math.sqrt(x*x+y*y+z*z);
	}
	
	public Vector4f identity(){
		x/=length();
		y/=length();
		z/=length();
		return this;
	}
	
	public float mulscalar(Vector3f vector){
		return x*vector.x+y*vector.y+z*vector.z;
	}
	
	public Vector4f mul(Vector3f vector){
		float x=this.x;
		float y=this.y;
		float z=this.z;
		
		this.x=y*vector.z-z*vector.y;
		this.y=z*vector.x-x*vector.z;
		this.z=x*vector.y-y*vector.x;
		return this;
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
	
	public void set(Vector3f vector,float w){
		this.x=vector.x;
		this.y=vector.y;
		this.z=vector.z;
		this.w=w;
	}
	
	public void set(float x,float y,float z,float w){
		this.x=x;
		this.y=y;
		this.z=z;
		this.w=w;
	}
	
	public void set(Vector4f vector){
		this.x=vector.x;
		this.y=vector.y;
		this.z=vector.z;
		this.w=vector.w;
	}
	
	public void set(float w){
		this.w=w;
	}
	
	public void set(Matrix4f matrix){
		matrix.mul(this);
	}
	
	public float[] get(){
		float[] a = {x,y,z};
		return a;
	}
}
