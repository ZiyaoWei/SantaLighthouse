package org.wzy.santa.optimizer.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.wzy.santa.data.Chimney;
import org.wzy.santa.util.PathCalculator;

public class LoadFromFileGenerator implements Generator {
    private List<List<Integer>> answer = new ArrayList<>();
    private int c = -1;
    
    public LoadFromFileGenerator(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file));
        List<Integer> pathA = new ArrayList<>();
        List<Integer> pathB = new ArrayList<>();
        scanner.nextLine();
        while (scanner.hasNext()) {
            String[] nums = scanner.nextLine().split(",");
            pathA.add(Integer.parseInt(nums[0].trim()));
            pathB.add(Integer.parseInt(nums[1].trim()));
        }
        answer.add(pathA);
        answer.add(pathB);
        
    }

    @Override
    public List<Integer> generate(Map<Integer, Chimney> chimneyMap) {
        c++;
        System.out.println(PathCalculator.calculateOnePath(answer.get(c), chimneyMap));
        return answer.get(c);
    }

}
