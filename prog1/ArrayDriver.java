public class ArrayDriver {

    public static void main(String[] args) {

        int testSize = 1600000;
        long lastTime = 1;
        System.out.println("Testing add in order ... ");

        for (int i = 0; i < 5; ++i) {
            long begin = System.currentTimeMillis(); // start time
            testAddInOrder(testSize);
            long end = System.currentTimeMillis(); // stop time

            long timeSpan = end - begin;
            double timeFactor = (double) timeSpan / lastTime; // time factor/ratio

            System.out.println("Size: " + testSize + "\t\t\t\t" + "Time: " + timeSpan + " ms" + "\t\t\t\t" + "Factor: "
                    + timeFactor);

            lastTime = timeSpan;
            testSize *= 2; // double testSize
        }
        System.out.println();

        System.out.println("Testing add in reverse ... ");
        testAddReverse(1600);
        System.out.println();

        System.out.println("Testing clear ... ");
        testClear(16000);
        System.out.println();

        System.out.println("Testing remove ... ");
        testRemove(5000);
        System.out.println();

    }

    private static void testAddInOrder(int testSize) {
        SortedArrayList<Integer> sal = new SortedArrayList<Integer>();
        for (int i = 0; i <= testSize - 1; i++) {
            sal.add(i);
        }
    }

    private static void testAddReverse(int testSize) {
        SortedArrayList<Integer> sal = new SortedArrayList<Integer>();
        long lastTime = 1;

        for (int i = 0; i < 5; ++i) { // up to 5 tests
            long begin = System.currentTimeMillis(); // start time

            for (int j = testSize - 1; j >= 0; j--) {
                sal.add(j); // add elements in reverse
            }
            long end = System.currentTimeMillis(); // stop time
            long timeSpan = end - begin;
            double timeFactor = (double) timeSpan / lastTime; // time factor/ratio

            System.out.println("Size: " + testSize + "\t\t\t\t" + "Time: " + timeSpan + " ms" + "\t\t\t\t" + "Factor: "
                    + timeFactor);

            testSize *= 2; // double testSize
            lastTime = timeSpan;
        }
    }

    private static void testRemove(int testSize) {
        SortedArrayList<Integer> sal = new SortedArrayList<>();
        long lastTime = 1;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <= testSize - 1; j++) {
                sal.add(j); // add elements
            }

            long begin = System.currentTimeMillis(); // start time
            int counter = testSize - 1; // set counter to end of testSize

            while (!sal.isEmpty()) {
                sal.remove(counter); // continue removing elements until isEmpty() is true
                --counter; // move counter
            }

            long end = System.currentTimeMillis(); // stop time
            long timeSpan = end - begin;
            double timeFactor = (double) timeSpan / lastTime; // time factor/ratio

            System.out.println("Size: " + testSize + "\t\t\t\t" + "Time: " + timeSpan + " ms" + "\t\t\t\t" + "Factor: "
                    + timeFactor);

            testSize *= 2; // double testSize
            lastTime = timeSpan;
        }
    }

    private static void testClear(int testSize) {
        SortedArrayList<Integer> sal = new SortedArrayList<Integer>();
        double lastTime = 1;

        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < testSize - 1; j++) {
                sal.add(j); // add elements
            }
            long begin = System.currentTimeMillis(); // start time
            sal.clear(); // clear array
            long end = System.currentTimeMillis(); // stop time

            long timeSpan = end - begin;
            lastTime = timeSpan;
            lastTime /= (double) timeSpan * 1.0; // time factor/ratio

            System.out.println("Size: " + testSize + "\t\t\t\t" + "Time: " + timeSpan + " ms" + "\t\t\t\t" + "Factor: "
                    + lastTime);

            testSize *= 2; // double testSize
        }
    }
}
