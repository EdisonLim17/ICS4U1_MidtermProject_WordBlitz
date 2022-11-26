import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;

/**
 * Actor CorrectWordOverlay
 * Displays The Correct Letters The Player Has Typed In Green On Top Of The Current Word
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 10, 2021
 */
public class CorrectWordOverlay extends Actor
{
    // Actor Dimensions
    public static final int WIDTH = GameWorld.WIDTH * 10 / 17;
    public static final int HEIGHT = GameWorld.HEIGHT * 5 / 8;
    
    // Image Variables
    public static final Color CORRECT_COLOR = new Color(0, 255, 0);
    public static final Font OVERLAY_FONT = new Font("Courier New", true, false, HEIGHT / 10);
    
    /**
     * Constructor for the correct word overlay
     */
    public CorrectWordOverlay() {
        setImage(new GreenfootImage(WIDTH, HEIGHT));
    }
     
    /**
     * Display correct letters in green text over the existing ones to show which letters have been typed correcty
     * @param word Current word
     * @param numCorrect Number of correct letters
     * @param clear If true, clear the overlay
     */
    public void setCorrectOverlay(String word, int numCorrect, boolean clear) {
        GreenfootImage display = new GreenfootImage(WIDTH, HEIGHT);
        
        // Determines the string containing all the consecutive correct letters
        String displayString = "";
        for(int i = 0; i < numCorrect; i++) displayString += Character.toString(word.charAt(i));
        
        // Draws the overlay
        display.setColor(CORRECT_COLOR);
        display.setFont(OVERLAY_FONT);
        display.drawString(displayString, WIDTH / 25, HEIGHT / 15);
        if(clear) display.clear();
        setImage(display);
    }
}