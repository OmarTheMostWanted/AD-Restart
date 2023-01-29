package DynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import DynamicProgramming.TheQuestForTheHolyGrail.*;

import java.util.HashSet;
import java.util.Set;

class TheQuestForTheHolyGrailTest {
    @Test
    @Timeout(100)
    public void example_dijkstra_wouldnt_work() {
        int n = 4;
        int m = 4;
        int k = 1;
        int g = 3;
        Node[] V = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            V[i] = new Node(i);
        }
        Set<Edge> E = new HashSet<>();
        E.add(new Edge(V[1], V[2], 5));
        E.add(new Edge(V[2], V[3], 0));
        E.add(new Edge(V[1], V[4], 2));
        E.add(new Edge(V[4], V[3], 2));
        /* Two paths to chose from:
         * One is 5 + 0, with the bonus: 2.5 + 0 = 2.5
         * One is 2 + 2, with the bonus: 2 + 1 = 3
         */
        Assertions.assertEquals(2.5, TheQuestForTheHolyGrail.solve(n, m, k, g, V, E), 1e-4);
    }

    @Test
    @Timeout(100)
    public void example_one_edge() {
        int n = 2;
        int m = 1;
        int k = 1;
        int g = 2;
        Node[] V = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            V[i] = new Node(i);
        }
        Set<Edge> E = new HashSet<>();
        E.add(new Edge(V[1], V[2], 8));
        /* Only one edge to go, so might as well use the team!
         * Costs are therefore 8/2 = 4.
         */
        Assertions.assertEquals(4, TheQuestForTheHolyGrail.solve(n, m, k, g, V, E), 1e-4);
    }

    @Test
    @Timeout(100)
    public void example_two_edges() {
        int n = 3;
        int m = 2;
        int k = 1;
        int g = 3;
        Node[] V = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            V[i] = new Node(i);
        }
        Set<Edge> E = new HashSet<>();
        E.add(new Edge(V[1], V[2], 8));
        E.add(new Edge(V[2], V[3], 3));
        /* Only two edge to go, so use it on the most expensive one.
         * Costs are therefore 8/2 + 3 = 7.
         */
        Assertions.assertEquals(7, TheQuestForTheHolyGrail.solve(n, m, k, g, V, E), 1e-4);
        // Or if we can use it twice, use it both times:
        k = 2;
        Assertions.assertEquals(5.5, TheQuestForTheHolyGrail.solve(n, m, k, g, V, E), 1e-4);
    }

    @Test
    @Timeout(100)
    public void example_two_paths() {
        int n = 4;
        int m = 4;
        int k = 1;
        int g = 3;
        Node[] V = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            V[i] = new Node(i);
        }
        Set<Edge> E = new HashSet<>();
        E.add(new Edge(V[1], V[2], 8));
        E.add(new Edge(V[2], V[3], 3));
        E.add(new Edge(V[1], V[4], 3));
        E.add(new Edge(V[4], V[3], 6));
        /* Two paths to chose from, best option is 3 + 6/2
         */
        Assertions.assertEquals(6, TheQuestForTheHolyGrail.solve(n, m, k, g, V, E), 1e-4);
    }
}