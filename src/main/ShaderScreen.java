package main;

import static org.lwjgl.opengl.GL20.*;

public class ShaderScreen {

	int shader_program;
	
	public ShaderScreen(int shader_program){
		this.shader_program = shader_program;
	}
	
	public void use(){
		glUseProgram(shader_program);
		glUniform1i(glGetUniformLocation(shader_program, "position_texture"), 0);
		glUniform1i(glGetUniformLocation(shader_program, "normal_texture"), 1);
		glUniform1i(glGetUniformLocation(shader_program, "specular_texture"), 2);
	}
}
