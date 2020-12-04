import java.lang.reflect.Constructor;
import java.util.Calendar;

import rccookie.util.Console;
import util.Day;
import util.DayGenerator;
import util.Day.NotImplementedException;

public class Main {

    /**
     * Overrides the current day. If null the actual day will be used.
     */
    public static final Integer DAY_OVERRIDE = null;



    // ----------------------------------------------------------------



    public static void main(String[] args) {
        try {
            
            int day = DAY_OVERRIDE != null ? DAY_OVERRIDE : Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            if(args != null && args.length > 0) day = Integer.parseInt(args[0]);

            if(DayGenerator.generateFilesForDay(day)) {
                Console.log("Generated template for day " + day);
                return;
            }

            @SuppressWarnings("unchecked")
            Class<Day> cls = (Class<Day>)Class.forName("day" + day + ".Day");
            Constructor<Day> ctor = cls.getDeclaredConstructor();
            ctor.setAccessible(true);
            Day dayInstance = ctor.newInstance();
            try{
                dayInstance.run2();
            } catch(NotImplementedException e) {
                dayInstance.run1();
            }
        
        } catch (Exception e) { e.printStackTrace(); }
    }
}
