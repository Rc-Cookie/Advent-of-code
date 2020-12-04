package day4;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import rccookie.util.Console;

public class Day extends util.Day {

    @Override
    public void run1(String[] args) throws Exception {
        ArrayList<String> currentPassport = new ArrayList<>();
        Scanner sc = inputScanner();


        int valid = 0;

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.replaceAll(" ", "").length() > 0) {
                currentPassport.add(line);
                continue;
            }

            int count = 0;
            mappingLoop:
            for(String mapping : currentPassport.stream().collect(Collectors.joining(" ")).split(" ")) {
                if(mapping.startsWith("cid")) continue mappingLoop;
                count++;
            }
            currentPassport.clear();
            if(count >= 7) valid++;
        }

        int count = 0;
            mappingLoop:
            for(String mapping : currentPassport.stream().collect(Collectors.joining(" ")).split(" ")) {
                if(mapping.startsWith("cid")) continue mappingLoop;
                count++;
            }
            currentPassport.clear();
            if(count >= 7) valid++;

        Console.log("Valid: " + valid);
    }

    @Override
    public void run2(String[] args) throws Exception {
        super.run2(args);
    }
}
