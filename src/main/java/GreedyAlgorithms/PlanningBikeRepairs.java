package GreedyAlgorithms;

import java.util.*;

public class PlanningBikeRepairs {

    static class Job implements Comparable<Job> {

        boolean Assigned = false;
        int Id, StartTime, EndTime, AssignedTo;

        public Job(int id, int startTime, int duration) {
            Id = id;
            StartTime = startTime;
            EndTime = startTime + duration;
        }

        @Override
        public String toString() {
            return "Job{" + "Id=" + Id + ", StartTime=" + StartTime + ", EndTime=" + EndTime + '}';
        }

        @Override
        public int compareTo(Job o) {
            return Integer.compare(this.StartTime, o.StartTime);
        }

        public boolean IsCompatibleWith(Job other) {
            return (this.StartTime >= other.EndTime || this.EndTime <= other.StartTime);
        }

    }

    public static int fixMyBikesPlease(int n, int[] starttimes, int[] durations) {
        if (n == 0) return 0;

        ArrayList<Job> jobs = new ArrayList<>(n);

        for (int i = 1; i <= n; i++) {
            jobs.add(new Job(i, starttimes[i], durations[i]));
        }

        jobs.sort(Comparator.naturalOrder());

        int count = 0;

        HashMap<Integer , HashSet<Job>> cpus = new HashMap<>();
        Set<Integer> possible = new HashSet<>();

        for (int i = 0; i < jobs.size(); i++) {
            var current = jobs.get(i);
            possible.clear();
            for (Integer cpu : cpus.keySet()) {
                var compatible = true;
                for (Job job : cpus.get(cpu)) {
                    if(!job.IsCompatibleWith(current)){
                       compatible = false;
                       break;
                    }
                }
                if(compatible) possible.add(cpu);
            }
            if(possible.isEmpty()){
                count++;
                cpus.put(count , new HashSet<>(List.of(current)));
            } else {
                cpus.get(possible.iterator().next()).add(current);
            }
        }
        return count;
    }

    public static int fixMyBikesPleaseBook(int n, int[] starttimes, int[] durations) {
        if (n == 0) return 0;

        ArrayList<Job> jobs = new ArrayList<>(n);

        for (int i = 1; i <= n; i++) {
            jobs.add(new Job(i, starttimes[i], durations[i]));
        }

        jobs.sort(Comparator.naturalOrder());

        int counter = 0;
        HashSet<Integer> resources = new HashSet<>();
        HashMap<Integer, ArrayList<Job>> assignedResources = new HashMap<>();

        for (Job job : jobs) {
            boolean assigned = false;
            for (Integer resource : resources) {
                if (assignedResources.containsKey(resource)) {
                    for (Job otherJob : assignedResources.get(resource)) {
                        if (job.IsCompatibleWith(otherJob)) {
                            assignedResources.get(resource).add(job);
                            assigned = true;
                            break;
                        }
                    }
                } else {
                    assignedResources.put(resource, new ArrayList<>(List.of(job)));
                    assigned = true;
                    break;
                }
                if (assigned) break;
            }
            if (assigned) break;
            else {
                counter++;
                resources.add(counter);
                assignedResources.put(counter, new ArrayList<>(List.of(job)));
            }
        }

        return counter;

    }


}

