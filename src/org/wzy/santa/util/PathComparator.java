package org.wzy.santa.util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.wzy.santa.data.Chimney;

public class PathComparator implements Comparator<List<Integer>> {
    Map<Integer, Chimney> chimneyMap;

    public PathComparator(Map<Integer, Chimney> chimneyMap) {
        this.chimneyMap = chimneyMap;
    }

    public int compare(List<Integer> o1, List<Integer> o2) {
        Double p1 = PathCalculator.calculateOnePath(o1, chimneyMap);
        Double p2 = PathCalculator.calculateOnePath(o2, chimneyMap);
        return p1.compareTo(p2);
    }

}
