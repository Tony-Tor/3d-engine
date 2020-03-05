package main;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Screen {

	int vao;
	int index_buffer;
	
	public Screen(){
		FloatBuffer vertex = BufferUtils.createFloatBuffer(4*3);
		FloatBuffer texture_coord = BufferUtils.createFloatBuffer(4*2);
		IntBuffer index = BufferUtils.createIntBuffer(6);
		
		float[] vertex_array = {-1.0f,-1.0f,0,
				-1.0f,1.0f,0,
				1.0f,1.0f,0,
				1.0f,-1.0f,0};
		float[] texture_coord_array = {1.0f,0.0f,
				1.0f,1.0f,
				0.0f,1.0f,
				0.0f,0.0f,};
		int[] index_array = {0,1,2,0,2,3};
		
		vertex.put(vertex_array);
		texture_coord.put(texture_coord_array);
		index.put(index_array);
		
		vertex.flip();
		texture_coord.flip();
		index.flip();
		
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		int vertex_buffer=glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vertex_buffer);
		glBufferData(GL_ARRAY_BUFFER, vertex, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		int texture_coord_buffer=glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, texture_coord_buffer);
		glBufferData(GL_ARRAY_BUFFER, texture_coord, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		index_buffer=glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, index_buffer);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, index, GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
	
	public void render(){
		glBindVertexArray(vao);
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, index_buffer);
		glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glBindVertexArray(0);
	}
	
	public void delete(){
		//TODO:
	}
}
