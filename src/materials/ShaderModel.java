package materials;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;
import static org.lwjgl.opengl.GL20.glUseProgram;

import math.Matrix4f;
import rendered.Camera;

public class ShaderModel {

	int shader_program;
	
	int uniform_projection;
	int uniform_view;
	int uniform_model;
	
	public ShaderModel(int shader_program){
		this.shader_program = shader_program;
		
		uniform_projection = glGetUniformLocation(shader_program, "projectionMatrix");
		uniform_view = glGetUniformLocation(shader_program, "viewMatrix");
		uniform_model = glGetUniformLocation(shader_program, "modelMatrix");
	}
	
	public void use(Matrix4f model_matrix/*, Camera cam*/){
		glUseProgram(shader_program);
		glUniformMatrix4fv(uniform_projection, false, Camera.proj_matrix.get());
		glUniformMatrix4fv(uniform_view, false, Camera.view_matrix.get());
		glUniformMatrix4fv(uniform_model, false, model_matrix.get());
		
		glUniform1i(glGetUniformLocation(shader_program, "diffuse_map"), 0);
		glUniform1i(glGetUniformLocation(shader_program, "normal_map"), 1);
		glUniform1i(glGetUniformLocation(shader_program, "specular_map"), 2);
	}
}
