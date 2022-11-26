import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Actor Achievement
 * A Medal With A Label And Description Showing How To Achieve The Medal
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 10, 2021
 */
public class Achievement extends Actor
{
    // Actor Dimensions
    public static final int WIDTH = GameWorld.WIDTH * 96 / 425;
    public static final int HEIGHT = GameWorld.HEIGHT * 3 / 16;
    
    // Image Variables
    private GreenfootImage image;
    private GreenfootImage bronzeMedal = new GreenfootImage("BronzeMedal.png");
    private GreenfootImage noBronzeMedal = new GreenfootImage("NoBronzeMedal.png");
    private GreenfootImage silverMedal = new GreenfootImage("SilverMedal.png");
    private GreenfootImage noSilverMedal = new GreenfootImage("NoSilverMedal.png");
    private GreenfootImage goldMedal = new GreenfootImage("GoldMedal.png");
    private GreenfootImage noGoldMedal = new GreenfootImage("NoGoldMedal.png");
    private GreenfootImage diamondMedal = new GreenfootImage("DiamondMedal.png");
    private GreenfootImage noDiamondMedal = new GreenfootImage("NoDiamondMedal.png");
    public static final Color NAME_COLOR = new Color(255, 255, 0);
    public static final Font NAME_FONT = new Font("Courier New", true, true, HEIGHT / 6);
    private String name;
    public static final Color DESCRIPTION_COLOR = new Color(255, 255, 0);
    public static final Color DESCRIPTION_BG_COLOR = new Color(255, 0, 100, 50);
    public static final Font DESCRIPTION_FONT = new Font("Courier New", true, true, HEIGHT / 6);
    private String desc;
    
    // Variables to track the type of achievement
    private String type;
    private boolean achieved, popup;
    private int popupDuration;
    
    // Arrays containing the information of each achievement
    private String[] names = {"Gotta Go Fast", "Almost There", "Just Getting Started", "2 Fast 2 Furious", "Spelling Counts", "Too Easy", "Speeder", 
        "Perfectionist", "TryHard", "Veteran"};
    private String[] descriptions = {"Get 70 WPM At 200 Points", "Get 95% Accuracy At 200 Points", "Get 1000 Points On Easy Difficulty", 
        "Get 100 WPM At 200 Points", "Get 97% Accuracy At 200 Points", "Get 1000 Points On Normal Difficulty", 
        "Get 130 WPM At 200 Points", "Get 100% accuracy At 200 Points", "Get 1000 Points On Hard Difficulty", "Play 100 Games"};
    private String[] types = {"bronze", "bronze", "bronze", "silver", "silver","silver", "gold", "gold", "gold", "diamond"};
    
    private MouseInfo mouse; // Variable to get the mouse info
    
    /**
     * Constructor for the achievement
     */
    public Achievement(int index, boolean achieved, boolean popup){
        image = new GreenfootImage(WIDTH + 1, HEIGHT + 1);
        
        name = names[index];
        desc = descriptions[index];
        type = types[index];
        this.achieved = achieved;
        this.popup = popup;
        
        if(popup) popupDuration = 150;
        
        drawMedal();
    }
    
    /**
     * Act method. Either removes or changes the achievement image
     */
    public void act() 
    {
        mouse = Greenfoot.getMouseInfo();
        
        // Removes the achievement after its duration is over if its a popup (when it pops up during the game)
        if(popup){
            if(popupDuration == 0) getWorld().removeObject(this);
            else popupDuration--;
        }
        // Displays the decription of the achievement when the user hovers over it with the mouse
        else{
            if(Greenfoot.mouseMoved(this)) drawDescription();
            if(Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) drawMedal();
        }
    }
    
    /**
     * Draws the achievement medal
     */
    private void drawMedal(){
        image.clear();
        
        // Determine what type of medal to draw (bronze, silver, gold, or diamond)
        if(achieved){
            if(type.equals("bronze")) image.drawImage(bronzeMedal, WIDTH / 2 - 34, 0);
            else if(type.equals("silver")) image.drawImage(silverMedal, WIDTH / 2 - 34, 0);
            else if(type.equals("gold")) image.drawImage(goldMedal, WIDTH / 2 - 37, 0);
            else if(type.equals("diamond")) image.drawImage(diamondMedal, WIDTH / 2 - 34, 0);
        }
        else{ // Set medal to black if the user hasn't achieved it yet
            if(type.equals("bronze")) image.drawImage(noBronzeMedal, WIDTH / 2 - 34, 0);
            else if(type.equals("silver")) image.drawImage(noSilverMedal, WIDTH / 2 - 34, 0);
            else if(type.equals("gold")) image.drawImage(noGoldMedal, WIDTH / 2 - 37, 0);
            else if(type.equals("diamond")) image.drawImage(noDiamondMedal, WIDTH / 2 - 34, 0);
        }
        
        image.setColor(NAME_COLOR);
        image.setFont(NAME_FONT);
        image.drawString(name, (image.getWidth() - (int)(name.length() * NAME_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 9 / 10));
        setImage(image);
    }
    
    /**
     * Draws the achievement's description
     */
    private void drawDescription(){
        image.clear();
        
        String[] words = desc.split(" ");
        int lineCounter = 1;
        int length = 0;
        int index = 0;
        String line1 = "";
        String line2 = "";
        String line3 = "";
        String line4 = "";
        String line5 = "";
        
        image.setColor(DESCRIPTION_BG_COLOR);
        image.fill();
        image.setColor(DESCRIPTION_COLOR);
        
        // Determines how many words to put on each line so it fits in the image
        while(index != words.length){
            if((length += words[index].length() + 1) * DESCRIPTION_FONT.getSize() * 0.6 < image.getWidth()){
                length += words[index].length() + 1;
                if(lineCounter == 1) line1 += words[index] + " ";
                else if(lineCounter == 2) line2 += words[index] + " ";
                else if(lineCounter == 3) line3 += words[index] + " ";
                else if(lineCounter == 4) line4 += words[index] + " ";
                else if(lineCounter == 5) line5 += words[index] + " ";
                index++;
            }
            else{
                lineCounter++;
                length = 0;
            }
        }
        
        // Determines where to draw each string so that it always stays centered
        if(lineCounter == 1) image.drawString(line1, (image.getWidth() - (int)(line1.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() / 2) + DESCRIPTION_FONT.getSize() / 4);
        else if(lineCounter == 2){
            image.drawString(line1, (image.getWidth() - (int)(line1.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 2 / 5) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line2, (image.getWidth() - (int)(line2.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 3 / 5) + DESCRIPTION_FONT.getSize() / 4);
        }
        else if(lineCounter == 3){
            image.drawString(line1, (image.getWidth() - (int)(line1.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 3 / 10) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line2, (image.getWidth() - (int)(line2.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() / 2) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line3, (image.getWidth() - (int)(line3.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 7 / 10) + DESCRIPTION_FONT.getSize() / 4);
        }
        else if(lineCounter == 4){
            image.drawString(line1, (image.getWidth() - (int)(line1.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() / 5) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line2, (image.getWidth() - (int)(line2.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 2 / 5) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line3, (image.getWidth() - (int)(line3.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 3 / 5) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line4, (image.getWidth() - (int)(line4.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 4 / 5) + DESCRIPTION_FONT.getSize() / 4);
        }
        else if(lineCounter == 5){
            image.drawString(line1, (image.getWidth() - (int)(line1.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() / 10) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line2, (image.getWidth() - (int)(line2.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 3 / 10) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line3, (image.getWidth() - (int)(line3.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() / 2) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line4, (image.getWidth() - (int)(line4.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 7 / 10) + DESCRIPTION_FONT.getSize() / 4);
            image.drawString(line5, (image.getWidth() - (int)(line5.length() * DESCRIPTION_FONT.getSize() * 0.6)) / 2, (image.getHeight() * 9 / 10) + DESCRIPTION_FONT.getSize() / 4);
        }
        setImage(image);
    }
}
