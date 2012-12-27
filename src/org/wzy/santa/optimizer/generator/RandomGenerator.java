package org.wzy.santa.optimizer.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.wzy.santa.data.Chimney;

public class RandomGenerator implements Generator {

    @Override
    public List<Integer> generate(Map<Integer, Chimney> chimneyMap) {
        List<Integer> result = new ArrayList<>(chimneyMap.keySet());
        Collections.shuffle(result);
        return result;
    }

}
