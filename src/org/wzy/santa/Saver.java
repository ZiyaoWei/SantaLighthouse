package org.wzy.santa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Saver {

    public void save(String fileName, List<List<Integer>> list)
            throws FileNotFoundException {
        File file = new File(fileName);
        PrintWriter pw = new PrintWriter(file);
        pw.println("path1, path2");
        List<Integer> path1 = list.get(0);
        List<Integer> path2 = list.get(1);
        for (int i = 0; i < path1.size(); i++) {
            pw.println(path1.get(i) + ", " + path2.get(i));
            pw.flush();
        }
    }

}
