import java.io.File;
import java.io.IOException;
import java.util.*;

public class Driver {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        Scanner in = new Scanner(file).useDelimiter("[,]");

        List<String> cities = new ArrayList<>(); // list to contain the cities
        List<List<Integer>> costs = new ArrayList<>(); // list containing the adjacency matrix

        if (args[0] == null) {
            // java Driver <source> -- no argument
            throw new IOException("No arguments found, try again.");
        }

        boolean found = file.exists();
        if (!found) {
            // if file not found, throw exception message
            throw new IOException("Sorry, the file specified was not found.");
        }

        String tokens;
        int index = -1;

        while (in.hasNext()) {
            tokens = in.next().trim(); // scanning in file
            if (tokens.matches("[0-9]+")) { // if the token matches a number
                int num = Integer.parseInt(tokens); // parse it
                costs.get(index).add(num); // add it to the adjacency matrix
            } else {
                cities.add(tokens); // if not a number, add it to list of cities
                List<Integer> temp = new ArrayList<>(); // new temp list, helps form adjacency matrix
                costs.add(temp);
                index++;
            }
        }

        GeneticAlgorithm ga = new GeneticAlgorithm(cities, costs);
        Scanner scan = new Scanner(System.in);
        System.out.print("Number of epochs: ");
        int epoch = scan.nextInt(); // user determines how many epochs to run

        for (int i = 0; i < epoch; i++) {
            System.out.print("--------------EPOCH #" + (i + 1));
            System.out.println("--------------\n");

            // -- shortest path
            List<List<String>> paths = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                paths.add(ga.genRandChromosome());
            }
            System.out.println("---OPTIMAL PATH--- " + ga.optimalPath(paths, epoch));

            // -- initial population test
            System.out.println("---INITIAL POPULATION--- " + ga.genInitPop(cities.size()));

            // -- fitness test
            List<String> chromosome = new ArrayList<>(cities);
            System.out.println("---AVERAGE FITNESS--- " + ga.fitness(chromosome));

            // -- sorting by fitness
            List<List<String>> chromosomePop = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                chromosomePop.add(ga.genRandChromosome());
            }
            System.out.println("---SORTING BY FITNESS--- "+ ga.sortByFitness(chromosomePop));

            // -- crossing over
            List<String> p1 = new ArrayList<>(cities);
            List<String> p2 = new ArrayList<>(cities);
            System.out.println("---CROSSOVER--- "  + ga.crossover(p1, p2));

            // -- mutation
            List<String> mutable = new ArrayList<>();
            for (int j = 0; j < 1; j++) {
                mutable.addAll(cities);
            }
            System.out.println("---MUTATION--- " + ga.mutate(mutable) + "\n");

            System.out.println("==========================================================================================================");
            System.out.println("==========================================================================================================\n");
        }
        System.out.println("\n======== C O M P L E T E !!! ========");
    }
}
