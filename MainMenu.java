import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * World Main Menu
 * Contains Buttons To Start Game, Change Difficulty, Go to World InstructionsMenu, Go to World AchievementsPage, And Displays Highscores
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 10, 2021
 */
public class MainMenu extends World
{
    // World Dimensions
    public static final int WIDTH = GameWorld.WIDTH;
    public static final int HEIGHT = GameWorld.HEIGHT;
    
    // Background Variables
    private GreenfootImage background;
    public static final GreenfootImage BG_IMAGE = new GreenfootImage("MenuBackground.jpg");
    public static final Color TITLE_COLOR = new Color(255, 0, 255);
    public static final Font TITLE_FONT = new Font("Courier New", true, false, HEIGHT / 10);
    private String title = "Word Blitz";
    public static final Color EASY_HS_COLOR = new Color(0, 255, 0);
    public static final Color NORMAL_HS_COLOR = new Color(255, 255, 0);
    public static final Color HARD_HS_COLOR = new Color(255, 0, 0);
    public static final Font HIGHSCORE_FONT = new Font("Courier New", true, false, HEIGHT / 20);
    private String easyHighscore, normalHighscore, hardHighscore;
    
    // Button Variables
    private Button startButton, difficultyButton, instructionsButton, achievementsButton;
    private GreenfootSound clickSound = new GreenfootSound("Menu Click.wav");
    private GreenfootSound errorSound = new GreenfootSound("Wrong.wav");
    private int buttonDelay = 0; // the delay before the difficulty button can be pressed again
    
    // Background Music Variables
    private GreenfootSound backgroundMusic = new GreenfootSound("BackgroundMusic.mp3");
    boolean musicStarted = false;
    
    // Variables to get mouse info and user info
    private MouseInfo mouse;
    public static UserInfo user;
    
    private Difficulty currentDifficulty = Difficulty.NOT_SET; // Variable to keep track of the what difficulty the user chooses
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MainMenu()
    {   
        // Create a new world with WIDTH*HEIGHT cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1);
        
        // Get the user's info and sets highscores as 0 is there is no info
        if(UserInfo.isStorageAvailable()){
            user = UserInfo.getMyInfo();
            
            //for(int i = 0; i < 3; i++) MainMenu.user.setString(i, "0");
            //MainMenu.user.store();
            //uncomment the two lines above to reset the user's highscore
            
            //for(int i = 0; i < 10; i++) MainMenu.user.setInt(i, 0);
            //MainMenu.user.store();
            //uncomment the two lines above to reset the user's achievements
            
            // Updates highscores
            easyHighscore = "EASY HIGHSCORE: " + user.getString(0);
            normalHighscore = "NORMAL HIGHSCORE: " + user.getString(1);
            hardHighscore = "HARD HIGHSCORE: " + user.getString(2);
        }
        else{
            easyHighscore = "EASY HIGHSCORE: 0" ;
            normalHighscore = "NORMAL HIGHSCORE: 0";
            hardHighscore = "HARD HIGHSCORE: 0";
        }
        
        // Drawing background
        background = new GreenfootImage(WIDTH, HEIGHT);
        background.drawImage(BG_IMAGE, 0, 0);
        background.setColor(TITLE_COLOR);
        background.setFont(TITLE_FONT);
        background.drawString(title, (getWidth() - (int)(title.length() * TITLE_FONT.getSize() * 0.6)) / 2, getHeight() / 7);
        background.setFont(HIGHSCORE_FONT);
        background.setColor(EASY_HS_COLOR);
        background.drawString(easyHighscore, (getWidth() - (int)(easyHighscore.length() * HIGHSCORE_FONT.getSize() * 0.6)) / 2, getHeight() * 2 / 7);
        background.setColor(NORMAL_HS_COLOR);
        background.drawString(normalHighscore, (getWidth() - (int)(normalHighscore.length() * HIGHSCORE_FONT.getSize() * 0.6)) / 2, getHeight() * 5 / 14);
        background.setColor(HARD_HS_COLOR);
        background.drawString(hardHighscore, (getWidth() - (int)(hardHighscore.length() * HIGHSCORE_FONT.getSize() * 0.6)) / 2, getHeight() * 3 / 7);
        setBackground(background);
        
        //Adding buttons
        startButton = new Button("Start Game", Color.BLACK, TITLE_COLOR, Color.WHITE, Color.YELLOW, Color.RED);
        addObject(startButton, WIDTH / 2, (int)(HEIGHT * 3 / 5));
        difficultyButton = new Button("Difficulty", Color.BLACK, TITLE_COLOR, Color.WHITE, Color.BLUE, Color.RED);
        addObject(difficultyButton, WIDTH / 2, (int)(HEIGHT * 3 / 4));
        instructionsButton = new Button("How To Play", Color.BLACK, TITLE_COLOR, Color.WHITE, Color.YELLOW, Color.RED);
        addObject(instructionsButton, WIDTH / 7, (int)(HEIGHT * 9 / 10));
        achievementsButton = new Button("Achievements", Color.BLACK, TITLE_COLOR, Color.WHITE, Color.YELLOW, Color.RED);
        addObject(achievementsButton, WIDTH * 6 / 7, (int)(HEIGHT * 9 / 10));
    }
    
    /**
     *  Start playing the background music in a loop when the game starts
     */
    public void started(){
        if(!musicStarted){
            backgroundMusic.setVolume(30);
            backgroundMusic.playLoop();
            musicStarted = true;
        }
    }
    
    /**
     * Act method. Checks for button presses to change worlds
     */
    public void act(){
        mouse = Greenfoot.getMouseInfo();
        
        // Start the game with the set current difficulty
        if((Greenfoot.mouseClicked(startButton) || (startButton.isHovering() && Greenfoot.isKeyDown("space"))) && currentDifficulty != Difficulty.NOT_SET){
            clickSound.play();
            Greenfoot.setWorld(new GameWorld(currentDifficulty.speed));
        }
        // Lets the user know that they haven't set the difficulty yet
        else if((Greenfoot.mouseClicked(startButton) || (startButton.isHovering() && Greenfoot.isKeyDown("space"))) && currentDifficulty == Difficulty.NOT_SET){
            errorSound.play();
            difficultyButton.flash(30);
        }
        // Changes the difficulty
        else if (Greenfoot.mouseClicked(difficultyButton) || (difficultyButton.isHovering() && Greenfoot.isKeyDown("space"))){
            if(buttonDelay == 0){
                clickSound.play();
                changeDifficulty();
                buttonDelay = 10;
            }
        }
        // Takes the user to the instructions menu
        else if(Greenfoot.mouseClicked(instructionsButton) || (instructionsButton.isHovering() && Greenfoot.isKeyDown("space"))){
            clickSound.play();
            Greenfoot.setWorld(new InstructionsMenu());
        }
        // Takes the user to their achievements page
        else if(Greenfoot.mouseClicked(achievementsButton) || (achievementsButton.isHovering() && Greenfoot.isKeyDown("space"))){
            clickSound.play();
            Greenfoot.setWorld(new AchievementsPage());
        }
        if(buttonDelay > 0) buttonDelay--;
    }
    
    /**
     *  Handles the difficulty and difficulty button changes
     */
    public void changeDifficulty() {
        switch(currentDifficulty) {
            case EASY:
                difficultyButton.update("Normal");
                difficultyButton.changeColor(Color.BLACK, Color.YELLOW);
                currentDifficulty = Difficulty.NORMAL;
                currentDifficulty.gameDifficulty = Difficulty.NORMAL;
                break;
            case NORMAL:
                difficultyButton.update("Hard");
                difficultyButton.changeColor(Color.BLACK, Color.RED);
                currentDifficulty = Difficulty.HARD;
                currentDifficulty.gameDifficulty = Difficulty.HARD;
                break;
            case HARD:
                difficultyButton.update("Easy");
                difficultyButton.changeColor(Color.BLACK, Color.GREEN);
                currentDifficulty = Difficulty.EASY;
                currentDifficulty.gameDifficulty = Difficulty.EASY;
                break;
            default:
                difficultyButton.update("Easy");
                currentDifficulty = Difficulty.EASY;
                difficultyButton.changeColor(Color.BLACK, Color.GREEN);
                currentDifficulty.gameDifficulty = Difficulty.EASY;
                break;
        }
    }
}
