import java.util.*;

public class GeneticAlgorithm {

    private List<String> cities;
    private List<List<Integer>> costs;

    public GeneticAlgorithm(List<String> cities, List<List<Integer>> costs) {
        this.cities = cities;
        this.costs = costs;
    }

    public List<List<String>> genInitPop(int popSize) {
        List<List<String>> pop = new ArrayList<>();
        for (int i = 0; i < popSize; i++) {
            pop.add(genRandChromosome());
            // add a random chromosome during each iteration up to popSize
        }
        return pop;
    }

    public List<String> optimalPath(List<List<String>> paths, int epoch) {
        for (int i = 0; i < epoch; i++) {
            paths.add(genRandChromosome()); // fill list with random chromosomes
        }
        sortByFitness(paths); // sort them by fitness

        return paths.get(0); // print out the list in the first index (its the most fit)
    }

    public List<String> genRandChromosome() {
        List<String> randomPop = new ArrayList<>();
        for (int i = 0; i <= cities.size() - 1; i++) {
            randomPop.add(cities.get(i));
            // add each city to a list of random chromosomes
        }

        Collections.shuffle(randomPop); // shuffle the list
        return randomPop;
    }

    public int fitness(List<String> chromosome) {
        int fitness = 0;

        for (int i = 0; i < chromosome.size() - 1; i++) {
            // in each iteration start and end will move thru points in list
            // and calculate the cost between to points and add that to the total fitness
            int start = cities.indexOf(chromosome.get(i));
            int end = cities.indexOf(chromosome.get(i + 1));
            fitness += costs.get(start).get(end);
        }

        int start = cities.indexOf(chromosome.get(chromosome.size() - 1)); // start from last visited
        int end = cities.indexOf(chromosome.get(0)); // go to the original starting point
        fitness += costs.get(start).get(end); // calculate cost and add that to fitness

        return fitness; // return cost of entire travel
    }

    public List<List<String>> sortByFitness(List<List<String>> chromosomePop) {
        chromosomePop.sort(new Comparator<List<String>>() {
            @Override
            public int compare(List<String> chromosome1, List<String> chromosome2) {
                return fitness(chromosome1) - fitness(chromosome2);
                // comparator that compares fitness between lists and sorts accordingly
                // most fit list (with smallest fitness cost) will come first
            }
        });

        return chromosomePop;
    }

    public List<String> crossover(List<String> p1, List<String> p2) {
        Set<String> labels = new HashSet<>();
        List<String> child = new ArrayList<>(labels);

        for (int i = 1; i <= (p1.size() - 1) / 2; i++) {
            labels.add(p1.get(i));
            // take a chunk from one parent and add it to a set
        }

        for (int i = 0; i < p2.size(); i++) {
            if (!labels.contains(p2.get(i))) {
                labels.addAll(p2);
                // add items from other parent that are not already contained in set
            }
        }

        child.addAll(labels); // add all items in set to a list
        Collections.shuffle(child); // shuffle list

        return child;
    }

    public List<String> mutate(List<String> mutable) {
        int len = mutable.size();
        int i1 = new Random().nextInt(len); // 2 random indices in list
        int i2 = new Random().nextInt(len);

        for (int i = 0; i < len; i++) { // loop thru list
            if (i1 == i2) {
                i2 = new Random().nextInt(len);
                // if both random #'s are equal generate a random # for one of them
                Collections.swap(mutable, i1, i2);// swap values in 2 random indices
                System.out.println("!!!MUTATION HAS OCCURRED!!!");
            }
        }

        return mutable;
    }
}