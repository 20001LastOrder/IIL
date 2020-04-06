package ill.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author Ziqi
 */
public class deleteDeclinedRequestsPanel extends Panel implements ActionListener {

    //Initializing Components
    JLabel lb, queryLabel;
    JButton backToMainBtn;
    JButton deleteBtn;
    JLabel resultText;

    //Creating Constructor for initializing JFrame components
    deleteDeclinedRequestsPanel() {

        queryLabel = new JLabel("Delete all declined requests by click \"Delete\" button");
        //queryLabel.setBounds(30, 20, 400, 20);

        deleteBtn = new JButton("Delete");
        //deleteBtn.setBounds(50, 50, 100, 20);
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // call the query
                String text = new ca.mcgill.comp421.iil.IILController().deleteDeclinedRequests();
                //String text = "success";
                resultText.setText(text);
            }
        });

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
        add(deleteBtn);
        add(backToMainBtn);
        add(lb);
        add(resultText);

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
        spLayout.putConstraint(SpringLayout.NORTH,deleteBtn,10,SpringLayout.SOUTH,queryLabel);
        spLayout.putConstraint(SpringLayout.WEST,deleteBtn,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.WEST,backToMainBtn,30,SpringLayout.EAST,deleteBtn);
        spLayout.putConstraint(SpringLayout.NORTH,backToMainBtn,10,SpringLayout.SOUTH,queryLabel);
        spLayout.putConstraint(SpringLayout.NORTH,lb,30,SpringLayout.SOUTH,backToMainBtn);
        spLayout.putConstraint(SpringLayout.WEST,lb,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,resultText,20,SpringLayout.SOUTH,lb);
        spLayout.putConstraint(SpringLayout.WEST,resultText,20,SpringLayout.WEST,this);
        //setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // intentionally empty
    }
}
