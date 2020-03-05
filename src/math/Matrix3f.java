package math;

public class Matrix3f {

	float m00,m01,m02;
	float m10,m11,m12;
	float m20,m21,m22;
	
	
	public Matrix3f(){
		identity();
	}
	
	public Matrix3f(Matrix3f matrix){
		set(matrix);
	}
	
	public Matrix3f(float[][] matrix){
		set(matrix);
	}
	
	public Matrix3f transpose(Matrix3f dest){
		float m00=this.m00,m01=this.m01,m02=this.m02;
		float m10=this.m10,m11=this.m11,m12=this.m12;
		float m20=this.m20,m21=this.m21,m22=this.m22;
		
		dest.m00=m00;dest.m01=m10;dest.m02=m20;
		dest.m10=m01;dest.m11=m11;dest.m12=m21;
		dest.m20=m02;dest.m21=m12;dest.m22=m22;
		return dest;
	}
	
	public Matrix3f transpose(){
		return transpose(this);
	}
	
	public Matrix3f add(Matrix3f matrix){
		m00+=matrix.m00;m01+=matrix.m01;m02+=matrix.m02;
		m10+=matrix.m10;m11+=matrix.m11;m12+=matrix.m12;
		m20+=matrix.m20;m21+=matrix.m21;m22+=matrix.m22;
		return this;
	}
	
	public Matrix3f add(float a){
		m00+=a;m01+=a;m02+=a;
		m10+=a;m11+=a;m12+=a;
		m20+=a;m21+=a;m22+=a;
		return this;
	}
	
	public Matrix3f invert(){
		m00=-m00;m01=-m01;m02=-m02;
		m10=-m10;m11=-m11;m12=-m12;
		m20=-m20;m21=-m21;m22=-m22;
		return this;
	}
	
	public Matrix3f mul(Matrix4f matrix){
		float m00=this.m00,m01=this.m01,m02=this.m02;
		float m10=this.m10,m11=this.m11,m12=this.m12;
		float m20=this.m20,m21=this.m21,m22=this.m22;
		
		this.m00=m00*matrix.m00+m01*matrix.m10+m02*matrix.m20;
		this.m01=m00*matrix.m01+m01*matrix.m11+m02*matrix.m21;
		this.m02=m00*matrix.m02+m01*matrix.m12+m02*matrix.m22;
		
		this.m10=m10*matrix.m00+m11*matrix.m10+m12*matrix.m20;
		this.m11=m10*matrix.m01+m11*matrix.m11+m12*matrix.m21;
		this.m12=m10*matrix.m02+m11*matrix.m12+m12*matrix.m22;
		
		this.m20=m20*matrix.m00+m21*matrix.m10+m22*matrix.m20;
		this.m21=m20*matrix.m01+m21*matrix.m11+m22*matrix.m21;
		this.m22=m20*matrix.m02+m21*matrix.m12+m22*matrix.m22;
		
		return this;
	}
	
	public Vector3f mul(Vector3f vector){
		float x = vector.x;
		float y = vector.y;
		float z = vector.z;
		
		vector.x = m00*x+m01*y+m02*z;
		vector.y = m10*x+m11*y+m12*z;
		vector.z = m20*x+m21*y+m22*z;
		
		return vector;
	}
	
	public Matrix3f mul(float a){
		m00*=a;m01*=a;m02*=a;
		m10*=a;m11*=a;m12*=a;
		m20*=a;m21*=a;m22*=a;
		return this;
	}
	
	public void identity(){
		m00=m11=m22=1.0f;
		m01=m02=m10=m12=m20=m21=0.0f;
	}
	
	public void set(float[][] matrix){
		m00=matrix[0][0];m01=matrix[0][1];m02=matrix[0][2];
		m10=matrix[1][0];m11=matrix[1][1];m12=matrix[1][2];
		m20=matrix[2][0];m21=matrix[2][1];m22=matrix[2][2];
	}
	
	public void set(Matrix3f matrix){
		m00=matrix.m00;m01=matrix.m01;m02=matrix.m02;
		m10=matrix.m10;m11=matrix.m11;m12=matrix.m12;
		m20=matrix.m20;m21=matrix.m21;m22=matrix.m22;
	}
	
	public float[][] get(){
		float[][] a = 
		{
		{m00,m01,m02},
		{m10,m11,m12},
		{m20,m21,m22}
		};
		return a;
	}

	public void setM00(float m00) {
		this.m00 = m00;
	}

	public void setM01(float m01) {
		this.m01 = m01;
	}

	public void setM02(float m02) {
		this.m02 = m02;
	}

	public void setM10(float m10) {
		this.m10 = m10;
	}

	public void setM11(float m11) {
		this.m11 = m11;
	}

	public void setM12(float m12) {
		this.m12 = m12;
	}

	public void setM20(float m20) {
		this.m20 = m20;
	}

	public void setM21(float m21) {
		this.m21 = m21;
	}

	public void setM22(float m22) {
		this.m22 = m22;
	}
}
