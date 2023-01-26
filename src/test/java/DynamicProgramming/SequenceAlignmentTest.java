package DynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class SequenceAlignmentTest {

    @Test
    @Timeout(100)
    public void example() {
        String a = "kitten";
        String b = "sitting";
        Assertions.assertEquals(3, SequenceAlignment.solve(a, b));
        Assertions.assertEquals("[(k,s), (i,i), (t,t), (t,t), (e,i), (n,n), (-,g)]", SequenceAlignment.extractSolution(a, b).toString());

    }

    @Test
    @Timeout(100)
    public void empty() {
        String a = "";
        String b = "";
        Assertions.assertEquals(0, SequenceAlignment.solve(a, b));
        Assertions.assertEquals("[]", SequenceAlignment.extractSolution(a, b).toString());
    }

    @Test
    @Timeout(100)
    public void two() {
        String a = "ab";
        String b = "";
        Assertions.assertEquals(2, SequenceAlignment.solve(a, b));
        Assertions.assertEquals("[(a,-), (b,-)]", SequenceAlignment.extractSolution(a, b).toString());

    }

    @Test
    @Timeout(100)
    public void two2() {
        String a = "ab";
        String b = "a";
        Assertions.assertEquals(1, SequenceAlignment.solve(a, b));
        Assertions.assertEquals("[(a,a), (b,-)]", SequenceAlignment.extractSolution(a, b).toString());

    }

    @Test
    @Timeout(100)
    public void two3() {
        String a = "ab";
        String b = "b";
        Assertions.assertEquals(1, SequenceAlignment.solve(a, b));
        Assertions.assertEquals("[(a,-), (b,b)]", SequenceAlignment.extractSolution(a, b).toString());

    }


    @Test
    @Timeout(100)
    public void example1() {
        String a = "abc";
        String b = "azbc";
        Assertions.assertEquals("[(a,a), (-,z), (b,b), (c,c)]", SequenceAlignment.extractSolution(a, b).toString());

    }

    @Test
    @Timeout(100)
    public void longTest() {
        Assertions.assertEquals(1, SequenceAlignment.solve("a", "b"));
        Assertions.assertEquals("[(a,b)]", SequenceAlignment.extractSolution("a", "b").toString());

        Assertions.assertEquals(0, SequenceAlignment.solve("a", "a"));
        Assertions.assertEquals("[(a,a)]", SequenceAlignment.extractSolution("a", "a").toString());


        Assertions.assertEquals(2, SequenceAlignment.solve("abcdef", "qbcdeq"));
        Assertions.assertEquals("[(a,q), (b,b), (c,c), (d,d), (e,e), (f,q)]", SequenceAlignment.extractSolution("abcdef", "qbcdeq").toString());


        Assertions.assertEquals(6, SequenceAlignment.solve("qwerty", "qwertyqwerty"));
        Assertions.assertEquals("[(-,q), (-,w), (-,e), (-,r), (-,t), (-,y), (q,q), (w,w), (e,e), (r,r), (t,t), (y,y)]", SequenceAlignment.extractSolution("qwerty", "qwertyqwerty").toString());


    }

}