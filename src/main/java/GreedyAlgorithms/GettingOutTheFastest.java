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

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
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

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", weight=" + weight +
                    '}';
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

        if(s == t) return 0;
        if(edges.isEmpty()) return -1;  

        var distances = new Node[n+1];

        for (int i = 1; i < distances.length; i++) {
            if(i == s) distances[i] = new Node(i, 0);
            else distances[i] = new Node(i, Integer.MAX_VALUE);
        }

        var graph = new HashMap<Integer, ArrayList<Edge>>();

        for (int i = 1; i < n; i++) {
            graph.put(i , new ArrayList<>());
        }

        for (Edge edge : edges) {
            if (!graph.containsKey(edge.from)) {
                graph.put(edge.from, new ArrayList<>());
            }
            graph.get(edge.from).add(edge);
        }

        for (Edge edge : edges) {
            if (edge.from == s) distances[edge.to].weight = edge.weight + graph.get(edge.from).size();
        }

        var queue = new PriorityQueue<Node>();

        for (Edge edge : graph.get(s)) {
            queue.add(distances[edge.to]);
        }

        while (!queue.isEmpty()) {
            var closest = queue.poll();

            var outGoingEdges = graph.get(closest.id);

            if (outGoingEdges == null) continue;

            for (Edge outGoingEdge : outGoingEdges) {
                if (distances[outGoingEdge.to].weight > ((outGoingEdge.weight + distances[closest.id].weight) + (  graph.get(outGoingEdge.from).size()))) {
                    distances[outGoingEdge.to].weight = ((outGoingEdge.weight + distances[closest.id].weight + (  graph.get(outGoingEdge.from).size())));
                    queue.add(distances[outGoingEdge.to]);
                }
            }
        }
        return distances[t].weight == Integer.MAX_VALUE ? -1 : distances[t].weight;

    }

}


