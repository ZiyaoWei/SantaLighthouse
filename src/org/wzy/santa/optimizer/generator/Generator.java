package org.wzy.santa.optimizer.generator;

import java.util.List;
import java.util.Map;

import org.wzy.santa.data.Chimney;

public interface Generator {
    public List<Integer> generate(Map<Integer, Chimney> chimneyMap);

}
