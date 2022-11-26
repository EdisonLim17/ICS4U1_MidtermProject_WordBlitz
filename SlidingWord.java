import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Actor SlidingWord
 * Slides A Word Into/Out Of The WordBox
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 10, 2021
 */
public class SlidingWord extends Actor
{
    // Actor Dimensions
    public static final int WIDTH = GameWorld.WIDTH;
    public static final int HEIGHT = GameWorld.HEIGHT;
    
    // Image Variables
    public static final Color WORD_COLOR = new Color(255, 255, 255);
    public static final Font WORD_FONT = new Font("Courier New", false, false, HEIGHT / 16);
    public static final Font BOLD_WORD_FONT = new Font("Courier New", true, false, HEIGHT / 16);
    
    // Variables to keep track of the sliding word
    private String slidingWord = "";
    private int slidingX = 0;
    private int slidingY = 0;
    private int slidingYEnd = 0;
    private boolean isBold = false; 
    
    /**
     * Constructor for the sliding word
     */
    public SlidingWord() {
        setImage(new GreenfootImage(WIDTH, HEIGHT));
    }
    
    /**
     * Act method. Slides a word if there is one in queue
     */
    public void act() {
        if(slidingWord.length() > 0){
            if(slidingY < slidingYEnd){
                GreenfootImage display = new GreenfootImage(WIDTH, HEIGHT);
                display.setColor(WORD_COLOR);
                if(isBold) display.setFont(BOLD_WORD_FONT);
                else display.setFont(WORD_FONT);
                display.drawString(slidingWord, slidingX, slidingY);
                slidingY += HEIGHT / 160;
                setImage(display);
            }
            else slidingWord = "";
        }
    }
    
    /**
     * Set a new word to slide
     * @param word Word to slide
     * @param x Starting x value
     * @param y Starting y value
     * @param y2 Ending y value
     */
    public void setSlidingWord(String word, int x, int y, int y2, boolean bold) {
        slidingWord = word;
        slidingX = x;
        slidingY = y;
        slidingYEnd = y2;
        isBold = bold;
    }
}
