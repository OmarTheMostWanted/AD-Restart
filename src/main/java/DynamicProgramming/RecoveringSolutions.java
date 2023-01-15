package DynamicProgramming;

import java.util.LinkedList;
import java.util.List;

public class RecoveringSolutions {
    public static List<Integer> solve(int n, int[] nodes, int result, int[] mem) {
        var list = new LinkedList<Integer>();
        int counter = n;

        while (counter > 0) {
            if (mem[counter] == nodes[counter] + mem[counter - 1]) {
                list.addFirst(counter);
                counter = counter - 1;
            } else if (mem[counter] == nodes[counter] + mem[counter - 2]) {
                list.addFirst(counter);
                counter = counter - 2;
            } else if (mem[counter] == nodes[counter] + mem[counter - 3]) {
                list.addFirst(counter);
                counter = counter - 3;
            } else counter--;
        }

        return list;
    }
}
