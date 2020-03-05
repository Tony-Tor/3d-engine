package main;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

public class FrameBuffer {

	int frame_buffer;
	int width = 800;
	int height = 600;
	int diffuse_texture;
	int normal_texture;
	int specular_texture;
	Screen screen;
	ShaderScreen shader_screen;
	
	public FrameBuffer(){
		screen = new Screen();
		shader_screen = new ShaderScreen(8);
		
		frame_buffer = glGenFramebuffers();
		glBindFramebuffer(GL_FRAMEBUFFER, frame_buffer);
		createTextureDiffuse();
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
	}

	public void createTextureDiffuse(){
		ByteBuffer NULL = BufferUtils.createByteBuffer(height*width*4);
		NULL.flip();
		
		diffuse_texture = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, diffuse_texture);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA16F, width, height, 0, GL_RGBA, GL_FLOAT, NULL);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, diffuse_texture, 0);
		
		normal_texture = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, normal_texture);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, NULL);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT1, GL_TEXTURE_2D, normal_texture, 0);
		
		specular_texture = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, specular_texture);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, NULL);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT2, GL_TEXTURE_2D, specular_texture, 0);
		
		int bufs[] = {GL_COLOR_ATTACHMENT0,GL_COLOR_ATTACHMENT1,GL_COLOR_ATTACHMENT2};
		glDrawBuffers(bufs);
		
		int depth = glGenRenderbuffers();
		glBindRenderbuffer(GL_RENDERBUFFER, depth);
		glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT, width, height);
		glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, depth);
		
		int error = glCheckFramebufferStatus(GL_FRAMEBUFFER);
		if(error!=GL_FRAMEBUFFER_COMPLETE)System.out.println("FRAMEBUFFER_ERROR: "+error);
	}
	
	public void render(){
		shader_screen.use();
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, diffuse_texture);
		glActiveTexture(GL_TEXTURE1);
		glBindTexture(GL_TEXTURE_2D, normal_texture);
		glActiveTexture(GL_TEXTURE2);
		glBindTexture(GL_TEXTURE_2D, specular_texture);
		screen.render();
	}
	
	public void bind(){
		glBindFramebuffer(GL_FRAMEBUFFER, frame_buffer);
		glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
	}
	
	public void rebind(){
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
	}
}
