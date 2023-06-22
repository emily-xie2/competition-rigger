// Import classes
import java.util.HashMap;
import java.util.HashSet;

/** [AdjacencyListHelper.java]
  * Desc: Assists with the adjacency list.
  * @author Emily Xie
  * @version Nov 2022
  */
public class AdjacencyListHelper {
    /** 
     * getAllAdjacentNumbers
     * This method, given the score, generates all the numbers that have the same length 
     * and only a single digit difference.
     * Time complexity = O(m), since there is a single loop through each digit of the score.
     * @param score An integer of the score.
     * @return set An Integer HashSet of all the adjacent numbers.
     */
    private HashSet<Integer> getAllAdjacentNumbers(Integer score) {
        HashSet<Integer> set = new HashSet<>();
        String scoreStr = String.valueOf(score);
        int modVal = 1;

        for (int digitPos = 0; digitPos < scoreStr.length(); digitPos++) {
            modVal = modVal * 10;
            for (int digitVal = 0; digitVal < 10; digitVal++) {
                // The first digit cannot be changed to 0, so need to catch the edge case
                if (digitVal != 0 || digitPos != (scoreStr.length() -1)) {
                    // Calculations to generate the adjacent numbers
                    int temp = score - score % modVal + score % (modVal / 10) + digitVal * (modVal / 10);
                    set.add(temp);
                }
            }
        }
        return set;
    }
    
    /** 
     * generateAdjacencyMap
     * This method generates an adjacency map for each player.
     * Time complexity = O(nm), since there is a nested loop; the outer loop runs n times, while the inner loop 
     * runs O(m) times, where m = largest number of digit among all scores.
     * @param scoreList An Integer HashSet of the scores.
     * @return adjacentScore A HashMap of the adjacent scores.
     */
    public HashMap<Integer, HashSet<Integer>> generateAdjacencyMap(HashSet<Integer> scoreList) {
        // Instantiate object
        HashMap<Integer, HashSet<Integer>> adjacentScore = new HashMap<>();

        for (int score: scoreList) {
            adjacentScore.put(score, new HashSet<>());
            HashSet<Integer> allAdjacent = getAllAdjacentNumbers(score);
            for (int num: allAdjacent) {
                // ScoreList is a hashset, so the method is amortized O(1)
                if (score != num && scoreList.contains(num)) {
                    adjacentScore.get(score).add(num);
                }
            }
        }
        return adjacentScore;
    }
}