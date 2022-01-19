package FarmSim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import static java.lang.Integer.parseInt;

public class Framer {
    private JPanel MainPanel;
    private JTextArea infoText;

    private JTextArea helpText;
    private JTextField DataField;
    private JPanel Panel1;
    private JLabel Info;
    private JLabel Requests;
    private JLabel Help;
    private JLabel Data;
    private JList<String> myList;
    private JButton RunButton;
    private JTextArea requestText;

    private myCrop thisCrop;
    private String currDataCode;

    private boolean acceptingAnswers = false;//if we accept answers or not
    private boolean acceptingButton = true;//if we accept the button or not
    private boolean acceptingList = true;//if we accept the list or not

    private boolean farmTrue = false;
    private boolean buyTrue = false;
    private boolean exploringTrue = false;
    private boolean fishTrue = false;
    private boolean craftTrue = false;
    private boolean craftExploringTrue = false;



    private boolean cancelConcat = false;

    private String whattoCraft;

    private int fishingArea = 0;
    private int fishCount = 0;
    private int minnowCount = 0;
    private int gummyCount = 0;
    //dont need wormcount, can just buy at the beginning...
    private int currSeeds = 0;
    private double stamConsumptionCount = 0;
    DefaultListModel<String> data;


    public void makeKey() {
        DataField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent b) {
                //super.keyTyped(e);

                //command 1 question 1
                if (b.getKeyCode() == KeyEvent.VK_ENTER && acceptingAnswers) {
                    logic(DataField.getText(), currDataCode);
                    acceptingAnswers = true;//TODO
                }

            }
        });
    }

    public void farmingSetup() {//not hard.
        acceptingButton = false;
        acceptingList = false;
        requestText.append("\nActivate Farming? y/n");
        acceptingAnswers = true;
        currDataCode = "11";
        makeKey();
    }

    public void exploringSetup() {//assuming WC.
        acceptingButton = false;
        acceptingList = false;
        requestText.append("\nActivate Exploring? y/n");
        acceptingAnswers = true;
        currDataCode = "21";
        makeKey();

    }

    public void fishingSetup() {//lots of different fishing areas....
        acceptingButton = false;
        acceptingList = false;
        requestText.append("\nActivate Fishing? y/n");
        acceptingAnswers = true;
        currDataCode = "31";
        makeKey();

    }


    public void craftingSetup(){//TODO: We have crafting after exploring and crafting just normally.
        //TODO: This will change the most because we will have many different items to craft. AKA, the hardest.
        //I say we ask for a string of all items, abcdefg for example and then we choose which to craft based on that.
        acceptingButton = false;
        acceptingList = false;
        requestText.append("\nActivate Crafting? y/n");
        acceptingAnswers = true;
        currDataCode = "41";
        makeKey();
    }
    public void quickClickerSetup(){//TODO: Fix this later
        acceptingButton = false;
        acceptingList = false;
        requestText.append("\nActivate quickClicker? y/n, NOTE, quickClicker can only run by itself");
        acceptingAnswers = true;
        currDataCode = "51";
        makeKey();

    }

    public void logic(String response, String commandCode) {
        String totalCode;
        if (cancelConcat) {
            totalCode = commandCode;
        } else {
            totalCode = commandCode + response;
        }
        switch (totalCode) {
            case "11y"://11 yes
                infoText.append("\nFarming Activated,");
                farmTrue = true;
                requestText.append("\nChoose 1 Pepper, 2 Carrot, 3 Hops, 4 Tomato, 5 Mushroom, 6 Corn");
                currDataCode = "12";//Command 1 Question 2
                //Should automatically invoke keyListener.
                acceptingAnswers = true;
                break;
            case "11n"://11 no
                infoText.append("Farming Deactivated");
                acceptingList = true;
                acceptingButton = true;
                farmTrue = false;
                break;
            case "121"://pepper
                Point first = new Point(1420, 347);
                Point second = new Point(1496, 367);
                long cropTime = 12 * 1000;
                int scrollAmount = 0;
                thisCrop = new myCrop(first, second, scrollAmount, cropTime);
                requestText.append("\nWould you like to activate Buying? y/n");
                infoText.append("\ncurrCrop is pepper");
                acceptingAnswers = true;
                currDataCode = "13";


                break;
            case "122"://carrot
                first = new Point(1422, 451);
                second = new Point(1500, 472);
                cropTime = 24 * 1000;
                scrollAmount = 0;
                thisCrop = new myCrop(first, second, scrollAmount, cropTime);
                requestText.append("\nWould you like to activate Buying? y/n");
                infoText.append("\ncurrCrop is carrot");
                currDataCode = "13";
                acceptingAnswers = true;
                break;
            case "123"://hops
                first = new Point(1408, 896);
                second = new Point(1487, 915);
                cropTime = 4 * 60 * 1000;
                scrollAmount = 2;
                thisCrop = new myCrop(first, second, scrollAmount, cropTime);
                requestText.append("\nWould you like to activate Buying? y/n");
                infoText.append("\ncurrCrop is hops");
                acceptingAnswers = true;
                currDataCode = "13";
                break;
            case "124"://tomato
                first = new Point(1410, 911);
                second = new Point(1503, 932);
                cropTime = 6 * 60 * 1000;
                scrollAmount = 4;
                thisCrop = new myCrop(first, second, scrollAmount, cropTime);
                requestText.append("\nWould you like to activate Buying? y/n");
                infoText.append("\ncurrCrop is tomato");
                acceptingAnswers = true;
                currDataCode = "13";
                break;
            case "125"://mushroom
                first = new Point(1404, 905);
                second = new Point(1502, 923);
                cropTime = 18 * 60 * 1000;
                scrollAmount = 13;
                thisCrop = new myCrop(first, second, scrollAmount, cropTime);
                infoText.append("\ncurrCrop is mushroom");
                requestText.append("\nWould you like to activate Buying? y/n");
                acceptingAnswers = true;
                currDataCode = "13";

                break;
            case "126"://corn
                cropTime = 49 * 60 * 1000;
                scrollAmount = 7;
                first = new Point(1401, 942);
                second = new Point(1502, 966);
                thisCrop = new myCrop(first, second, scrollAmount, cropTime);
                infoText.append("\ncurrCrop is corn");
                requestText.append("\nWould you like to activate Buying? y/n");
                acceptingAnswers = true;
                currDataCode = "13";

                break;
            case "13y"://yes to buying
                buyTrue = true;
                infoText.append("\nBuying Activated");
                requestText.append("\nHow many seeds do you currently have?");
                acceptingAnswers = true;
                currDataCode = "14";
                cancelConcat = true;
                break;
            case "13n"://no to buying
                buyTrue = false;
                infoText.append("Buying Deactivated");
                acceptingList = true;
                acceptingButton = true;

                break;
            case "14":
                currSeeds = Integer.parseInt(response);
                requestText.append("\nFarming Configuration Complete");
                infoText.append("\ncurrSeeds is " + currSeeds);
                acceptingList = true;
                acceptingButton = true;
                cancelConcat = false;
                break;
            case "21y"://yes to exploring//TODO: Need to ask if we want to craft after exploring.
                infoText.append("\nExploring Activated");
                exploringTrue = true;
                requestText.append("\nHow much stam do you want to use?");
                currDataCode = "22";//Command 2 question 1
                acceptingAnswers = true;
                cancelConcat = true;
                break;
            case "21n"://no to exploring
                infoText.append("Exploring Deactivated");
                acceptingList = true;
                acceptingButton = true;
                exploringTrue = false;
                break;
            case "22":
                stamConsumptionCount = Integer.parseInt(response);
                requestText.append("\nExploring Configuration Complete");
                infoText.append("\n Stam usage amount is " + stamConsumptionCount);
                acceptingList = true;
                acceptingButton = true;
                cancelConcat = false;
                break;
            case"31y":
                infoText.append("\nFishing Activated");
                fishTrue = true;
                requestText.append("\nHow many Minnows do you have?");
                currDataCode = "32";//Command 2 question 1
                acceptingAnswers = true;
                cancelConcat = true;
                break;
            case"31n":
                infoText.append("Fishing Deactivated");
                acceptingList = true;
                acceptingButton = true;
                fishTrue = false;
                break;
            case"32":
                minnowCount = Integer.parseInt(response);
                requestText.append("\nHow many gummies do you have?");
                infoText.append("\n Minnow amount is" + minnowCount);
                currDataCode = "33";
                //dont need to cancelConcat
                break;
            case"33":
                gummyCount = Integer.parseInt(response);
                infoText.append("\n Gummy amount is" + gummyCount);
                requestText.append("\nHow much do you want to fish?");
                currDataCode = "34";
                break;
            case"34":
                fishCount = Integer.parseInt(response);
                infoText.append("\n Fish amount is" + fishCount);
                requestText.append("\nWhere do you want to fish? ");//TODO: Switch statement for fishing area
                currDataCode = "35";
                break;
            case"35":
                fishingArea = Integer.parseInt(response);
                infoText.append("\n Fishing area is" + fishingArea);//TODO: Implement from case 34
                requestText.append("\nFishing Configuration Complete");
                acceptingList = true;
                acceptingButton = true;
                cancelConcat = false;
                break;
            case"41y":
                craftTrue = true;
                infoText.append("\nCrafting Activated");
                requestText.append("\nWhat things do you want to craft?");//TODO: abcdef, choose what to craft
                cancelConcat = true;
                currDataCode = "42";
                break;
            case"41":
                craftTrue = false;
                infoText.append("\n Crafting Deactivated");
                acceptingList = true;
                acceptingButton = true;
                break;
            case"42":
                infoText.append("\nYour crafting items are:" + response);
                requestText.append("You will craft every hour.");
                requestText.append("\nCrafting Configuration Complete");
                acceptingList = true;
                acceptingButton = true;
                cancelConcat = false;
                break;
            case"51"://QuickClicker... TODO
                break;
            case"61y"://This will be the run questions.
                infoText.append("\nRunning in 10 seconds.");
                try{
                    Thread.sleep(10000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                runStart();//Here we go, we are going to start the method
                break;
            case"61n"://This will be the run questions.
                infoText.append("\nRun Cancelled");
                acceptingList = true;
                acceptingButton = true;
                cancelConcat = false;
                break;
            default:
                requestText.append("\nError, something happened. If you have a confirmation message, keep going");
                acceptingList = true;
                acceptingButton = true;
                acceptingAnswers = false;
                cancelConcat = false;
        }
    }


    public Framer() throws Exception {
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
                            farmingSetup();
                            break;
                        case 1:
                            //Fishing
                            fishingSetup();
                            break;
                        case 2:
                            //Exploring
                            exploringSetup();
                            break;
                        case 3:
                            //Crafting
                            craftingSetup();
                            break;
                        case 4:
                            quickClickerSetup();
                            //QuickClicker
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
                    acceptingList = false;
                    //runStart();
                }
            }
        });


    }//end constructor]

    public void runStart() {
        //Run/Timer methods here.

    }


    public void init() {
        JFrame frame = new JFrame("framer");
        try {
            frame.setContentPane(new Framer().MainPanel);
        } catch (Exception e) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}//end framer
