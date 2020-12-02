import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        String day = "day" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        try{
            Main.class.getDeclaredMethod(day, String[].class);
        } catch (NoSuchMethodException e) { }
    }

    public void day1(String[] args) {
        
    }
}
