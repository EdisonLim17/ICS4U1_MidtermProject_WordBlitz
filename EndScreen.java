import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * World EndScreen
 * Game Over Screen
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 10, 2021
 */
public class EndScreen extends World
{
    // World Dimensions
    public static final int WIDTH = GameWorld.WIDTH;
    public static final int HEIGHT = GameWorld.HEIGHT;
    
    // Background Variables
    private GreenfootImage background;
    public static final GreenfootImage BG_IMAGE = MainMenu.BG_IMAGE;
    public static final Color TITLE_COLOR = MainMenu.TITLE_COLOR;
    public static final Font TITLE_FONT = MainMenu.TITLE_FONT;
    private String title = "GAME OVER";
    public static final Color SCORE_COLOR = InstructionsMenu.INSTRUCTIONS_COLOR;
    public static final Font SCORE_FONT = MainMenu.HIGHSCORE_FONT;
    private String score;
    private String highscore = "NEW HIGHSCORE!";
    
    // Button Variables
    private Button returnButton;
    private GreenfootSound clickSound = new GreenfootSound("Menu Click.wav");
    
    private ArrayList<Integer> medals; // ArrayList to keep track of what medals the user has unlocked during the game
    
    /**
     * Constructor for objects of class EndScreen.
     */
    public EndScreen()
    {    
        // Create a new world with WIDTH*HEIGHT cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1);
        
        score = "SCORE: " + Integer.toString(GameWorld.score); // Get score
        
        medals = StatDisplay.medalsUnlocked; // Get medals
        
        // Drawing background
        background = new GreenfootImage(WIDTH, HEIGHT);
        background.drawImage(BG_IMAGE, 0, 0);
        background.setColor(TITLE_COLOR);
        background.setFont(TITLE_FONT);
        background.drawString(title, (getWidth() - (int)(title.length() * TITLE_FONT.getSize() * 0.6)) / 2, getHeight() / 7);
        background.setColor(SCORE_COLOR);
        background.setFont(SCORE_FONT);
        background.drawString(score, (getWidth() - (int)(score.length() * SCORE_FONT.getSize() * 0.6)) / 2, getHeight() / 4);
        // Gets the user's info and checks if they beat their highscore, if they did, set their new highscore
        if(UserInfo.isStorageAvailable()){
            MainMenu.user = UserInfo.getMyInfo();
            if( Difficulty.gameDifficulty == Difficulty.EASY && GameWorld.score > Integer.parseInt(MainMenu.user.getString(0))){
                background.drawString(highscore, (getWidth() - (int)(highscore.length() * SCORE_FONT.getSize() * 0.6)) / 2, getHeight() * 5 / 14);
                MainMenu.user.setString(0, Integer.toString(GameWorld.score));
            }
            else if(Difficulty.gameDifficulty == Difficulty.NORMAL && GameWorld.score > Integer.parseInt(MainMenu.user.getString(1))){
                background.drawString(highscore, (getWidth() - (int)(highscore.length() * SCORE_FONT.getSize() * 0.6)) / 2, getHeight() * 5 / 14);
                MainMenu.user.setString(1, Integer.toString(GameWorld.score));
            }
            else if(Difficulty.gameDifficulty == Difficulty.HARD && GameWorld.score > Integer.parseInt(MainMenu.user.getString(2))){
                background.drawString(highscore, (getWidth() - (int)(highscore.length() * SCORE_FONT.getSize() * 0.6)) / 2, getHeight() * 5 / 14);
                MainMenu.user.setString(2, Integer.toString(GameWorld.score));
            }
            MainMenu.user.setInt(9, MainMenu.user.getInt(9) + 1); // Increase the total amount of games they played by one
            MainMenu.user.store();
            if(MainMenu.user.getInt(9) >= 100) medals.add(9);
        }
        if(medals.size() > 0) background.drawString("NEW ACHIEVEMENTS!", (getWidth() - (int)(17 * SCORE_FONT.getSize() * 0.6)) / 2, getHeight() * 6 / 7);
        setBackground(background);
        
        // Adds the medals that the user just unlocked to the screen
        addMedals();
        
        // Adding button
        returnButton = new Button("Return", Color.BLACK, TITLE_COLOR, Color.WHITE, Color.YELLOW, Color.RED);
        addObject(returnButton, WIDTH / 7, HEIGHT * 9 /10);
    }
    
    /**
     * Display the medals that the user just unlocked
     */
    private void addMedals(){
        int numMedals = medals.size();
        
        if(numMedals == 1){
            addObject(new Achievement(medals.get(0), true, false), WIDTH / 2, HEIGHT * 17 / 28);
        }
        else if(numMedals == 2){
            int x = 2;
            for(int i = 0; i < 2; i++){
                addObject(new Achievement(medals.get(i), true, false), WIDTH * x / 5, HEIGHT * 17 / 28);
                x++;
            }
        }
        else if(numMedals == 3){
            int x = 3;
            for(int i = 0; i < 3; i++){
                addObject(new Achievement(medals.get(i), true, false), WIDTH * x / 10, HEIGHT * 17 / 28);
                x += 2;
            }
        }
        else if(numMedals == 4){
            int x = 1;
            for(int i = 0; i < 4; i++){
                addObject(new Achievement(medals.get(i), true, false), WIDTH * x / 5, HEIGHT * 17 / 28);
                x++;
            }
        }
        else if(numMedals == 5){
            int x = 1;
            for(int i = 0; i < 5; i++){
                addObject(new Achievement(medals.get(i), true, false), WIDTH * x / 10, HEIGHT * 17 / 28);
                x += 2;
            }
        }
        else if(numMedals == 6){
            int x = 3;
            for(int i = 0; i < 3; i++){
                addObject(new Achievement(medals.get(i), true, false), WIDTH * x / 10, HEIGHT * 1 / 2);
                x += 2;
            }
            x = 3;
            for(int i = 3; i < 6; i++){
                addObject(new Achievement(medals.get(i), true, false), WIDTH * x / 10, HEIGHT * 5 / 7);
                x += 2;
            }
        }
        else if(numMedals == 7){
            int x = 3;
            for(int i = 0; i < 3; i++){
                addObject(new Achievement(medals.get(i), true, false), WIDTH * x / 10, HEIGHT * 1 / 2);
                x += 2;
            }
            x = 1;
            for(int i = 3; i < 7; i++){
                addObject(new Achievement(medals.get(i), true, false), WIDTH * x / 5, HEIGHT * 5 / 7);
                x++;
            }
        }
        else if(numMedals == 8){
            int x = 1;
            for(int i = 0; i < 4; i++){
                addObject(new Achievement(medals.get(i), true, false), WIDTH * x / 5, HEIGHT * 1 / 2);
                x++;
            }
            x = 1;
            for(int i = 4; i < 8; i++){
                addObject(new Achievement(medals.get(i), true, false), WIDTH * x / 5, HEIGHT * 5 / 7);
                x++;
            }
        }
        else if(numMedals == 9){
            int x = 1;
            for(int i = 0; i < 4; i++){
                addObject(new Achievement(medals.get(i), true, false), WIDTH * x / 5, HEIGHT * 1 / 2);
                x++;
            }
            x = 1;
            for(int i = 4; i < 9; i++){
                addObject(new Achievement(medals.get(i), true, false), WIDTH * x / 10, HEIGHT * 5 / 7);
                x += 2;
            }
        }
        else if(numMedals == 10){
            int x = 1;
            for(int i = 0; i < 5; i++){
                addObject(new Achievement(medals.get(i), true, false), WIDTH * x / 10, HEIGHT * 1 / 2);
                x += 2;
            }
            x = 1;
            for(int i = 5; i < 10; i++){
                addObject(new Achievement(medals.get(i), true, false), WIDTH * x / 10, HEIGHT * 5 / 7);
                x += 2;
            }
        }
    }
    
    /**
     * Act method. Checks for button press
     */
    public void act(){
        // Takes the user back to the main menu
        if(Greenfoot.mouseClicked(returnButton) || (returnButton.isHovering() && Greenfoot.isKeyDown("space"))){
            clickSound.play();
            Greenfoot.setWorld(new MainMenu());
        }
    }
}
