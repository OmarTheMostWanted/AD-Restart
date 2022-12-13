package DivideAndConquer;

import DivideAndConquer.FindMinimum.Equation;
import DivideAndConquer.FindMinimum.QuadraticEquation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class FindMinimumTest {
    @Test
    @Timeout(100)
    public void testExample() {
        Equation eq1 = new QuadraticEquation(0, 8, -240);
        Equation eq2 = new QuadraticEquation(0, 6, -156);
        Assertions.assertEquals(42, FindMinimum.findMin(eq1, eq2, 0, 100));
    }

    @Test
    @Timeout(100)
    public void testExampleQuadratic() {
        Equation eq1 = new QuadraticEquation(1, 8, -240);
        Equation eq2 = new QuadraticEquation(2, 6, -156);
        Assertions.assertEquals(1, FindMinimum.findMin(eq1, eq2, 0, 100));
        // As confirmed by Wolfram Alpha when you input: minimum abs(x^2+8x-240 - (2x^2+6x-156))
        // from 0 to 100
    }
}