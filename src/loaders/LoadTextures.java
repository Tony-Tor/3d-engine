package loaders;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

public class LoadTextures {
	
	public String directory = "textures/";
	private static Map<String,Integer> textures = new HashMap<String,Integer>();

	public LoadTextures(){
		
	}
	
	public void load(){
		URL url = Thread.currentThread().getContextClassLoader().getResource(directory);
		File file = new File(url.getFile());
		String[] listtextures;
		listtextures = file.list(new FilenameFilter() {		
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".png");
			}
		});
		for(String texture:listtextures){
			//texture=texture.substring(0, texture.length()-3);
			createTexture(texture,texture);
		}
	}
	
	public int createTexture(String texture,String name){
		
		URL url = Thread.currentThread().getContextClassLoader().getResource(directory+texture);
		File file = new File(url.getFile());
		BufferedImage img=null;
		try {
			img = ImageIO.read(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		int[] pixels = new int[img.getWidth() * img.getHeight()];
	    PixelGrabber grabber = new PixelGrabber(img, 0, 0, img.getWidth(), img.getHeight(), pixels, 0, img.getWidth());
	    try {
	        grabber.grabPixels();
	    } catch (InterruptedException e) {
	       	
	    }

	    int bufLen = 0;
	    bufLen = pixels.length * 4;

	    ByteBuffer oglPixelBuf = BufferUtils.createByteBuffer(bufLen);

	    for (int y = img.getHeight() - 1; y >= 0; y--) {
	       for (int x = 0; x < img.getWidth(); x++) {
	           int pixel = pixels[y * img.getWidth() + x];
	           oglPixelBuf.put((byte) ((pixel >> 16) & 0xFF));
	           oglPixelBuf.put((byte) ((pixel >> 8) & 0xFF));
	           oglPixelBuf.put((byte) ((pixel >> 0) & 0xFF));
	           oglPixelBuf.put((byte) ((pixel >> 24) & 0xFF));
	       }
	    }

	    oglPixelBuf.flip();

	    
	    int textureID = glGenTextures();

	    glBindTexture(GL_TEXTURE_2D, textureID);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
	    glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);

	    glTexImage2D(GL_TEXTURE_2D,0,GL_RGBA8,img.getWidth(),img.getHeight(),
	    		0, GL_RGBA, GL_UNSIGNED_BYTE,oglPixelBuf);

	    textures.put(name, textureID);
	    System.out.println(name+" "+textureID);
	    return textureID;
	}
	
	public static int getIndex(String name){
		return textures.get(name);
	}
}
