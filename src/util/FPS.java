package util;

import static org.lwjgl.glfw.GLFW.*;

public class FPS {

	static float fps;
	static double lastFPS=glfwGetTime();
	
	public static void counterFPS(){
		if( glfwGetTime() - lastFPS >= 1.0 ){
			System.out.println(fps);
			fps=0;
			lastFPS+=1.0;
		}
		fps++;
	}
	
	public static void toFPS(int fps){
		try {
			Thread.sleep(960/fps);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
