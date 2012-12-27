package org.wzy.santa;

import java.util.List;
import java.util.Map;

import org.wzy.santa.data.Chimney;

public abstract class Optimizer {
    protected Map<Integer, Chimney> chimneyMap;

    public Optimizer(Map<Integer, Chimney> chimneyMap) {
        this.chimneyMap = chimneyMap;
    }

    abstract public List<List<Integer>> optimize();

}
