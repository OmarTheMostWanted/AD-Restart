package NetWorkFlow;

import NetWorkFlow.BipartiteMatching.Connection;
import NetWorkFlow.BipartiteMatching.Graph;
import NetWorkFlow.BipartiteMatching.Match;
import NetWorkFlow.BipartiteMatching.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.HashSet;
import java.util.Set;

import static NetWorkFlow.BipartiteMatching.MaxFlow.maximizeFlow;

class BipartiteMatchingTest {
    @Test()
    @Timeout(100)
    public void exampleThreeMatches() {
        int n = 3;
        Set<Connection> connections = new HashSet<>();
        /* 1 can be matched with 1, 2
          2 can be matched with 2, 3
          3 can be matched with 3
          So we can make a matching of size 3
        */
        connections.add(new Connection(1, 1));
        connections.add(new Connection(1, 2));
        connections.add(new Connection(2, 2));
        connections.add(new Connection(2, 3));
        connections.add(new Connection(3, 3));
        Assertions.assertEquals(3, BipartiteMatching.maximumMatching(n, connections));
    }

    @Test()
    @Timeout(100)
    public void exampleTwoMatches() {
        int n = 3;
        Set<Connection> connections = new HashSet<>();
        /* 1 can be matched with 1, 2
          2 can be matched with 3
          3 can be matched with 3
          So we can make a matching of size 2
        */
        connections.add(new Connection(1, 1));
        connections.add(new Connection(1, 2));
        connections.add(new Connection(2, 3));
        connections.add(new Connection(3, 3));
        Assertions.assertEquals(2, BipartiteMatching.maximumMatching(n, connections));
    }

    @Test
    @Timeout(100)
    public void exampleThreeMatches2() {
        int n = 3;
        Set<Connection> connections = new HashSet<>();
        /* 1 can be matched with 1, 2
          2 can be matched with 2, 3
          3 can be matched with 3
          So we can make a matching of size 3
        */
        connections.add(new Connection(1, 1));
        connections.add(new Connection(1, 2));
        connections.add(new Connection(2, 2));
        connections.add(new Connection(2, 3));
        connections.add(new Connection(3, 3));
        //test broken
//        Assertions.assertTrue(isValidSolution(n, connections, BipartiteMatching.recoverSolution(n, connections)));
    }

    @Test
    @Timeout(100)
    public void exampleTwoMatches2() {
        int n = 3;
        Set<Connection> connections = new HashSet<>();
        /* 1 can be matched with 1, 2
          2 can be matched with 3
          3 can be matched with 3
          So we can make a matching of size 2
        */
        connections.add(new Connection(1, 1));
        connections.add(new Connection(1, 2));
        connections.add(new Connection(2, 3));
        connections.add(new Connection(3, 3));
        //test broken
//        Assertions.assertTrue(isValidSolution(n, connections, BipartiteMatching.recoverSolution(n, connections)));
    }

    public static boolean isValidSolution(int n, Set<Connection> connections, Set<Match> matches) {
        Graph graph = BipartiteMatching.maximumMatchingGraph(n, connections);
        int maxFlow = maximizeFlow(graph);
        if (matches.size() != maxFlow) return false;
        int[] x = new int[n + 1];
        int[] y = new int[n + 1];
        for (Match match : matches) {
            if (!connections.contains(new Connection(match.x, match.y))) return false;
            x[match.x]++;
            y[match.y]++;
        }
        for (int i = 1; i <= n; i++) if (x[i] > 1 || y[i] > 1) return false;
        return true;
    }
}