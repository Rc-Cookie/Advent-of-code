package day2;

import java.util.Scanner;

import rccookie.util.Console;

public class Day extends util.Day {

    @Override
    public void run1() throws Exception {
        Scanner sc = inputScanner();
        
        int valid = 0;

        while(sc.hasNextLine()) {
            StringBuilder line = new StringBuilder(sc.nextLine());
            
            int min = Integer.parseInt(line.substring(0, line.indexOf("-")));
            int max = Integer.parseInt(line.substring(line.indexOf("-") + 1, line.indexOf(" ")));
            line.delete(0, line.indexOf(" ") + 1);

            char c = line.charAt(0);
            line.delete(0, line.indexOf(" ") + 1);
            
            int count = 0;
            for(int i=0; i<line.length(); i++) {
                if(line.charAt(i) == c) count++;
            }

            if(count >= min && count <= max) valid++;
        }

        Console.log("Valid: " + valid);
    }

    @Override
    public void run2() throws Exception {
        Scanner sc = inputScanner();

        int valid = 0;

        while(sc.hasNextLine()) {
            StringBuilder line = new StringBuilder(sc.nextLine());
            
            int a = Integer.parseInt(line.substring(0, line.indexOf("-")));
            int b = Integer.parseInt(line.substring(line.indexOf("-") + 1, line.indexOf(" ")));
            line.delete(0, line.indexOf(" ") + 1);

            char c = line.charAt(0);
            line.delete(0, line.indexOf(" ")); // No +1 means first character is automatically '1'
            
            if(line.length() <= a) continue;
            if(line.charAt(a) == c) {
                if(line.length() <= b || line.charAt(b) != c) valid++;
            }
            else if(line.length() > b && line.charAt(b) == c) valid++;
        }

        Console.log("Valid: " + valid);
    }
}
