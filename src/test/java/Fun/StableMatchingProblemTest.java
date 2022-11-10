package Fun;

import org.junit.jupiter.api.Test;

public class StableMatchingProblemTest {

    @Test
    public void Test(){
        StableMatchingProblem stableMatchingProblem = new StableMatchingProblem(8);
        var res = stableMatchingProblem.MatchAsAndBsWithBiasForA();
    }

}
