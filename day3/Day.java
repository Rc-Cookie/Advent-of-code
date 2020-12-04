package day3;

import rccookie.util.Console;

public class Day extends util.Day {

    private static final char TREE = '#';

    @Override
    public void run1() throws Exception {
        Console.log("Count: " + numberOfTreeHits(3, 1));
    }

    @Override
    public void run2() throws Exception {
        int[] hits = {
            numberOfTreeHits(1, 1),
            numberOfTreeHits(3, 1),
            numberOfTreeHits(5, 1),
            numberOfTreeHits(7, 1),
            numberOfTreeHits(1, 2),
        };
        long result = 1;
        for(int i=0; i<hits.length; i++) result *= hits[i];

        Console.log(hits, "Result: " + result);
    }

    private int numberOfTreeHits(int vx, int vy) {
        final String[] input = inputInLines();
        if(input.length == 0) return 0;
        final int width = input[0].length();
        int count = 0;
        for(int i=0; i<input.length/vy; i++) {
            if(input[i * vy].charAt((i * vx) % width) == TREE) count++;
        }
        return count;
    }
}
