package NetWorkFlow;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import NetWorkFlow.SweetsProof.*;

import java.util.HashSet;
import java.util.Set;

class SweetsProofTest {
    @Test()
    @Timeout(100)
    public void noSolution() {
        int n = 2;
        int k = 3;
        Set<Connection> connections = new HashSet<>();
        // Person 1 doesn't like candy 3
        // Person 2 doesn't like candy 2, 3
        connections.add(new Connection(1, 3));
        connections.add(new Connection(2, 2));
        connections.add(new Connection(2, 3));
        int[] personMinCandy = new int[n + 1];
        int[] personMaxCandy = new int[n + 1];
        // Person 1 want 1 candy at least, and can have max 1 candy
        personMinCandy[1] = 1;
        // Person 1 want 1 candy at least, and can have max 1 candy
        personMaxCandy[1] = 1;
        personMinCandy[2] = 1;
        personMaxCandy[2] = 1;
        int[] candyMinApprovals = new int[k + 1];
        int[] candyQuantity = new int[k + 1];
        // 1 person is needed to review the candy and there is a limited stock of 1 candy
        candyMinApprovals[1] = 1;
        // 1 person is needed to review the candy and there is a limited stock of 1 candy
        candyQuantity[1] = 1;
        candyMinApprovals[2] = 1;
        candyQuantity[2] = 1;
        candyMinApprovals[3] = 1;
        candyQuantity[3] = 1;
        Assertions.assertFalse(
                SweetsProof.isCandyProofingPossible(
                        n,
                        k,
                        connections,
                        personMinCandy,
                        personMaxCandy,
                        candyMinApprovals,
                        candyQuantity));
    }

    @Test()
    @Timeout(100)
    public void noSolutionDueToConnections() {
        int n = 2;
        int k = 2;

        Set<Connection> connections = new HashSet<>();

        // Person 2 doesn't like candy 2
        connections.add(new Connection(2, 2));

        int[] personMinCandy = new int[n+1];
        int[] personMaxCandy = new int[n+1];
        personMinCandy[1] = 1; personMaxCandy[1] = 2; // Person 1 and 2 want 1 candy at least, and can have max 2 candy
        personMinCandy[2] = 1; personMaxCandy[2] = 2;

        int[] candyMinApprovals = new int[k+1];
        int[] candyQuantity = new int[k+1];
        candyMinApprovals[1] = 2; candyQuantity[1] = 2; // 2 people are needed to review the candy and there is a limited stock of 2 candy
        candyMinApprovals[2] = 2; candyQuantity[2] = 2;

        Assertions.assertFalse(SweetsProof.isCandyProofingPossible(n, k, connections, personMinCandy, personMaxCandy, candyMinApprovals, candyQuantity));
    }

    @Test()
    @Timeout(100)
    public void exampleOnePersonOneCandy() {
        int n = 1;
        int k = 1;
        Set<Connection> connections = new HashSet<>();
        int[] personMinCandy = new int[n + 1];
        int[] personMaxCandy = new int[n + 1];
        personMinCandy[1] = 1;
        personMaxCandy[1] = 1;
        int[] candyMinApprovals = new int[k + 1];
        int[] candyQuantity = new int[k + 1];
        candyMinApprovals[1] = 1;
        candyQuantity[1] = 1;
        Assertions.assertTrue(
                SweetsProof.isCandyProofingPossible(
                        n,
                        k,
                        connections,
                        personMinCandy,
                        personMaxCandy,
                        candyMinApprovals,
                        candyQuantity));
    }

    @Test()
    @Timeout(100)
    public void simpleExample() {
        int n = 3;
        int k = 3;
        Set<Connection> connections = new HashSet<>();
        // Person 1 doesn't like candy 3
        // Person 2 doesn't like candy 1
        // Person 3 doesn't like candy 1, 2
        connections.add(new Connection(1, 3));
        connections.add(new Connection(2, 1));
        connections.add(new Connection(3, 1));
        connections.add(new Connection(3, 2));
        int[] personMinCandy = new int[n + 1];
        int[] personMaxCandy = new int[n + 1];
        personMinCandy[1] = 1;
        personMaxCandy[1] = 2;
        personMinCandy[2] = 1;
        personMaxCandy[2] = 2;
        personMinCandy[3] = 1;
        personMaxCandy[3] = 1;
        int[] candyMinApprovals = new int[k + 1];
        int[] candyQuantity = new int[k + 1];
        candyMinApprovals[1] = 1;
        candyQuantity[1] = 4;
        candyMinApprovals[2] = 2;
        candyQuantity[2] = 3;
        candyMinApprovals[3] = 2;
        candyQuantity[3] = 3;
        Assertions.assertTrue(
                SweetsProof.isCandyProofingPossible(
                        n,
                        k,
                        connections,
                        personMinCandy,
                        personMaxCandy,
                        candyMinApprovals,
                        candyQuantity));
    }
}