package Fun;

import java.util.Arrays;

public class AWizardIsNeverLate {

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

    public static int SmallestTimeFirst(Task[] tasks){
        Arrays.sort(tasks , Task::SmallestTime);
        int time = 0;
        int lateness = 0;
        for (Task task : tasks) {
            time += task.time;
            lateness += Math.max(0 , time - task.deadline);
        }
        return lateness;
    }

    public static int LargestTimeFirst(Task[] tasks){
        Arrays.sort(tasks , Task::LargestTime);
        int time = 0;
        int lateness = 0;
        for (Task task : tasks) {
            time += task.time;
            lateness += Math.max(0 , time - task.deadline);
        }
        return lateness;
    }

    public static int SmallestDeadLine(Task[] tasks){
        Arrays.sort(tasks , Task::SmallestDeadline);
        int time = 0;
        int lateness = 0;
        for (Task task : tasks) {
            time += task.time;
            lateness += Math.max(0 , time - task.deadline);
        }
        return lateness;
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[6];
        int i = 0;
        tasks[i]  = new Task(4  , 17);
        i++;
        tasks[i]  = new Task( 8 , 18 );
        i++;
        tasks[i]  = new Task( 9, 9);
        i++;
        tasks[i]  = new Task( 3, 32);
        i++;
        tasks[i]  = new Task( 6,24 );
        i++;
        tasks[i]  = new Task(5 , 31);


        System.out.println("Smallest Time lateness " +  SmallestTimeFirst(tasks) + ": " + Arrays.toString(tasks) );
        System.out.println("Largest Time lateness " +  LargestTimeFirst(tasks) + ": " + Arrays.toString(tasks) );
        System.out.println("Smallest DeadLine lateness " +  SmallestDeadLine(tasks) + ": " + Arrays.toString(tasks) );

    }
}
