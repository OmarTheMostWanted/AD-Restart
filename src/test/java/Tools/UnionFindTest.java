package Tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnionFindTest {

    @Test
    public void Test(){
        UnionFind unionFind = new UnionFind();
        unionFind.MakeSet(10);
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(i , unionFind.Find(i));
        }
        Assertions.assertTrue(unionFind.Union(0 , 1));
        Assertions.assertTrue(unionFind.Union( 2, 0));
        Assertions.assertTrue(unionFind.Union(0 , 3));
        Assertions.assertTrue(unionFind.Union(5 , 7));
        Assertions.assertTrue(unionFind.Union(6 , 7));
        assertFalse(unionFind.Union(5 ,6));


        Assertions.assertTrue(unionFind.Union(3 , 7));

        Assertions.assertTrue(unionFind.Find(0) == unionFind.Find(1) && unionFind.Find(2) == unionFind.Find(3) && unionFind.Find(0) == unionFind.Find(5) & unionFind.Find(6) == unionFind.Find(7) );

    }

}