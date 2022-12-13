package exam2021;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TaskMaster {

    private static class Task implements Comparable<Task>{
        String name;
        int startTime;
        int finishTime;

        public Task(String name, int startTime) {
            this.name = name;
            this.startTime = startTime;
            finishTime = startTime+5;
        }

        public boolean isCompateble(Task other){
            return this.finishTime <= other.startTime;
        }



        @Override
        public int compareTo(Task o) {
            return Integer.compare(this.startTime , o.startTime);
        }

        @Override
        public String toString() {
            return "Task{" +
                    "name='" + name + '\'' +
                    ", startTime=" + startTime +
                    ", finishTime=" + finishTime +
                    '}';
        }
    }

    /**
     * You should implement this method.
     *
     * @param n the number of tasks available
     * @param taskNames the names of the tasks d_1 through d_n. Note you should only use entries
     *     taskNames[1] up to and including taskNames[n].
     * @param startTimes the start times of the tasks s_1 through s_n. Note you should only use
     *     entries startTimes[1] up to and including startTimes[n].
     * @return the names of a largest possible set of tasks you can complete.
     */
    public static Set<String> winningTheTrophy(int n, String[] taskNames, int[] startTimes) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            tasks.add(new Task(taskNames[i] , startTimes[i]));
        }
        tasks.sort(Task::compareTo);
        Set<String> set = new HashSet<>();
        set.add(tasks.get(0).name);
        var last = tasks.get(0);
        for (int i = 1; i < tasks.size(); i++) {
            if(last.isCompateble(tasks.get(i))){
                set.add(tasks.get(i).name);
                last = tasks.get(i);
            }
        }
        return set;
    }

}
