import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;

/**
 * Actor CorrectWordOverlay
 * Displays The Wrong Letters The Player Has Typed In Red On Top Of The Current Word
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 10, 2021
 */
public class WrongWordOverlay extends Actor
{
    // Actor Dimensions
    public static final int WIDTH = GameWorld.WIDTH * 10 / 17;
    public static final int HEIGHT = GameWorld.HEIGHT * 5 / 8;
    
    // Image Variables
    public static final Color WRONG_COLOR = new Color(255, 0, 0);
    public static final Font OVERLAY_FONT = new Font("Courier New", true, false, HEIGHT / 10);
    
    /**
     * Constructor for the wrong word overlay
     */
    public WrongWordOverlay() {
        setImage(new GreenfootImage(WIDTH, HEIGHT));
    }
    
    /**
     * Display wrong letters in red text over the existing ones to show which letters have been typed incorrectly
     * @param word Current word
     * @param numCorrect Number of correct letters
     * @param numWrong Number of wrong letters
     * @param clear If true, clear the overlay
     */
    public void setWrongOverlay(String word, int numCorrect, int numWrong, boolean clear){
        GreenfootImage display = new GreenfootImage(WIDTH, HEIGHT);
        
        // Determines the string containing all the letters after and including the first wrong letter
        String displayString = "";
        for(int i = numCorrect; i < (numCorrect + numWrong); i++) displayString += Character.toString(word.charAt(i));
        
        // Draws the overlay
        display.setColor(WRONG_COLOR);
        display.setFont(OVERLAY_FONT);
        display.drawString(displayString, WIDTH / 25 + (int)((numCorrect) * OVERLAY_FONT.getSize() * 0.6), HEIGHT / 15);
        if(clear) display.clear();
        setImage(display);
    }
}