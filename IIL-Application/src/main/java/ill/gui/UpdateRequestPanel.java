package ill.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpringLayout;

import ca.mcgill.comp421.iil.IILApplication;

public class UpdateRequestPanel extends Panel {
	 //Initializing Components
    JLabel lb, queryLabel;
    JButton backToMainBtn;
    JButton addBtn;
    JLabel resultText;
    JTextField emailText, barcodeText;
    JLabel emailLabel, barcodeLabel, approveLabel;
    JCheckBox approveBox;
    
    //Creating Constructor for initializing JFrame components
    UpdateRequestPanel() {

        queryLabel = new JLabel("Update a Request");

        addBtn = new JButton("Update");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // call the query
                String text = IILApplication.getController().updateRequest(emailText.getText(), barcodeText.getText(), approveBox.isSelected());
                resultText.setText(text);
            }
        });
        
        emailText = new JTextField(20);
        barcodeText = new JTextField(20);

        emailLabel = new JLabel("Email:");
        barcodeLabel = new JLabel("Barcode:");
        approveLabel = new JLabel("Approve:");
        approveBox = new JCheckBox();
        
        backToMainBtn = new JButton("Back");
        //backToMainBtn.setBounds(50, 80, 100, 20);
        backToMainBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.getInstance().setPanel(new MainMenuPanel());
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
        add(barcodeText);
        add(emailLabel);
        add(barcodeLabel);
        add(approveLabel);
        add(approveBox);
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
        spLayout.putConstraint(SpringLayout.WEST,barcodeLabel,15,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,barcodeLabel,15,SpringLayout.SOUTH,emailLabel);
        spLayout.putConstraint(SpringLayout.NORTH,barcodeText,15,SpringLayout.SOUTH,emailLabel);
        spLayout.putConstraint(SpringLayout.WEST,barcodeText,10,SpringLayout.EAST,barcodeLabel);
        
        //approve
        spLayout.putConstraint(SpringLayout.WEST,approveLabel,15,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,approveLabel,15,SpringLayout.SOUTH,barcodeLabel);
        spLayout.putConstraint(SpringLayout.NORTH,approveBox,15,SpringLayout.SOUTH,barcodeLabel);
        spLayout.putConstraint(SpringLayout.WEST,approveBox,10,SpringLayout.EAST,approveLabel);
        
        //buttons
        spLayout.putConstraint(SpringLayout.WEST,addBtn,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,addBtn,10,SpringLayout.SOUTH,approveLabel);
        spLayout.putConstraint(SpringLayout.WEST,backToMainBtn,20,SpringLayout.EAST,addBtn);
        spLayout.putConstraint(SpringLayout.NORTH,backToMainBtn,10,SpringLayout.SOUTH,approveLabel);
        
        //result
        spLayout.putConstraint(SpringLayout.WEST,lb,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,lb,10,SpringLayout.SOUTH,addBtn);
        spLayout.putConstraint(SpringLayout.WEST,resultText,10,SpringLayout.EAST,lb);
        spLayout.putConstraint(SpringLayout.NORTH,resultText,10,SpringLayout.SOUTH,addBtn);
    }
}
