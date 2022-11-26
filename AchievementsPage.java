import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * World AchievementsPage
 * Screen Displaying Achievements
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 10, 2021
 */
public class AchievementsPage extends World
{
    // World Dimensions
    public static final int WIDTH = GameWorld.WIDTH;
    public static final int HEIGHT = GameWorld.HEIGHT;
    
    // Background Variables
    private GreenfootImage background;
    public static final GreenfootImage BG_IMAGE = MainMenu.BG_IMAGE;
    public static final Color TITLE_COLOR = MainMenu.TITLE_COLOR;
    public static final Font TITLE_FONT = MainMenu.TITLE_FONT;
    private String title = "Achievements";
    public static final Color ACHIEVEMENTS_COLOR = InstructionsMenu.INSTRUCTIONS_COLOR;
    public static final Font ACHIEVEMENTS_FONT = MainMenu.HIGHSCORE_FONT;
    
    // Button Variables
    private Button backButton;
    private GreenfootSound clickSound = new GreenfootSound("Menu Click.wav");
    
    /**
     * Constructor for objects of class AchievementsMenu.
     */
    public AchievementsPage()
    {    
        // Create a new world with WIDTH*HEIGHT cells with a cell size of 1x1 pixels.
        super(WIDTH, HEIGHT, 1);
        
        // Drawing background
        background = new GreenfootImage(WIDTH, HEIGHT);
        background.drawImage(BG_IMAGE, 0, 0);
        background.setColor(TITLE_COLOR);
        background.setFont(TITLE_FONT);
        background.drawString(title, (getWidth() - (int)(title.length() * TITLE_FONT.getSize() * 0.6)) / 2, getHeight() / 7);
        background.setColor(ACHIEVEMENTS_COLOR);
        background.setFont(ACHIEVEMENTS_FONT);
        setBackground(background);
        
        // Gets the user's info and displays their achievements
        if(UserInfo.isStorageAvailable()){
            if(MainMenu.user.getInt(0) == 0){ //70 wpm at 200 points
                Achievement bronze1 = new Achievement(0, false, false);
                addObject(bronze1, WIDTH / 5, HEIGHT * 27 / 100);
            }
            else if(MainMenu.user.getInt(0) == 1){
                Achievement bronze1 = new Achievement(0, true, false);
                addObject(bronze1, WIDTH / 5, HEIGHT * 27 / 100);
            }
            if(MainMenu.user.getInt(1) == 0){ //95% accuracy at 200 points
                Achievement bronze2 = new Achievement(1, false, false);
                addObject(bronze2, WIDTH / 2, HEIGHT * 27 / 100);
            }
            else if(MainMenu.user.getInt(1) == 1){
                Achievement bronze2 = new Achievement(1, true, false);
                addObject(bronze2, WIDTH / 2, HEIGHT * 27 / 100);
            }
            if(MainMenu.user.getInt(2) == 0){ //1000 points on easy
                Achievement bronze3 = new Achievement(2, false, false);
                addObject(bronze3, WIDTH * 4 / 5, HEIGHT * 27 / 100);
            }
            else if(MainMenu.user.getInt(2) == 1){
                Achievement bronze3 = new Achievement(2, true, false);
                addObject(bronze3, WIDTH * 4 / 5, HEIGHT * 27 / 100);
            }
            if(MainMenu.user.getInt(3) == 0){ //100 wpm at 200 points
                Achievement silver1 = new Achievement(3, false, false);
                addObject(silver1, WIDTH / 5, HEIGHT * 283 / 600);
            }
            else if(MainMenu.user.getInt(3) == 1){
                Achievement silver1 = new Achievement(3, true, false);
                addObject(silver1, WIDTH / 5, HEIGHT * 283 / 600);
            }
            if(MainMenu.user.getInt(4) == 0){ //97% accuracy at 200 points
                Achievement silver2 = new Achievement(4, false, false);
                addObject(silver2, WIDTH / 2, HEIGHT * 283 / 600);
            }
            else if(MainMenu.user.getInt(4) == 1){
                Achievement silver2 = new Achievement(4, true, false);
                addObject(silver2, WIDTH / 2, HEIGHT * 283 / 600);
            }
            if(MainMenu.user.getInt(5) == 0){ //1000 points on normal
                Achievement silver3 = new Achievement(5, false, false);
                addObject(silver3, WIDTH * 4 / 5, HEIGHT * 283 / 600);
            }
            else if(MainMenu.user.getInt(5) == 1){
                Achievement silver3 = new Achievement(5, true, false);
                addObject(silver3, WIDTH * 4 / 5, HEIGHT * 283 / 600);
            }
            if(MainMenu.user.getInt(6) == 0){ //130 wpm at 200 points
                Achievement gold1 = new Achievement(6, false, false);
                addObject(gold1, WIDTH / 5, HEIGHT * 101 / 150);
            }
            else if(MainMenu.user.getInt(6) == 1){
                Achievement gold1 = new Achievement(6, true, false);
                addObject(gold1, WIDTH / 5, HEIGHT * 101 / 150);
            }
            if(MainMenu.user.getInt(7) == 0){ //100% accuracy at 200 points
                Achievement gold2 = new Achievement(7, false, false);
                addObject(gold2, WIDTH / 2, HEIGHT * 101 / 150);
            }
            else if(MainMenu.user.getInt(7) == 1){
                Achievement gold2 = new Achievement(7, true, false);
                addObject(gold2, WIDTH / 2, HEIGHT * 101 / 150);
            }
            if(MainMenu.user.getInt(8) == 0){ //1000 points on hard
                Achievement gold3 = new Achievement(8, false, false);
                addObject(gold3, WIDTH * 4 / 5, HEIGHT * 101 / 150);
            }
            else if(MainMenu.user.getInt(8) == 1){
                Achievement gold3 = new Achievement(8, true, false);
                addObject(gold3, WIDTH * 4 / 5, HEIGHT * 101 / 150);
            }
            if(MainMenu.user.getInt(9) < 100){ //play 100 games
                Achievement diamond = new Achievement(9, false, false);
                addObject(diamond, WIDTH / 2, HEIGHT * 7 / 8);
            }
            else if(MainMenu.user.getInt(9) >= 100){
                Achievement diamond = new Achievement(9, true, false);
                addObject(diamond, WIDTH / 2, HEIGHT * 7 / 8);
            }
        }
        
        // Adding button
        backButton = new Button("Back", Color.BLACK, TITLE_COLOR, Color.WHITE, Color.YELLOW, Color.RED);
        addObject(backButton, WIDTH / 7, HEIGHT * 9 /10);
    }
    
    /**
     * Act method. Checks for button press.
     */
    public void act(){
        // Takes the user back to the main menu
        if(Greenfoot.mouseClicked(backButton) || (backButton.isHovering() && Greenfoot.isKeyDown("space"))){
            clickSound.play();
            Greenfoot.setWorld(new MainMenu());
        }
    }
}
