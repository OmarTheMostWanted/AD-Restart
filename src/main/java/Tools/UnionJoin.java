package Tools;

import java.util.*;

public class UnionJoin {
    private int[] sets;

    public UnionJoin(int size) {
        sets = new int[size];
        Arrays.fill(sets, -1);
    }

    public int Find(int e) {
        int pointer = sets[e];
        if (pointer < 0) {
            return e;
        } else return Find(pointer);
    }

    public void Join(int a, int b) {
        var setA = Find(a);
        var setB = Find(b);
        if(sets[setA] <= sets[setB]){
            sets[b] = setA;
        } else {
            sets[a] = setB;
        }
    }
}
