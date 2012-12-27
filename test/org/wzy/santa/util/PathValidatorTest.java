package org.wzy.santa.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class PathValidatorTest {
    List<Integer> pathA = new ArrayList<>();
    List<Integer> pathB = new ArrayList<>();

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
        System.out.println(pathA.size());
        assert pathA.size() == 150000;
        assert pathB.size() == 150000;
    }

    @Test
    public void testSuccessOnePathValidation() {
        assertTrue(PathValidator.validateOnePath(pathA));
        assertTrue(PathValidator.validateOnePath(pathB));
    }

    @Test
    public void testFailOnePathValidation() {
        pathA.set(0, pathA.get(2));
        pathB.set(1, pathA.get(10));
        assertTrue(!PathValidator.validateOnePath(pathA));
        assertTrue(!PathValidator.validateOnePath(pathB));
    }

    @Test
    public void testSuccessTwoPathesValidation() {
        assertTrue(PathValidator.validate(pathA, pathB));
    }

    @Test
    public void testFailTwoPathesValidation() {
        pathA.set(0, pathB.get(0));
        pathA.set(1, pathB.get(1));
        assertFalse(PathValidator.validate(pathA, pathB));
    }
}
