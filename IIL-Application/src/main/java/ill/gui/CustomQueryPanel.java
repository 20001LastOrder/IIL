package ill.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

import ca.mcgill.comp421.iil.IILApplication;
import ca.mcgill.comp421.ill.model.ResultTable;

public class CustomQueryPanel extends Panel {
	 //Initializing Components
    JLabel lb, queryLabel;
    JButton backToMainBtn;
    JButton addBtn;
    JTable resultTextTable;
    JScrollPane resultScrollPane;
    JTextArea text;

    //Creating Constructor for initializing JFrame components
    CustomQueryPanel() {

        queryLabel = new JLabel("Write an Custom Query and Execute");

        addBtn = new JButton("Execute");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // call the query
                ResultTable resultTable = IILApplication.getController().customerQuery(text.getText());
                String[][] data;
                if(resultTable.getErrorMessage() == null) {
                	data = GUIUtil.to2DArray(resultTable);
                }else {
                	data = new String[1][1];
                	data[0][0] = resultTable.getErrorMessage();
                }
                
                Object[] colName = resultTable.getKeyTypes().toArray();
                if(colName.length == 0) {
                	colName = new Object[1];
                	colName[0] = "";
                }
                // store the data in a JTable
                resultTextTable = new JTable(data,colName);
                // decorate the resultText with a JScrollPane to make it scrollable
                resultScrollPane.setViewportView(resultTextTable);
                //resultScrollPane.add(resultText);
                resultScrollPane.setVisible(true);

                // ** revalidate and repaint the panel when changing the component in scrollPane
                revalidate();
                repaint();
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

        lb = new JLabel("Result From Database:");
        //lb.setBounds(30, 110, 450, 30);
        lb.setForeground(Color.red);
        lb.setFont(new Font("Serif", Font.BOLD, 20));
        
        text = new JTextArea(10, 50);

        //resultText.setBounds(30, 150, 700, 20);

        resultScrollPane = new JScrollPane();
        resultScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        resultScrollPane.setVisible(false);  // false by default, set to true when the button is clicked
        resultScrollPane.setPreferredSize(new Dimension(990, 200));
        
        //Add components to the panel
        add(queryLabel);
        add(addBtn);
        add(backToMainBtn);
        add(lb);
        add(text);
        add(resultScrollPane);
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
        
        spLayout.putConstraint(SpringLayout.WEST,text,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,text,20,SpringLayout.SOUTH,queryLabel);
        //buttons
        spLayout.putConstraint(SpringLayout.WEST,addBtn,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,addBtn,10,SpringLayout.SOUTH,text);
        spLayout.putConstraint(SpringLayout.WEST,backToMainBtn,20,SpringLayout.EAST,addBtn);
        spLayout.putConstraint(SpringLayout.NORTH,backToMainBtn,10,SpringLayout.SOUTH,text);
        
        //result
        spLayout.putConstraint(SpringLayout.WEST,lb,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,lb,10,SpringLayout.SOUTH,addBtn);
        spLayout.putConstraint(SpringLayout.WEST,resultScrollPane,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,resultScrollPane,10,SpringLayout.SOUTH,lb);
    }
}
