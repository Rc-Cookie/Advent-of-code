package day1;

import rccookie.util.Console;

public class Day extends util.Day {

    @Override
    public void run1(String[] args) {
        int[] numbers = new int[args.length];
        for(int i=0; i<numbers.length; i++) numbers[i] = Integer.parseInt(args[i]);

        for(int i=0; i<numbers.length-1; i++) {
            for(int j=i+1; j<numbers.length; j++) {
                if(numbers[i] + numbers[j] == 2020) {
                    Console.log(numbers[i], numbers[j], numbers[i] * numbers[j]);
                }
            }
        }
    }

    @Override
    public void run2(String[] args) {
        int[] numbers = new int[args.length];
        for(int i=0; i<numbers.length; i++) numbers[i] = Integer.parseInt(args[i]);

        for(int i=0; i<numbers.length-2; i++) {
            for(int j=i+1; j<numbers.length-1; j++) {
                for(int k=j+1; k<numbers.length; k++) {
                    if(numbers[i] + numbers[j] + numbers[k] == 2020) {
                        Console.log(numbers[i], numbers[j], numbers[k], numbers[i] * numbers[j] * numbers[k]);
                    }
                }
            }
        }
    }
}
