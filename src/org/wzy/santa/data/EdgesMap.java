package org.wzy.santa.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdgesMap {
    Map<Integer, Integer> edgesMap = new HashMap<Integer, Integer>();

    public EdgesMap(List<Integer> path) {
        int s = path.size();
        for (int i = 1; i < s; i++) {
            edgesMap.put(path.get(i - 1), path.get(i));
        }
    }

    public boolean containsEdge(Integer a, Integer b) {
        if (a == null || b == null) {
            return false;
        }
        return b.equals(edgesMap.get(a)) || a.equals(edgesMap.get(b));
    }

    public void put(Integer a, Integer b) {
        edgesMap.put(a, b);
    }

    public Integer get(Integer a) {
        return edgesMap.get(a);
    }
}
