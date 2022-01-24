package FarmSim;

import com.sun.source.tree.AnnotatedTypeTree;

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
    private JButton closeButton;

    private myCrop thisCrop;
    private String currDataCode;

    private boolean acceptingAnswers = false;//if we accept answers or not
    private boolean acceptingButton = true;//if we accept the button or not
    private boolean acceptingList = true;//if we accept the list or not

    private boolean farmTrue = false;//Farming, yes or no
    private boolean buyTrue = false;//Buying, yes or no
    private boolean exploringTrue = false;//Exploring, yes or no
    private boolean fishTrue = false;//Fishing, yes or no
    private boolean craftTrue = false;//Crafting, yes or no
    private boolean craftExploringTrue = false;//Craft after exploring, yes or no
    private boolean clickerTrue = false;

    private long timeLeft = 0;//How much time we have left until the next farming cycle starts.
    private long runningTimer;//Time each of the methods takes up.
    //Probably will take a tiny bit of millseconds more. So, I'll actually add 1 second to every process and 10 ms to Exploring.
    //we will have lots of nested whiles. its ok, because we aren't trying to search for something.

    private String whattoCraft; //The string of what we craft , we will make a default class.

    private int fishingArea = 0;//Which fishing area we choose
    private int fishCount = 0; //how much fish we want to fish
    private int minnowCount = 0;//How many minnows we have
    private int gummyCount = 0;//How many gummies we have
    //dont need wormcount, can just buy at the beginning...

    private int currSeeds = 0; //How many seeds we have (when we hit 0, we have to start buying!)

    private double stamConsumptionCount = 0;//how much stamina we are allowed to consume

    DefaultListModel<String> data;//holds the list for our data
    private boolean cancelConcat = false;//If we cancel concatenation on our switch statement

    private boolean infiniteRun = true;

    //TODO: Special class for mushrooms.
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
                requestText.append("You will craft every hour, be sure to change the items when you dont want them anymore.");
                infoText.append("Current items crafting: Iron Cup, Twine, Rope, Yarn, Wood Plank, Sturdy Shield");
                requestText.append("\nCrafting Configuration Complete");
                acceptingList = true;
                acceptingButton = true;
                cancelConcat = false;
                break;
            case"41":
                craftTrue = false;
                infoText.append("\n Crafting Deactivated");
                acceptingList = true;
                acceptingButton = true;
                break;
            case"51"://QuickClicker... TODO
                break;
            case"61y"://This will be the run questions.
                infoText.append("\n Run has been accepted.");
                if(farmTrue){
                    requestText.append("\nSince you've selected farming. How much time do you have left on your crop?");
                }
                cancelConcat = true;
                break;
            case"61n"://This will be the run questions.
                infoText.append("\nRun Cancelled");
                acceptingList = true;
                acceptingButton = true;
                cancelConcat = false;
                break;
            case "62":
                if(farmTrue) {
                    timeLeft = Long.parseLong(response);
                    infoText.append("\nYou have" + response + "milliseconds left on your crop.");
                }
                infoText.append("\nRunning in 10 seconds.");
                try{
                    Thread.sleep(10000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                try {
                    runStart();
                }
                catch(AWTException e){
                    e.printStackTrace();
                }//Here we go, we are going to start the method
                cancelConcat = false;
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


        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }//end constructor]

    public void farming() throws AWTException{//This will include: Going (from Home) to the Farm, then clicking back to Home.//TODO

        Robot myRobot = new Robot();
        double myRandom;
        int myX;
        int myY;

        //Click Home

        //Click My Farm

        //Harvest All Crops. (259,205) --> (876,237)
        myRandom = Math.random();
        myX = (int) (259 + (876 - 259) * myRandom);
        myY = (int) (205 + (237 - 205) * myRandom);
        myRobot.mouseMove(myX, myY);
        myRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        myRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        timeLeft -=  ((int) (1000 + 1000 * myRandom));
        myRobot.delay((int) (1000 + 1000 * myRandom));


        //Click Plant All Selected. (889,205 --> 1512, 237)
        myRandom = Math.random();
        myX = (int) (889 + (1512 - 889) * myRandom);
        myY = (int) (205 + (237 - 205) * myRandom);
        myRobot.mouseMove(myX, myY);
        myRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        myRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        timeLeft -=  ((int) (1000 + 1000 * myRandom));
        myRobot.delay((int) (1000 + 1000 * myRandom));

        //Click "yes". (732,944 --> 1184, 972)
        myX = (int) (732 + (1184 - 732) * myRandom);
        myY = (int) (944 + (972 - 944) * myRandom);
        myRobot.mouseMove(myX, myY);
        myRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        myRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        myRandom = Math.random();
        timeLeft -=  ((int) (1000 + 1000 * myRandom));
        myRobot.delay((int) (1000 + 1000 * myRandom));


    }

    public void mushroomCrafting() throws AWTException{//TODO
        //We want to craft mushrooms, so

        //click Home
        //click My Workshop
        //Scroll 10 times to Mushrooms
        //Click Craft on mushroom button


    }

    public void exploringCrafting() throws AWTException{
        //click Home
        //click My Workshop
        //click Fancy Drum
        //click Garnet
        //scroll down 1
        //click Garnet Ring
        //scroll down 13 more
        //click Salt
    }

    public void sellAll() throws AWTException{
        //click Go into Town
        //click Farmers Market
        //click Sell All Unlocked
        //click Yes
        //click OK

    }

    public void buying() throws AWTException{//TODO
    //Click Home
        //Click Go To Town
        //Click Country Store
        //Scroll x amount
        //Click on input box
        //input 1100
        //click buy button
        //click yes
        //click OK
    }

    public void quickClicker() throws AWTException{
        //turns everything off
        //clicks really fast (with variation)

    }

    public void crafting() throws AWTException{
        //click Home
        //click Go into Workshop
        //this will be subject to change a lot.
        //scroll down 5
        //craft Iron Cup //every hour = full, so maybe every 10 minutes?
        //scroll 13
        //craft Twine
        //craft Rope
        //scroll down 4
        //craft Wood Plank
        //craft Sturdy Shield
        //scroll down 1
        //craft Yarn
        //maybe we even want fishing nets? idk

    }

    public void fishing() throws AWTException{
        //click go Home
        //click Go Fishing
        //click which place we want to fish at (switch statement for this/fishing object)
        //while() here, removing time from each process, until 5 minutes are left
        //Need to find the timer. so: At what rate do fish spawn?
        //In which locations do fish spawn? Click all these locations.
        //Click the really fast bar/really slow bar..
        //wait X time for next fish
        //repeat

    }

    public void exploring() throws AWTException{
        //click GO Home
        //click Explore the Area
        //Click Whispering Creek
        //while here, 5 minutes
        //CLick at about 175 average clicks/sec, removing X amount of stamina each click.
        //Once we get to x stamina, go and craft, or if it decreases below 5 minutes.
        //explore again, until we are out of stamina.
        //repeat


    }

    public void runStart() throws AWTException{
        //Run/Timer methods here.
        //Logic- We want fishing, farming, crafting to all run at the same time.
        /*
        Farming is definitely first. So, we take the time left of the crop. let's say 9 hours. We subtract
        by the amount that we used with randomization. Maybe add 1 second.
        If it's greater than 5 minutes, we will run Crafting.
        Subtract once after all crafting processes have finished.
        If it's still greater than 5 minutes, we will run Exploring.
        Subtract once, every time we run Exploring. (one click). We do some math with the stamina here, and the fact
        that we need to budget at least 5 minutes to craft the stuff we explored for, as well as eat all apples.
        OK, finally, we go to fishing, and use up the rest of our time there, until we're about 5 minutes off. Then, we wait
        for Farming to continue. Loop this entire thing.
         */

            //check for each method.
            //if farmingTrue; we base around the loop of cropTimer. TimerTask farming each 18 minutes, for example.
            //if CraftingTrue; and not farmingTrue; we base around the loop of each hour. TimerTask Crafting each hour, for example.
            //After both farming/Crafting has been satisfied, we timerTask Exploring for X amount of times based on how much time we have left.
            //Fishing, too.
            //Have to implement timerTask.
            //Different things: if ONLY farmTrue, ONLY exploring, ONLY fishing, ONLY crafting.

        //THus, there are 3 possibilities. 1: Farming. 2: Crafting, no farming. 3: none of them.
        //1's timer will be based off of farming. 2's timer would be based off of crafting (1 hour). 3's timer would be based off of expl/fishing.

        if(farmTrue){
            while(infiniteRun) {
                while (timeLeft > 0) {
                    //for each step- subtract timeLeft. we want every 10 minutes? for farming. So,
                    /*
                    if we start at t = 60 minutes. When t = 0, we run Crafting.
                    Let's say Farming takes up... 20 minutes.
                    if we start long millis

                    long start = System.currentTimeMillis();
// some time passes
long end = System.currentTimeMillis();
long elapsedTime = end - start;
                    end-start; so we mod 60 and find the number we modded. If it's greater than craftCount(which counts how many times we ran Crafting
                    then we will run crafting again. For example.
                     */
                    //go to farm
                    //farming
                    //if its mushrooms we craft it up
                    // if crafting we craft it up
                    //if exploring we run stam dry until timeLeft = 5 minutes or stam <=0
                    //craft up exploring, save time for this
                    //if exploring is false we will then go fishing for remainder of the time
                    //loop all

                }
                timeLeft = 0;
                timeLeft += thisCrop.getCropTime();//adds the time again so we can loop.

            }

        }
        else if(craftTrue){
            while(infiniteRun) {
                while (timeLeft > 0) {
                    //for each step- subtract timeLeft.
                    // if crafting we craft it up
                    //if exploring we run stam dry until timeLeft = 5 minutes or stam <=0
                    //if exploring is false we will then go fishing for remainder of the time
                    //loop all

                }
                timeLeft = 0;
                timeLeft += 60 * 60 * 1000;//adds the time again so we can loop.

            }

        }
        else if(clickerTrue){
            //loop check if exploring is true
            //if its false then we just run fishing
            //do this forever
        }
        else{//only expl/fishing

        }


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
        //TODO: add new key binding to close the frame...
        //TODO: Closing this frame out of focus is going to be annoying.
        //TODO: the best solution is just to add a button.
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
