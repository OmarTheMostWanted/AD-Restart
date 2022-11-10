package GreedyAlgorithms;


import java.util.*;


public class GettingOutTheFastest {
    static class Edge {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return from == edge.from && to == edge.to && weight == edge.weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to, weight);
        }
    }

    static class Node implements Comparable<Node> {
        int id;
        int weight;

        public Node(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }


    /**
     * @param n     the number of nodes
     * @param m     the number of edges
     * @param s     the starting vertex (1 <= s <= n)
     * @param t     the ending vertex (1 <= t <= n)
     * @param edges the set of edges of the graph, with endpoints labelled between 1 and n
     *              inclusive.
     * @return the time required to get out, or -1 if it is not possible to get out.
     */
    public static int getMeOuttaHere(int n, int m, int s, int t, Set<Edge> edges) {

        var distances = new Node[n + 1];

        for (int i = 0; i < distances.length; i++) {
            distances[i] = new Node(i, -1);
        }

        var graph = new HashMap<Integer, ArrayList<Edge>>();

        for (Edge edge : edges) {
            if (!graph.containsKey(edge.from)) {
                graph.put(edge.from, new ArrayList<>());
            }
            graph.get(edge.from).add(edge);
        }

        for (Edge edge : edges) {
            if (edge.to == s) {
                distances[s].weight = 0;
                continue;
            }
            if (edge.from == s) distances[edge.to].weight = edge.weight + graph.get(edge.from).size();
            else distances[edge.to].weight = Integer.MAX_VALUE;
        }

        var queue = new PriorityQueue<Node>();
        queue.add(distances[s]);

        var visited = new HashSet<Integer>();
        visited.add(s);

        while (!queue.isEmpty()) {
            var closest = queue.poll();
            var outGoingEdges = graph.get(closest.id);

            if (outGoingEdges == null) continue;

            for (Edge outGoingEdge : outGoingEdges) {
                if (distances[outGoingEdge.to].weight > (outGoingEdge.weight + distances[closest.id].weight) + graph.get(outGoingEdge.from).size()) {
                    distances[outGoingEdge.to].weight = (outGoingEdge.weight + distances[closest.id].weight + graph.get(outGoingEdge.from).size());
                }
                if(!visited.contains(outGoingEdge.to)) queue.add(distances[outGoingEdge.to]);
            }
            visited.add(closest.id);
        }
        return distances[t].weight + 1;

    }

}


