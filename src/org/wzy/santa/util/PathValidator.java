package org.wzy.santa.util;

import java.util.List;
import org.wzy.santa.Loader;
import org.wzy.santa.data.EdgesMap;

public class PathValidator {

    public static boolean validate(List<Integer> pathA, List<Integer> pathB) {
        return validateOnePath(pathA) && validateOnePath(pathB)
                && validateTwoPathes(pathA, pathB);
    }

    public static boolean validateOnePath(List<Integer> pathB) {
        if (pathB == null) {
            return false;
        }
        int s = Loader.LIST_SIZE;
        if (s != pathB.size()) {
            return false;
        }
        boolean[] nodes = new boolean[s];
        for (int i = 0; i < s; i++) {
            nodes[i] = false;
        }
        for (int i : pathB) {
            nodes[i] = true;
        }
        for (boolean i : nodes) {
            if (!i) {
                System.out.println("Does not have all chimneys?");
                return false;
            }
        }
        return true;
    }

    private static boolean validateTwoPathes(List<Integer> path,
            List<Integer> list) {
        int s = Loader.LIST_SIZE;
        EdgesMap edgesMap = new EdgesMap(path);
        for (int i = 1; i < s; i++) {
            Integer x0 = list.get(i - 1);
            Integer y0 = list.get(i);
            if (edgesMap.containsEdge(x0, y0)) {
                return false;
            }
        }
        return true;
    }

}
