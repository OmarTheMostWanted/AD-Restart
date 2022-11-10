package GreedyAlgorithms;

import java.util.*;


public class TrainRouting {

    static class Edge {

        int from, to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return from == edge.from && to == edge.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    /**
     * @param n     the number of nodes
     * @param m     the number of edges
     * @param edges the set of edges, with endpoints labelled between 1 and n inclusive.
     * @return true iff there is a cycle in the graph
     */
    public static boolean isThereACycle(int n, int m, Set<Edge> edges) {

        if (edges == null || edges.isEmpty() || n == 0) return false;

        Map<Integer, HashSet<Integer>> graph = new HashMap<>();
        for (Edge edge : edges) {
            if (graph.containsKey(edge.from)) graph.get(edge.from).add(edge.to);
            else {
                graph.put(edge.from, new HashSet<Integer>());
                graph.get(edge.from).add(edge.to);
            }
        }

        for (int current : graph.keySet()) {

            var stack = new Stack<Integer>();
            var visited = new HashSet<>();
            var rechableToCurrent = new HashSet<>();
            stack.push(current);
            visited.add(current);

            while (!stack.isEmpty()) {

                if (graph.get(stack.peek()) == null) {
                    stack.pop();
                    continue;
                }

                rechableToCurrent.add(stack.peek());

                for (int integer : graph.get(stack.pop())) {
                    if (!visited.contains(integer)) stack.push(integer);
                    visited.add(integer);
                    if (integer == current) return true;
                }
            }

        }
        return false;
    }
}
