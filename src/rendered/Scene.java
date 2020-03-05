package rendered;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.FrameBuffer;

public class Scene implements Renderable{

	ArrayList<Renderable> render = new ArrayList<>();
	ArrayList<Light> light = new ArrayList<>();
	Map<String,Position> model = new HashMap<>();
	Camera camera;
	FrameBuffer frame_buffer;
	
	public Scene(Camera camera){
		this.camera = camera;
		this.frame_buffer = new FrameBuffer();
	}

	@Override
	public void render() {
		camera.update();
		this.frame_buffer.bind();
		for(Renderable r:render){
			r.render();
		}
		this.frame_buffer.rebind();
		this.frame_buffer.render();
	}
}
