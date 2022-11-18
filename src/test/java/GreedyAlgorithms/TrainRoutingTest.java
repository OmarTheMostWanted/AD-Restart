package GreedyAlgorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.*;

import java.util.concurrent.TimeUnit;

import GreedyAlgorithms.TrainRouting.Edge;

class TrainRoutingTest {

    private static class SNode {

        List<SNode> outgoingEdges;

        int id;

        boolean marked;

        public SNode(int id) {
            this.outgoingEdges = new ArrayList<>();
            this.marked = false;
            this.id = id;
        }

        public String toString() {
            return Integer.toString(id);
        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    private  static class SEdge {

        int from, to;

        public SEdge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SEdge edge = (SEdge) o;
            return from == edge.from && to == edge.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    public boolean solve(int n, int m, Set<SEdge> edges) {
        Map<Integer, SNode> nodes = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            nodes.put(i, new SNode(i));
        }
        for (SEdge e : edges) {
            nodes.get(e.from).outgoingEdges.add(nodes.get(e.to));
        }
        for (int i = 1; i <= n; i++) {
            if (dfsCycleI(nodes.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean dfsCycleI(SNode node) {
        Stack<SNode> stack = new Stack<>();
        Set<SNode> visited = new HashSet<>();
        for (SNode n : node.outgoingEdges) {
            stack.push(n);
            visited.add(n);
        }
        while (!stack.isEmpty()) {
            SNode curr = stack.pop();
            if (curr == node) {
                return true;
            }
            for (SNode n : curr.outgoingEdges) {
                if (!visited.contains(n)) {
                    stack.push(n);
                    visited.add(n);
                }
            }
        }
        return false;
    }



    private static void runTestWithFile(String fileName) {

        var pathIn = Paths.get("GreedyAlgorithms/TrainRoutingTest/" + fileName + ".in");
        var pathOut = Paths.get("GreedyAlgorithms/TrainRoutingTest/" + fileName + ".out");

        try {
            ProblemInstance p = parseInput(new ByteArrayInputStream(Files.readAllBytes(pathIn)));
            Assertions.assertEquals(Files.readString(pathOut).trim().equals("yes"), TrainRouting.isThereACycle(p.n, p.m, p.edges));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static ProblemInstance parseInput(ByteArrayInputStream input) {
        Scanner sc = new Scanner(input);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();
        Set<Edge> edges = new HashSet<>();
        for (int i = 1; i <= m; i++) {
            edges.add(new Edge(sc.nextInt(), sc.nextInt()));
            sc.nextInt();
        }
        sc.close();
        return new ProblemInstance(n, m, s, edges);
    }

    static class ProblemInstance {

        int n;

        int m;

        int s;

        Set<Edge> edges;

        public ProblemInstance(int n, int m, int s, Set<Edge> edges) {
            this.n = n;
            this.m = m;
            this.s = s;
            this.edges = edges;
        }
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void example() {
        int n = 5;
        int m = 6;
        Set<Edge> edges = new HashSet<>();
        edges.add(new Edge(1, 2));
        edges.add(new Edge(3, 2));
        edges.add(new Edge(1, 3));
        edges.add(new Edge(4, 5));
        edges.add(new Edge(2, 4));
        edges.add(new Edge(5, 3));
        Assertions.assertTrue(TrainRouting.isThereACycle(n, m, edges));
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    public void exampleRememberItIsDirected() {
        int n = 4;
        int m = 5;
        Set<Edge> edges = new HashSet<>();
        edges.add(new Edge(1, 2));
        edges.add(new Edge(1, 3));
        edges.add(new Edge(2, 4));
        edges.add(new Edge(3, 4));
        edges.add(new Edge(2, 3));
        Assertions.assertFalse(TrainRouting.isThereACycle(n, m, edges));
    }

}