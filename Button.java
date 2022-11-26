import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Actor Button
 * Contains A Label And Changes Color When The User Hovers Over It With The Mouse
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 10, 2021
 */
public class Button extends Actor
{
    // Actor Dimensions
    public static final int WIDTH = GameWorld.WIDTH * 96 / 425;
    public static final int HEIGHT = GameWorld.HEIGHT * 9 / 88;
    
    //Image Variables
    private GreenfootImage image;
    private Color bgColor;
    private Color outlineColor;
    private Color labelColor;
    private Color hoverColor;
    private Color flashColor;
    public static final Font LABEL_FONT = new Font("Courier New", true, false, HEIGHT / 2);
    private String label;
    
    // Sound Effect Variables
    private GreenfootSound moveSound = new GreenfootSound("Menu Move.wav");
    private boolean soundPlayed = false;
    
    // Button State Variables
    private boolean hovering = false;
    private boolean flashing = false;
    private int duration;
    
    private MouseInfo mouse; // Variable to get the mouse info
    
    /**
     * Constructor for the button
     */
    public Button(String label, Color bgColor, Color outlineColor, Color labelColor, Color hoverColor, Color flashColor){
        image = new GreenfootImage(WIDTH + 1, HEIGHT + 1);
        
        this.label = label;
        this.bgColor = bgColor;
        this.outlineColor = outlineColor;
        this.labelColor = labelColor;
        this.hoverColor = hoverColor;
        this.flashColor = flashColor;
        
        drawButton(flashing);
        setImage(image);
    }
    
    /**
     * Act method. Changes the appearance of the button
     */
    public void act() 
    {
        mouse = Greenfoot.getMouseInfo();
        
        // Changes button label's color when the mouse is hovering over it
        if(Greenfoot.mouseMoved(this)){
            hovering = true;
            if(!soundPlayed){ //adds sound
                moveSound.play();
                soundPlayed = true;
            }
            drawButton(flashing);
            setImage(image);
        }
        if(Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)){
            hovering = false;
            soundPlayed = false;
            drawButton(flashing);
            setImage(image);
        }
        
        // Flashes the button
        if(flashing){
            if(duration == 0){
                flashing = false;
                drawButton(flashing);
            }
            else duration--;
        }
    }
    
    /**
     * Changed the button's label
     */
    public void update(String label){
        this.label = label;
        drawButton(flashing);
        this.setImage(image);
    }
    
    /**
     * Changes the label and background color to the colors set in the parameter
     */
    public void changeColor(Color labelColor, Color bgColor)
    {
        this.labelColor = labelColor;
        this.bgColor = bgColor;
        update(this.label);
    }
    
    /**
     * Flashes the button for the duration set in the parameter
     */
    public void flash(int duration){
        this.duration = duration;
        flashing = true;
        drawButton(flashing);
    }
    
    /**
     * Returns true if the mouse is currently hovering the button
     */
    public boolean isHovering(){
        return hovering;
    }
    
    /**
     * Draws the button
     */
    private void drawButton(boolean flashing){
        image.clear();
        //drawing button
        image.setColor(bgColor);
        image.fillOval(0, 0, (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.fillOval(0, image.getHeight() - 1 - ((image.getWidth() - 1) / 10), (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.fillOval(image.getWidth() - 1- ((image.getWidth() - 1) / 10), 0, (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.fillOval(image.getWidth() - 1- ((image.getWidth() - 1) / 10), image.getHeight() - 1 - ((image.getWidth() - 1) / 10), (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        if(flashing) image.setColor(flashColor);
        else image.setColor(outlineColor);
        image.drawOval(0, 0, (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.drawOval(0, image.getHeight() - 1 - ((image.getWidth() - 1) / 10), (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.drawOval(image.getWidth() - 1- ((image.getWidth() - 1) / 10), 0, (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.drawOval(image.getWidth() - 1- ((image.getWidth() - 1) / 10), image.getHeight() - 1 - ((image.getWidth() - 1) / 10), (image.getWidth() - 1) / 10, (image.getWidth() - 1) / 10);
        image.setColor(bgColor);
        image.fillRect((image.getWidth() - 1) / 20, 0, image.getWidth() - 1 - (image.getWidth() - 1) / 10, image.getHeight() - 1);
        image.fillRect(0, (image.getWidth() - 1) / 20, image.getWidth() - 1, image.getHeight() - 1 - (image.getWidth() - 1) / 10);
        if(flashing) image.setColor(flashColor);
        else image.setColor(outlineColor);
        image.drawLine((image.getWidth() - 1) / 20, 0, image.getWidth() - 1- (image.getWidth() - 1) / 20, 0);
        image.drawLine((image.getWidth() - 1) / 20, image.getHeight() - 1, image.getWidth() - 1- (image.getWidth() - 1) / 20, image.getHeight() - 1);
        image.drawLine(0, (image.getWidth() - 1) / 20, 0, image.getHeight() - 1 - (image.getWidth() - 1) / 20);
        image.drawLine(image.getWidth() - 1, (image.getWidth() - 1) / 20, image.getWidth() - 1, image.getHeight() - 1 - (image.getWidth() - 1) / 20);
        //adding label
        if(flashing) image.setColor(flashColor);
        else image.setColor(labelColor);
        image.setFont(LABEL_FONT);
        if(hovering){ //change the label color if the mouse is hovering the button
            image.setColor(hoverColor);
        }
        image.drawString(label, (image.getWidth() - (int)(label.length() * LABEL_FONT.getSize() * 0.6)) / 2, (image.getHeight() + LABEL_FONT.getSize() / 2) / 2);
    }
}