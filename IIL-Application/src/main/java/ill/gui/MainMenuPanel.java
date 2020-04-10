package ill.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

public class MainMenuPanel extends Panel{
	//Initializing Components
    JLabel lb;
    JLabel description;
    JButton addNewPatronBtn, customQueryBtn, deleteDeclinedRequestBtn, findBookLocationBtn, getAllLoansBtn, makeRequestBtn, updateRequestBtn;

    //Creating Constructor for initializing JFrame components
    MainMenuPanel() {
    	
    	lb = new JLabel("Inter-Institutional Library System");
    	description = new JLabel("Inter-Institutional Library System");
    	
    	lb.setForeground(Color.BLACK);
        lb.setFont(new Font("Serif", Font.BOLD, 20));
        addNewPatronBtn = new JButton("Add New Patron");
        addNewPatronBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               GUI.getInstance().setPanel(new AddNewPatronPanel());
            }
        });
        
        customQueryBtn = new JButton("Custom Query");
        customQueryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               GUI.getInstance().setPanel(new CustomQueryPanel());
            }
        });
        
        
        deleteDeclinedRequestBtn = new JButton("Delete Declined Request");
        deleteDeclinedRequestBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               GUI.getInstance().setPanel(new DeleteDeclinedRequestsPanel());
            }
        });
        
        findBookLocationBtn = new JButton("Find Book Location");
        findBookLocationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               GUI.getInstance().setPanel(new FindBookLocationPanel());
            }
        });
        
        getAllLoansBtn = new JButton("Get All Loans");
        getAllLoansBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               GUI.getInstance().setPanel(new GetAllLoansForPatronPanel());
            }
        });
        
        makeRequestBtn = new JButton("Make Request");
        makeRequestBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               GUI.getInstance().setPanel(new MakeRequestPanel());
            }
        });
        
        updateRequestBtn = new JButton("Update Request");
        updateRequestBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               GUI.getInstance().setPanel(new UpdateRequestPanel());
            }
        });
        

        //Set the panel size
        setSize(1024, 768);

        add(lb);
        add(description);
        add(addNewPatronBtn);
        add(customQueryBtn);
        add(deleteDeclinedRequestBtn);
        add(findBookLocationBtn);
        add(getAllLoansBtn);
        add(makeRequestBtn);
        add(updateRequestBtn);
        
        
        //using spring layout for this panel
        SpringLayout spLayout = new SpringLayout();
        setLayout(spLayout);
        
        // put constraint on components
        // Ex: SpringLayout.WEST is the left edge of the component, the first putConstraint
        // means the left edge of queryLabel is 10 pixel far from the left edge of the panel (which is the left border of our window)
        // value(e1, c1) = value(e2, c2) + pad
        spLayout.putConstraint(SpringLayout.WEST,lb, 500,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,lb, 20,SpringLayout.NORTH,this);
        
        spLayout.putConstraint(SpringLayout.WEST,description, 500,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,description, 20,SpringLayout.SOUTH,lb);
        
        spLayout.putConstraint(SpringLayout.WEST,addNewPatronBtn, 500,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,addNewPatronBtn, 20,SpringLayout.SOUTH,description);
        
        spLayout.putConstraint(SpringLayout.WEST,customQueryBtn, 500,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,customQueryBtn, 20,SpringLayout.SOUTH,addNewPatronBtn);
        
        spLayout.putConstraint(SpringLayout.WEST,deleteDeclinedRequestBtn, 500,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,deleteDeclinedRequestBtn, 20,SpringLayout.SOUTH,customQueryBtn);
        
        spLayout.putConstraint(SpringLayout.WEST,findBookLocationBtn, 500,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,findBookLocationBtn, 20,SpringLayout.SOUTH,deleteDeclinedRequestBtn);
        
        spLayout.putConstraint(SpringLayout.WEST,getAllLoansBtn, 500,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,getAllLoansBtn, 20,SpringLayout.SOUTH,findBookLocationBtn);
        
        spLayout.putConstraint(SpringLayout.WEST,makeRequestBtn, 500,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,makeRequestBtn, 20,SpringLayout.SOUTH,getAllLoansBtn);
        
        spLayout.putConstraint(SpringLayout.WEST,updateRequestBtn, 500,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,updateRequestBtn, 20,SpringLayout.SOUTH,makeRequestBtn);
    }
}
