// Import classes
import java.util.HashMap;
import java.util.HashSet;

/** [InputFileResult.java]
  * Desc: Stores the HashMaps/Sets from input.
  * @author Emily Xie
  * @version Nov 2022
  */

public class InputFileResult {
    // Declare variables
    HashMap<Integer, HashSet<String>> scoreToPlayers;
    HashSet<Integer> scoreSet;
    HashSet<String> playerNameSet;

    /** 
     * InputFileResult
     * This constructor stores the input.
     * @param playerMap A HashMap of the player scores (Integer) and names (String HashSet).
     * @param scoreSet An Integer HashSet of the player scores.
     * @param nameSet A String HashSet of the player names.
     */
    public InputFileResult(HashMap<Integer, HashSet<String>> playerMap, HashSet<Integer> scoreSet, HashSet<String> nameSet) {
        this.scoreToPlayers = playerMap;
        this.scoreSet = scoreSet;
        this.playerNameSet = nameSet;
    }
}
