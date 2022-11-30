package GreedyAlgorithms;

import java.util.Arrays;
import java.util.PriorityQueue;

public class AssigningWorkstations {


    private static class WorkStation implements Comparable<WorkStation> {
        int id;
        public int busyUntil;
        static int timeout;

        public int locksAt() {
            return busyUntil + timeout;
        }

        public WorkStation(int id, int timeout) {
            this.id = id;
            this.timeout = timeout;
        }

        @Override
        public String toString() {
            return "WorkStation{" + "id=" + id + ", busyUntil=" + busyUntil + '}';
        }

        @Override
        public int compareTo(WorkStation workStation) {
            return Integer.compare(this.busyUntil, workStation.busyUntil);
        }
    }

    private static class Job implements Comparable<Job> {
        int start, duration;

        public Job(int start, int duration) {
            this.start = start;
            this.duration = duration;
        }

        @Override
        public String toString() {
            return "Job{" + "start=" + start + ", duration=" + duration + '}';
        }

        @Override
        public int compareTo(Job job) {
            return Integer.compare(this.start, job.start);
        }
    }

    /**
     * @param n        number of researchers
     * @param m        number of minutes after which workstations lock themselves
     * @param start    start times of jobs 1 through n. NB: you should ignore start[0]
     * @param duration duration of jobs 1 through n. NB: you should ignore duration[0]
     * @return the number of unlocks that can be saved.
     */
    public static int solve(int n, int m, int[] start, int[] duration) {
        Job[] jobs = new Job[n + 1];
        for (int i = 1; i < n; i++) {
            jobs[i] = new Job(start[i], duration[i]);
        }
        Arrays.sort(jobs, 1, n);

        int unlocks = 0;
        PriorityQueue<WorkStation> workStations = new PriorityQueue<>();

        for (Job job : jobs) {
            if (workStations.isEmpty()) {
                unlocks++;
                var ws = new WorkStation(workStations.size(), m);
                ws.busyUntil = job.start + job.duration;
                workStations.add(ws);
                continue;
            }
            if (workStations.peek() != null && workStations.peek().busyUntil < job.start) {
                var ws = workStations.poll();
                if (ws.locksAt() <= job.start) {
                    unlocks++;
                }
                ws.busyUntil = job.start + job.duration;
            } else {

            }
        }
        return unlocks;

    }
}
