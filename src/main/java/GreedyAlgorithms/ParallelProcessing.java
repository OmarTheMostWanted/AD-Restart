package GreedyAlgorithms;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ParallelProcessing {

    static class Processor implements Comparable<Processor> {
        int busyUntil = 0;
        int id;

        public Processor(int busyUntil, int id) {
            this.busyUntil = busyUntil;
            this.id = id;
        }

        @Override
        public String toString() {
            return "Processor{" + "busyUntil=" + busyUntil + ", id=" + id + '}';
        }

        @Override
        public int compareTo(Processor processor) {
            return Integer.compare(this.busyUntil, processor.busyUntil);
        }
    }

    /**
     * @param n         the number of jobs
     * @param m         the number of processors
     * @param deadlines the deadlines of the jobs 1 through n. NB: you should ignore deadlines[0]
     * @return the minimised maximum lateness.
     */
    public static int solve(int n, int m, int[] deadlines) {

        PriorityQueue<Processor> queue = new PriorityQueue<>();

        for (int i = 0; i < m; i++) {
            queue.add(new Processor(0, i));
        }

        var time = 1;
        var maxLateness = 0;

        Arrays.sort(deadlines, 1, n+1);


        for (int i = 1; i <= n; ) {

            while (!queue.isEmpty() && queue.peek().busyUntil <= time) {
                var p = queue.poll();
                p.busyUntil = 1 + time;
                maxLateness = Math.max(maxLateness, Math.max(0, time - deadlines[i]));
                queue.add(p);
                i++;
                if(i >= deadlines.length)break;
            }
            time++;
        }

        return maxLateness;

    }
}
