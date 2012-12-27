package org.wzy.santa.optimizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.wzy.santa.Optimizer;
import org.wzy.santa.data.Chimney;
import org.wzy.santa.optimizer.generator.Generator;
import org.wzy.santa.optimizer.generator.RandomGenerator;
import org.wzy.santa.util.PathCalculator;
import org.wzy.santa.util.PathValidator;

public class RandomOptimizer extends Optimizer {

    public RandomOptimizer(Map<Integer, Chimney> chimneyMap) {
        super(chimneyMap);
    }

    @Override
    public List<List<Integer>> optimize() {
        int max_iteration = 10;
        Generator rpg = new RandomGenerator();
        List<List<Integer>> answer = new ArrayList<>();
        double[] p = new double[] { -1, -1 };
        answer.add(rpg.generate(chimneyMap));
        p[0] = PathCalculator.calculateOnePath(answer.get(0), chimneyMap);
        while (answer.size() < 2) {
            List<Integer> path = rpg.generate(chimneyMap);
            if (PathValidator.validate(answer.get(0), path)) {
                double pt = PathCalculator.calculateOnePath(path, chimneyMap);
                answer.add(path);
                p[1] = pt;
            }
        }
        for (int i = 0; i < max_iteration; i++) {
            List<Integer> path = rpg.generate(chimneyMap);
            double pt = PathCalculator.calculateOnePath(path, chimneyMap);
            // System.out.println(pt);
            for (int j = 0; j < 2; j++) {
                if (pt < p[j]
                        && PathValidator.validate(path, answer.get(1 - j))) {
                    System.out.println(j + " " + pt);
                    answer.set(j, path);
                    p[j] = pt;
                    break;
                }
            }
        }
        SwitchOptimizer.optimizer(answer, chimneyMap);
        System.out.println("0 "
                + PathCalculator.calculateOnePath(answer.get(0), chimneyMap));
        System.out.println("1 "
                + PathCalculator.calculateOnePath(answer.get(1), chimneyMap));
        System.out.println(PathValidator.validate(answer.get(0), answer.get(1)));
        System.out.println(PathCalculator.calculateTwoPathes(answer.get(0),
                answer.get(1), chimneyMap));
        return answer;
    }

}
