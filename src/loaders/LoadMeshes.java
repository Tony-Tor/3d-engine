package loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import math.Vector2f;
import math.Vector3f;
import rendered.Mesh;
import rendered.Vertex;
import util.Index;

public class LoadMeshes {

	/*TODO:Optimization, comments*/
	public String directory = "meshes/";
	private static Map<String,Mesh> meshes = new HashMap<String,Mesh>();
	
	public LoadMeshes(){
		
	}
	
	public void load(){
		URL url = Thread.currentThread().getContextClassLoader().getResource(directory);
		File file = new File(url.getFile());
		String[] listmesh;
		listmesh = file.list();
		for(String mesh:listmesh){
			if(mesh.toLowerCase().endsWith(".obj")){
				createMesh(mesh);
			}
			if(mesh.toLowerCase().endsWith(".sfo")){
				createMeshSfo(mesh);
			}
		}
	}
	
	private void createMeshSfo(String resourse) {
		ArrayList<Vertex> meshvertex = new ArrayList<Vertex>();
		ArrayList<Integer> meshindex = new ArrayList<Integer>();
		
		URL url = Thread.currentThread().getContextClassLoader().getResource(directory+resourse);
		File file = new File(url.getFile());
		
		FileReader read = null;
		BufferedReader bufferedRead;
		
		try {
			read = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bufferedRead = new BufferedReader(read);
		
		String line;
		
		while(true){
			try {
				line = bufferedRead.readLine();
			} catch (IOException e) {
				line=null;
				e.printStackTrace();
			}
			if(line==null){
				break;
			}
			
			if(line.startsWith("vertexes: ")){
				String[] l = line.split(" ");
				for(int i = 1; i < l.length;i++){
				meshvertex.add(new Vertex(
						new Vector3f(
								Float.valueOf(l[i++]),
								Float.valueOf(l[i++]),
								Float.valueOf(l[i++])
								),
						new Vector3f(
								Float.valueOf(l[i++]),
								Float.valueOf(l[i++]),
								Float.valueOf(l[i++])
								),
						new Vector2f(
								Float.valueOf(l[i++]),
								Float.valueOf(l[i])
								)
						));
				}
			}
			
			if(line.startsWith("indexes: ")){
				String[] l = line.split(" ");
				for(int i = 1; i < l.length;i++){
				meshindex.add(Integer.valueOf(l[i]));
				}
			}
		}
		meshes.put(resourse,new Mesh(meshvertex,meshindex));
	}

	private int getIndex(ArrayList<Index> uniqueindex,Index index){
		int x=0;
		for(Index i:uniqueindex){
			if(i.isEqual(index))return x;
			x++;
		}
		return -1;
	}
	
	public int createMesh(String resourse){
		ArrayList<Vector3f> vertex = new ArrayList<Vector3f>();
		ArrayList<Vector3f> normal = new ArrayList<Vector3f>();
		ArrayList<Vector2f> texture = new ArrayList<Vector2f>();
		ArrayList<Index> index = new ArrayList<Index>();
		
		ArrayList<Index> uniqueindex = new ArrayList<Index>();
		
		ArrayList<Vertex> meshvertex = new ArrayList<Vertex>();
		ArrayList<Integer> meshindex = new ArrayList<Integer>();
		
		URL url = Thread.currentThread().getContextClassLoader().getResource(directory+resourse);
		File file = new File(url.getFile());
		
		FileReader read = null;
		BufferedReader bufferedRead;
		
		try {
			read = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bufferedRead = new BufferedReader(read);
		
		String line;
		
		while(true){
			try {
				line = bufferedRead.readLine();
			} catch (IOException e) {
				line=null;
				e.printStackTrace();
			}
			if(line==null){
				break;
			}
			
			if(line.startsWith("v ")){
				vertex.add(new Vector3f(Float.valueOf(line.split(" ")[1]),
						Float.valueOf(line.split(" ")[2]),
						Float.valueOf(line.split(" ")[3])));
			}
			
			if(line.startsWith("vn ")){
				normal.add(new Vector3f(Float.valueOf(line.split(" ")[1]),
						Float.valueOf(line.split(" ")[2]),
						Float.valueOf(line.split(" ")[3])));
			}
			
			if(line.startsWith("vt ")){
				texture.add(new Vector2f(Float.valueOf(line.split(" ")[1]),
						Float.valueOf(line.split(" ")[2])));
			}
			
			if(line.startsWith("f ")){
				index.add(new Index(Integer.valueOf(line.split(" ")[1].split("/")[0])-1,
						Integer.valueOf(line.split(" ")[1].split("/")[1])-1,
						Integer.valueOf(line.split(" ")[1].split("/")[2])-1));
				
				index.add(new Index(Integer.valueOf(line.split(" ")[2].split("/")[0])-1,
						Integer.valueOf(line.split(" ")[2].split("/")[1])-1,
						Integer.valueOf(line.split(" ")[2].split("/")[2])-1));
				
				index.add(new Index(Integer.valueOf(line.split(" ")[3].split("/")[0])-1,
						Integer.valueOf(line.split(" ")[3].split("/")[1])-1,
						Integer.valueOf(line.split(" ")[3].split("/")[2])-1));
			}
		}
		
		for(Index i:index){
			if(getIndex(uniqueindex,i)==-1){
				uniqueindex.add(i);
				meshvertex.add(new Vertex(
						new Vector3f(vertex.get(i.v)),
						new Vector3f(normal.get(i.n)),
						new Vector2f(texture.get(i.t))));
			}
			System.out.print(getIndex(uniqueindex,i)+"_");
			meshindex.add(getIndex(uniqueindex,i));
		}
		
		System.out.println();
		
		meshes.put(resourse,new Mesh(meshvertex,meshindex));
		return 0;
	}
	
	public static Mesh getIndex(String name){
		return meshes.get(name);
	}
}
