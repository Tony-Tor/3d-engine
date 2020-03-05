package gui;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUseProgram;

public class GUIShader {

	int shader_program;
	
	public GUIShader(int shader_program){
		this.shader_program = shader_program;
	}
	
	public void use(){
		glUseProgram(shader_program);
		glUniform1i(glGetUniformLocation(shader_program, "position_texture"), 0);
	}
}
