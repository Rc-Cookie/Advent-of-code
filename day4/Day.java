package day4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import rccookie.util.Console;

public class Day extends util.Day {

    @Override
    public void run1() throws Exception {
        int valid = 0;
        for(Passport p : parsePassports()) {
            if(p.validKeys()) valid++;
        }
        Console.log("Valid: " + valid);
    }

    @Override
    public void run2() throws Exception {
        super.run2();
        int valid = 0;
        for(Passport p : parsePassports()) {
            if(p.valid()) valid++;
        }
        Console.log("Valid: " + valid);
    }

    private List<Passport> parsePassports() throws Exception {
        List<Passport> passports = new ArrayList<>();
        passports.add(new Passport());

        Scanner sc = inputScanner();

        while(sc. hasNextLine()) {
            String line = sc.nextLine();
            if(line.replaceAll(" ", "").length() == 0) {
                passports.add(new Passport());
                continue;
            }
            for(String pair : line.split(" ")) {
                int colonIndex = pair.indexOf(':');
                passports.get(passports.size() - 1).put(pair.substring(0, colonIndex), pair.substring(colonIndex + 1));
            }
        }

        return passports;
    }
}
