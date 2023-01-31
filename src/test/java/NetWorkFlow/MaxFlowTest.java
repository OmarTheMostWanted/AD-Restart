package NetWorkFlow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import NetWorkFlow.MaxFlow.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MaxFlowTest {
    /** Tests a network of 4 nodes and 5 edges. */
    @Test
    @Timeout(100)
    public void n4m5() {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int x = 0; x < 4; x++) nodes.add(new Node(x));
        nodes.get(0).addEdge(nodes.get(1), 10);
        nodes.get(0).addEdge(nodes.get(2), 10);
        nodes.get(1).addEdge(nodes.get(3), 10);
        nodes.get(2).addEdge(nodes.get(3), 10);
        nodes.get(1).addEdge(nodes.get(2), 2);
        Graph g = new Graph(nodes, nodes.get(0), nodes.get(3));
        Assertions.assertEquals(20, MaxFlow.maximizeFlow(g));
    }
}