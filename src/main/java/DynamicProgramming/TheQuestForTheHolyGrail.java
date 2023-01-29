package DynamicProgramming;

import java.util.*;

public class TheQuestForTheHolyGrail {
    /**
     * You should implement this method.
     *
     * @param n the number of nodes.
     * @param m the number of edges.
     * @param k the number of times we can use the research team
     * @param g the index of the holy grail in V.
     * @param V a list of Nodes, where V[1] is the current state and V[g] is the holy grail. You
     *          should not use V[0].
     * @param E a set of Edges
     * @return The length of the shortest path that uses the research team at most k times.
     */
    public static double solve(int n, int m, int k, int g, Node[] V, Set<Edge> E) {
        double[][] mem = new double[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                mem[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int j = 0; j <= k; j++) {
            // We start in node 1.
            mem[1][j] = 0;
        }
        for (int counter = 1; counter <= n; counter++) {
            for (int i = 1; i <= n; i++) {
                double[] minEdges = new double[k + 1];
                Arrays.fill(minEdges, Integer.MAX_VALUE);
                for (Edge e : E) {
                    if (e.to.getId() == i) {
                        minEdges[0] = Math.min(minEdges[0], e.cost + mem[e.from.getId()][0]);
                        for (int j = 1; j <= k; j++) {
                            minEdges[j] =
                                    Math.min(
                                            minEdges[j],
                                            Math.min(
                                                    mem[e.from.getId()][j - 1] + e.cost * 0.5,
                                                    mem[e.from.getId()][j] + e.cost));
                        }
                    }
                }
                for (int j = 0; j <= k; j++) {
                    mem[i][j] = Math.min(mem[i][j], minEdges[j]);
                }
            }
        }
        if (mem[g][k] == Integer.MAX_VALUE) {
            return -1;
        }
        return mem[g][k];
    }


        public static double solveDijkstra(int n, int m, int k, int g, Node[] V, Set<Edge> E) {

        HashMap<Node, Set<Edge>> outGoingEdges = new HashMap<>();

        ArrayList<Edge> shortestPath = new ArrayList<>();

        PriorityQueue<Distance> queue = new PriorityQueue<>();

        HashMap<Node, Double> distancesTo = new HashMap<>();

        HashMap<Node, Edge> cheapestPathToFrom = new HashMap<>();

        for (Edge edge : E) {

            if (outGoingEdges.containsKey(edge.from)) {
                outGoingEdges.get(edge.from).add(edge);
            } else {
                outGoingEdges.put(edge.from, new HashSet<>());
                outGoingEdges.get(edge.from).add(edge);
            }

            if (edge.from == V[1]) {
                queue.add(new Distance(edge.cost, edge.to));
                distancesTo.put(edge.to, (double) edge.cost);
                cheapestPathToFrom.put(edge.to, edge);
            } else {
                distancesTo.put(edge.to, Double.POSITIVE_INFINITY);
            }
        }

        HashSet<Node> visited = new HashSet<>();
        visited.add(V[1]);

        while (!queue.isEmpty()) {
            var d = queue.poll();
            visited.add(d.node);
            if (outGoingEdges.containsKey(d.node)) {
                for (Edge edge : outGoingEdges.get(d.node)) {
                    if (distancesTo.get(edge.to) > d.distance + edge.cost) {
                        distancesTo.put(edge.to, d.distance + edge.cost);
                        cheapestPathToFrom.put(edge.to, edge);
                        if (outGoingEdges.containsKey(edge.to)) {
                            for (Edge outgoing : outGoingEdges.get(edge.to)) {
                                if (!visited.contains(outgoing.to)) {
                                    queue.offer(new Distance(d.distance + edge.cost + outgoing.cost, outgoing.to));
                                }
                            }
                        }
                    }
                }
            }
        }

        ArrayList<Edge> path = new ArrayList<>();
        var pointer = (cheapestPathToFrom.get(V[g]));
        while (pointer.from != V[1]) {
            path.add(pointer);
            pointer = cheapestPathToFrom.get(pointer.from);
        }
        path.add(pointer);

        path.sort(Comparator.comparing(edge -> edge.cost));


        double cost = 0;

        for (int i = path.size() - 1; i >= 0; i--) {
            cost += (k > 0 ? ((double) path.get(i).cost / 2) : path.get(i).cost);
            k--;
        }
        return cost;
    }

    private static class Distance implements Comparable<Distance> {
        double distance;
        Node node;

        public Distance(double distance, Node node) {
            this.distance = distance;
            this.node = node;
        }

        @Override
        public int compareTo(Distance o) {
            return Double.compare(distance, o.distance);
        }

        @Override
        public String toString() {
            return "Distance{" + "distance=" + distance + ", node=" + node + '}';
        }
    }

    static class Node {

        protected int id;

        public Node(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public boolean equals(Object other) {
            if (other instanceof Node) {
                Node that = (Node) other;
                return this.id == that.id;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Node{" + "id=" + id + '}';
        }
    }

    static class Edge {

        protected int cost;

        protected Node from;

        protected Node to;

        protected Edge(Node from, Node to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public Node getFrom() {
            return from;
        }

        public Node getTo() {
            return to;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return cost == edge.cost && Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cost, from, to);
        }

        @Override
        public String toString() {
            return "Edge{" + "cost=" + cost + ", from=" + from + ", to=" + to + '}';
        }
    }
}
