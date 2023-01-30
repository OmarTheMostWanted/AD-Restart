package NetWorkFlow;

import java.util.*;

public class BipartiteMatching {

    static class Connection {

        int x;

        int y;

        public Connection(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Graph {

        private List<Node> nodes;

        private Node source;

        private Node sink;

        public Graph(List<Node> nodes) {
            this.nodes = nodes;
            this.source = null;
            this.sink = null;
        }

        public Graph(List<Node> nodes, Node source, Node sink) {
            this.nodes = nodes;
            this.source = source;
            this.sink = sink;
        }

        public Node getSink() {
            return sink;
        }

        public Node getSource() {
            return source;
        }

        public List<Node> getNodes() {
            return nodes;
        }

        public boolean equals(Object other) {
            if (other instanceof Graph) {
                Graph that = (Graph) other;
                return this.nodes.equals(that.nodes);
            }
            return false;
        }

        public boolean hasCirculation() {
            this.removeLowerBounds();
            int D = this.removeSupplyDemand();
            int x = MaxFlow.maximizeFlow(this);
            return x == D;
        }

        private void removeLowerBounds() {
            for (Node n : this.getNodes()) {
                for (Edge e : n.edges) {
                    if (e.lower > 0) {
                        e.capacity -= e.lower;
                        e.backwards.capacity -= e.lower;
                        e.backwards.flow -= e.lower;
                        e.from.d += e.lower;
                        e.to.d -= e.lower;
                        e.lower = 0;
                    }
                }
            }
        }

        private int removeSupplyDemand() {
            int Dplus = 0, Dmin = 0;
            int maxId = 0;
            for (Node n : this.getNodes()) {
                maxId = Math.max(n.id, maxId);
            }
            Node newSource = new Node(maxId + 1, 0);
            Node newSink = new Node(maxId + 2, 0);
            for (Node n : this.getNodes()) {
                if (n.d < 0) {
                    newSource.addEdge(n, 0, -n.d);
                    Dmin -= n.d;
                } else if (n.d > 0) {
                    n.addEdge(newSink, 0, n.d);
                    Dplus += n.d;
                }
                n.d = 0;
            }
            if (Dmin != Dplus) {
                throw new IllegalArgumentException("Demand and supply are not equal!");
            }
            this.nodes.add(newSource);
            this.nodes.add(newSink);
            this.source = newSource;
            this.sink = newSink;
            return Dplus;
        }
    }

    static class Node {

        protected int id;

        protected int d;

        protected Collection<Edge> edges;

        /**
         * Create a new node
         *
         * @param id: Id for the node.
         */
        public Node(int id) {
            this(id, 0);
        }

        /**
         * Create a new node
         *
         * @param id: Id for the node.
         * @param d:  demand for the node. Remember that supply is represented as a negative demand.
         */
        public Node(int id, int d) {
            this.id = id;
            this.d = d;
            this.edges = new ArrayList<Edge>();
        }

        public void addEdge(Node destination, int capacity) {
            this.addEdge(destination, 0, capacity);
        }

        public void addEdge(Node to, int lower, int upper) {
            Edge e = new Edge(lower, upper, this, to);
            edges.add(e);
            to.getEdges().add(e.getBackwards());
        }

        public Collection<Edge> getEdges() {
            return edges;
        }

        public int getId() {
            return id;
        }

        public boolean equals(Object other) {
            if (other instanceof Node) {
                Node that = (Node) other;
                if (id == that.getId()) return edges.equals(that.getEdges());
            }
            return false;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.getId());
            sb.append(" ");
            sb.append(this.getEdges().size());
            sb.append(":");
            for (Edge e : this.getEdges()) {
                sb.append("(");
                sb.append(e.from.getId());
                sb.append(" --[");
                sb.append(e.lower);
                sb.append(',');
                sb.append(e.capacity);
                sb.append("]-> ");
                sb.append(e.to.getId());
                sb.append(")");
            }
            return sb.toString();
        }
    }

    static class Edge {

        protected int lower;

        protected int capacity;

        protected int flow;

        protected Node from;

        protected Node to;

        protected Edge backwards;

        private Edge(Edge e) {
            this.lower = 0;
            this.flow = e.getCapacity();
            this.capacity = e.getCapacity();
            this.from = e.getTo();
            this.to = e.getFrom();
            this.backwards = e;
        }

        protected Edge(int lower, int capacity, Node from, Node to) {
            this.lower = lower;
            this.capacity = capacity;
            this.from = from;
            this.to = to;
            this.flow = 0;
            this.backwards = new Edge(this);
        }

        public void augmentFlow(int add) {
            assert (flow + add <= capacity);
            flow += add;
            backwards.setFlow(getResidual());
        }

        public Edge getBackwards() {
            return backwards;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getFlow() {
            return flow;
        }

        public Node getFrom() {
            return from;
        }

        public int getResidual() {
            return capacity - flow;
        }

        public Node getTo() {
            return to;
        }

        private void setFlow(int f) {
            assert (f <= capacity);
            this.flow = f;
        }

        public boolean equals(Object other) {
            if (other instanceof Edge) {
                Edge that = (Edge) other;
                return this.capacity == that.capacity && this.flow == that.flow && this.from.getId() == that.getFrom().getId() && this.to.getId() == that.getTo().getId();
            }
            return false;
        }
    }

    static class MaxFlow {

        private static List<Edge> findPath(Graph g, Node start, Node end) {
            Map<Node, Edge> mapPath = new HashMap<Node, Edge>();
            Queue<Node> sQueue = new LinkedList<Node>();
            Node currentNode = start;
            sQueue.add(currentNode);
            while (!sQueue.isEmpty() && currentNode != end) {
                currentNode = sQueue.remove();
                for (Edge e : currentNode.getEdges()) {
                    Node to = e.getTo();
                    if (to != start && mapPath.get(to) == null && e.getResidual() > 0) {
                        sQueue.add(e.getTo());
                        mapPath.put(to, e);
                    }
                }
            }
            if (sQueue.isEmpty() && currentNode != end) return null;
            LinkedList<Edge> path = new LinkedList<Edge>();
            Node current = end;
            while (mapPath.get(current) != null) {
                Edge e = mapPath.get(current);
                path.addFirst(e);
                current = e.getFrom();
            }
            return path;
        }

        public static int maximizeFlow(Graph g) {
            int f = 0;
            Node sink = g.getSink();
            Node source = g.getSource();
            List<Edge> path;
            while ((path = findPath(g, source, sink)) != null) {
                int r = Integer.MAX_VALUE;
                for (Edge e : path) {
                    r = Math.min(r, e.getResidual());
                }
                for (Edge e : path) {
                    e.augmentFlow(r);
                }
                f += r;
            }
            return f;
        }
    }

    /**
     * You should implement this method
     *
     * @param n           the number of nodes in X and Y (i.e. n = |X| = |Y|)
     * @param connections set of connections between one object from X and one object from Y.
     *                    Objects in X and Y are labelled 1 <= label <= n
     * @return the size of the maximum matching
     */
    public static int maximumMatching(int n, Set<Connection> connections) {
        List<Node> nodes = new ArrayList<>();
        Node source = new Node(0, 0);
        Node sink = new Node(n + 1, 0);
        nodes.add(source);
        nodes.add(sink);
        Node[] xs = new Node[n + 1];
        Node[] ys = new Node[n + 1];
        // Create one node for every object in X, and every object in Y.
        for (int i = 1; i <= n; i++) {
            xs[i] = new Node(i);
            ys[i] = new Node(i);
            source.addEdge(xs[i], 1);
            ys[i].addEdge(sink, 1);
            nodes.add(xs[i]);
            nodes.add(ys[i]);
        }
        for (Connection con : connections) {
            xs[con.x].addEdge(ys[con.y], 1);
        }
        Graph g = new Graph(nodes, source, sink);
        return MaxFlow.maximizeFlow(g);
    }

    static class Match {

        int x;

        int y;

        public Match(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + " - " + y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Match match = (Match) o;
            return x == match.x && y == match.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


    /**
     * Recovers the solution for maximum matches.
     *
     * @param n the number of nodes in X and Y (i.e. n = |X| = |Y|)
     * @param connections set of connections between one object from X and one object from Y.
     *     Objects in X and Y are labelled 1 <= label <= n
     * @return solution to the maximum matches problem
     */
    public static Set<Match> recoverSolution(int n, Set<Connection> connections) {
        Graph graph = maximumMatchingGraph(n, connections);
        MaxFlow.maximizeFlow(graph);
        return recoverMatches(graph);
    }

    public static
    /**
     * Recovers the matches from a 1-1 bipartite matching problem
     *
     * @param graph the graph on which maximum matching algorithm was applied
     * @return a set of matches recovered
     */
    Set<Match> recoverMatches(Graph graph) {
        HashSet<Match> matches = new HashSet<>();
        for (Edge edge : graph.getSource().getEdges()) {
            if(edge.getFlow() == 1){
                for (Edge edge1 : edge.getTo().getEdges()) {
                    if(edge.getTo() != graph.getSource() && edge1.getFlow() == 1){
                        matches.add(new Match(edge1.getFrom().getId() , edge1.getTo().getId()));
                    }
                }
            }
        }
        return matches;
    }

    /**
     * Construct network flow graph from the set of connections
     *
     * @param n the number of nodes in X and Y (i.e. n = |X| = |Y|)
     * @param connections set of connections between one object from X and one object from Y.
     *     Objects in X and Y are labelled 1 <= label <= n
     * @return graph representing the connections
     */
    public static Graph maximumMatchingGraph(int n, Set<Connection> connections) {
        List<Node> nodes = new ArrayList<>();
        Node source = new Node(-1, 0);
        Node sink = new Node(-2, 0);
        nodes.add(source);
        nodes.add(sink);
        Node[] xs = new Node[n + 1];
        Node[] ys = new Node[n + 1];
        // Create one node for every object in X, and every object in Y.
        // We recommend we put them in xs and ys for easy reference, but make sure to also put them
        // in nodes!
        for (int i = 1; i <= n; i++) {
            xs[i] = new Node(i);
            ys[i] = new Node(i);
            source.addEdge(xs[i], 1);
            ys[i].addEdge(sink, 1);
            nodes.add(xs[i]);
            nodes.add(ys[i]);
        }
        for (Connection con : connections) {
            xs[con.x].addEdge(ys[con.y], 1);
        }
        Graph g = new Graph(nodes, source, sink);
        return g;
    }
}
