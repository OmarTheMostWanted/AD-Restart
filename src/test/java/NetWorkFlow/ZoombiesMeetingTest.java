package NetWorkFlow;

import static org.junit.jupiter.api.Assertions.*;
import NetWorkFlow.ZoombiesMeeting.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.*;

class ZoombiesMeetingTest {
    @Test()
    @Timeout(100)
    public void verySimpleTest() {
        int n = 3;
        Set<Connection> connections = new HashSet<>();
        connections.add(new Connection(1, 2));
        connections.add(new Connection(2, 3));
        Assertions.assertEquals(0, ZoombiesMeeting.zombieFreeRuns(n, 1, 3, connections));
    }

    @Test()
    @Timeout(100)
    public void simpleTest() {
        int n = 4;
        Set<Connection> connections = new HashSet<>();
        connections.add(new Connection(1, 2));
        connections.add(new Connection(2, 3));
        connections.add(new Connection(1, 4));
        connections.add(new Connection(4, 3));
        Assertions.assertEquals(1, ZoombiesMeeting.zombieFreeRuns(n, 1, 3, connections));
    }

    @Test()
    @Timeout(100)
    public void onePathTest() {
        int n = 7;
        Set<Connection> connections = new HashSet<>();
        connections.add(new Connection(1, 2));
        connections.add(new Connection(1, 3));
        connections.add(new Connection(1, 4));
        connections.add(new Connection(1, 5));
        connections.add(new Connection(2, 6));
        connections.add(new Connection(3, 6));
        connections.add(new Connection(4, 6));
        connections.add(new Connection(5, 6));
        // bottleneck
        connections.add(new Connection(6, 7));
        Assertions.assertEquals(0, ZoombiesMeeting.zombieFreeRuns(n, 1, 7, connections));
    }

    @Test()
    @Timeout(100)
    public void bookExampleTest() {
        int n = 7;
        Set<Connection> connections = new HashSet<>();
        connections.add(new Connection(1, 2));
        connections.add(new Connection(2, 3));
        connections.add(new Connection(3, 7));
        connections.add(new Connection(1, 4));
        connections.add(new Connection(4, 5));
        connections.add(new Connection(5, 7));
        connections.add(new Connection(1, 6));
        connections.add(new Connection(6, 7));
        // cycles
        connections.add(new Connection(3, 5));
        connections.add(new Connection(5, 6));
        connections.add(new Connection(6, 4));
        connections.add(new Connection(4, 3));
        Assertions.assertEquals(1, ZoombiesMeeting.zombieFreeRuns(n, 1, 7, connections));
    }
}