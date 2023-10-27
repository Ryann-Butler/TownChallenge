/* Create a new Java project called TownChallenge containing a class file TownChallenge.java
with the following functionality in the main() method.
i. Define a String array called towns to hold the names of 8 towns of your choice.
ii. Generate and display a set of sporting results such that towns[0] plays towns[1],
towns[2] plays towns[3], and so on. The number of points won by each town
should be a random integer in the range 0-9. For example, the display of results might be
in the form…
Banbridge 7 Ballymena 4
Belfast 3 Newry 3
Coleraine 9 Enniskillen 0
Lurgan 5 Portadown 5 */

/* i. The application should read the eight town names from an input file called
towns.txt and store them in the towns array.
ii. Array reading the collection of towns, the application should ask the user how
many sets of results are required and accept this numeric value from the
keyboard.
iii. The program should generate as many sets of random results as were
requested by the user. Note that all sets of results will consist of the same
fixtures.
iv. All results (and home/draw/away summaries) should be written to file
called results.txt such that every time the program is run, new output is
added to the end of the file. The information in the results file does not need
to be formatted (i.e., as a table). */

import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;



public class TownChallenge {

    public static void main(String[] args) throws Exception {
        // setting up array and size
        String[] towns = new String[8];

        Random random = new Random();
        int homeScore, awayScore;
        int homes = 0, draws = 0, aways = 0;
        //counter to count through the teams
        int thisTown = 0;
        // declaring variable as int
        int numSetsOfResults = 0;

        Scanner keyboard = new Scanner(System.in);
        // creating instance of file object and take data from text file
        File fileIn = new File("C:\\Users\\Ryan7\\IdeaProjects\\TownChallenge\\TownChallenge\\src\\towns.txt");
        //new instance of scanner to work with filein object need exception due to two scanners
        Scanner fileInput = new Scanner(fileIn);
        // creating an instance of file writer | append true dont wipe previous entries
        FileWriter fileOut = new FileWriter("C:\\Users\\Ryan7\\IdeaProjects\\TownChallenge\\TownChallenge\\src\\results.txt", true);

        // while loop keep putting in teams while file object has next line of info
        while (fileInput.hasNextLine()) {
            // index into towns array | next line is from file input
            towns[thisTown] = fileInput.nextLine();
            //add one to this town for every entry
            thisTown++;
        }

        //prompt user to input number of sets of results that are required
        System.out.println("How many sets fo results would you like to generate >");
        numSetsOfResults = keyboard.nextInt();

        //loop results set will count upto set of results inputted.
        for (int resultSet = 0; resultSet < numSetsOfResults; resultSet++) {

            // 8 teams so 4 results
            for (int i = 0; i < 4; i++) {
                homeScore = random.nextInt(10);
                awayScore = random.nextInt(10);
                System.out.printf("%-12s %3d %-12s 3%d \n",
                        // home teams in position, 0,2,4,6 in array | away teams in position 1,3,5,7
                        //since counting form zero home team is basically 2*i with away team always being one more
                        towns[i * 2], homeScore, towns[i * 2 + 1], awayScore);
                //writing town in position 2 alongside home score and away score
                fileOut.write(towns[i*2] + " " + homeScore + " " + towns[i * 2 + 1] + " " + awayScore + "\n");

                // calculate a draw condition
                if (homeScore > awayScore) {
                    homes++;
                } else if (homeScore < awayScore) {
                    aways++;
                } else {
                    draws++;
                }

            }
            System.out.printf("Home %d, Draws %d, Aways %d \n \n", homes, draws, aways);
            fileOut.write("Homes " + homes + ", Draws " + draws + ", Aways " + aways + "\n\n");
        }
        // need to close output file before it will write to txt file
        fileOut.close();
    }
}

