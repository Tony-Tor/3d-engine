package math;

public class Quaternion {

	/*TODO:Comments, proxy*/
	float x;
	float y;
	float z;
	float w;
	
	public Quaternion(){}
	
	public Quaternion(float x,float y,float z,float w){
		this.set(new Vector3f(x,y,z), w);
	}
	
	public Quaternion(Vector3f axis,float angle){
		this.set(axis, angle);//TODO:
	}
	
	public Quaternion(Vector4f v){
		this.set(new Vector3f(v.x,v.y,v.z), v.w);
	}
	
	public Quaternion(Vector4f v,float w){
		this.set(new Vector3f(v.x,v.y,v.z), w);
	}
	
	public Quaternion(Quaternion q){
		this.x=q.x;
		this.y=q.y;
		this.z=q.z;
		this.w=q.w;
	}
	
	public Quaternion invert(Quaternion dust){
		dust.x=-x;
		dust.y=-y;
		dust.z=-z;
		dust.w=w;
		return dust;
	}
	
	public Quaternion invert(){
		return invert(this);
	}
	
	public float lenght(){
		return (float) Math.sqrt(w*w+x*x+y*y+z*z);
	}
	
	public Quaternion normal(Quaternion dust){
		float l=dust.lenght();
		
		dust.w/=l;
		dust.x/=l;
		dust.y/=l;
		dust.z/=l;
		
		return dust;
	}
	
	public Quaternion normal(){
		return normal(this);
	}
	
	public Quaternion mul(Quaternion q,Quaternion dust){//TODO:
		Vector3f q1 = getVector();
		Vector3f q2 = q.getVector();
		float w1 = w;
		
		dust.w=w1*q.w+q1.dot(q2);
		dust.setVector(q2.dot(w1).add(q1.dot(q.w)).add(q1.cross(q2)));
		
		return dust.normal();
	}
	
	public Quaternion mul(Quaternion q){
		return mul(q,this);
	}
	
	public Matrix3f toMatrix3f(Matrix3f m){
		float xx=x*x;
		float xy=x*y;
		float xz=x*z;
		float xw=x*w;
		
		float yy=y*y;
		float yz=y*z;
		float yw=y*w;
		float zz=z*z;
		float zw=z*w;
		
		m.setM00(1-2*(yy+zz));
		m.setM01(2*(xy-zw));
		m.setM02(2*(xz+yw));
		
		m.setM10(2*(xy+zw));
		m.setM11(1-2*(xx+zz));
		m.setM12(2*(yz-xw));
		
		m.setM20(2*(xz-yw));
		m.setM21(2*(yz+xw));
		m.setM22(1-2*(xx+yy));
		return m;
	}
	
	public Matrix3f toMatrix3f(){
		return toMatrix3f(new Matrix3f());
	}
	
	public Matrix4f toMatrix4f(Matrix4f m){
		m.set(toMatrix3f());
		return m;
	}
	
	public Matrix4f toMatrix4f(){
		return toMatrix4f(new Matrix4f());
	}
	
	public Quaternion set(Quaternion q,Vector3f axis,float angle){
		float sin_a = (float) Math.sin(angle*0.5f);
		float cos_a = (float) Math.cos(angle*0.5f);
		
		q.x=axis.x*sin_a;
		q.y=axis.y*sin_a;
		q.z=axis.z*sin_a;
		q.w=cos_a;
		
		q.normal();
		
		return q;
	}
	
	public Quaternion set(Vector3f axis,float angle){
		return set(this, axis, angle);
	}
	
	public Quaternion set(Quaternion q,float angle,float latitude ,float longitude){
		float sin_a = (float)Math.sin(angle);
		float cos_a = (float)Math.cos(angle);
		
		float sin_lat = (float)Math.sin(latitude);
		float cos_lat = (float)Math.cos(latitude);
		
		float sin_long = (float)Math.sin(longitude);
		float cos_long = (float)Math.cos(longitude);
		
		q.x = sin_a*cos_lat*sin_long;
		q.y = sin_a*sin_lat;
		q.z = sin_a*sin_lat*cos_long;
		q.w = cos_a;
		
		return q;
	}
	
	public Quaternion set(float angle,float latitude ,float longitude){
		return set(this, angle, latitude, longitude);
	}
	
	public Quaternion lookAt(Quaternion q,Vector3f pos,Vector3f look){
		Vector3f forward_vector = new Vector3f(look.x-pos.x,look.y-pos.y,look.z-pos.z).normal();
		float dot = new Vector3f(0,0,1).dot(forward_vector);
		if(dot==1){
			return q.set(new Vector3f(0,0,0), 0);
		}
		if(dot==0){
			return q.set(new Vector3f(0,1,0), 3.14f);
		}
		float rotAngle = (float)Math.acos(dot);
		Vector3f rotAxis = new Vector3f(0,0,1).cross(forward_vector);
		rotAxis.normal();
		q.set(rotAxis, rotAngle);
		q.normal();
		return q;
	}
	
	public Quaternion lookAt(Vector3f pos,Vector3f look){
		return lookAt(this,pos,look);
	}
	
	public Vector3f getVector(){
		return new Vector3f(x,y,z);
	}
	
	public Quaternion setVector(Vector3f v){
		x=v.x;
		y=v.y;
		z=v.z;
		
		return this;
	}
}
