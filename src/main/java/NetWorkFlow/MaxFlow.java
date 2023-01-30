package NetWorkFlow;

import java.util.*;

public class MaxFlow {

    static class Graph {

        private List<Node> nodes;

        private Node source;

        private Node sink;

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
    }

    static class Node {

        protected int id;

        protected Collection<Edge> edges;

        /**
         * Create a new node
         *
         * @param id: Id for the node.
         */
        public Node(int id) {
            this.id = id;
            this.edges = new ArrayList<Edge>();
        }

        public void addEdge(Node to, int capacity) {
            Edge e = new Edge(capacity, this, to);
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
                sb.append(e.capacity);
                sb.append("]-> ");
                sb.append(e.to.getId());
                sb.append(")");
            }
            return sb.toString();
        }
    }

    static class Edge {

        protected int capacity;

        protected int flow;

        protected Node from;

        protected Node to;

        protected Edge backwards;

        private Edge(Edge e) {
            this.flow = e.getCapacity();
            this.capacity = e.getCapacity();
            this.from = e.getTo();
            this.to = e.getFrom();
            this.backwards = e;
        }

        protected Edge(int capacity, Node from, Node to) {
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
                return this.capacity == that.capacity
                        && this.flow == that.flow
                        && this.from.getId() == that.getFrom().getId()
                        && this.to.getId() == that.getTo().getId();
            }
            return false;
        }
    }

    private static List<Edge> findPath(Node from, Node to, Graph graph) {
        if (from == to) return null;

        Queue<Node> queue = new LinkedList<>();
        queue.add(from);
        HashMap<Node, Edge> map = new HashMap<>();

        while (!queue.isEmpty()) {
            var node = queue.poll();
            for (Edge edge : node.getEdges()) {
                if (edge.getTo() != from && !map.containsKey(edge.getTo()) && edge.getResidual() > 0) {
                    queue.add(edge.getTo());
                    map.put(edge.getTo(), edge);
                }
            }
        }

        if (map.get(to) == null) return null;
        LinkedList<Edge> path = new LinkedList<>();
        var pointer = to;
        while (pointer != from) {
            path.addFirst(map.get(pointer));
            pointer = map.get(pointer).from;
        }
        return path;
    }

    public static int maximizeFlow(Graph g) {
        var path = findPath(g.getSource() , g.getSink() , g);
        while (path != null){

            var min = Integer.MAX_VALUE;
            for (Edge edge : path) {
                if(min > edge.getResidual()) min = edge.getResidual();
            }
            for (Edge edge : path) {
                edge.augmentFlow(min);
            }
            path = findPath(g.getSource(), g.getSink() , g);
        }
        var flow = 0;
        for (Edge edge : g.getSource().getEdges()) {
            flow+= edge.getFlow();
        }
        return flow;
    }
}
