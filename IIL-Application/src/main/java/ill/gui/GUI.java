package ill.gui;

import ca.mcgill.comp421.iil.database.DatabaseConnector;

import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
					try {
						DatabaseConnector.initilize("jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421", "cs421g80", "COMP421G80iil");
					} catch (Exception e) {
						DatabaseConnector.close();
					}
					GUI window = new GUI();
					instance = window;
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally {
				}
			}
		});
	}
	
	public GUI() {
		 	frame = new JFrame();
	        frame.setSize(1024, 768);
			//TODO: set the application main page
			setPanel(new findBookLocationPanel());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// when close window, disconnect the database
			frame.addWindowListener(new WindowAdapter()
			{
				@Override
				public void windowClosing(WindowEvent e)
				{
					DatabaseConnector.close();
					System.out.println("Closed");
					//e.getWindow().dispose();
				}
			});

	}


}
