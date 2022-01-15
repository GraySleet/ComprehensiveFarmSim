package FarmSim;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Framer {
    private JPanel MainPanel;
    private JTextArea infoText;
    private JTextArea RequestText;
    private JTextArea helpText;
    private JTextField DataField;
    private JPanel Panel1;
    private JLabel Info;
    private JLabel Requests;
    private JLabel Help;
    private JLabel Data;
    private JList myList;
    private JButton RunButton;

    DefaultListModel data;

    public void farming(){
        RequestText.append("\nYou have requested Farming. Input y if you wish to continue");
        String currText = DataField.getText();
        System.out.println("currText");

    }

    public Framer() {
        infoText.setEditable(false);
        RequestText.setEditable(false);
        helpText.setEditable(false);


        myList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    int selectedItem = myList.getSelectedIndex(); //Gets our index of what we clicked on the list
                    switch (selectedItem) {
                        case 0:
                            //farming
                            farming();
                            break;

                        case 1:
                            //Fishing
                            break;

                        case 2:
                            //Exploring
                            break;

                        case 3:
                            //Crafting
                            break;

                        case 4:
                            //QuickClicker
                            break;

                        case 5:
                            //MouseLocation
                            break;

                    }
                }
            }
        });
    }//end constructor]


    public void init() {
        JFrame frame = new JFrame("framer");
        frame.setContentPane(new Framer().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // myList.setModel(data);


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        data = new DefaultListModel();


        data.add(0, "Farming");
        data.add(1, "Fishing");
        data.add(2, "Exploring");
        data.add(3, "Crafting");
        data.add(4, "QuickClicker");
        data.add(5, "MouseLocation");

        myList = new JList(data);
    }

    public static void main(String[] args) {
        Framer f = new Framer();
        f.init();


    }

}//end framer
