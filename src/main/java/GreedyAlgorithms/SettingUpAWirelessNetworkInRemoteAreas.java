package GreedyAlgorithms;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class SettingUpAWirelessNetworkInRemoteAreas {

    public static class Edge {

        // from, to and length
        int x, y, l;

        public Edge(int from, int to, int length) {
            x = from;
            y = to;
            l = length;
        }

        @Override
        public String toString() {
            return "Edge{" + "x=" + x + ", y=" + y + ", l=" + l + '}';
        }
    }

    public static class Solution {

        public long cost, number;

        public Solution(long cost, long number) {
            this.cost = cost;
            this.number = number;
        }

        public static
        /**
         * @param n The number of stations
         * @param m The number of edges
         * @param b the budget
         * @param edges the edges in the network, you should ignore edges[0] and only use edges[1] to
         *     edges[m].
         */
        Solution setUpTheNetwork(int n, int m, int b, Edge[] edges) {
            PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.l, e2.l));
            for (int i = 1; i < edges.length; i++) {
                priorityQueue.add(edges[i]);
            }
            int c = 0;
            int number = 0;

            UnionFind unionFind = new UnionFind(n);
            while (!priorityQueue.isEmpty()){
                var e = priorityQueue.poll();
                if(unionFind.union(e.x , e.y)){
                    if(c+e.l <= b )number++;
                    c+=e.l;
                }
            }

            return new Solution(c, number);
        }
    }
}
