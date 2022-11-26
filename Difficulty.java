/**
 * Enum Difficulty
 * Sets Game Difficulty
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 10, 2021
 */
public enum Difficulty {
    EASY (0.5f),
    NORMAL (1.5f),
    HARD (2.5f),
    NOT_SET(0.5f);
    
    public final float speed;
    
    // game speed is set on title screen, but needs to be known throughout all other screens.
    public static Difficulty gameDifficulty = NOT_SET;
    
    private Difficulty(float speed) {
        this.speed = speed;
    }
}