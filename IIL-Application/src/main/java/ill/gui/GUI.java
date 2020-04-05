package ill.gui;

import java.awt.EventQueue;
import java.awt.Panel;

import javax.swing.JFrame;

public class GUI {
	private JFrame frame;
	private Panel currentPanel;
	private static GUI instance;
	
	public void setPanel(Panel p) {
		if(currentPanel!= null) {
			frame.remove(currentPanel);
		}
		currentPanel = p;
		frame.add(p);
	}
	
	public static GUI getInstance() {
		return instance;
	}
	
	public static boolean exist() {
		return instance != null;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					instance = window;
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GUI() {
		 frame = new JFrame();
	        frame.setSize(1024, 768);
	        
	        //TODO: set the application main page 
	        //setPanel(new SwingSearchApp());
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
