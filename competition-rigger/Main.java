/** [Main.java]
  * Desc: The main class.
  * @author Emily Xie
  * @version Nov 2022
  */

public class Main {
    public static void main(String[] args) {
        // Instantiate objects
        try {
            // User input
            CompetitionRigger competitionRigger = new CompetitionRigger(null,null);
            System.out.println("\n");
            // Test case #1
            competitionRigger = new CompetitionRigger("Jobie Willow","Feliza Electra");
            System.out.println("\n");
            // Test case #2
            competitionRigger = new CompetitionRigger("Nollie Calli","Lorianna Emlynn");
            System.out.println("\n");
            // Test case #3
            competitionRigger = new CompetitionRigger("Kariotta Suzann","Nada Cyndie");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}