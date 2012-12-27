package org.wzy.santa.util;

import java.util.List;
import java.util.Map;

import org.wzy.santa.data.Chimney;

public class PathCalculator {

    public static double calculateOnePath(List<Integer> path,
            Map<Integer, Chimney> chimneyMap) {
        double result = 0;
        for (int i = 1; i < path.size(); i++) {
            Chimney c0 = chimneyMap.get(path.get(i - 1));
            Chimney c1 = chimneyMap.get(path.get(i));
            result += c0.distanceTo(c1);
        }
        return result;
    }

    public static double calculateTwoPathes(List<Integer> list,
            List<Integer> list2, Map<Integer, Chimney> chimneyMap) {
        double pA = calculateOnePath(list, chimneyMap);
        double pB = calculateOnePath(list2, chimneyMap);
        System.out.println(pA + " " + pB);
        return Math.max(pA, pB);
    }

}
