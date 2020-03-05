package rendered;

import materials.Material;
import materials.ShaderModel;
import math.Matrix4f;
import math.Quaternion;
import math.Vector3f;

public class Model extends Position implements Renderable{

	public Mesh mesh;
	public Material material;
	public ShaderModel shader_model;
	
	public Matrix4f model_matrix;
	
	public Model(Mesh mesh,Material material,ShaderModel shader_model){
		super(new Vector3f(0,-4,-6), new Quaternion());
		this.mesh = mesh;
		this.material = material;
		this.shader_model = shader_model;
		model_matrix = new Matrix4f();
	}
	
	public void render(){
		update();
		shader_model.use(model_matrix);
		material.bind();
		mesh.render();
	}
	
	public void size(float x,float y,float z){
		model_matrix.size(x, y, z);
	}
	
	public void size(float size){
		model_matrix.size(size, size, size);
	}

	@Override
	public void update() {
		model_matrix.identity();
		model_matrix.move(position);
		model_matrix.rotate(quaternion);
	}
}
