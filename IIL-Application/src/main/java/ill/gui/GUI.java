package ill.gui;

import ca.mcgill.comp421.iil.database.DatabaseConnector;

import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GUI {
	private static final String DB_URL = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
	private static final String DB_USERNAME = "cs421g80";
	private static final String DB_PASSWORD = "COMP421G80iil";
	
	private JFrame frame;
	private Panel currentPanel;
	private static GUI instance;
	
	public void setPanel(Panel p) {
		if(currentPanel!= null) {
			frame.remove(currentPanel);
		}
		currentPanel = p;
		frame.add(p);
		p.repaint();
		p.revalidate();
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
						DatabaseConnector.initilize(DB_URL, DB_USERNAME, DB_PASSWORD);
						instance = new GUI();
						instance.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Application Initializing Failed");
						DatabaseConnector.close();
					}
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
			setPanel(new MainMenuPanel());
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
