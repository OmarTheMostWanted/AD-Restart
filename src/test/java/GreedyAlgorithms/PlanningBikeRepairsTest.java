package GreedyAlgorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlanningBikeRepairsTest {

    //todo learn this
    public static int solve(int n, int[] starts, int[] durations) {
        int[] ends = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ends[i] = starts[i] + durations[i];
        }
        Arrays.sort(starts, 1, n + 1);
        Arrays.sort(ends, 1, n + 1);
        int i = 1;
        int j = 1;
        int cnt = 0;
        int m = 0;
        while (i <= n && j <= n) {
            if (starts[i] < ends[j]) {
                cnt += 1;
                m = Math.max(cnt, m);
                i++;
            } else {
                cnt -= 1;
                j++;
            }
        }
        return m;
    }

    public static int solveQuadratic(int n, int[] starttimes, int[] durations) {
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(starttimes[i + 1], durations[i + 1]);
        }
        Arrays.sort(jobs);
        int depth = 0;
        for (int i = 1; i < n; i++) {
            int count = 1;
            for (int j = 0; j < i; j++) {
                if (jobs[j].s + jobs[j].l > jobs[i].s) {
                    count++;
                }
            }
            if (count > depth) {
                depth = count;
            }
        }
        return depth;
    }

    private static class Job implements Comparable<Job> {

        // start time and length
        int s, l;

        public Job(int start, int length) {
            this.s = start;
            this.l = length;
        }

        @Override
        public int compareTo(Job j) {
            return this.s - j.s;
        }

        @Override
        public String toString() {
            return "Job: " + s + ", " + l;
        }
    }


    @Test
    public void example() {
        int n = 6;
        int[] starttimes = {0, 6, 3, 1, 8, 3, 1};
        int[] durations = {0, 1, 5, 2, 3, 3, 4};
        Assertions.assertEquals(3, PlanningBikeRepairs.fixMyBikesPlease(n, starttimes, durations));
    }

    @Test
    public void Small() {
        int n = 2;
        int[] start = {0 ,0, 2};
        int[] durations = {0 , 2, 2,};
        Assertions.assertEquals(solve(n , start , durations), PlanningBikeRepairs.fixMyBikesPlease(n, start, durations));

    }

    @Test
    public void Same() {
        int n = 4;
        int[] start = {0 ,0, 0, 0, 0};
        int[] durations = {0 , 2, 2, 2, 2};
        Assertions.assertEquals(solve(n , start , durations), PlanningBikeRepairs.fixMyBikesPlease(n, start, durations));

    }

    @Test
    public void Different() {
        int n = 4;
        int[] starttime = {0 , 1, 2, 5, 8};
        int[] durations = {0 , 5, 6, 2, 2};
        Assertions.assertEquals(3, PlanningBikeRepairs.fixMyBikesPlease(n, starttime, durations));
    }

    @Test
    public void Test() {
        int n = 6;
        int[] starttime = {0 , 0, 1, 8, 10, 14, 19};
        int[] durations = {0 , 15, 5, 8, 9, 6, 18};
        Assertions.assertEquals(4, PlanningBikeRepairs.fixMyBikesPlease(n, starttime, durations));
    }

    @Test void TestRandom5(){
        int n = 5;

        int[] starttime = new int[n+1];
        int[] durations = new int[n+1];

        for (int i = 1; i < n ; i++) {
            starttime[i] = (int) Math.floor(Math.random()*10 / 2);
            durations[i] = (int) Math.floor(Math.random()*10 / 2);
        }

        var solution = solve(n , starttime , durations);

        Assertions.assertEquals(solution , PlanningBikeRepairs.fixMyBikesPlease(n , starttime , durations));

    }
}