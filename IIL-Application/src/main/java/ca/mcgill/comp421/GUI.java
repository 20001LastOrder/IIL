package ca.mcgill.comp421;

import java.awt.EventQueue;
import java.awt.Panel;

import javax.swing.JFrame;

public class GUI {

	public JFrame frame;
	public Panel currentPanel;
	public static GUI instance;
	
	public void setPanel(Panel p) {
		if(currentPanel!= null) {
			frame.remove(currentPanel);
		}
		currentPanel = p;
		frame.add(p);
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

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		 JTextField TextField1 = new JTextField("firstTextField");
//	        JTextField TextField2 = new JTextField("secondTextField");
//	        JPanel firstPanel = new JPanel();
//	        firstPanel.setLayout(new GridLayout(0, 2, 10, 10));
//	        firstPanel.add(TextField1);
//	        firstPanel.add(TextField2);
//
//	        JComboBox comboBox1 = new JComboBox(new Object[]{"Ester", "Jordi", "Jordina", "Jorge", "Sergi"});
//	        JComboBox comboBox2 = new JComboBox(new Object[]{"Ester", "Jordi", "Jordina", "Jorge", "Sergi"});
//	        JComboBox comboBox3 = new JComboBox(new Object[]{"Ester", "Jordi", "Jordina", "Jorge", "Sergi"});
//	        JPanel secondPanel = new JPanel();
//	        secondPanel.setLayout(new GridLayout(0, 3, 10, 10));
//	        secondPanel.add(comboBox1);
//	        secondPanel.add(comboBox2);
//	        secondPanel.add(comboBox3);
//
	        frame = new JFrame();
	        frame.setSize(1000, 700);
	        setPanel(new SwingSearchApp());
//	        frame.setLayout(new GridLayout(2, 1, 10, 10));
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	        frame.add(firstPanel);
//	        frame.add(secondPanel);
//	        frame.pack();
//	        frame.setLocation(150, 150);
//	        frame.setVisible(true);
	}

}
