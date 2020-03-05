package math;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Matrix4f {

	/*TODO:Comments, proxy, interface*/
	float m00,m01,m02,m03;
	float m10,m11,m12,m13;
	float m20,m21,m22,m23;
	float m30,m31,m32,m33;
	
	public Matrix4f(){
		identity();
	}
	
	public Matrix4f(Matrix4f matrix){
		set(matrix);
	}
	
	public Matrix4f(float[][] matrix){
		set(matrix);
	}
	
	public Matrix4f transpose(Matrix4f dest){
		float m00=this.m00,m01=this.m01,m02=this.m02,m03=this.m03;
		float m10=this.m10,m11=this.m11,m12=this.m12,m13=this.m13;
		float m20=this.m20,m21=this.m21,m22=this.m22,m23=this.m23;
		float m30=this.m30,m31=this.m31,m32=this.m32,m33=this.m33;
		
		dest.m00=m00;dest.m01=m10;dest.m02=m20;dest.m03=m30;
		dest.m10=m01;dest.m11=m11;dest.m12=m21;dest.m13=m31;
		dest.m20=m02;dest.m21=m12;dest.m22=m22;dest.m23=m32;
		dest.m30=m03;dest.m31=m13;dest.m32=m23;dest.m33=m33;
		return dest;
	}
	
	public Matrix4f transpose(){
		return transpose(this);
	}
	
	public Matrix4f add(Matrix4f matrix){
		m00+=matrix.m00;m01+=matrix.m01;m02+=matrix.m02;m03+=matrix.m03;
		m10+=matrix.m10;m11+=matrix.m11;m12+=matrix.m12;m13+=matrix.m13;
		m20+=matrix.m20;m21+=matrix.m21;m22+=matrix.m22;m23+=matrix.m23;
		m30+=matrix.m30;m31+=matrix.m31;m32+=matrix.m32;m33+=matrix.m33;
		return this;
	}
	
	public Matrix4f add(float a){
		m00+=a;m01+=a;m02+=a;m03+=a;
		m10+=a;m11+=a;m12+=a;m13+=a;
		m20+=a;m21+=a;m22+=a;m23+=a;
		m30+=a;m31+=a;m32+=a;m33+=a;
		return this;
	}
	
	public Matrix4f invert(){
		m00=-m00;m01=-m01;m02=-m02;m03=-m03;
		m10=-m10;m11=-m11;m12=-m12;m13=-m13;
		m20=-m20;m21=-m21;m22=-m22;m23=-m23;
		m30=-m30;m31=-m31;m32=-m32;m33=-m33;
		return this;
	}
	
	public Matrix4f mul(Matrix4f matrix){
		float m00=this.m00,m01=this.m01,m02=this.m02,m03=this.m03;
		float m10=this.m10,m11=this.m11,m12=this.m12,m13=this.m13;
		float m20=this.m20,m21=this.m21,m22=this.m22,m23=this.m23;
		float m30=this.m30,m31=this.m31,m32=this.m32,m33=this.m33;
		
		this.m00=m00*matrix.m00+m01*matrix.m10+m02*matrix.m20+m03*matrix.m30;
		this.m01=m00*matrix.m01+m01*matrix.m11+m02*matrix.m21+m03*matrix.m31;
		this.m02=m00*matrix.m02+m01*matrix.m12+m02*matrix.m22+m03*matrix.m32;
		this.m03=m00*matrix.m03+m01*matrix.m13+m02*matrix.m23+m03*matrix.m33;
		
		this.m10=m10*matrix.m00+m11*matrix.m10+m12*matrix.m20+m13*matrix.m30;
		this.m11=m10*matrix.m01+m11*matrix.m11+m12*matrix.m21+m13*matrix.m31;
		this.m12=m10*matrix.m02+m11*matrix.m12+m12*matrix.m22+m13*matrix.m32;
		this.m13=m10*matrix.m03+m11*matrix.m13+m12*matrix.m23+m13*matrix.m33;
		
		this.m20=m20*matrix.m00+m21*matrix.m10+m22*matrix.m20+m23*matrix.m30;
		this.m21=m20*matrix.m01+m21*matrix.m11+m22*matrix.m21+m23*matrix.m31;
		this.m22=m20*matrix.m02+m21*matrix.m12+m22*matrix.m22+m23*matrix.m32;
		this.m23=m20*matrix.m03+m21*matrix.m13+m22*matrix.m23+m23*matrix.m33;
		
		this.m30=m30*matrix.m00+m31*matrix.m10+m32*matrix.m20+m33*matrix.m30;
		this.m31=m30*matrix.m01+m31*matrix.m11+m32*matrix.m21+m33*matrix.m31;
		this.m32=m30*matrix.m02+m31*matrix.m12+m32*matrix.m22+m33*matrix.m32;
		this.m33=m30*matrix.m03+m31*matrix.m13+m32*matrix.m23+m33*matrix.m33;
		
		return this;
	}
	
	public Vector4f mul(Vector4f vector){
		float x = vector.x;
		float y = vector.y;
		float z = vector.z;
		float w = vector.w;
		
		vector.x = m00*x+m01*y+m02*z+m03*w;
		vector.y = m10*x+m11*y+m12*z+m13*w;
		vector.z = m20*x+m21*y+m22*z+m23*w;
		vector.w = m30*x+m31*y+m32*z+m33*w;
		
		return vector;
	}
	
	public Matrix4f mul(float a){
		m00*=a;m01*=a;m02*=a;m03*=a;
		m10*=a;m11*=a;m12*=a;m13*=a;
		m20*=a;m21*=a;m22*=a;m23*=a;
		m30*=a;m31*=a;m32*=a;m33*=a;
		return this;
	}
	
	public void identity(){
		m00=m11=m22=m33=1.0f;
		m01=m02=m03=m10=m12=m13=m20=m21=m23=m30=m31=m32=0.0f;
	}
	
	public void rotate(float x,float y,float z,float angle){
		float sinx = (float)Math.sin(angle*x);
		float cosx = (float)Math.cos(angle*x);
		
		float siny = (float)Math.sin(angle*y);
		float cosy = (float)Math.cos(angle*y);
		
		float sinz = (float)Math.sin(angle*z);
		float cosz = (float)Math.cos(angle*z);
		
		float rm00,rm01;
		float rm10,rm11,rm12;
		float rm20,rm21,rm22;
		
		rm00=cosy*cosz;
		rm01=cosy*sinz;
		
		rm10=sinx*siny*cosz-sinz*cosx;
		rm11=sinx*siny*sinz+cosx*cosz;
		rm12=sinx*cosy;
		
		rm20=cosx*siny*cosz+sinz*sinx;
		rm21=cosx*siny*sinz-sinx*cosz;
		rm22=cosx*cosy;
		
		float m00=this.m00,m01=this.m01,m02=this.m02;
		float m10=this.m10,m11=this.m11,m12=this.m12;
		float m20=this.m20,m21=this.m21,m22=this.m22;
		
		this.m00=m00*rm00+m01*rm10+m02*rm20;
		this.m01=m00*rm01+m01*rm11+m02*rm21;
		this.m02=m00*(-siny)+m01*rm12+m02*rm22;
		
		this.m10=m10*rm00+m11*rm10+m12*rm20;
		this.m11=m10*rm01+m11*rm11+m12*rm21;
		this.m12=m10*(-siny)+m11*rm12+m12*rm22;
		
		this.m20=m20*rm00+m21*rm10+m22*rm20;
		this.m21=m20*rm01+m21*rm11+m22*rm21;
		this.m22=m20*(-siny)+m21*rm12+m22*rm22;
	}
	
	public void rotateX(float angle){
		float sin = (float)Math.sin(angle);
		float cos = (float)Math.cos(angle);
        m11=cos;
        m12=sin;
        m21=-sin;
        m22=cos;
	}
	
	public void rotateY(float angle){
		float sin = (float)Math.sin(angle);
		float cos = (float)Math.cos(angle);
        m00=cos;
        m02=-sin;
        m20=sin;
        m22=cos;
	}
	
	public void rotateZ(float angle){
		float sin = (float)Math.sin(angle);
		float cos = (float)Math.cos(angle);
        m00=cos;
        m01=sin;
        m10=-sin;
        m11=cos;
	}
	
	public void rotate(Vector3f v,float angle){
		rotate(v.x,v.y,v.z,angle);
	}
	
	public void rotate(Quaternion q){
		float xx=q.x*q.x;
		float xy=q.x*q.y;
		float xz=q.x*q.z;
		float xw=q.x*q.w;
		
		float yy=q.y*q.y;
		float yz=q.y*q.z;
		float yw=q.y*q.w;
		float zz=q.z*q.z;
		float zw=q.z*q.w;
		
		float q00=1-2*(yy+zz);
		float q01=2*(xy-zw);
		float q02=2*(xz+yw);
		
		float q10=2*(xy+zw);
		float q11=1-2*(xx+zz);
		float q12=2*(yz-xw);
		
		float q20=2*(xz-yw);
		float q21=2*(yz+xw);
		float q22=1-2*(xx+yy);
		
		float m00=this.m00,m01=this.m01,m02=this.m02;
		float m10=this.m10,m11=this.m11,m12=this.m12;
		float m20=this.m20,m21=this.m21,m22=this.m22;
		
		this.m00=m00*q00+m01*q10+m02*q20;
		this.m01=m00*q01+m01*q11+m02*q21;
		this.m02=m00*q02+m01*q12+m02*q22;
		
		this.m10=m10*q00+m11*q10+m12*q20;
		this.m11=m10*q01+m11*q11+m12*q21;
		this.m12=m10*q02+m11*q12+m12*q22;
		
		this.m20=m20*q00+m21*q10+m22*q20;
		this.m21=m20*q01+m21*q11+m22*q21;
		this.m22=m20*q02+m21*q12+m22*q22;
	}
	
	public void move(float x,float y,float z){
		this.m03=m00*x+m01*y+m02*z+m03;
		this.m13=m10*x+m11*y+m12*z+m13;
		this.m23=m20*x+m21*y+m22*z+m23;
		this.m33=m30*x+m31*y+m32*z+m33;
	}
	
	public void move(Vector3f pos){
		this.move(pos.x, pos.y, pos.z);
	}
	
	public void size(float x,float y, float z){
		m00*=x;
		m11*=y;
		m22*=z;
	}
	
	public void set(float[][] matrix){
		m00=matrix[0][0];m01=matrix[0][1];m02=matrix[0][2];m03=matrix[0][3];
		m10=matrix[1][0];m11=matrix[1][1];m12=matrix[1][2];m13=matrix[1][3];
		m20=matrix[2][0];m21=matrix[2][1];m22=matrix[2][2];m23=matrix[2][3];
		m30=matrix[3][0];m31=matrix[3][1];m32=matrix[3][2];m33=matrix[3][3];
	}
	
	public void set(Matrix4f matrix){
		m00=matrix.m00;m01=matrix.m01;m02=matrix.m02;m03=matrix.m03;
		m10=matrix.m10;m11=matrix.m11;m12=matrix.m12;m13=matrix.m13;
		m20=matrix.m20;m21=matrix.m21;m22=matrix.m22;m23=matrix.m23;
		m30=matrix.m30;m31=matrix.m31;m32=matrix.m32;m33=matrix.m33;
	}
	
	public void set(Matrix3f matrix){
		m00=matrix.m00;m01=matrix.m01;m02=matrix.m02;m03=0;
		m10=matrix.m10;m11=matrix.m11;m12=matrix.m12;m13=0;
		m20=matrix.m20;m21=matrix.m21;m22=matrix.m22;m23=0;
		m30=0;m31=0;m32=0;m33=1;
	}
	
	public FloatBuffer get(){
		FloatBuffer buf = BufferUtils.createFloatBuffer(16);
		buf.put(m00);
		buf.put(m10);
		buf.put(m20);
		buf.put(m30);
		
		buf.put(m01);
		buf.put(m11);
		buf.put(m21);
		buf.put(m31);
		
		buf.put(m02);
		buf.put(m12);
		buf.put(m22);
		buf.put(m32);
		
		buf.put(m03);
		buf.put(m13);
		buf.put(m23);
		buf.put(m33);
		
		buf.flip();

		return buf;
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

	public void setM03(float m03) {
		this.m03 = m03;
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

	public void setM13(float m13) {
		this.m13 = m13;
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

	public void setM23(float m23) {
		this.m23 = m23;
	}

	public void setM30(float m30) {
		this.m30 = m30;
	}

	public void setM31(float m31) {
		this.m31 = m31;
	}

	public void setM32(float m32) {
		this.m32 = m32;
	}

	public void setM33(float m33) {
		this.m33 = m33;
	}
}
