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
    }

    @Test
    @Timeout(100)
    public void empty() {
        String a = "";
        String b = "";
        Assertions.assertEquals(0, SequenceAlignment.solve(a, b));
    }

    @Test
    @Timeout(100)
    public void two() {
        String a = "ab";
        String b = "";
        Assertions.assertEquals(2, SequenceAlignment.solve(a, b));
    }

    @Test
    @Timeout(100)
    public void two2() {
        String a = "ab";
        String b = "a";
        Assertions.assertEquals(1, SequenceAlignment.solve(a, b));
    }

    @Test
    @Timeout(100)
    public void two3() {
        String a = "ab";
        String b = "b";
        Assertions.assertEquals(1, SequenceAlignment.solve(a, b));
    }


    @Test
    @Timeout(100)
    public void example1() {
        String a = "abc";
        String b = "a-bc";
        Assertions.assertEquals(1, SequenceAlignment.solve(a, b));
    }

    @Test
    @Timeout(100)
    public void longTest() {
        Assertions.assertEquals(1, SequenceAlignment.solve("a", "b"));
        Assertions.assertEquals(0, SequenceAlignment.solve("a", "a"));

        Assertions.assertEquals(2, SequenceAlignment.solve("abcdef", "qbcdeq"));

        Assertions.assertEquals(6, SequenceAlignment.solve("qwerty", "asdfgh"));

        Assertions.assertEquals(6, SequenceAlignment.solve("qwerty", "qwertyqwerty"));

    }

}