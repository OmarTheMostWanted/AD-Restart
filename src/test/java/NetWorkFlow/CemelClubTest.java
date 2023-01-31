package NetWorkFlow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.HashSet;
import java.util.Set;

class CemelClubTest {

    @Test
    @Timeout(100)
    public void example01() {
        int n = 2;
        int m = 3;
        String[] mNames = {null, "Oliver", "Caleb"};
        int[] t = {0, 5, 8};
        Set<String>[] ms = new Set[n + 1];
        ms[1] = new HashSet<>();
        ms[2] = new HashSet<>();
        ms[1].add("interviewing");
        ms[1].add("investigation");
        ms[2].add("interviewing");
        ms[2].add("literature");
        String[] jNames = {null, "Hire Member", "Interview Author", "Solve Crime"};
        int[] p = {0, 1, 3, 4};
        Set<String>[] js = new Set[m + 1];
        js[1] = new HashSet<>();
        js[2] = new HashSet<>();
        js[3] = new HashSet<>();
        js[1].add("interviewing");
        js[2].add("interviewing");
        js[2].add("literature");
        js[3].add("investigation");
        Assertions.assertTrue(CemelClub.solve(n, m, mNames, t, ms, jNames, p, js));
    }
}