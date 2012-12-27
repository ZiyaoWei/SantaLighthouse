package org.wzy.santa.optimizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.wzy.santa.data.Chimney;
import org.wzy.santa.data.EdgesMap;
import org.wzy.santa.util.PathCalculator;

public class SwitchOptimizer {

    private static Random rand = new Random();

    public static List<List<Integer>> optimizer(List<List<Integer>> answer,
            Map<Integer, Chimney> chimneyMap) {
        int max_improvement = 1000000;
        int max_attempts = 10;
        List<EdgesMap> pathMaps = new ArrayList<>();
        pathMaps.add(new EdgesMap(answer.get(0)));
        pathMaps.add(new EdgesMap(answer.get(1)));
        int p = 0;
        int g = 0;
        for (int i = 0; i < max_improvement; i++) {
            boolean improved = false;
            int item = i % 10 != 0 ? 1 : 0;
            p++;
            // System.out.println("round: " + i);
            for (int j = 0; j < max_attempts; j++) {
                // System.out.println("attempt: " + j);
                improved = improve(answer.get(item), answer.get(1 - item),
                        pathMaps.get(item), pathMaps.get(1 - item), chimneyMap);
                if (improved) {
                    g++;
                    break;
                }
            }
            if (i % 100000 == 0) {
                System.out.println(g * 1.0 / p * 1.0);
                if (g != 0) {
                    System.out.println(PathCalculator.calculateTwoPathes(answer.get(0),
                            answer.get(1), chimneyMap));
                }
                g = 0;
                p = 0;
            }
        }
        return answer;
    }

    private static boolean improve(List<Integer> list, List<Integer> list2,
            EdgesMap edgesMap, EdgesMap edgesMap2,
            Map<Integer, Chimney> chimneyMap) {
        int a = rand.nextInt(list.size());
        int b = rand.nextInt(list.size());
        if (a == b) {
            return false;
        }

        double sideSum = sideSum(list, a, b, chimneyMap);
        boolean reduce = sideSum > 0;
        boolean distinct = distinct(list, a, b, edgesMap2);
        if (reduce && distinct) {
            System.out.println(sideSum);
            Integer cA = list.get(a);
            Integer cAp = a == 0 ? null : list.get(a - 1);
            Integer cAn = a == list.size() - 1 ? null : list.get(a + 1);
            Integer cB = list.get(b);
            Integer cBp = b == 0 ? null : list.get(b - 1);
            Integer cBn = b == list.size() - 1 ? null : list.get(b + 1);
            if (cAp != null) {
                edgesMap.put(cAp, cB);
            }
            if (cAn != null) {
                edgesMap.put(cB, cAn);
            }
            if (cBp != null) {
                edgesMap.put(cBp, cA);
            }
            if (cBn != null) {
                edgesMap.put(cA, cBn);
            }
            Collections.swap(list, a, b);
            return true;
        }
        return false;
    }

    private static boolean distinct(List<Integer> list, int a, int b,
            EdgesMap edgesMap2) {
        Integer cA = list.get(a);
        Integer cAp = a == 0 ? null : list.get(a - 1);
        Integer cAn = a == list.size() - 1 ? null : list.get(a + 1);
        Integer cB = list.get(b);
        Integer cBp = b == 0 ? null : list.get(b - 1);
        Integer cBn = b == list.size() - 1 ? null : list.get(b + 1);
        return !(edgesMap2.containsEdge(cAp, cB) || edgesMap2.containsEdge(cB, cAn)
                || edgesMap2.containsEdge(cBp, cA) || edgesMap2.containsEdge(cA, cBn));
    }

    private static double sideSum(List<Integer> list, int a, int b,
            Map<Integer, Chimney> chimneyMap) {
        double p = 0;
        Chimney cA = chimneyMap.get(list.get(a));
        Chimney cAp = a == 0 ? null : chimneyMap.get(list.get(a - 1));
        Chimney cAn = a == list.size() - 1 ? null : chimneyMap.get(list.get(a + 1));
        Chimney cB = chimneyMap.get(list.get(b));
        Chimney cBp = b == 0 ? null : chimneyMap.get(list.get(b - 1));
        Chimney cBn = b == list.size() - 1 ? null : chimneyMap.get(list.get(b + 1));
        if (a - b != 1) {
            if (cAp != null) {
                p += cA.distanceTo(cAp);
                p -= cB.distanceTo(cAp);
            }
            if (cBn != null) {
                p -= cA.distanceTo(cBn);
                p += cB.distanceTo(cBn);
            }
        }
        if (b - a != 1) {
            if (cAn != null) {
                p += cA.distanceTo(cAn);
                p -= cB.distanceTo(cAn);
            }
            if (cBp != null) {
                p -= cA.distanceTo(cBp);
                p += cB.distanceTo(cBp);
            }
        }
        return p;
    }
}
