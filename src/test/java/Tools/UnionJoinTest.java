package Tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnionJoinTest {

    @Test
    public void Test(){
        UnionJoin unionJoin = new UnionJoin(4);
        unionJoin.Join(0 , 1);
        unionJoin.Join(2 , 3);
        unionJoin.Join(0 , 3);
        unionJoin.Find(0);
        unionJoin.Find(1);
        unionJoin.Find(2);
        unionJoin.Find(3);
    }

}