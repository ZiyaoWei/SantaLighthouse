package org.wzy.santa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.wzy.santa.data.Chimney;

public class Loader {
    public static final int LIST_SIZE = 150000;

    public static Map<Integer, Chimney> loadChinmeyMap(String inputFile)
            throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFile));
        Map<Integer, Chimney> chinmeyMap = new HashMap<Integer, Chimney>();
        scanner.nextLine();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] nums = line.split(",");
            int id = Integer.parseInt(nums[0]);
            int x = Integer.parseInt(nums[1]);
            int y = Integer.parseInt(nums[2]);
            Chimney chimney = new Chimney(id, x, y);
            chinmeyMap.put(id, chimney);
        }
        return chinmeyMap;
    }
}
