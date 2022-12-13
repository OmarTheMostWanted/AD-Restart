package DivideAndConquer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class LongestPrefixTest {

    @Test
    @Timeout(1)
    public void example() {
        int n = 2;
        String[] encodings = new String[n + 1];
        encodings[1] = "Hello World";
        encodings[2] = "Hello Weasley";
        // Note the "2" as input on the next line!
        Assertions.assertEquals("Hello W", LongestPrefix.longestPrefix(n, encodings));
    }

    @Test
    @Timeout(1)
    public void secondExample() {
        int n = 3;
        String[] encodings = new String[n + 1];
        encodings[1] = "Hello World";
        encodings[2] = "Hello Weasley";
        encodings[3] = "Hiya mate!";
        Assertions.assertEquals("H", LongestPrefix.longestPrefix(n, encodings));
    }

    @Test
    @Timeout(1)
    public void exampleBinary() {
        int n = 7;
        String[] encodings = new String[n + 1];
        encodings[1] = "010101";
        encodings[2] = "00101010";
        encodings[3] = "00010101";
        encodings[4] = "10101";
        encodings[5] = "00001";
        encodings[6] = "00000";
        encodings[7] = "10111";
        Assertions.assertEquals("", LongestPrefix.longestPrefix(n, encodings));
    }


    @Test
    @Timeout(1)
    public void exampleBinary1() {
        int n = 7;
        String[] encodings = new String[n + 1];
        encodings[1] = "1011010101";
        encodings[2] = "101100101010";
        encodings[3] = "101100010101";
        encodings[4] = "101110101";
        encodings[5] = "101100001";
        encodings[6] = "101100000";
        encodings[7] = "101110111";
        Assertions.assertEquals("1011", LongestPrefix.longestPrefix(n, encodings));
    }
}