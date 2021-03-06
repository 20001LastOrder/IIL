package ca.mcgill.comp421;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;  
public class SwingSearchApp2 extends Panel implements ActionListener {  
//Initializing Components  
    JLabel lb, lb1, lb2, lb3, lb4, lb5;  
    JTextField tf1, tf2, tf3, tf4, tf5;  
    JButton btn;  
    //Creating Constructor for initializing JFrame components  
    SwingSearchApp2() {  
        //Providing Title  
        //super("Fetching Student Information");  
        lb5 = new JLabel("Enter Name:");  
        lb5.setBounds(20, 20, 100, 20);  
        tf5 = new JTextField(20);  
        tf5.setBounds(130, 20, 200, 20);  
        btn = new JButton("Submit");  
        btn.setBounds(50, 50, 100, 20);  
        btn.addActionListener(this);  
        lb = new JLabel("Fetching Student Information From Database");  
        lb.setBounds(30, 80, 450, 30);  
        lb.setForeground(Color.red);  
        lb.setFont(new Font("Serif", Font.BOLD, 20));  
        setVisible(true);  
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );  
        setSize(500, 500);  
        lb1 = new JLabel("U_Name:");  
        lb1.setBounds(20, 120, 100, 20);  
        tf1 = new JTextField(50);  
        tf1.setBounds(130, 120, 200, 20);  
        lb2 = new JLabel("U_Mail:");  
        lb2.setBounds(20, 150, 100, 20);  
        tf2 = new JTextField(100);  
        tf2.setBounds(130, 150, 200, 20);  
        lb3 = new JLabel("U_Pass:");  
        lb3.setBounds(20, 180, 100, 20);  
        tf3 = new JTextField(50);  
        tf3.setBounds(130, 180, 200, 20);  
        lb4 = new JLabel("U_Country:");  
        lb4.setBounds(20, 210, 100, 20);  
        tf4 = new JTextField(50);  
        tf4.setBounds(130, 210, 100, 20);  
        setLayout(null);  
        //Add components to the JFrame  
        add(lb5);  
        add(tf5);  
        add(btn);  
        add(lb);  
        add(lb1);  
        //Set TextField Editable False  
        tf1.setEditable(false);  
        tf2.setEditable(false);  
        tf3.setEditable(false);  
        tf4.setEditable(false);  
    }  
    static int i = 0;
    public void actionPerformed(ActionEvent e) {  
    	GUI.instance.setPanel(new SwingSearchApp());
    }  
    //Running Constructor  
    public static void main(String args[]) {  
    	JFrame frame = new JFrame();
    	frame.add(new SwingSearchApp2());
        
    }  
}  