package FarmSim;

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;

import static java.lang.Integer.parseInt;

public class Framer {
    public JPanel MainPanel;
    public JTextArea infoText;

    public JTextArea helpText;
    public JTextField DataField;
    public JPanel Panel1;
    public JLabel Info;
    public JLabel Requests;
    public JLabel Help;
    public JLabel Data;
    public JList<String> myList;
    public JButton RunButton;
    public JTextArea requestText;

    public final LinkedList<String> answerContainer = new LinkedList<String>();

    public boolean acceptingAnswers = false;//if we accept answers or not
    public boolean acceptingButton = true;//if we accept the button or not
    public boolean acceptingList = true;//if we accept the list or not
    DefaultListModel<String> data;

    public void farmingSetup() throws Exception {

        DataField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent b) {
                //super.keyTyped(e);
                synchronized(answerContainer) {
                    if (b.getKeyCode() == KeyEvent.VK_ENTER && acceptingAnswers) {
                        answerContainer.add(DataField.getText());
                        answerContainer.notifyAll();
                    }
                }

            }
        });

        requestText.append("\ntestestestestest");
        synchronized(answerContainer){
            try{
                Thread.sleep(50);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }

            while(answerContainer.isEmpty()){
                answerContainer.wait();
            }

        }
        String answer = answerContainer.remove(0);
        requestText.append("\n Your answer is: " + answer);
        requestText.append("\nCrop type: 1 Pepper, 2 Carrot, 3 Hops, 4 Tomato, 5 Mushroom, 6 Corn, 7 Pine");

        requestText.append("\nWould you like to activate Buying? y/n");

        requestText.append("\nHow many seeds do you currently have? ");

    }

    //TODO: Need a method that lets us wait for a response.

    public void appendRequestText(String text) {
        requestText.append("\n" + text);

    }

    public void appendInfoText(String text) {
        infoText.append("\n" + text);

    }

    public void setAcceptingAnswers(boolean b) {
        acceptingAnswers = b;
    }

    public void setAcceptingButton(boolean b) {
        acceptingButton = b;

    }

    public void setAcceptingList(boolean b) {
        acceptingList = b;

    }

    public Framer() throws Exception{


        infoText.setEditable(false);
        requestText.setEditable(false);
        helpText.setEditable(false);

        helpText.append("Information: Press (`) to stop the program.");

        myList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // super.mouseClicked(e);
                if (e.getClickCount() == 2 && acceptingList) {
                    int selectedItem = myList.getSelectedIndex(); //Gets our index of what we clicked on the list
                    switch (selectedItem) {
                        case 0:
                            //farming
                            try {
                                farmingSetup();
                            }
                            catch(Exception a){
                                requestText.append("\nError, Framer class Exception");
                            }
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


        RunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acceptingButton) {
                    requestText.append("\nDo you want to start the program? y/n");

                    acceptingButton = false;
                    //runStart();
                }
            }
        });


    }//end constructor]

    public void runStart() {
        acceptingAnswers = true;

        DataField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent b) {
                //super.keyTyped(e);

                if (b.getKeyCode() == KeyEvent.VK_ENTER && acceptingAnswers) {
                    String currString = DataField.getText();
                }

            }
        });
    }


    public void init() {
        JFrame frame = new JFrame("framer");
        try {
            frame.setContentPane(new Framer().MainPanel);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // myList.setModel(data);


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here


        data = new DefaultListModel<>();


        data.add(0, "Farming");
        data.add(1, "Fishing");
        data.add(2, "Exploring");
        data.add(3, "Crafting");
        data.add(4, "QuickClicker");
        data.add(5, "MouseLocation");

        myList = new JList<>(data);//a
    }

    public static void main(String[] args) {
        try {
            Framer f = new Framer();
            f.init();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        //Maybe... T1 and T2:
        //T1 = keyListener
        //T2 = Framer
        //do something with that..? This is so, so confusing.



    }

}//end framer
