package org.wzy.santa.util;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.wzy.santa.Loader;
import org.wzy.santa.data.Chimney;

public class PathCalculatorTest {
    List<Integer> pathA = new ArrayList<>();
    List<Integer> pathB = new ArrayList<>();
    Map<Integer, Chimney> chimneyMap = new HashMap<Integer, Chimney>();

    @Before
    public void loadFile() throws FileNotFoundException {
        File file = new File("path.csv");
        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        while (scanner.hasNext()) {
            String[] nums = scanner.nextLine().split(",");
            pathA.add(Integer.parseInt(nums[0].trim()));
            pathB.add(Integer.parseInt(nums[1].trim()));
        }
        chimneyMap = Loader.loadChinmeyMap("santa_cities.csv");
        assert pathA.size() == 150000;
        assert pathB.size() == 150000;
    }

    @Test
    public void pathCalculatorTestOne() {
        System.out.println(PathCalculator.calculateOnePath(pathA, chimneyMap));
        assertTrue(PathCalculator.calculateOnePath(pathA, chimneyMap) < 1290678098.0);
        System.out.println(PathCalculator.calculateOnePath(pathB, chimneyMap));
        assertTrue(PathCalculator.calculateOnePath(pathB, chimneyMap) < 1290678098.0);
    }

    @Test
    public void pathCalculatorTestTwo() {
        System.out.println(PathCalculator.calculateTwoPathes(pathA, pathB,
                chimneyMap));
        assertTrue(PathCalculator.calculateTwoPathes(pathA, pathB, chimneyMap) < 1290678098.0);
    }

}
