package org.wzy.santa.optimizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.wzy.santa.Optimizer;
import org.wzy.santa.data.Chimney;
import org.wzy.santa.data.EdgesMap;
import org.wzy.santa.optimizer.generator.Generator;
import org.wzy.santa.optimizer.generator.NearestGenerator;
import org.wzy.santa.util.PathCalculator;
import org.wzy.santa.util.PathValidator;

public class CloseGenerator extends Optimizer {

    public CloseGenerator(Map<Integer, Chimney> chimneyMap) {
        super(chimneyMap);
    }

    @Override
    public List<List<Integer>> optimize() {
        Generator gen = new NearestGenerator(null);
        List<List<Integer>> answer = new ArrayList<>();
        answer.add(gen.generate(chimneyMap));
        gen = new NearestGenerator(new EdgesMap(answer.get(0)));
        answer.add(gen.generate(chimneyMap));
        // SwitchOptimizer.optimizer(answer, chimneyMap);
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
