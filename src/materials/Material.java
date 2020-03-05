package materials;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL13.*;

public class Material {

	int diffuse_map;
	int normal_map;
	int specular_map;
	
	public Material(int diffuse_map,int normal_map,int specular_map){
		this.diffuse_map = diffuse_map;
		this.normal_map = normal_map;
		this.specular_map = specular_map;
	}
	
	public void bind(){
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, diffuse_map);
		glActiveTexture(GL_TEXTURE1);
		glBindTexture(GL_TEXTURE_2D, normal_map);
		glActiveTexture(GL_TEXTURE2);
		glBindTexture(GL_TEXTURE_2D, specular_map);
	}
}
