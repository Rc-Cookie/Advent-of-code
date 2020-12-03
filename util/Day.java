package util;

public abstract class Day {
    public abstract void run1(String[] args) throws Exception;
    public void run2(String[] args) throws Exception {
        throw new NotImplementedException();
    };

    public class NotImplementedException extends Exception {
        private static final long serialVersionUID = -4881948333783821683L;
    }
}
