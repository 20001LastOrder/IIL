package ill.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class AddNewPatronPanel extends Panel{
	 //Initializing Components
    JLabel lb, queryLabel;
    JButton backToMainBtn;
    JButton addBtn;
    JLabel resultText;
    JTextField emailText, uNameText, phoneNumberText, uAddressText, inameText;
    JLabel emailLabel, uNameLabel, phoneNumberLabel, uAddressLabel, inameLabel;
    
    //Creating Constructor for initializing JFrame components
    AddNewPatronPanel() {

        queryLabel = new JLabel("Add a new Patron");
        //queryLabel.setBounds(30, 20, 400, 20);

        addBtn = new JButton("Add");
        //deleteBtn.setBounds(50, 50, 100, 20);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // call the query
                String text = new ca.mcgill.comp421.iil.IILController().addNewPatron(emailText.getText(), uNameText.getText(), phoneNumberText.getText(), uAddressText.getText(), inameText.getText());
                //String text = "success";
                resultText.setText(text);
            }
        });
        
        emailText = new JTextField(20);
        uNameText = new JTextField(20);
        phoneNumberText = new JTextField(20);
        uAddressText = new JTextField(20);
        inameText = new JTextField(20);

        emailLabel = new JLabel("Email:");
        uNameLabel = new JLabel("Name:");
        phoneNumberLabel = new JLabel("Phone Number:");
        uAddressLabel = new JLabel("Address:");
        inameLabel = new JLabel("Institution Name:");
        
        backToMainBtn = new JButton("Back");
        //backToMainBtn.setBounds(50, 80, 100, 20);
        backToMainBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: show main panel
                //GUI.instance.setPanel(new mainPanel);
            }
        });

        lb = new JLabel("Result From Database:");
        //lb.setBounds(30, 110, 450, 30);
        lb.setForeground(Color.red);
        lb.setFont(new Font("Serif", Font.BOLD, 20));

        resultText = new JLabel("");
        //resultText.setBounds(30, 150, 700, 20);
        resultText.setForeground(Color.black);
        resultText.setFont(new Font("Serif", Font.BOLD, 20));

        //Add components to the panel
        add(queryLabel);
        add(addBtn);
        add(backToMainBtn);
        add(lb);
        add(resultText);
        add(emailText);
        add(uNameText);
        add(phoneNumberText);
        add(uAddressText);
        add(inameText);
        add(emailLabel);
        add(uNameLabel);
        add(phoneNumberLabel);
        add(uAddressLabel);
        add(inameLabel);
        //Set the panel size
        setSize(1024, 768);

        //using spring layout for this panel
        SpringLayout spLayout = new SpringLayout();
        setLayout(spLayout);

        // put constraint on components
        // Ex: SpringLayout.WEST is the left edge of the component, the first putConstraint
        // means the left edge of queryLabel is 10 pixel far from the left edge of the panel (which is the left border of our window)
        // value(e1, c1) = value(e2, c2) + pad
        spLayout.putConstraint(SpringLayout.WEST,queryLabel,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,queryLabel,20,SpringLayout.NORTH,this);
        
        //email
        spLayout.putConstraint(SpringLayout.WEST,emailLabel,15,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,emailLabel,20,SpringLayout.SOUTH,queryLabel);
        spLayout.putConstraint(SpringLayout.NORTH,emailText,20,SpringLayout.SOUTH,queryLabel);
        spLayout.putConstraint(SpringLayout.WEST,emailText,10,SpringLayout.EAST,emailLabel);
        
        //uname
        spLayout.putConstraint(SpringLayout.WEST,uNameLabel,15,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,uNameLabel,15,SpringLayout.SOUTH,emailLabel);
        spLayout.putConstraint(SpringLayout.NORTH,uNameText,15,SpringLayout.SOUTH,emailLabel);
        spLayout.putConstraint(SpringLayout.WEST,uNameText,10,SpringLayout.EAST,uNameLabel);
        
        // phone number
        spLayout.putConstraint(SpringLayout.WEST,phoneNumberLabel,15,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,phoneNumberLabel,15,SpringLayout.SOUTH,uNameLabel);
        spLayout.putConstraint(SpringLayout.NORTH,phoneNumberText,15,SpringLayout.SOUTH,uNameLabel);
        spLayout.putConstraint(SpringLayout.WEST,phoneNumberText,10,SpringLayout.EAST,phoneNumberLabel);
        
        // uAddress
        spLayout.putConstraint(SpringLayout.WEST,uAddressLabel,15,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,uAddressLabel,15,SpringLayout.SOUTH,phoneNumberLabel);
        spLayout.putConstraint(SpringLayout.NORTH,uAddressText,15,SpringLayout.SOUTH,phoneNumberLabel);
        spLayout.putConstraint(SpringLayout.WEST,uAddressText,10,SpringLayout.EAST,uAddressLabel);
        
        //iname
        spLayout.putConstraint(SpringLayout.WEST,inameLabel,15,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,inameLabel,15,SpringLayout.SOUTH,uAddressLabel);
        spLayout.putConstraint(SpringLayout.NORTH,inameText,15,SpringLayout.SOUTH,uAddressLabel);
        spLayout.putConstraint(SpringLayout.WEST,inameText,10,SpringLayout.EAST,inameLabel);
        
        //buttons
        spLayout.putConstraint(SpringLayout.WEST,addBtn,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,addBtn,10,SpringLayout.SOUTH,inameLabel);
        spLayout.putConstraint(SpringLayout.WEST,backToMainBtn,20,SpringLayout.EAST,addBtn);
        spLayout.putConstraint(SpringLayout.NORTH,backToMainBtn,10,SpringLayout.SOUTH,inameLabel);
        
        //result
        spLayout.putConstraint(SpringLayout.WEST,lb,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,lb,10,SpringLayout.SOUTH,addBtn);
        spLayout.putConstraint(SpringLayout.WEST,resultText,10,SpringLayout.EAST,lb);
        spLayout.putConstraint(SpringLayout.NORTH,resultText,10,SpringLayout.SOUTH,addBtn);
    }

}
