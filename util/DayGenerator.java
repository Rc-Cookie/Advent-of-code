package util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Formatter;

public class DayGenerator {

    public static final boolean createDay(int day) {
        try {
            File dir = new File("day" + day + "");
            if(!dir.exists()) dir.mkdir();

            File file = new File("day" + day + "/Day.java");
            if(file.exists()) return false;
            file.createNewFile();

            String fileText = Files.readString(Path.of("data/Day.template"));

            Formatter f = new Formatter(file);

            f.format(fileText, day);

            f.close();
            return true;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
