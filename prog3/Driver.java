import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main (String [] args) throws IOException{
        File myFile = new File(args[0]);
        Scanner scan = new Scanner(myFile); // scanner object for text file
        List<String> myList = new ArrayList<>(); // create list to store tokens

        if (args[0] == null) {
            // java Driver <source> -- no argument
            throw new IOException("No arguments found, try again.");
        }

        boolean found = myFile.exists();
        if (!found) {
            // if file not found, throw exception message
            throw new IOException("Sorry, the file was not found.");
        }

        String tokens;
        while (scan.hasNext()) {
            tokens = scan.next().replaceAll("[\t\n]", "").toLowerCase().trim();
            // parse tokens
            myList.add(tokens); // add tokens to a list
        }

        // Frequency Count object with a degree of 1
        FrequencyCount fc = new FrequencyCount(myList);
        System.out.println("20 Most Frequent Tokens: " + fc.head());
        System.out.println("20 Least Frequent Tokens: " + fc.tail());
        System.out.println("Random token from list: " + fc.randomToken());
        System.out.println("Count of the token: " + fc.count("of"));
        System.out.printf("%s : %f \n","Percent of occurence", fc.percent("the"));
        System.out.println("Add token: " + fc.add("their"));
        System.out.println("\n\t\tPOEM");

        // Frequency Count object with a degree of 4
        FrequencyCount fc2 = new FrequencyCount(myList, 4);
        System.out.println(fc2.randomToken());
        System.out.println(fc2.randomToken() + fc2.randomToken());
        System.out.println(fc2.randomToken());

    }
}
