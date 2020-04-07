package ill.gui;
import ca.mcgill.comp421.GUI;
import ca.mcgill.comp421.SwingSearchApp2;
import ca.mcgill.comp421.ill.model.ResultTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import javax.swing.*;

/**
 * @author Ziqi
 */
public class findBookLocationPanel extends Panel implements ActionListener  {

    findBookLocationPanel instance;
    //Initializing Components
    JLabel lb, queryLabel;
    JTextField inputText;
    JButton backToMainBtn;
    JButton searchBtn;
    //JTextArea resultText;
    JTable resultText;
    JScrollPane resultScrollPane;
    SpringLayout spLayout;

        //Creating Constructor for initializing JFrame components
    findBookLocationPanel() {
        instance = this;
        queryLabel = new JLabel("Please enter a book name to find the name of the institutions and the libraries which have this book:");
        //queryLabel.setBounds(20, 20, 150, 20);

        inputText = new JTextField(20);
        //inputText.setBounds(190, 20, 200, 20);

        searchBtn = new JButton("Search");
        //searchBtn.setBounds(50, 50, 100, 20);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: showing the resultTable needs to be fix!
                ResultTable resultTable = new ca.mcgill.comp421.iil.IILController().findBookLocation(inputText.getText());

                //put the data in a 2D array (using weird index, since the order of elements in keyType is inverse from the
                // order in the resultTable.toList())
                String[][] data = new String[resultTable.getSize()][resultTable.getKeyType().size()];
                for(int i=resultTable.getKeyType().size()-1, k =0; i >= 0; i--, k++)
                {
                    for(int j = 0; j < resultTable.getSize(); j++)
                    {
                        data[j][k] = resultTable.toList().get(i).get(j+1);
                    }
                }
                Object[] colName = resultTable.getKeyType().toArray();

                // store the data in a JTable
                resultText = new JTable(data,colName);
                // decorate the resultText with a JScrollPane to make it scrollable
                resultScrollPane.setViewportView(resultText);
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
                //TODO: show main panel
                //GUI.instance.setPanel(new mainPanel);
            }
        });

        lb = new JLabel("Result From Database");
        //lb.setBounds(30, 110, 450, 30);
        lb.setForeground(Color.red);
        lb.setFont(new Font("Serif", Font.BOLD, 20));

        resultScrollPane = new JScrollPane();
        resultScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        resultScrollPane.setVisible(false);  // false by default, set to true when the button is clicked

        //Add components to the panel
        add(queryLabel);
        add(inputText);
        add(searchBtn);
        add(backToMainBtn);
        add(lb);
        add(resultScrollPane);  // add the ScrollPane to the panel, not the textResult

        //Set the panel size
        setSize(1024, 768);

        //using spring layout for this panel
        spLayout = new SpringLayout();
        setLayout(spLayout);

        // put constraint on components
        // Ex: SpringLayout.WEST is the left edge of the component. The first putConstraint
        // means the left edge of queryLabel is 10 pixel far from the left edge of the panel (which is the left border of our window)
        // value(e1, c1) = value(e2, c2) + pad
        spLayout.putConstraint(SpringLayout.WEST,queryLabel,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,queryLabel,20,SpringLayout.NORTH,this);
        spLayout.putConstraint(SpringLayout.NORTH,inputText,15,SpringLayout.SOUTH,queryLabel);
        spLayout.putConstraint(SpringLayout.WEST,inputText,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,searchBtn,10,SpringLayout.SOUTH,inputText);
        spLayout.putConstraint(SpringLayout.WEST,searchBtn,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.WEST,backToMainBtn,30,SpringLayout.EAST,searchBtn);
        spLayout.putConstraint(SpringLayout.NORTH,backToMainBtn,10,SpringLayout.SOUTH,inputText);
        spLayout.putConstraint(SpringLayout.NORTH,lb,30,SpringLayout.SOUTH,backToMainBtn);
        spLayout.putConstraint(SpringLayout.WEST,lb,10,SpringLayout.WEST,this);
        spLayout.putConstraint(SpringLayout.NORTH,resultScrollPane,20,SpringLayout.SOUTH,lb);
        spLayout.putConstraint(SpringLayout.WEST,resultScrollPane,10,SpringLayout.WEST,instance);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // intentionally empty
    }
}
