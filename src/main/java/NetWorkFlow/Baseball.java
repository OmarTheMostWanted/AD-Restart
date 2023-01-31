package NetWorkFlow;

import java.util.*;

public class Baseball {

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

        public static void maximizeFlow(Graph g) {
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

        public void maximizeFlow() {
            MaxFlow.maximizeFlow(this);
        }
    }

    static class Node {

        protected int id;

        protected Collection<Edge> edges;

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
                return this.capacity == that.capacity && this.flow == that.flow && this.from.getId() == that.getFrom().getId() && this.to.getId() == that.getTo().getId();
            }
            return false;
        }
    }

    /**
     * @param x      the id of the team you are rooting for.
     * @param m      the total number of teams (team ids are between 1 and m inclusive)
     * @param w      the number of wins of each team already has in w[1] to w[m], you should ignore w[0].
     * @param toPlay the number of games team i and j still play against each other, e.g.
     *               toPlay[1][2] is the number of games 1 and two still play against each other. Note that
     *               this is symmetrical, i.e., toPlay[i][j] == toPlay[j][i].
     * @return true iff x can still be the team to have won the most games after all games have been
     * played.
     */
    public static boolean solve(int x, int m, int[] w, int[][] toPlay) {
        Node s = new Node(-1);
        Node t = new Node(-2);

        var xBestScore = 0; //games for x left
        for (int i = 1; i <= m; i++) {
            if (x == i) continue;
            xBestScore += toPlay[x][i];
        }

        xBestScore += w[x];

        Node[] teams = new Node[m + 1];

        for (int i = 1; i < teams.length; i++) {
            if (i == x) continue;
            teams[i] = new Node(i);
            teams[i].addEdge(t, xBestScore - w[i]);
        }

        ArrayList<Node> games = new ArrayList<>();

        var gamesSum = 0;
        for (int i = 1; i <= m; i++) {
            if (i == x) continue;
            if(xBestScore < w[i]) return false;
            for (int j = i; j <= m; j++) {
                if (j == x) continue;
                if(i == j) continue;
                Node game = new Node(i);
                s.addEdge(game, toPlay[i][j]);
                game.addEdge(teams[i], Integer.MAX_VALUE);
                game.addEdge(teams[j], Integer.MAX_VALUE);
                gamesSum += toPlay[i][j];
            }
        }

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(s);
        nodes.add(t);


        for (int i = 1; i < teams.length; i++) {
            if (i == x) continue;
            nodes.add(teams[i]);
        }
        nodes.addAll(games);

        Graph g = new Graph(nodes, s, t);

        MaxFlow.maximizeFlow(g);

        var flow = 0;
        for (Edge edge : g.getSink().getEdges()) {
            if (edge.getTo() != g.getSink()) {
                flow += edge.flow;
            }
        }

        return flow >= gamesSum;
    }

}
