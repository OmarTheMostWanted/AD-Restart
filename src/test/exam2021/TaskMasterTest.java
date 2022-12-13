package exam2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TaskMasterTest {
    @Test
    @Timeout(1)
    public void example() {
        int n = 4;
        String[] tasks = {
                "",
                "Sit in a cake, fastest wins",
                "Paint the best picture of a horse whilst riding a horse",
                "Do something that will look impressive in reverse",
                "Excite Alex. Greatest increase in heart rate wins."
        };
        int[] startTimes = {0, 6, 1, 5, 3};
        /** We can do task 2 from 1 to 6 and then start task 1 at 6 (taking untill 11). */
        Set<String> result = new HashSet<>();
        result.add(tasks[1]);
        result.add(tasks[2]);
        Assertions.assertEquals(result, TaskMaster.winningTheTrophy(n, tasks, startTimes));
    }
}