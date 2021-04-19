import edu.sdsu.cs.datastructures.List;
import edu.sdsu.cs.datastructures.SinglyLinkedList;

public class Driver {
    public static void main(String[] args) {

        int testSize = 8000;

        System.out.println("\nTiming add first ... ");
        timingAddFirst(testSize);

        System.out.println("\nTiming add last ... ");
        timingAddLast(testSize);

        System.out.println("\nTiming remove first ... ");
        timingRemoveFirst(testSize);

        System.out.println("\nTiming remove last ... ");
        timingRemoveLast(testSize);

        System.out.println("\nTesting clear on empty list ... ");
        clearEmptyList();

        System.out.println("\nTesting clear on full list ... ");
        clearFullList();

        System.out.println("\nTesting set using count ... ");
        testSetUsingCount();

        System.out.println("\nTesting the sort method ... ");
        testSort();

        System.out.println("\nTesting remove with negative indices ... ");
        testRemoveIndex();

        System.out.println("\nTesting reverse with empty list ... ");
        testReverseEmptyList();

        System.out.println("\nTesting reverse with full list ... ");
        testReverseFullList();

        System.out.println("\nTesting add other list ... ");
        testAddOther();

        System.out.println("\nTesting set with negative indices ... ");
        testSetIndex();

    }

    private static void timingAddFirst(int testSize) {
        List<Integer> sll = new SinglyLinkedList<>();
        long finalTime = 0;

        for (int i = 0; i < 5; ++i) { // up to 5 tests
            int j;
            for (j = 0; j <= testSize - 1; j++) {
                sll.add(j);
            }

            long start = System.currentTimeMillis(); // start time
            sll.addFirst(j); // adds item to front
            long finish = System.currentTimeMillis(); // stop time

            long timeSpan = finish - start;
            double timeFactor = (double) timeSpan / finalTime; // time factor/ratio

            System.out.println("Size: " + testSize + "\t\t\t\t" + "Time: " + timeSpan + " ms" + "\t\t\t\t" + "Factor: "
                    + timeFactor);

            testSize *= 2; // double testSize
            finalTime = timeSpan;
        }
    }

    private static void timingAddLast(int testSize) {
        List<Integer> sll = new SinglyLinkedList<>();
        long finalTime = 0;

        for (int i = 0; i < 5; ++i) { // up to 5 tests
            int j;
            for (j = 0; j <= testSize - 1; j++) {
                sll.add(j);
            }

            long start = System.currentTimeMillis(); // start time
            sll.addLast(j); // adds item to end
            long finish = System.currentTimeMillis(); // stop time

            long timeSpan = finish - start;
            double timeFactor = (double) timeSpan / finalTime; // time factor/ratio

            System.out.println("Size: " + testSize + "\t\t\t\t" + "Time: " + timeSpan + " ms" + "\t\t\t\t" + "Factor: "
                    + timeFactor);

            testSize *= 2; // double testSize
            finalTime = timeSpan;
        }
    }

    private static void timingRemoveFirst(int testSize) {
        List<Integer> sll = new SinglyLinkedList<>();
        long finalTime = 0;

        for (int i = 0; i < 5; ++i) { // up to 5 tests
            int j;
            for (j = 0; j < testSize - 1; j++) {
                sll.add(j);
            }

            long start = System.currentTimeMillis(); // start time
            sll.removeFirst(); // removes first item
            long finish = System.currentTimeMillis(); // stop time

            long timeSpan = finish - start;
            double timeFactor = (double) timeSpan / finalTime; // time factor/ratio

            System.out.println("Size: " + testSize + "\t\t\t\t" + "Time: " + timeSpan + " ms" + "\t\t\t\t" + "Factor: "
                    + timeFactor);

            testSize *= 2; // double testSize
            finalTime = timeSpan;
        }
    }

    private static void timingRemoveLast(int testSize) {
        List<Integer> sll = new SinglyLinkedList<>();
        long finalTime = 0;

        for (int i = 0; i < 5; ++i) { // up to 5 tests
            for (int j = 0; j <= testSize - 1; j++) {
                sll.add(j);
            }
            long start = System.currentTimeMillis(); // start time
            sll.removeLast(); // removes last item
            long finish = System.currentTimeMillis(); // stop time
            long timeSpan = finish - start;
            double timeFactor = (double) timeSpan / finalTime; // time factor/ratio
            System.out.println("Size: " + testSize + "\t\t\t\t" + "Time: " + timeSpan + " ms" + "\t\t\t\t" + "Factor: "
                    + timeFactor);
            testSize *= 2; // double testSize
            finalTime = timeSpan;
        }
    }

    private static void clearEmptyList() {
        List<Integer> sll = new SinglyLinkedList<>();

        System.out.println(sll.toString() + " Size: " + sll.size()); // print list & size
        System.out.println("List empty?: " + sll.isEmpty()); // checks if list is empty

        sll.clear(); // clear list

        System.out.println(sll.toString() + " Size: " + sll.size()); // print list & size
        System.out.println("List empty?: " + sll.isEmpty()); // checks if list is empty
    }

    private static void clearFullList() {
        List<Integer> sll = new SinglyLinkedList<>();

        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * 15);
            sll.add(random); // fill list with 10 random #'s
        }

        System.out.println(sll.toString() + " Size: " + sll.size());
        System.out.println("List empty?: " + sll.isEmpty()); // returns false

        sll.clear(); // clears list

        System.out.println(sll.toString() + " Size: " + sll.size());
        System.out.println("List empty?: " + sll.isEmpty()); // returns true
    }

    private static void testSetUsingCount() {
        List<Integer> sll = new SinglyLinkedList<>();

        int i;
        for (i = 0; i < 10; i++) {
            sll.add(310); // fill list with 10 310's
        }

        System.out.println(sll.toString() + " Size: " + sll.size()); // print list & size
        System.out.println("Count of 310: " + sll.count(310)); // count # of 310's
        System.out.println("Count of -310: " + sll.count(-310)); // count # of -310's

        for (i = 0; i < sll.size(); i++) {
            sll.set(i, -310); // set each item in list to -310
        }

        System.out.println(sll.toString() + " Size: " + sll.size()); // print list & size
        System.out.println("Count of 310: " + sll.count(310)); // count # of 310's
        System.out.println("Count of -310: " + sll.count(-310)); // count # of -310's
    }

    private static void testSort() {
        List<Integer> sll = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * 100);
            sll.addFirst(random); // fill list with 10 random #'s
        }

        System.out.println(sll.toString() + " Size: " + sll.size()); // print list & size
        sll.sort(); // sort the list
        System.out.println(sll.toString() + " Size: " + sll.size()); // print sorted list & size
    }

    private static void testRemoveIndex() {
        List<Integer> sll = new SinglyLinkedList<>();

        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * 50);
            sll.add(random); // fill list with 10 random #'s
        }

        System.out.println(sll.toString() + " Size: " + sll.size()); // print list & size
        sll.remove(-2); // removes second to last element
        System.out.println(sll.toString() + " Size: " + sll.size()); // print list & size

    }

    private static void testReverseEmptyList() {
        List<Integer> sll = new SinglyLinkedList<>();

        System.out.println(sll.toString() + " Size: " + sll.size()); // print list & size
        sll.reverse(); // reverse empty list
        System.out.println(sll.toString() + " Size: " + sll.size()); // print list & size

    }

    private static void testReverseFullList() {
        List<Integer> sll = new SinglyLinkedList<>();

        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * 50);
            sll.add(random); // fill list with random elements
        }

        System.out.println(sll.toString() + " Size: " + sll.size()); // print current list
        sll.reverse(); // reverse the list
        System.out.println(sll.toString() + " Size: " + sll.size()); // prints reversed list
    }

    private static void testAddOther() {
        List<Integer> sll = new SinglyLinkedList<>();

        for (int i = 0; i < 5; i++) {
            int random = (int) (Math.random() * 15);
            sll.add(random); // fill list with random elements
        }

        System.out.println(sll.toString() + " Size: " + sll.size());

        for (int i = 0; i < 5; i++) {
            int random = (int) (Math.random() * 15);
            sll.add(random); // fill up 'other' list with random elements
        }

        System.out.println(sll.toString() + " Size: " + sll.size());
        // appends list other's elements to the end of the default list
    }

    private static void testSetIndex() {
        List<Integer> sll = new SinglyLinkedList<>();

        for (int i = 0; i < 5; i++) {
            int random = (int) (Math.random() * 10);
            sll.add(random); // fill up list with random elements
        }

        System.out.println(sll.toString() + " Size: " + sll.size());
        // prints current list (before alteration)

        System.out.println("Item changed: " + sll.set(-2, 12));
        // changes item in list and returns item previously stored at specified index

        System.out.println(sll.toString() + " Size: " + sll.size());
        // prints current list (after alteration)
    }
}
