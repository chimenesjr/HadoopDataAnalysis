package Tests.Helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class OutputCollector<T1, T2> {

    public Map<T1, T2> list;

    public void collect(T1 t1, T2 t2) {
        
        if(list == null)
            list = new HashMap<T1, T2>();

        this.list.put(t1, t2);
    }
}
