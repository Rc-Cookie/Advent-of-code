import java.io.File;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Scanner;

import rccookie.util.Console;

public class Main {

    /**
     * Overrides the current day. If null the actual day will be used.
     */
    public static final Integer DAY_OVERRIDE = null;


    // ----------------------------------------------------------------







    // ----------------------------------------------------------------


    public static void day2_1(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("data/day2.passwords"));
        
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

    public static void day2_2(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("data/day2.passwords"));
        
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


    // ------------------------------------------------------------------------------------------------


    public static void day1_1(String[] args) {
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

    public static void day1_2(String[] args) {
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







    public static void main(String[] args) {
        try {
            
            int day = DAY_OVERRIDE != null ? DAY_OVERRIDE : Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            Method method;
            try {
                method = Main.class.getDeclaredMethod("day" + day + "_2", String[].class);
            } catch(NoSuchMethodException e) {
                method = Main.class.getDeclaredMethod("day" + day + "_1", String[].class);
            }

            method.setAccessible(true);
            method.invoke(null, (Object)args); // As element of array because it is the first (and only) argument of the method
        
        } catch (Exception e) { e.printStackTrace(); }
    }
}
