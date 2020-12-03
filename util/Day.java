package util;

import static java.nio.StandardCharsets.US_ASCII;
import static java.util.Files.readString;

public abstract class Day {

    public abstract void run1(String[] args) throws Exception;

    public void run2(String[] args) throws Exception {
        throw new NotImplementedException();
    };

    protected String getInput() {
        try {
            int day = Integer.parseInt(getClass().getSimpleName().subString(3));
            return readString("data/day" + day + ".input", US_ASCII);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public class NotImplementedException extends Exception {
        private static final long serialVersionUID = -4881948333783821683L;
    }
}
