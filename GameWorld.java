import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.Math;
import java.util.Arrays;

/**
 * World GameWorld
 * Main Game World
 *  
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 10, 2021
 */ 
public class GameWorld extends World {
    // World Dimensions
    public static final int WIDTH = 850;
    public static final int HEIGHT = 480;
    
    // Background Variables
    private GreenfootImage background;
    public static final GreenfootImage BG_IMAGE = MainMenu.BG_IMAGE;
    
    // Word Bank
    private ArrayList<String> nouns;
    private ArrayList<String> verbs;
    private ArrayList<String> adjectives;
    private ArrayList<ArrayList<String>> listOfWordTypes;
    
    private Queue<String> playerWordQueue; // The queue of words to display
    
    // Word Display Actors
    private WordBox wordBox;
    // The words that slide in and out of the screen
    private SlidingWord slideIn;
    private SlidingWord slideOut;
    private CorrectWordOverlay correctWordOverlay;
    private WrongWordOverlay wrongWordOverlay;
    
    // Time Tracking Variables (60 = 1 second)
    private StatBar timeBar;
    private float time = 300, speed; // Current time and how fast the timer should go down
    public static final int MAX_TIME = 300, TIME_BONUS = 120, TIME_PENALTY = 30;
    
    // Stat Sisplay Variables
    public static int score = 0;
    public static final int POINTS = 10; // Number of points the user gets for each correct word
    private StatDisplay statDisplay;
    private int gameTime; // Keep track of how long the game has been running
    
    // Game Variables
    private String currentWord; // The current word
    private String key; // String to keep track of what key the user typed
    private boolean spaceDown; // Boolean to keep track of if the user had released the spacebar
    private String playerInput; // String to keep track of the letters that the player has typed. Resets on new word
    private int numWrong; // Number of wrong letters the player has typed. Resets on new word
    // Ints to keep track of the number of right and wrong characters to calculate accuracy
    private int totalWrongChars;
    private int totalRightChars;
    
    // Sound Effects
    private GreenfootSound[] correctSounds, wrongSounds, achievementSounds;
    private int correctSoundIndex, wrongSoundIndex, achievementSoundIndex;
    
    int numMedals; // Int to keep track of the number of medals the user unlocked during the game

    /**
     * Constructor for objects of class MyWorld.
     */
    public GameWorld(float speed) {
        // Create a new world with WIDTH*HEIGHT cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1);
        
        // Drawing background
        background = new GreenfootImage(WIDTH, HEIGHT);
        background.drawImage(BG_IMAGE, 0, 0);
        setBackground(background);
        
        // Setting word lists
        listOfWordTypes = ReadWordFiles.readWordFiles();
        nouns = listOfWordTypes.get(0);
        verbs = listOfWordTypes.get(1);
        adjectives = listOfWordTypes.get(2);
        
        // Adding words to the queue
        playerWordQueue = new LinkedList<String>();
        String tmp = "";
        for (String i : generateWords(3)) {
            tmp = i;
            playerWordQueue.add(i + " ");
        }
        
        // Adding word display actors
        wordBox = new WordBox(new LinkedList<String>(playerWordQueue));
        slideIn = new SlidingWord();
        slideOut = new SlidingWord();
        slideIn.setSlidingWord(tmp, 296, 114, 115, false);
        correctWordOverlay = new CorrectWordOverlay();
        wrongWordOverlay = new WrongWordOverlay();
        addObject(wordBox, 425, 371);
        addObject(slideIn, 425, 240);
        addObject(slideOut, 425, 240);
        addObject(correctWordOverlay, 525, 472);
        addObject(wrongWordOverlay, 525, 472);
        
        // Setting the time and timer speed and adding the time bar
        time = MAX_TIME;
        this.speed = speed;
        timeBar = new StatBar((int)time, (int)time, WIDTH, HEIGHT / 20, 0, Color.GREEN, Color.WHITE, false, Color.BLACK, HEIGHT / 100);
        addObject(timeBar, WIDTH / 2, HEIGHT - HEIGHT / 40);
        
        // Setting the score and current game time and adding the stat display
        score = 0;
        gameTime = 0;
        statDisplay = new StatDisplay(score);
        addObject(statDisplay, statDisplay.WIDTH / 2, statDisplay.HEIGHT / 2);
        
        // Setting the game variables
        currentWord = playerWordQueue.remove();
        key = null;
        spaceDown = false;
        playerInput = "";
        numWrong = 0;
        totalRightChars = 0;
        totalWrongChars = 0;
        
        // Setting the number of medals unlocked during the game
        numMedals = 0;
        
        // Creating sound arrays
        correctSoundIndex = 0;
        correctSounds = new GreenfootSound[5];
        for(int i = 0; i < correctSounds.length; i++) correctSounds[i] = new GreenfootSound("Correct.wav");
        wrongSoundIndex = 0;
        wrongSounds = new GreenfootSound[15];
        for(int i = 0; i < wrongSounds.length; i++) wrongSounds[i] = new GreenfootSound("Wrong.wav");
        achievementSoundIndex = 0;
        achievementSounds = new GreenfootSound[10];
        for(int i = 0; i < achievementSounds.length; i++){
            achievementSounds[i] = new GreenfootSound("Achievement.wav");
            achievementSounds[i].setVolume(70); 
        }
    }
    
    /**
     * Act method. Handles general gameplay
     */
    public void act() {
        // Check if the user submitted the current word
        if(spaceDown != Greenfoot.isKeyDown("space")){
            spaceDown = !spaceDown;
            if(!spaceDown){
                checkWords(true, " ");
            }
        }
        // Check if the user entered a valid key
        else{
            if(key == null || key.equals("space")) key = Greenfoot.getKey();
            if(key != null && !key.equals("space")){
                if((key.length() == 1 && Character.isLetter(key.charAt(0))) || key.equals("backspace") || key.equals("-")){
                    checkWords(false, key);
                }
                key = null;
            }
        }
        
        // Check if the user unlocked any achievements and if they did, display the respective medals
        if(numMedals != StatDisplay.medalsUnlocked.size()){
            for(int i = numMedals; i < StatDisplay.medalsUnlocked.size(); i++){
                int medal = StatDisplay.medalsUnlocked.get(i);
                if(medal == 0) addObject(new Achievement(medal, true, true), WIDTH * 23 / 32, HEIGHT / 5);
                else if(medal == 3) addObject(new Achievement(medal, true, true), WIDTH * 23 / 32, HEIGHT * 2 / 5);
                else if(medal == 6) addObject(new Achievement(medal, true, true), WIDTH * 23 / 32, HEIGHT * 3 / 5);
                else if(medal == 1) addObject(new Achievement(medal, true, true), WIDTH * 7 / 8, HEIGHT * 3 / 10);
                else if(medal == 4) addObject(new Achievement(medal, true, true), WIDTH * 7 / 8, HEIGHT / 2);
                else if(medal == 7) addObject(new Achievement(medal, true, true), WIDTH * 7 / 8, HEIGHT * 7 / 10);
                else addObject(new Achievement(medal, true, true), WIDTH * 23 / 32, HEIGHT * 4 / 5);
                
                // Play sound effects
                achievementSounds[achievementSoundIndex].play();
                achievementSoundIndex++;
                if(achievementSoundIndex >= achievementSounds.length) achievementSoundIndex = 0;
            }
            numMedals = StatDisplay.medalsUnlocked.size();
        }
        
        time -= this.speed; // Update the time
        // Check if the timer ran out, and if it did, end the game
        if(time <= 0){
            Collections.sort(StatDisplay.medalsUnlocked);
            Greenfoot.setWorld(new EndScreen());
        }
        timeBar.update((int)time); // Update the time bar
        
        gameTime++; // Update the total game run time
    }
    
    /**
     * Handle the gameplay (checks for input and see if it matches the word)
     */
    public void checkWords(boolean enter, String key){
        numWrong = 0;
        // Checks if there is space in the word to add the new key, or deletes the previous key if the current key is a backspace
        if(playerInput.length() < currentWord.length()){
            if(key.equals("backspace") && playerInput.length() > 0) playerInput = playerInput.substring(0, playerInput.length() - 1);
            else if(playerInput.length() < currentWord.length() - 1 && !key.equals("backspace"))playerInput += key;
            else if(key.equals(" ")) playerInput += key;
        }
        // Counts the number of keys after (and including) the first wrong key
        for(int i = 0; i < playerInput.length(); i++){
            if(!Character.toString(playerInput.charAt(i)).equalsIgnoreCase(Character.toString(currentWord.charAt(i)))){
                numWrong = playerInput.length() - i;
                break;
            }
        }
        // Compares the user's input to the current word
        if(enter){ // Checks if the user entered the correct word
            // Correct word
            if(playerInput.equals(currentWord)){
                // Update time
                time += TIME_BONUS;
                if(time > MAX_TIME) time = MAX_TIME;
                // Update stats
                score += POINTS;
                totalRightChars++;
                // Play sound effects
                correctSounds[correctSoundIndex].play();
                correctSoundIndex++;
                if(correctSoundIndex >= correctSounds.length) correctSoundIndex = 0;
            }
            // Incorrect word
            else{
                time -= TIME_PENALTY; // Update time
                totalWrongChars++; // Update stats
                // Play sound effects
                wrongSounds[wrongSoundIndex].play();
                wrongSoundIndex++;
                if(wrongSoundIndex >= wrongSounds.length) wrongSoundIndex = 0;
            }
            // Updates visual effects
            // Updates the words on screen
            slideOut.setSlidingWord(currentWord, 296, 390, 500, false);
            String newWord = generateWords(1).get(0) + " ";
            playerWordQueue.add(newWord);
            slideIn.setSlidingWord(newWord, 296, 0, 115, false);
            wordBox.setWordBox(new LinkedList<String>(playerWordQueue));
            // Set the new current word and reset player input
            currentWord = playerWordQueue.remove();
            playerInput = "";
            // Reset word overlays
            correctWordOverlay.setCorrectOverlay("", 0, true);
            wrongWordOverlay.setWrongOverlay("", 0, 0, true);
            statDisplay.update(score, gameTime, totalRightChars, totalWrongChars); // Updates the stat display
        }
        else if(numWrong == 0){
            // Updates the word overlays
            correctWordOverlay.setCorrectOverlay(currentWord, playerInput.length(), false);
            wrongWordOverlay.setWrongOverlay("", playerInput.length() - numWrong, numWrong, false);
        }
        else if(numWrong > 0){
            // Updates the word overlays
            wrongWordOverlay.setWrongOverlay(currentWord, playerInput.length() - numWrong, numWrong, false);
        }
    }
    
    /**
     * Generate a random ArrayList of words
     * @param amount Amount of words to generate
     */
    public ArrayList<String> generateWords(int amount){
        ArrayList<String> list = new ArrayList<String>();
        // Choose which list to choose a word from randomly (nouns, verbs, adjectives)
        int random1 = (int) Math.floor(Math.random()*(2-0+1)+0);
        int length = listOfWordTypes.get(random1).size();
        for (int i = 0; i < amount; i++) {
            // Choose a random word from that list
            int random2 = (int) Math.floor(Math.random()*(length-1-0+1)+0);
            list.add(listOfWordTypes.get(random1).get(random2));
        }
        return list;
    }
}