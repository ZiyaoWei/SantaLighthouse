package org.wzy.santa.optimizer;

import java.util.List;
import java.util.Map;

import org.wzy.santa.Optimizer;
import org.wzy.santa.data.Chimney;

public class DummyOptimizer extends Optimizer {

    public DummyOptimizer(Map<Integer, Chimney> chimneyMap) {
        super(chimneyMap);
    }

    @Override
    public List<List<Integer>> optimize() {
        return null;
    }

}
