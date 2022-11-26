import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Actor WordBox
 * Displays All The Words In The Word Queue.
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 10, 2021
 */
public class WordBox extends Actor
{
    // Actor Dimensions
    public static final int WIDTH = GameWorld.WIDTH * 6 / 17;
    public static final int HEIGHT = GameWorld.HEIGHT * 5 / 6;
    
    // Image Variables
    public static final Color WORD_COLOR = new Color(255, 255, 255);
    public static final Font WORD_FONT = new Font("Courier New", HEIGHT * 3 / 40);
    public static final Font BOLD_WORD_FONT = new Font("Courier New", true, false, HEIGHT * 3 / 40);
    
    /**
     * Constructor for the word box
     */
    public WordBox(Queue<String> queue) {
        setWordBox(queue);
    }
    
    /**
     * Updates the word box with new words
     * @param queue Queue of words
     */
    public void setWordBox(Queue<String> queue) {
        GreenfootImage display = new GreenfootImage(WIDTH, HEIGHT);
        
        ArrayList<String> list = new ArrayList<String>();
        int y = HEIGHT / 17;
        
        display.setColor(WORD_COLOR);
        
        while(!queue.isEmpty()) list.add(queue.remove());
        Collections.reverse(list); // To make the bottom start with the first word
        list.remove(0);
        for(int i = 0; i < list.size(); i++){
            if(i == list.size() - 1) display.setFont(BOLD_WORD_FONT);
            else display.setFont(WORD_FONT);
            display.drawString("\n" + list.get(i), WIDTH / 15, y);
            y += HEIGHT * 57 / 200;
        }
        
        setImage(display);
    }
}
