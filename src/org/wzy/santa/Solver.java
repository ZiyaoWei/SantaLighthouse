package org.wzy.santa;

import java.io.FileNotFoundException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.wzy.santa.data.Chimney;
import org.wzy.santa.optimizer.SwitchOptimizer;
import org.wzy.santa.optimizer.generator.Generator;
import org.wzy.santa.optimizer.generator.LoadFromFileGenerator;

public class Solver {

    private static String FILE_NAME = "path.csv";
    public static long jvmUpTime = ManagementFactory.getRuntimeMXBean().getStartTime();

    private void solve() throws FileNotFoundException {
        Map<Integer, Chimney> mapChimney = Loader.loadChinmeyMap("santa_cities.csv");
        System.out.println("Data load time: " + (System.currentTimeMillis() - jvmUpTime));
        // Optimizer optimizer = new CloseGenerator(mapChimney);
        Generator gen = new LoadFromFileGenerator("answer.csv");
        List<List<Integer>> answer = new ArrayList<>();
        answer.add(gen.generate(mapChimney));
        answer.add(gen.generate(mapChimney));
        Saver saver = new Saver();
        saver.save(FILE_NAME,
                SwitchOptimizer.optimizer(answer, mapChimney));
        System.out.println("After solving: " + (System.currentTimeMillis() - jvmUpTime));
    }

    public static void main(String[] args) throws FileNotFoundException {
        Solver slr = new Solver();
        slr.solve();
    }

}
