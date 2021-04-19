import java.util.*;

public class FrequencyCount {

    private HashMap<String, Integer> maps;

    public FrequencyCount(List<String> text) {
        maps = new HashMap<>();
        for (String monogram : text) {
            if (maps.containsKey(monogram)) {
                maps.replace(monogram, maps.get(monogram) + 1);
                // if monogram is in list, increment its count
            } else {
                maps.put(monogram, 1);
                // if monogram is not in list, add it with a count of 1
            }
        }
    }

    public FrequencyCount(List<String> text, int degree) {

        this.maps = new HashMap<>();

        for (int i = 0; i <= text.size() - degree; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            int cursor = i;
            while (cursor < i + degree) {
                stringBuilder.append(text.get(cursor)).append(" ");
                // bunches tokens together based on degree
                // ex: degree of 3 would group 3 tokens together
                cursor++;
            }

            String nGram = stringBuilder.toString();

            if (maps.containsKey(nGram)) {
                this.maps.replace(nGram, maps.get(nGram) + 1);
                // if NGram is in list, increment its count
            } else {
                this.maps.put(nGram, 1);
                // if NGram not in list, add it with a count of 1
            }
        }
    }

    public List<String> head() {
        List<Map.Entry<String, Integer>> frequency = new ArrayList<>(maps.entrySet());

        frequency.sort((val1, val2) -> val2.getValue().compareTo(val1.getValue()));
        // sorts by value, largest count tokens come first (most frequent tokens)

        List<String> mostFreq = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mostFreq.add(frequency.get(i) + " : " + percent(frequency.get(i).getKey()) + "%");
            // add each token to list with their frequency and percent of occurence
        }

        return mostFreq; // returns list of 20 most frequent tokens in map
    }

    public List<String> tail() {
        List<Map.Entry<String, Integer>> frequency = new ArrayList<>(maps.entrySet());

        frequency.sort(Map.Entry.comparingByValue());
        // sorts by value, smallest count tokens come first (least frequent tokens)

        List<String> leastFreq = new ArrayList<>();
        for (int i = 19; i >= 0; i--) {
            leastFreq.add(frequency.get(i) + " : " + percent(frequency.get(i).getKey()) + "%");
            // add each token to list with their frequency and percent of occurence
        }

        return leastFreq; // returns list of 20 least frequent tokens in map
    }

    public String randomToken() {
        List<String> randoms = new ArrayList<>(); // list to store all tokens
        Random rand = new Random();

        for (String token : maps.keySet()) {
            int cursor = 0;
            while (cursor < maps.get(token)) {
                randoms.add(token); // add tokens to the list
                cursor++;
            }
        }
        return randoms.get(rand.nextInt(randoms.size()));
        // return a random token from the list
    }

    public int count(String token) {
        if (maps.containsKey(token)) {
            maps.replace(token, maps.get(token)); // get token count if it is in map
        } else {
            return 0; // if token does not exist, count is 0
        }

        return maps.get(token); // return count of token
    }

    public double percent(String token) {
        List<String> myList = new ArrayList<>();

        for (String tokens : maps.keySet()) {
            for (int i = 0; i < maps.get(tokens); i++) {
                myList.add(tokens); // adding keys from map to a list
            }
        }

        return (double) count(token) / myList.size(); // return the count of token / size of list
    }

    public int add(String token) {
        if (maps.containsKey(token)) {
            maps.replace(token, maps.get(token) + 1);
            // increment count of token if it is in map
        } else {
            maps.put(token, 1); // add the token to the map with a count of 1
        }

        return maps.get(token); // return (new) count after adding token
    }
}