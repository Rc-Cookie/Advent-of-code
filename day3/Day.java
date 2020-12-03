package day3;

import rccookie.util.Console;

public class Day extends util.Day {

    private static final char TREE = '#';

    @Override
    public void run1(String[] args) throws Exception {

        final int width = args[0].length();
        Console.log("Width: " + width);
        int count = 0;

        for(int i=0; i<args.length; i++) {
            Console.log(i % width);
            if(args[i].charAt((i * 3) % width) == TREE) count++;
        }

        Console.log("Count: " + count);
    }
    
}
