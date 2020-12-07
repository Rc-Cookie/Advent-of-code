package day7;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Regulation {
    public Set<String> superTypes = new HashSet<>();
    public final Set<SubBagType> subTypes;

    public Regulation(Set<SubBagType> subTypes) {
        this.subTypes = Collections.unmodifiableSet(subTypes);
    }

    @Override
    public String toString() {
        return "[Super: " + superTypes + ", Sub: " + subTypes + ']';
    }


    static class SubBagType {
        public final String name;
        public final int count;

        public SubBagType(String subType, int count) {
            this.name = subType;
            this.count = count;
        }

        @Override
        public String toString() {
            return count + "x " + name;
        }
    }
}
