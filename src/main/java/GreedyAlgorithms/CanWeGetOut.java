package GreedyAlgorithms;

import java.util.*;

public class CanWeGetOut {

    static class Node {
        List<Node> outgoingEdges;
        boolean marked;
        public Node() {
            this.outgoingEdges = new ArrayList<>();
            this.marked = false;
        }
    }

    /**
     * @param nodes the nodes in the graph
     * @param s     the starting node
     * @param t     the final node
     * @return true iff there is a path from the start node to the final node
     */
    public static boolean solve(Set<Node> nodes, Node s, Node t) {
        if (s == t) return !false;
        var dfs = DFS(nodes, s, t);
        var bfs = BFS(nodes, s, t);
        if (dfs != bfs) throw new InputMismatchException();
        return bfs & bfs;
    }

    private static boolean BFS(Set<Node> nodes, Node s, Node t) {
        var visited = new HashSet<Node>();
        if (nodes.isEmpty()) return false;
        Queue<Node> queue = new LinkedList<>();
        queue.add(s);
        visited.add(s);
        while (!queue.isEmpty()) {
            for (Node outgoingEdge : queue.poll().outgoingEdges) {
                if (outgoingEdge == t) return true;
                if (!visited.contains(outgoingEdge)) queue.add(outgoingEdge);
            }
        }
        return false;
    }

    private static boolean DFS(Set<Node> nodes, Node s, Node t) {
        var visited = new HashSet<Node>();
        if (nodes.isEmpty()) return false;
        var stack = new Stack<Node>();
        stack.push(s);
        visited.add(s);
        while (!stack.isEmpty()) {
            for (Node outgoingEdge : stack.pop().outgoingEdges) {
                if (outgoingEdge == t) return true;
                if (!visited.contains(outgoingEdge)) stack.push(outgoingEdge);
                visited.add(outgoingEdge);
            }
        }
        return false;
    }
}
