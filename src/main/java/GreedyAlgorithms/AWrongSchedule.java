package GreedyAlgorithms;

public class AWrongSchedule {

    static class Timings implements Comparable<Timings> {

        private final int hardwareTime;

        private final int softwareTime;

        public Timings(int hardwareTime, int softwareTime) {
            this.hardwareTime = hardwareTime;
            this.softwareTime = softwareTime;
        }

        @Override
        public int compareTo(Timings other) {
            return -Integer.compare(this.hardwareTime, other.hardwareTime);
        }

        public int getHardwareTime() {
            return hardwareTime;
        }

        public int getSoftwareTime() {
            return softwareTime;
        }
    }

    /**
     * @return An array of timings that will not give an optimal solution with the proposed soring.
     */
    public static Timings[] counterExample() {
        Timings t1 = new Timings(10 , 4);
        Timings t2 = new Timings(5 , 4);
        Timings t3 = new Timings(1 , 134);
        return new Timings[]{t1, t2 , t3};
    }
}

