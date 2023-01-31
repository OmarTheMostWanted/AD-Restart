package NetWorkFlow;

import java.util.*;

public class CemelClub {

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

        static void maximizeFlow(Graph g) {
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
            }
        }
    }

    static class Graph {

        private Collection<Node> nodes;

        private Node source;

        private Node sink;

        public Graph(Collection<Node> nodes, Node source, Node sink) {
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

        public Collection<Node> getNodes() {
            return nodes;
        }

        public void maximizeFlow() {
            MaxFlow.maximizeFlow(this);
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

        protected String id;

        protected Collection<Edge> edges;

        public Node(String id) {
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

        public String getId() {
            return id;
        }

        public boolean equals(Object other) {
            if (other instanceof Node) {
                Node that = (Node) other;
                if (id.equals(that.getId())) return edges.equals(that.getEdges());
            }
            return false;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id='" + id + '\'' +
                    '}';
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
                return this.capacity == that.capacity && this.flow == that.flow && this.from.getId().equals(that.getFrom().getId()) && this.to.getId().equals(that.getTo().getId());
            }
            return false;
        }
    }

    /**
     * @param n        The number of members of the Camel Club
     * @param m        The number of jobs for the club
     * @param mNames   The names of the members, in indices 1 to n.
     * @param t        The times that each member is available, in indices 1 to n.
     * @param ms       The skillsets of each member, in indices in 1 to n.
     * @param jobNames The names/descriptions of the jobs, in indices 1 to m.
     * @param p        The times that each job takes, in indices 1 to m.
     * @param js       The skillsets required for each job, in indices 1 to m.
     * @return true iff the club can complete all jobs.
     */
    public static boolean solve(int n, int m, String[] mNames, int[] t, Set<String>[] ms, String[] jobNames, int[] p, Set<String>[] js) {
        Node s = new Node("source");
        Node e = new Node("sink");

        Node[] members = new Node[n + 1];
        for (int i = 1; i < members.length; i++) {
            members[i] = new Node(mNames[i]);
            s.addEdge(members[i], t[i]);
        }

        Node[] jobs = new Node[m + 1];
        for (int i = 1; i < jobs.length; i++) {
            jobs[i] = new Node(jobNames[i]);
            jobs[i].addEdge(e, p[i]);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                var member = members[i];
                var skills = ms[i];
                var job = jobs[j];
                var jobSkills = js[j];
                if (skills.containsAll(jobSkills)) {
                    member.addEdge(job, p[j]);
                }
            }
        }

        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(s);
        nodes.add(e);
        nodes.addAll(Arrays.asList(members));
        nodes.addAll(Arrays.asList(jobs));

        Graph g = new Graph(nodes, s, e);

        g.maximizeFlow();

        var timeSpent = 0;
        for (Edge edge : g.getSource().getEdges()) {
            timeSpent+= edge.getFlow();
        }
        var timeNeeded = 0;
        for (int i : p) {
            timeNeeded+=i;
        }

        return timeSpent == timeNeeded;
    }
}
