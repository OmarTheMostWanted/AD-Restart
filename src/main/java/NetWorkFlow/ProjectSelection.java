package NetWorkFlow;

import java.util.*;

public class ProjectSelection {
    public static
    /**
     * You should implement this method
     *
     * @param projects List of projects, you can ignore list.get(0)
     * @return A set of feasible projects that yield the maximum possible profit.
     */
    int maximumProjects(List<Project> projects) {
        Node s = new Node(-1 , 0);
        Node t = new Node(-2 , 0);

        LinkedList<Node> nodes = new LinkedList<>();

        int maxProfit = 0;

        for (Project project : projects) {
            var pn = new Node(project.getId());
            var profit =  project.getRevenue() - project.getCost();
            if(profit > 0){
                s.addEdge(pn , profit);
                maxProfit+= profit;
            } else{
                pn.addEdge(t , -profit);
            }
            nodes.add(pn);
        }

        for (int i = 0; i < nodes.size(); i++) {
            var req = projects.get(i).getRequirements();
            var pn = nodes.get(i);
            for (Integer integer : req) {
                var dependency = nodes.get(integer-1);
                pn.addEdge(dependency , Integer.MAX_VALUE/2);
            }
        }
        nodes.add(s);
        nodes.add(t);
        Graph g = new Graph(nodes , s , t);
        return maxProfit - MaxFlow.maximizeFlow(g);
    }

    public static HashSet<Integer> recoverFeasibleSolution(Graph g, int maxProfit) {
        HashSet<Integer> solution = new HashSet<>();
        if (maxProfit > 0) {
            // Use visited to differentiate edges from backward edges
            HashSet<Integer> visited = new HashSet<>();
            visited.add(g.getSource().getId());
            Queue<Node> q = new LinkedList<>();
            for (Edge edge : g.getSource().getEdges()) {
                // if residual
                if (edge.capacity - edge.flow > 0) {
                    q.add(edge.to);
                }
            }
            while (!q.isEmpty()) {
                Node curr = q.poll();
                visited.add(curr.getId());
                solution.add(curr.getId());
                for (Edge edge : curr.getEdges())
                    if (edge.flow > 0
                            && !edge.to.equals(g.getSink())
                            && !visited.contains(edge.to.getId())) {
                        q.add(edge.to);
                    }
            }
        }
        return solution;
    }

   static class Connection {

        int x;

        int y;

        public Connection(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static   class Project {

        private int id;

        private int cost;

        private int revenue;

        private List<Integer> requirements;

        public Project(int revenue, int cost) {
            this.revenue = revenue;
            this.cost = cost;
            this.requirements = new ArrayList<>();
        }

        public Project(int id, int revenue, int cost) {
            this(revenue, cost);
            this.id = id;
        }

        public void addRequirement(int requirement) {
            requirements.add(requirement);
        }

        public void addRequirements(List<Integer> requirements) {
            this.requirements.addAll(requirements);
        }

        public int getId() {
            return id;
        }

        public int getCost() {
            return cost;
        }

        public int getRevenue() {
            return revenue;
        }

        public List<Integer> getRequirements() {
            return requirements;
        }

        @Override
        public String toString() {
            return "Project{"
                    + "id="
                    + id
                    + ", cost="
                    + cost
                    + ", revenue="
                    + revenue
                    + ", requirements="
                    + requirements
                    + '}'
                    + "\n";
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

    static  class Node {

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
         * @param d: demand for the node. Remember that supply is represented as a negative demand.
         */
        public Node(int id, int d) {
            this.id = id;
            this.d = d;
            this.edges = new ArrayList<>();
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

    static   class Edge {

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
                return this.capacity == that.capacity
                        && this.flow == that.flow
                        && this.from.getId() == that.getFrom().getId()
                        && this.to.getId() == that.getTo().getId();
            }
            return false;
        }
    }

    static   class MaxFlow {

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

}
