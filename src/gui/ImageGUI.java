package gui;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
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

import main.Screen;
import math.Vector3f;

public class ImageGUI extends FrameGUI{

	int texture;
	Screen screen;
	GUIShader shader;
	
	int vao;
	int index_buffer;
	
	public ImageGUI(Vector3f position, Vector3f size, int texture){
		super(position, size);
		this.texture = texture;
		shader = new GUIShader(9);
		
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

	@Override
	public void render() {
		shader.use();
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, texture);
		
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
}
