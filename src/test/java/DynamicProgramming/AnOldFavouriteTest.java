package DynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class AnOldFavouriteTest {
    @Test
    @Timeout(100)
    public void example() {
        Assertions.assertEquals(8, AnOldFavourite.fibonacci(6));
    }

    @Test
    @Timeout(100)
    public void test05() {
        Assertions.assertEquals(5, AnOldFavourite.fibonacci(5));
    }
}