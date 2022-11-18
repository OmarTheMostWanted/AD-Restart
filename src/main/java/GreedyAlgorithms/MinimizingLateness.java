package GreedyAlgorithms;

import java.util.ArrayList;

public class MinimizingLateness {

        static class Task {
        int time, deadline;

        public Task(int time, int deadline) {
            this.time = time;
            this.deadline = deadline;
        }

        int SmallestTime(Task other){
            return Integer.compare(this.time , other.time);
        }

        int LargestTime(Task other){
            return Integer.compare(other.time , this.time);
        }

        int SmallestDeadline(Task other){
            return Integer.compare(this.deadline , other.deadline);
        }

        @Override
        public String toString() {
            return "Task{" +
                    "time=" + time +
                    ", deadline=" + deadline +
                    '}';
        }
    }

    /**
     * @param n the number of jobs
     * @param t the times of jobs 1 through n, NB: you should ignore t[0]
     * @param d the deadlines of the jobs 1 through n. NB: you should ignore deadlines[0]
     * @return the minimised maximum lateness L.
     */
    public static int solve(int n, int[] t, int[] d) {
        ArrayList<Task> tasks = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            tasks.add(new Task(t[i] , d[i]));
        }
        tasks.sort(Task::SmallestDeadline);

        var time = 0;
        var maxL = 0;
        for (Task task : tasks) {
            time += task.time;
            maxL = Math.max(maxL , Math.max(0  , time - task.deadline));
        }

        return maxL;

    }
}
