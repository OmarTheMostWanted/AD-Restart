package GreedyAlgorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import GreedyAlgorithms.GettingOutTheFastest.Edge;

import java.util.HashSet;
import java.util.Set;

class GettingOutTheFastestTest {
    @Test
    public void example() {
        int n = 7;
        int m = 7;
        int s = 1;
        int t = 5;
        Set<Edge> edges = new HashSet<>();
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(2, 3, 100));
        edges.add(new Edge(3, 4, 10));
        edges.add(new Edge(4, 5, 10));
        edges.add(new Edge(2, 6, 10));
        edges.add(new Edge(6, 7, 10));
        edges.add(new Edge(7, 4, 80));
        Assertions.assertEquals(118, GettingOutTheFastest.getMeOuttaHere(n, m, s, t, edges));
    }
}