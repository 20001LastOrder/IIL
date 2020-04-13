package ill.gui;

import ca.mcgill.comp421.ill.model.ResultTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Ziqi
 */
public class MakeRequestPanel extends Panel implements ActionListener {

    //Initializing Components
    JLabel lb, queryLabel,resultText,emailLabel,isbnLabel;
    JTextField emailInput,isbnInput;
    JButton backToMainBtn;
    JButton requestBtn;

    //Creating Constructor for initializing JFrame components
    MakeRequestPanel() {

        queryLabel = new JLabel("<html>Please enter your email and a book isbnNumber, if any library that does not belong to your institution<br>" +
                " have such a book, make a request on one of the book copies of this Book.</html>");
        //queryLabel.setBounds(20, 20, 150, 20);
        emailLabel = new JLabel("Email:");
        isbnLabel = new JLabel("ISBN number:");

        emailInput = new JTextField(25);
        isbnInput = new JTextField(25);
        //inputText.setBounds(190, 20, 200, 20);

        requestBtn = new JButton("Request");
        //searchBtn.setBounds(50, 50, 100, 20);
        requestBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = new ca.mcgill.comp421.iil.IILController().makeRequest(emailInput.getText(),isbnInput.getText());
                //String text = "success";
                resultText.setText(text);
            }
        });

        backToMainBtn = new JButton("Back");
        //backToMainBtn.setBounds(50, 80, 100, 20);
        backToMainBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.getInstance().setPanel(new MainMenuPanel());
            }
        });

        lb = new JLabel("Result From Database");
        //lb.setBounds(30, 110, 450, 30);
        lb.setForeground(Color.red);
        lb.setFont(new Font("Serif", Font.BOLD, 20));

        resultText = new JLabel("");
        //resultText.setBounds(30, 150, 700, 20);
        resultText.setForeground(Color.black);
        resultText.setFont(new Font("Serif", Font.BOLD, 13));

        //Add components to the panel
        add(queryLabel);
        add(emailLabel);
        add(isbnLabel);
        add(emailInput);
        add(isbnInput);
        add(requestBtn);
        add(backToMainBtn);
        add(lb);
        add(resultText);  // add the ScrollPane to the panel, not the textResult

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
        spLayout.putConstraint(SpringLayout.NORTH,emailLabel,15,SpringLayout.SOUTH,queryLabel);
        spLayout.putConstraint(SpringLayout.WEST,emailLabel,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,isbnLabel,15,SpringLayout.SOUTH,emailLabel);
        spLayout.putConstraint(SpringLayout.WEST,isbnLabel,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,emailInput,12,SpringLayout.SOUTH,queryLabel);
        spLayout.putConstraint(SpringLayout.NORTH,isbnInput,12,SpringLayout.SOUTH,emailLabel);
        spLayout.putConstraint(SpringLayout.WEST,emailInput,10,SpringLayout.EAST,isbnLabel);
        spLayout.putConstraint(SpringLayout.WEST,isbnInput,10,SpringLayout.EAST,isbnLabel);
        spLayout.putConstraint(SpringLayout.NORTH,requestBtn,15,SpringLayout.SOUTH,isbnLabel);
        spLayout.putConstraint(SpringLayout.WEST,requestBtn,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.WEST,backToMainBtn,30,SpringLayout.EAST,requestBtn);
        spLayout.putConstraint(SpringLayout.NORTH,backToMainBtn,15,SpringLayout.SOUTH,isbnLabel);
        spLayout.putConstraint(SpringLayout.NORTH,lb,30,SpringLayout.SOUTH,backToMainBtn);
        spLayout.putConstraint(SpringLayout.WEST,lb,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,resultText,20,SpringLayout.SOUTH,lb);
        spLayout.putConstraint(SpringLayout.WEST,resultText,10,SpringLayout.WEST,this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // intentionally empty
    }
}
