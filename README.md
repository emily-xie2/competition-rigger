# competition-rigger

You are a host of a popular online game competition. Players earn points for every game they play, based not just on whether they win but on a multitude of criteria, e.g. current ranking, length of play, their and their opponent’s history, demonstrated skills, etc. The criteria also changes from time to time, which makes score tracking very difficult. Besides, the list of players is never sorted (because it changes in real time very quickly), so nobody knows where exactly they stand at any moment. One thing is certain, though. After gaining enough experience players are invited to move to elimination stage (with big prizes!), where they play against players with the same score. This is their ultimate goal. You have a favourite player (someone you bet on), so you want to rig the competition by choosing their opponent for each elimination game. Thus, you must ensure the desired opponent has the same score. You can change one of the scores before announcing next round of elimination matches, but not outright. You rather do it one digit at a time and ensure that the score matches another score on the list, in order to cover your tracks to the chosen opponent.

This program finds and outlines the steps for changing one player’s score to match another’s, following these rules:
- The two scores must be of the same length, i.e. same number of digits.
- You can change one digit at a time.
- Every change must produce a score that matches another score on the list.

The program outputs the sequence of steps, provided the transition is possible. Essentially, the task is to migrate one int number to another, digit by digit, making sure at every step of the migration the modified number has an exact replica already on the list.
- The scores are int numbers stored in a text file.
- On each line there is a score number and a name, separated by space(s).
- The scores/players are not stored in any particular order.
- Your program will take as parameters two players from the list, with scores of the same length, and convert the first one to the second one, following the requirements above.

Examples:
1. current opponent: Jobie Willow, 73917 points desired opponent: Feliza Electra, 53319 points
73917 -> 53917 -> 53617 -> 53619 -> 53319
Jobie Willow -> Leila Shelba -> Rosalinde Christi -> Ethel Gizela -> Feliza Electra
2. current opponent: Adan Doro, 8277 points desired opponent: Laryssa Jackquelin, 9904 points
8277 -> 9277 -> 9207 -> 9907 -> 9904
Adan Doro -> Dasie Abigael -> Jamima Vivianna -> Minnnie Nerta -> Laryssa Jackquelin
