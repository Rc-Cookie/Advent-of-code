package day3;

import rccookie.util.Console;

public class Day extends util.Day {

    private static final char TREE = '#';

    @Override
    public void run1(String[] args) throws Exception {
        Console.log("Count: " + numberOfTreeHits(args, 3, 1));
    }

    @Override
    public void run2(String[] args) throws Exception {
        int[] hits = {
            numberOfTreeHits(args, 1, 1),
            numberOfTreeHits(args, 3, 1),
            numberOfTreeHits(args, 5, 1),
            numberOfTreeHits(args, 7, 1),
            numberOfTreeHits(args, 1, 2),
        };
        long result = 1;
        for(int i=0; i<hits.length; i++) result *= hits[i];

        Console.log(hits, "Result: " + result);
    }

    private int numberOfTreeHits(String[] args, int vx, int vy) {
        final int width = args[0].length();
        int count = 0;
        for(int i=0; i<args.length/vy; i++) {
            if(args[i * vy].charAt((i * vx) % width) == TREE) count++;
        }
        return count;
    }
}
