package console;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Console implements Runnable{

	boolean active_console = true;
	
	public Console(){
		
	}
	
	public void consoleStop(){
		active_console = false;
	}
	
	@Override
	public void run() {
		JFrame f = new JFrame();
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new JLabel("Camera"));
		f.add(new JLabel("x"));
		f.add(new JLabel("y"));
		f.add(new JLabel("z"));
		f.pack();
		f.setVisible(true);
		while(active_console){
			
		}
		
	}

}
