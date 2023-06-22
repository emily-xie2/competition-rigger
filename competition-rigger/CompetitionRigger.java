// Import classes
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/** [CompetitionRigger.java]
  * Desc: Changes the current player's score to match the desired.
  * @author Emily Xie
  * @version Nov 2022
  */

public class CompetitionRigger {
    /** 
     * CompetitionRigger
     * This constructor changes a player's score to another digit-by-digit and checks if each
     * score change matches another player's score on the score list.
     * @exception IOException if stream to file cannot be written to or closed.
     */
    public CompetitionRigger(String currentOpponentName, String desiredOpponentName) throws IOException{
        // Initalize objects
        InputHelper inputHelper = new InputHelper();
        AdjacencyListHelper neighborGenerator = new AdjacencyListHelper();
        Queue<Integer> queue = new LinkedList<>(); // Queue used for search
        HashMap<Integer, Boolean> visited = new HashMap<>(); // Provides information on visited nodes to ensure no duplicate searches
        HashMap<Integer, Integer> previousNode = new HashMap<>(); // Need to store information on the "parent" of each node on the path to retrieve it

        // Read the player file into a HashSet of player objects
        InputFileResult playerInfo = inputHelper.readPlayerFile();

        // Get the names of the current and desired opponents
        if (currentOpponentName == null){
            currentOpponentName = inputHelper.getPlayerNameInput(OpponentType.CURRENT, playerInfo.playerNameSet);
        }
        if (desiredOpponentName == null){
            desiredOpponentName = inputHelper.getPlayerNameInput(OpponentType.DESIRED, playerInfo.playerNameSet);
        }
        
        // Create the adjacency list of each score
        HashMap<Integer, HashSet<Integer>> adjacencyMap = neighborGenerator.generateAdjacencyMap(playerInfo.scoreSet);
        
        Integer dest = null;
        // Loop through each player and initialize values for the visited and previousNode HashMap
        // The time complexity of this loop is O(n)
        for (Integer score: playerInfo.scoreSet) {
            previousNode.put(score, null);
            if (playerInfo.scoreToPlayers.get(score).contains(currentOpponentName)) {
                // Mark player as visited
                visited.put(score, true);
                // Add player to queue
                queue.add(score);
            } else {
                // Otherwise, mark the player as unvisited
                visited.put(score, false);
            }
            if (playerInfo.scoreToPlayers.get(score).contains(desiredOpponentName)) {
                // Find the desired opponent
                dest = score;
            }
        }

        // Time complexity = O(n+E), where E is the number of edges.
        // Worst time complexity = O(n^2), when E is very large.
        while (!queue.isEmpty()) {
            // Get the top of the queue and remove
            int currentScore = queue.remove();
            
            // Loop through each neighbour of the player
            for (int neighbor: adjacencyMap.get(currentScore)) {
                // If the neighbor is already visited, do not move
                if (!visited.get(neighbor)) {
                    queue.add(neighbor); // Add the neighbour to the queue for later exploration
                    visited.put(neighbor, true); // Mark the neighbour as visited
                    previousNode.put(neighbor, currentScore); // Add the parent node of this neighbour
                    // If the destination is found, end the loop
                    if (dest != null && dest.equals(neighbor)) {
                        break;
                    }
                }
            }
        }

        // If the desired score is never visited, that means it is not possible to get to the target
        // Worst time complexity = O(n).
        if (!visited.get(dest)) {
            System.out.println("Cannot reach the target opponent.");
        } else {
            // Initialize a stack to store elements on the path
            Stack<Integer> result = new Stack<>();

            while (dest != null) {
                result.push(dest);
                dest = previousNode.get(dest);
            }
            // Manually print out the current and desired opponent name as a score could 
            // correspond to multiple players, but for intermediate players, it does not matter
            // because any player with the same score works.
            System.out.print(result.pop() + " (" + currentOpponentName + ") -> ");
            while (result.size() > 1) {
                int score = result.peek();
                String player = playerInfo.scoreToPlayers.get(score).iterator().next();
                System.out.print(score + " (" + player + ") -> ");
                result.pop();
            }
            System.out.print(result.pop() + " (" + desiredOpponentName + ")");
        }
    }
}