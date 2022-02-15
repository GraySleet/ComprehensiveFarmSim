package FarmSim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Integer.parseInt;

class Framer {
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

    private boolean acceptingButton = true;//if we accept the button or not
    private boolean acceptingList = true;//if we accept the list or not

    private boolean farmTrue = false;//Farming, yes or no
    private boolean buyTrue = false;//Buying, yes or no

    private boolean fishTrue = false;//Fishing, yes or no
    private boolean craftTrue = false;//Crafting, yes or no

    private long timeLeft = 0;//How much time we have left until the next farming cycle starts.
    private long runningTimer;//Time each of the methods takes up.
    //Probably will take a tiny bit of millseconds more. So, I'll actually add 1 second to every process and 10 ms to Exploring.
    //we will have lots of nested whiles. its ok, because we aren't trying to search for something.


    private int fishingArea = 0;//Which fishing area we choose


    private int currSeeds = 0; //How many seeds we have (when we hit 0, we have to start buying!)
    private boolean mushroomTrue = false;

    DefaultListModel<String> data;//holds the list for our data
    private boolean cancelConcat = false;//If we cancel concatenation on our switch statement

    private boolean infiniteRun = true;
    private int amountCrafted = 0;

    //TODO: Special class for mushrooms.
    public void makeKey() {
        DataField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent b) {
                //super.keyTyped(e);

                //command 1 question 1
                if (b.getKeyCode() == KeyEvent.VK_ENTER) {
                    logic(DataField.getText(), currDataCode);
                }

            }
        });
    }

    public void logic(String response, String commandCode) {
        String totalCode;
        if (cancelConcat) {
            totalCode = commandCode;
        } else {
            totalCode = commandCode + response;
        }
        switch (totalCode) {
            case "start1":
                acceptingButton = false;
                acceptingList = false;
                requestText.append("\nActivate Farming? y/n");
                currDataCode = "11";
                makeKey();
                break;
            case "start3":
                acceptingButton = false;
                acceptingList = false;
                requestText.append("\nActivate Fishing? y/n");
                currDataCode = "31";
                makeKey();
                break;
            case "start4":
                acceptingButton = false;
                acceptingList = false;
                requestText.append("\nActivate Crafting? y/n");
                currDataCode = "41";
                makeKey();
                break;
            case "11y"://11 yes
                infoText.append("\nFarming Activated,");
                farmTrue = true;
                requestText.append("\nChoose 1 Pepper, 2 Carrot, 3 Hops, 4 Tomato, 5 Mushroom, 6 Corn");
                currDataCode = "12";//Command 1 Question 2
                //Should automatically invoke keyListener.
                break;
            case "11n"://11 no
                infoText.append("\nFarming Deactivated");
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
                break;
            case "123"://hops
                first = new Point(1408, 896);
                second = new Point(1487, 915);
                cropTime = 4 * 60 * 1000;
                scrollAmount = 2;
                thisCrop = new myCrop(first, second, scrollAmount, cropTime);
                requestText.append("\nWould you like to activate Buying? y/n");
                infoText.append("\ncurrCrop is hops");
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
                currDataCode = "13";
                break;
            case "125"://mushroom
                first = new Point(1404, 905);
                second = new Point(1502, 923);
                cropTime = 18 * 60 * 1000;
                scrollAmount = 13;

                mushroomTrue = true;

                thisCrop = new myCrop(first, second, scrollAmount, cropTime);
                infoText.append("\ncurrCrop is mushroom");
                requestText.append("\nWould you like to activate Buying? y/n");
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
                currDataCode = "13";

                break;
            case "13y"://yes to buying
                buyTrue = true;
                infoText.append("\nBuying Activated");
                requestText.append("\nHow many seeds do you currently have?");
                currDataCode = "14";
                cancelConcat = true;
                break;
            case "13n"://no to buying
                buyTrue = false;
                infoText.append("\nBuying Deactivated");
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
            case "31y":
                infoText.append("\nFishing Activated");
                fishTrue = true;
                requestText.append("\nWhere do you want to fish?");//TODO: Switch statement for fishing area
                currDataCode = "32";
                cancelConcat = true;
                break;

            case "31n":
                infoText.append("\nFishing Deactivated");
                acceptingList = true;
                acceptingButton = true;
                fishTrue = false;
                break;
            case "32":
                fishingArea = Integer.parseInt(response);
                infoText.append("\n Fishing area is" + fishingArea);//TODO: Implement from case 34
                requestText.append("\nFishing Configuration Complete");
                acceptingList = true;
                acceptingButton = true;
                cancelConcat = false;
                break;
            case "41y":
                craftTrue = true;
                infoText.append("\nCrafting Activated");
                requestText.append("\nYou will craft every hour, be sure to change the items when you dont want them anymore.");
                requestText.append("\nCrafting Configuration Complete");
                acceptingList = true;
                acceptingButton = true;
                cancelConcat = false;
                break;
            case "41":
                craftTrue = false;
                infoText.append("\n Crafting Deactivated");
                acceptingList = true;
                acceptingButton = true;
                break;
            case "61y"://This will be the run questions.
                infoText.append("\n Run has been accepted.");
                if (farmTrue) {
                    requestText.append("\nSince you've selected farming. How much time do you have left on your crop?");
                }//TODO
                try {
                    runStart();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
                cancelConcat = true;
                break;
            case "61n"://This will be the run questions.
                infoText.append("\nRun Cancelled");
                acceptingList = true;
                acceptingButton = true;
                cancelConcat = false;
                break;
            case "62":
                if (farmTrue) {
                    timeLeft = Long.parseLong(response);
                    infoText.append("\nYou have" + response + "milliseconds left on your crop.");
                }
                infoText.append("\nRunning in 10 seconds.");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    runStart();
                } catch (AWTException e) {
                    e.printStackTrace();
                }//Here we go, we are going to start the method
                cancelConcat = false;
            default:
                requestText.append("\nError, something happened. If you have a confirmation message, keep going");
                acceptingList = true;
                acceptingButton = true;
                cancelConcat = false;
        }
    }


    //TODO: FishingLocation method needs to be created.
//TODO: CraftingLocation method needs to be created.


    public Framer(boolean runFirst) {

        if (runFirst == true) {

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
                                logic("", "start1");
                                break;
                            case 1:
                                //Fishing
                                logic("", "start3");
                                break;
                            case 2:
                                break;
                            case 3:
                                //Crafting
                                logic("", "start4");
                                break;
                            case 4:
                                logic("", "start5");
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
                        currDataCode = "61";
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
        }


    }//end constructor]


    public void farming() throws AWTException {//This will include: Going (from Home) to the Farm, then clicking back to Home.//TODO

        Robot myRobot = new Robot();
        double myRandom;
        int myX;
        int myY;

        //Click Home
        //0,170 to 215,189
        //227,289 to 1510, 327
        //Click My Farm

        //Harvest All Crops. (259,205) --> (876,237)
        myRandom = Math.random();
        myX = (int) (259 + (876 - 259) * myRandom);
        myY = (int) (205 + (237 - 205) * myRandom);
        myRobot.mouseMove(myX, myY);
        myRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        myRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        timeLeft -= ((int) (1000 + 1000 * myRandom));
        myRobot.delay((int) (1000 + 1000 * myRandom));


        //Click Plant All Selected. (889,205 --> 1512, 237)
        myRandom = Math.random();
        myX = (int) (889 + (1512 - 889) * myRandom);
        myY = (int) (205 + (237 - 205) * myRandom);
        myRobot.mouseMove(myX, myY);
        myRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        myRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        timeLeft -= ((int) (1000 + 1000 * myRandom));
        myRobot.delay((int) (1000 + 1000 * myRandom));

        //Click "yes". (732,944 --> 1184, 972)
        myX = (int) (732 + (1184 - 732) * myRandom);
        myY = (int) (944 + (972 - 944) * myRandom);
        myRobot.mouseMove(myX, myY);
        myRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        myRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        myRandom = Math.random();
        timeLeft -= ((int) (1000 + 1000 * myRandom));
        myRobot.delay((int) (1000 + 1000 * myRandom));


    }

    public void fishing() throws AWTException {//TODO- Unfinished
        //while() here, removing time from each process, until 5 minutes are left
        //Need to find the timer. so: At what rate do fish spawn?
        //In which locations do fish spawn? Click all these locations.
        //Click the really fast bar/really slow bar..
        //wait X time for next fish
        //repeat

        //Times will be finicky.
        //First time- Clicking the blue bar.
        //Second time- Fish respawn time.

        //Plan: every 0.5 --> 1.5 seconds, we will sweep a click around all boxes.
        //We will assume we caught a fish; and click near the same area for 3 seconds.
        //Resume; start with Minnows, and then switch to Gummies, and then Worms. TODO
        //For now, we will just sweep click... this will happen once.

        Robot myRobot = new Robot();


        //sweep clicks. We don't need a "random" here, because the system can't detect unpressed clicks.

        for (int i = 740; i < 1100; i += 90) {
            for (int j = 300; j < 510; j += 70) {
                myRobot.mouseMove(i, j);
                myRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                myRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


            }

        }

        //ok, sweep is complete. Now, we will click.
        myRobot.delay(300);
        for (int i = 825; i < 1100; i += 12.5) {
            myRobot.mouseMove(i, 905);
            myRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            myRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        }

        myRobot.delay(1850);

        //hopefully we caught a fish... Regardless, we will loop this over and over.

    }

    public void mushroomCrafting() throws AWTException {//TODO
        //We want to craft mushrooms, so

        //click Home
        //click My Workshop
        //Scroll 10 times to Mushrooms
        //Click Craft on mushroom button
        //0,292 to 220,312
        //scroll down 13 times
        //1401,899 to 1500,926

    }


    public void sellAll() throws AWTException {
        //click Go into Town
        //click Farmers Market
        //click Sell All Unlocked
        //click Yes
        //click OK

    }

    public void buying() throws AWTException {//TODO
        //Click Home
        //0,154 to 219,290
        //Click Go To Town
        //231, 467 to 1508, 503
        //Click Country Store
        //232,284 to 1512, 327
        //Scroll x amount
        //Click on input box
        //input 1100, this is important, so...

        //Mushroom input...






        //click buy button
        //click yes
        //click OK




    }

    public void crafting() throws AWTException {
        //click Home
        //click Go into Workshop
        //this will be subject to change a lot.
        //scroll down 5
        //craft Iron Cup //every hour = full, so maybe every 10 minutes?
        //scroll 13
        //craft Twine
        //craft Rope
        //scroll down 4
        //Craft Bucket
        //scroll down 1
        //craft Yarn
        //maybe we even want fishing nets? idk
        //sell all afterwards (not in method)
    }

    public void enterFishing() throws AWTException {
        //click go Home
        //click Go Fishing
        //click which place we want to fish at (switch statement for this/fishing object)


    }


    public void runStart() throws AWTException {
        try {
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (farmTrue) {
            while (infiniteRun) {
                if (timeLeft > 0) {
                    try {
                        Thread.sleep(timeLeft);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                farming();
                timeLeft = 0;
                timeLeft += thisCrop.getCropTime();
                if (fishTrue) {
                    enterFishing();
                }
                while (timeLeft > 0) {
                    if (fishTrue) {//want this to run in 10 minute increments
                        long start = System.currentTimeMillis();
                        fishing();
                        timeLeft -= (System.currentTimeMillis() - start);
                    }

                    if ((int) (System.currentTimeMillis() / 100000) > amountCrafted && craftTrue) {//TODO: 10  minutes vs 1 hour.
                        //TODO: ask if we want 10 mins or 1 hour
                        long start = System.currentTimeMillis();
                        amountCrafted++;
                        crafting();
                        sellAll();
                        if (fishTrue) {
                            enterFishing();
                        }
                        timeLeft -= (System.currentTimeMillis() - start);
                    }
                    if (!fishTrue) {//if neither exploring or fishing is true
                        try {
                            Thread.sleep(timeLeft);
                            timeLeft = 0;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
                timeLeft = 0;

            }

        } else if (craftTrue) {
            while (infiniteRun) {
                crafting();
                long start = System.currentTimeMillis();
                sellAll();
                if (fishTrue) {
                    enterFishing();
                }
                timeLeft -= (System.currentTimeMillis() - start);
                while (timeLeft > 0) {
                    if (fishTrue) {//want this to run in 10 minute increments
                        start = System.currentTimeMillis();
                        fishing();
                        timeLeft -= (System.currentTimeMillis() - start);
                    } else {//neither expl or fishing is true
                        try {
                            Thread.sleep(timeLeft);
                            timeLeft = 0;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
                timeLeft = 0;
                timeLeft += 60 * 60 * 1000;//adds the time again so we can loop.

            }

        } else if (fishTrue) {//only fishing

            try {
                Robot myRobot = new Robot();
                enterFishing();
                int twentyTimes = 0;
                while (infiniteRun) {

                    twentyTimes++;
                    if (twentyTimes % 20 == 0) {
                        int x = (int) (1000 * Math.random());
                        myRobot.delay(x);
                    }
                    if (twentyTimes % 1300 == 0){
                        //buy more worms...
                    //home
                        //go to town
                        //country store
                        //20 times scroll
                        //click
                        //click yes
                        //click OK
                        //TODO: Error OK is 848,605 to 1073, 629

                    }
                    fishing();

                }
            }
            catch(AWTException e){
                e.printStackTrace();

            }


        } else {
            requestText.append("\nRun failed. No list was selected.");
        }

    }


    public void init() {

        JFrame frame = new JFrame("framer");
        try {
            frame.setContentPane(new Framer(true).MainPanel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //TODO: add new key binding to close the frame...
        //TODO: Closing this frame out of focus is going to be annoying.
        //TODO: the best solution is just to add a button.
        myList.setModel(data);


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here


        data = new DefaultListModel<>();


        data.add(0, "Farming");
        data.add(1, "Fishing");
        data.add(2, "Blank");
        data.add(3, "Crafting");

        myList = new JList<>(data);//a
    }

    public static void main(String[] args) {
        try {
            Framer f = new Framer(true);
            f.init();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}//end framer
