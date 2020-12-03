package util;

import java.io.File;
import java.util.Formatter;

public class DayGenerator {
    public static final void createDay(int day) {
        File file = new File("day" + day + "\\Day.java");
        if(file.exists()) return;
        file.mkDirs();

        Formatter f = new Formatter(file);
        
    }
}
