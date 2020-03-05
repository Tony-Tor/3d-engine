package rendered;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

public class Mesh {
	
	public ArrayList<Vertex> vert = new ArrayList<Vertex>();
	public ArrayList<Integer> i = new ArrayList<Integer>();
	
	private int vert_buffer;
	private int norm_buffer;
	private int tex_buffer;
	private int index_buffer;
	private int vao;
	private int count;
	
	public Mesh(ArrayList<Vertex> vert,ArrayList<Integer> i){
		this.vert=vert;
		this.i=i;
		createVBO();
	}
	
	public void createVBO(){
		FloatBuffer vertex = BufferUtils.createFloatBuffer(vert.size()*3);
		FloatBuffer normal = BufferUtils.createFloatBuffer(vert.size()*3);
		FloatBuffer texture = BufferUtils.createFloatBuffer(vert.size()*2);
		IntBuffer index = BufferUtils.createIntBuffer(i.size());
		
		for(Vertex v:vert){
			vertex.put(v.pos.x);
			vertex.put(v.pos.y);
			vertex.put(v.pos.z);
			
			normal.put(v.norm.x);
			normal.put(v.norm.y);
			normal.put(v.norm.z);
			
			texture.put(v.tex.x);
			texture.put(v.tex.y);
		}
		
		vertex.flip();
		normal.flip();
		texture.flip();
		
		for(int v:i){
			index.put(v);
		}
		
		index.flip();
		
		count = i.size();
		
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		vert_buffer=glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vert_buffer);
		glBufferData(GL_ARRAY_BUFFER, vertex, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		norm_buffer=glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, norm_buffer);
		glBufferData(GL_ARRAY_BUFFER, normal, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		tex_buffer=glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, tex_buffer);
		glBufferData(GL_ARRAY_BUFFER, texture, GL_STATIC_DRAW);
		glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);
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
		glEnableVertexAttribArray(2);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, index_buffer);
		glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
		glBindVertexArray(0);
	}
	
	public void deleteVBO(){
		glDeleteBuffers(vert_buffer);
		glDeleteBuffers(norm_buffer);
		glDeleteBuffers(tex_buffer);
		glDeleteBuffers(index_buffer);
		glDeleteVertexArrays(vao);
	}
}
