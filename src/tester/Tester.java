package tester;

import java.util.ArrayList;

import gui.ImageGUI;
import loaders.LoadMeshes;
import loaders.LoadShaders;
import loaders.LoadTextures;
import main.Display;
import materials.Material;
import materials.ShaderModel;
import math.Quaternion;
import math.Vector3f;
import physics.main.Phisics;
import physics.main.SimpleBroadPhase;
import physics.main.SimpleNarrowPhase;
import rendered.Camera;
import rendered.Model;
import util.FPS;

public class Tester extends Display{
	
	Model test;
	ArrayList<Model> geometry = new ArrayList<>();
	ImageGUI frame;

	public static void main(String[] args) {
		Tester test = new Tester();
		test.run();
	}
	float x = 0;
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		for(Model g:geometry){
			g.render();
			g.rotate(new Quaternion(new Vector3f(0,1,0).normal(),x));
		}
		if(x<=6.28){
			x=x+0.05f;
		}else{x = 0; }
		test.render();
		FPS.toFPS(40);
		
	}

	@Override
	public void loadResourse() {
		new LoadTextures().load();
		new LoadShaders().load();
		new LoadMeshes().load();
		int size = 1;
		int l =-5;
		for(int x = 0;x<size;x++){
			for(int y = 0;y<size;y++){
				for(int z = l;z>l-size;z--){
					test = new Model(LoadMeshes.getIndex("monkey.obj"), new Material(3,4,2), new ShaderModel(7));
					//phisics.addRigidBody(test);
					geometry.add(test);
				}
			}
		}
		frame = new ImageGUI(new Vector3f(0.0f,0.0f,0.0f),new Vector3f(0.0f,0.0f,0.0f),6);
		//phisics.setImpulseRigidBody(0, new Vector3f(0.0f,0.0f,0.08f));
	}

	@Override
	public void initDisplay() {
		this.setDisplaySize(800, 600);
		this.cam = new Camera();
		this.cam.setDefaultMatrix();
		cam.moveTo(new Vector3f(0,0,0));
		cam.lookAt(new Vector3f(0,0,10));
		//this.active_console = true;
		//phisics = new Phisics(new SimpleBroadPhase(), new SimpleNarrowPhase());
	}

}
