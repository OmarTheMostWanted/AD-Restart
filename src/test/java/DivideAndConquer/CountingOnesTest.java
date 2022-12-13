package DivideAndConquer;

import DivideAndConquer.CountingOnes.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class CountingOnesTest {
    @Test
    @Timeout(100)
    public void example() {
        int solution = 2;
        int n = 3;
        Skill[] skills = new Skill[4];
        skills[1] = new Skill("Binary counting", false);
        skills[2] = new Skill("Huffman encoding", true);
        skills[3] = new Skill("Exchange arguments", true);
        Assertions.assertEquals(solution, CountingOnes.numberOfCompletedSkills(n, skills));
    }

    @Test
    @Timeout(100)
    public void test() {
        int solution = 5;
        int n = 10;
        Skill[] skills = new Skill[n+1];

        for (int i = 1; i < skills.length; i++) {
            if(i< skills.length - solution){
                skills[i] = new Skill("" , false);
            }
            else skills[i] = new Skill("" , true);
        }

        Assertions.assertEquals(solution, CountingOnes.numberOfCompletedSkills(n, skills));
    }

    @Test
    @Timeout(100)
    public void test1() {
        int solution = 9;
        int n = 10;
        Skill[] skills = new Skill[n+1];

        for (int i = 1; i < skills.length; i++) {
            if(i< skills.length - solution){
                skills[i] = new Skill("" , false);
            }
            else skills[i] = new Skill("" , true);
        }

        Assertions.assertEquals(solution, CountingOnes.numberOfCompletedSkills(n, skills));
    }
}