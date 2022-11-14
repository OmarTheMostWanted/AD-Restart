package Fun;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnapsackTest {

    @Test
    public void Test() {

        var knapSack = new Knapsack(20);
        knapSack.AddItem(3, 3).AddItem(4, 4).AddItem(9, 10).AddItem(10, 1).AddItem(8, 6).AddItem(2, 5);

        System.out.println("By Weight: " + knapSack.IncreasingWeight());
        System.out.println("By Value: " + knapSack.DecreasingValue());
        System.out.println("By Ratio: " + knapSack.DecreasingRatio());
    }

}