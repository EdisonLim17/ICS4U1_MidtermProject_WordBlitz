import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class ReadWordFiles
 * Saves All The Words From The Files To Their Corresponding ArrayList (Word Files Must Be In The Same Directory)
 * 
 * @author Edison Lim, Vaughn Chan, Jaylen Cheung
 * @version November 10, 2021
 */
public class ReadWordFiles {
    // Accesses the word files and saves the words to their respective lists
    public static ArrayList<ArrayList<String>> readWordFiles() {
        // Create the word lists
        ArrayList<String> nouns = new ArrayList<String>();
        ArrayList<String> verbs = new ArrayList<String>();
        ArrayList<String> adjectives = new ArrayList<String>();
        ArrayList<ArrayList<String>> lst = new ArrayList<ArrayList<String>>();
        lst.add(nouns);
        lst.add(verbs);
        lst.add(adjectives);
        
        String[] types = {"nouns.txt", "verbs.txt", "adjectives.txt"};
        
        // Reads the words in the files and adds them to their lists
        try{
            for(int i = 0; i < 3; i++){
                File file = new File(types[i]);
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()) lst.get(i).add(scanner.nextLine());
                scanner.close();
            }
        }
        catch(FileNotFoundException ignored){}
        
        return lst;
    }
}
