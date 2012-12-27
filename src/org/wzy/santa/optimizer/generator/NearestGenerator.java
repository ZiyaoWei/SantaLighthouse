package org.wzy.santa.optimizer.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.wzy.santa.Loader;
import org.wzy.santa.Solver;
import org.wzy.santa.data.Chimney;
import org.wzy.santa.data.EdgesMap;

public class NearestGenerator implements Generator {
    EdgesMap em;

    public NearestGenerator(EdgesMap m) {
        em = m;
    }

    @Override
    public List<Integer> generate(final Map<Integer, Chimney> chimneyMap) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Chimney> m = new HashMap<>(chimneyMap);
        result.add(0);
        m.remove(0);
        for (int i = 1; i < Loader.LIST_SIZE; i++) {
            if (i % 1000 == 0) {
                System.out.println(i);
                System.out.println(System.currentTimeMillis() - Solver.jvmUpTime);
                // System.out.println(m.size());
            }
            Chimney chi = chimneyMap.get(result.get(i - 1));
            double best = -1;
            int id = -1;
            for (int k : m.keySet()) {
                Chimney other = m.get(k);
                double d = chi.distanceTo(other);
                if ((em == null || !em.containsEdge(chi.getId(), other.getId()))
                        && (best == -1 || d < best)) {
                    best = d;
                    id = k;
                }
            }
            // System.out.println(id);
            result.add(id);
            m.remove(id);
        }
        return result;
    }

}
