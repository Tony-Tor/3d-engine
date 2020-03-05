package rendered;

import math.Matrix4f;
import math.Quaternion;
import math.Vector3f;

public class Camera extends Position{

	/*public final static Camera CAMERA_UP = new Camera(new Vector3f(0,0,0),new Quaternion(0,0,0,0).lookAt(new Vector3f(0,0,0), new Vector3f(0,1,0)));
	public final static Camera CAMERA_RIGHT = new Camera(new Vector3f(0,0,0),new Quaternion(0,0,0,0).lookAt(new Vector3f(0,0,0), new Vector3f(1,0,0)));
	public final static Camera CAMERA_LEFT = new Camera(new Vector3f(0,0,0),new Quaternion(0,0,0,0).lookAt(new Vector3f(0,0,0), new Vector3f(-1,0,0)));
	public final static Camera CAMERA_DOWN = new Camera(new Vector3f(0,0,0),new Quaternion(0,0,0,0).lookAt(new Vector3f(0,0,0), new Vector3f(0,-1,0)));
	public final static Camera CAMERA_FRONT = new Camera(new Vector3f(0,0,0),new Quaternion(0,0,0,0).lookAt(new Vector3f(0,0,0), new Vector3f(0,0,1)));
	public final static Camera CAMERA_BACK = new Camera(new Vector3f(0,0,0),new Quaternion(0,0,0,0).lookAt(new Vector3f(0,0,0), new Vector3f(0,0,-1)));*/
	
	public static Matrix4f view_matrix;
	public static Matrix4f proj_matrix;
	
	public Camera(){
		super(new Vector3f(0,0,0), new Quaternion(0,0,0,0));
		view_matrix = new Matrix4f();
	}

	public Camera(Vector3f pos,Quaternion q){
		super(pos, q);
		view_matrix = new Matrix4f();
	}
	
	public void update(){
		view_matrix.identity();
		view_matrix.move(position);
		view_matrix.rotate(quaternion);
	}
	
	public void setMatrix(float field,float aspect,float near,float far){
		float y_scale = (float)(1f/Math.tan((field/2)*(float)(Math.PI/180d)));
		float x_scale = y_scale/aspect;
		float frustum_length = far-near;
		
		proj_matrix = new Matrix4f();
		proj_matrix.setM00(x_scale);
		proj_matrix.setM11(y_scale);
		proj_matrix.setM22(-((far+near)/frustum_length));
		proj_matrix.setM23(-1);
		proj_matrix.setM32(-((2*near*far)/frustum_length));
		proj_matrix.setM33(0);
	}
	
	public void setDefaultMatrix(){
		setMatrix(60,(float)800/(float)600,0.9f,100f);
	}
}
