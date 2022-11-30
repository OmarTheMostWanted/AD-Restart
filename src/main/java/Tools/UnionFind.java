package Tools;


import java.util.Arrays;

public class UnionFind {
    private int[] set;
    private int[] rank;

    public void MakeSet(int n){
        set = new int[n];
        rank = new int[n];
        for (int i = 0; i < set.length; i++) {
            set[i] = i;
            rank[i] = 0;
        }
    }

    public int Find(int x){
        if(set[x] != x) set[x] = Find(set[x]);
        return set[x];
    }

    public boolean Union(int x , int y){
        int xSet = Find(x);
        int ySet = Find(y);
        if(xSet == ySet){
            return false;
        }
        if(rank[xSet] < rank[ySet]){
            set[xSet]  = ySet;
            rank[ySet]++;

        }else { set[ySet] = xSet;
            if(rank[xSet] == rank[ySet]){
                rank[xSet]++;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "UnionFind{" +
                "set=" + Arrays.toString(set) +
                ", rank=" + Arrays.toString(rank) +
                '}';
    }
}
