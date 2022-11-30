package Main;

import java.util.Comparator;

public class NumberTurnsComparator implements Comparator<Beer> {
    @Override
    public int compare(Beer o1, Beer o2) {
        if (o1.getNumberTurns() == o2.getNumberTurns()) {
            return 0;
        }
        if (o1.getNumberTurns() > o2.getNumberTurns()) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
