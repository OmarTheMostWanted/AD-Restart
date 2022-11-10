package GreedyAlgorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import GreedyAlgorithms.TrainRouting.Edge;

class TrainRoutingTest {

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