// Import classes
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

// Enumeration of current and desired player constants
enum OpponentType {
    CURRENT,
    DESIRED
}

/** [InputHelper.java]
  * Desc: Assists with the input of information.
  * @author Emily Xie
  * @version Nov 2022
  */

public class InputHelper {
    /** 
     * getPlayerNameInput
     * This method gets the input of the current and desired players.
     * @param type The player type, either current or desired.
     * @param playerNameList A String HashSet of the list of player names.
     * @return opponent A string of the opponent name.
     */
    public String getPlayerNameInput(OpponentType type, HashSet<String> playerNameList) {
        String typeStr = "";
        if (type == OpponentType.CURRENT) {
            typeStr = "current";
        } else {
            typeStr = "desired";
        }
        System.out.print("Enter " + typeStr + " opponent name(e.g. John Doe): ");
        Scanner reader = new Scanner(System.in);  // Create a Scanner object
        String opponent = reader.nextLine();
        while (!playerNameList.contains(opponent)) {
            System.out.println("Player with name " + opponent + " does not exist.");
            System.out.print("Enter " + typeStr + " opponent name(e.g. John Doe): ");
            opponent = reader.nextLine();
        }
        return opponent;
    }
    
    /** 
     * readPlayerFile
     * This method reads the file with the list of scores and players, and inputs it.
     * @return The stored file input.
     * @exception IOException if stream to file cannot be written to or closed.
     * @exception NumberFormatException if String cannot be converted to a numeric value.
     */
    public InputFileResult readPlayerFile() throws IOException {
        BufferedReader bufferedReader = null;
        HashMap<Integer, HashSet<String>> playerMap = new HashMap<>();
        HashSet<Integer> scoreSet = new HashSet<>();
        HashSet<String> nameSet = new HashSet<>();
        try {
            // Create objects
            File file = new File("score_list.txt");
            bufferedReader = new BufferedReader(new FileReader(file));
            
            String line = null;
            while ((line = bufferedReader.readLine()) != null) { // Read file line by line
                // Split the lines by spaces
                String[] parts = line.split(" ");
                
                // First part is the number, second is the first name, third is the last name
                String lastName = parts[2].trim();
                String firstName = parts[1].trim();
                String number = parts[0].trim();
                
                // Parse the score into integer and initialize a new player object, 
                // then add the player to the HashSet
                try {
                    int score = Integer.parseInt(number);
                    String fullName = firstName + " " + lastName;
                    nameSet.add(fullName);
                    scoreSet.add(score);
                    if (!playerMap.containsKey(score)) {
                        playerMap.put(score, new HashSet<>());
                    }
                    playerMap.get(score).add(fullName);
                }
                catch (NumberFormatException e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        return new InputFileResult(playerMap, scoreSet, nameSet);
    }
}