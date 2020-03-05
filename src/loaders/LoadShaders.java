package loaders;

import static org.lwjgl.opengl.GL20.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;

public class LoadShaders {
	
	public String directory = "shaders/";
	
	private static Map<String,Integer> vert_shaders = new HashMap<String,Integer>();
	private static Map<String,Integer> frag_shaders = new HashMap<String,Integer>();
	
	public LoadShaders(){
	}

	public void load(){
		URL url = Thread.currentThread().getContextClassLoader().getResource(directory);
		File file = new File(url.getFile());
		String[] listshaders;
		listshaders = file.list();
		for(String shader:listshaders){
			if(shader.toLowerCase().endsWith(".vs")){
				createShader(shader,GL_VERTEX_SHADER);
			}else{if(shader.toLowerCase().endsWith(".fs")){
				createShader(shader,GL_FRAGMENT_SHADER);
			}else{
				System.err.println("File not shader: "+shader);
			}}
			

		}
		System.out.println("Model: "+createDefaultProgram());
		System.out.println("Screen: "+createDefaultProgram2());
		System.out.println("GUISader "+createDefaultProgram3());
		
		
		
	}
	
	private ByteBuffer getByteBuffer(String resource){
		ByteBuffer buffer;		
        URL url = Thread.currentThread().getContextClassLoader().getResource(directory+resource);
        File file = new File(url.getFile());
        if (file.isFile()) {
        	try{
            FileInputStream fis = new FileInputStream(file);
            FileChannel fc = fis.getChannel();
            buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            fc.close();
            fis.close();
			} catch (IOException e) {
				buffer = null;//TODO:
				e.printStackTrace();
			}
        } else {
        	buffer = null;//TODO:
        	System.err.println("Not file");
        }
		
		return buffer;
	}
	
	public int createShader(String resource,int type){
        int shader = glCreateShader(type);
        ByteBuffer source;
        source = getByteBuffer(resource);
        
        resource=resource.substring(0, resource.length()-3);
        if(type==GL_VERTEX_SHADER)vert_shaders.put(resource, shader);
        else frag_shaders.put(resource, shader);
        
        PointerBuffer strings = BufferUtils.createPointerBuffer(1);
        IntBuffer lengths = BufferUtils.createIntBuffer(1);
        
        strings.put(0, source);
        lengths.put(0, source.remaining());
        
        glShaderSource(shader, strings, lengths);
        glCompileShader(shader);
        
        int compiled = glGetShaderi(shader, GL_COMPILE_STATUS);
        String shaderLog = glGetShaderInfoLog(shader);
        
        if (shaderLog != null && shaderLog.trim().length() > 0) {
            System.err.println(shaderLog);
        }
        if (compiled == 0) {
        	System.err.println("Could not compile shader");
        }
        
        return shader;
	}
	
	public int createDefaultProgram(){
		int shaderProgram;//TODO:
		shaderProgram = glCreateProgram();
		
        	glAttachShader(shaderProgram, getVertIndex("shader"));
        	glAttachShader(shaderProgram, getFragIndex("shader"));
		
            glBindAttribLocation(shaderProgram, 0, "in_Position");
            glBindAttribLocation(shaderProgram, 1, "in_Normal");
            glBindAttribLocation(shaderProgram, 2, "in_TextureCoord");
        	
		glLinkProgram(shaderProgram);
		
        int compiled = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        String shaderLog = glGetProgramInfoLog(shaderProgram);
        
        if (shaderLog != null && shaderLog.trim().length() > 0) {
            System.err.println(shaderLog);
        }
        if (compiled == 0) {
        	System.err.println("Could not link shader");
        }

		return shaderProgram;
	}
	
	public int createDefaultProgram2(){
		int shaderProgram;//TODO:
		shaderProgram = glCreateProgram();
		
        	glAttachShader(shaderProgram, getVertIndex("shader2"));
        	glAttachShader(shaderProgram, getFragIndex("shader2"));
		
            glBindAttribLocation(shaderProgram, 0, "in_Position");
            glBindAttribLocation(shaderProgram, 1, "in_TexCoord");
        	
		glLinkProgram(shaderProgram);
		
        int compiled = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        String shaderLog = glGetProgramInfoLog(shaderProgram);
        
        if (shaderLog != null && shaderLog.trim().length() > 0) {
            System.err.println(shaderLog);
        }
        if (compiled == 0) {
        	System.err.println("Could not link shader");
        }

		return shaderProgram;
	}
	
	public int createDefaultProgram3(){
		int shaderProgram;//TODO:
		shaderProgram = glCreateProgram();
		
        	glAttachShader(shaderProgram, getVertIndex("guishader"));
        	glAttachShader(shaderProgram, getFragIndex("guishader"));
		
            glBindAttribLocation(shaderProgram, 0, "in_Position");
            glBindAttribLocation(shaderProgram, 1, "in_TexCoord");
        	
		glLinkProgram(shaderProgram);
		
        int compiled = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        String shaderLog = glGetProgramInfoLog(shaderProgram);
        
        if (shaderLog != null && shaderLog.trim().length() > 0) {
            System.err.println(shaderLog);
        }
        if (compiled == 0) {
        	System.err.println("Could not link shader");
        }

		return shaderProgram;
	}

	public int createProgram(String[] vertex_shader,String[] fragment_shader,String name){
		int shaderProgram;//TODO:
		shaderProgram = glCreateProgram();
		
        for(String shader:vertex_shader){
        	glAttachShader(shaderProgram, getVertIndex(shader));
        }
        for(String shader:fragment_shader){
        	glAttachShader(shaderProgram, getVertIndex(shader));
        }
		
		glLinkProgram(shaderProgram);
		
        int compiled = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        String shaderLog = glGetProgramInfoLog(shaderProgram);
        
        if (shaderLog != null && shaderLog.trim().length() > 0) {
            System.err.println(shaderLog);
        }
        if (compiled == 0) {
        	System.err.println("Could not link shader");
        }

		return shaderProgram;
	}
	
	public static int getVertIndex(String name){
		return vert_shaders.get(name);
	}
	
	public static int getFragIndex(String name){
		return frag_shaders.get(name);
	}
}
