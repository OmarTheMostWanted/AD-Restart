package GreedyAlgorithms;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AssigningWorkstations {


    private static class WorkStation implements Comparable<WorkStation> {
        public int busyUntil;
        static int timeout;

        public int locksAt() {
            return busyUntil + timeout;
        }

        public WorkStation(int timeout) {
            this.timeout = timeout;
        }

        @Override
        public String toString() {
            return "WorkStation{ busyUntil=" + busyUntil +  ", locks at: " + locksAt() + '}';
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

        public int endAt(){
            return start + duration;
        }

        @Override
        public String toString() {
            return "Job{" + "start=" + start + ", duration=" + duration + ", ends: " + endAt() +  '}';
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
        ArrayList<Job> jobs = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            jobs.add(new Job(start[i], duration[i]));
        }
        jobs.sort(Job::compareTo);

        int unlocks = 0;
        PriorityQueue<WorkStation> workStations = new PriorityQueue<>();

        for (Job job : jobs) {
            if (workStations.isEmpty()) {
                unlocks++;
                var ws = new WorkStation(m);
                ws.busyUntil = job.endAt();
                workStations.add(ws);
                continue;
            }
            if (workStations.peek().busyUntil < job.start) {
                while (!workStations.isEmpty()){
                    var ws = workStations.peek();
                    if(ws.locksAt() >= job.start) break;
                    else workStations.poll();
                }
                if(workStations.isEmpty()){
                    unlocks++;
                    var ws = new WorkStation(m);
                    ws.busyUntil = job.endAt();
                    workStations.add(ws);
                } else {
                    var ws = workStations.poll();
                    ws.busyUntil = job.endAt();
                    workStations.add(ws);
                }


//                var ws = workStations.poll();
//                if (ws.locksAt() <= job.start) {
//                    unlocks++;
//                }
//                ws.busyUntil = job.endAt();
//                workStations.add(ws);
            } else {
                var ws = new WorkStation(m);
                ws.busyUntil = job.endAt();
                unlocks++;
                workStations.add(ws);
            }
        }
        return unlocks;

    }
}
