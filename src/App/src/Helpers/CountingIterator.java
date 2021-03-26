package HDA.Helpers;

import java.util.Iterator;

public class CountingIterator {
    public static int Count(Iterator it) {
        int j = 0;
        while (it.hasNext()) {
            it.next();
            j++;
        }
        return j;
    }
}
