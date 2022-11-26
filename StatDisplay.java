import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

/**
 * Actor StatDisplay
 * Displays The Following Stats: Score, WPM, Accuracy
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 10, 2021
 */
public class StatDisplay extends Actor
{
    // Actor Dimensions
    public static final int WIDTH = GameWorld.WIDTH / 3;
    public static final int HEIGHT = GameWorld.HEIGHT;
    
    // Image Variables
    private GreenfootImage image;
    public static final Color SCORE_COLOR = new Color(255, 255, 0);
    public static final Font SCORE_FONT = MainMenu.HIGHSCORE_FONT;
    
    // Variables to keep track of the stats
    private int score, wpm, accuracy;
    private String scoreDisplay, wpmDisplay, accuracyDisplay;
    
    public static ArrayList<Integer> medalsUnlocked; // Variable to keep track of what medals were unlocked during the game
    
    /**
     * Constructor for the stat display
     */
    public StatDisplay(int score){
        image =  new GreenfootImage(WIDTH + 1, HEIGHT + 1);
        
        // Setting the player's stats
        this.score = score;
        wpm = 0;
        accuracy = 0;
        scoreDisplay = "SCORE: " + this.score;
        wpmDisplay = "WPM: " + wpm;
        accuracyDisplay = "ACCURACY: " + accuracy + "%";
        
        medalsUnlocked = new ArrayList<Integer>();
        
        drawStatDisplay();
    }
    
    /**
     * Updates the player's stats
     */
    public void update(int score, int gameTime, int correct, int incorrect) 
    {
        // Updating the stats and the display
        this.score = score;
        if(gameTime / 60 != 0) wpm = 60 * (score / 5) / (gameTime / 60);
        this.accuracy = (int) Math.round((1.0*correct / (correct + incorrect)) * 100);
        wpmDisplay = "WPM: " + wpm;
        scoreDisplay = "SCORE: " + this.score;
        accuracyDisplay = "ACCURACY: " + this.accuracy + "%";
        drawStatDisplay();
        
        // Gets user info and saves their new achievements if they fulfilled its requirements
        if(UserInfo.isStorageAvailable()){
            if(score >= 200 && wpm >= 70 && MainMenu.user.getInt(0) == 0){
                MainMenu.user.setInt(0, 1);
                medalsUnlocked.add(0);
            }
            if(score >= 200 && wpm >= 100 && MainMenu.user.getInt(3) == 0){
                MainMenu.user.setInt(3, 1);
                medalsUnlocked.add(3);
            }
            if(score >= 200 && wpm >= 130 && MainMenu.user.getInt(6) == 0){
                MainMenu.user.setInt(6, 1);
                medalsUnlocked.add(6);
            }
            
            if(score >= 200 && accuracy >= 95 && MainMenu.user.getInt(1) == 0){
                MainMenu.user.setInt(1, 1);
                medalsUnlocked.add(1);
            }
            if(score >= 200 && accuracy >= 97 && MainMenu.user.getInt(4) == 0){
                MainMenu.user.setInt(4, 1);
                medalsUnlocked.add(4);
            }
            if(score >= 200 && accuracy >= 100 && MainMenu.user.getInt(7) == 0){
                MainMenu.user.setInt(7, 1);
                medalsUnlocked.add(7);
            }
            
            if(score >= 1000 && Difficulty.gameDifficulty == Difficulty.EASY && MainMenu.user.getInt(2) == 0){
                MainMenu.user.setInt(2, 1);
                medalsUnlocked.add(2);
            }
            if(score >= 1000 && Difficulty.gameDifficulty == Difficulty.NORMAL && MainMenu.user.getInt(5) == 0){
                MainMenu.user.setInt(5, 1);
                medalsUnlocked.add(5);
            }
            if(score >= 1000 && Difficulty.gameDifficulty == Difficulty.HARD && MainMenu.user.getInt(8) == 0){
                MainMenu.user.setInt(8, 1);
                medalsUnlocked.add(8);
            }
            
            MainMenu.user.store();
        }
    }
    
    /**
     * Draws the stat display
     */
    private void drawStatDisplay(){
        //drawing the display
        image.clear();
        image.setColor(SCORE_COLOR);
        image.setFont(SCORE_FONT);
        image.drawString(scoreDisplay, WIDTH / 10, (HEIGHT - SCORE_FONT.getSize()) / 4);
        image.drawString(wpmDisplay, WIDTH / 10, (HEIGHT - SCORE_FONT.getSize()) / 2);
        image.drawString(accuracyDisplay, WIDTH / 10, (HEIGHT - SCORE_FONT.getSize()) * 3 / 4);
        setImage(image);
    }
}
