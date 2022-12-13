package exam19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.EMPTY_SET;

public class PresentationTime {

    private static class Presentation implements Comparable<Presentation>{
        String name;
        int start;
        int end;

        public Presentation(String name, int start, int end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }

        public boolean isCompatibleWith(Presentation other){
            return this.start >= other.end | this.end <= other.start;
        }

        @Override
        public int compareTo(Presentation o) {
            return Integer.compare(this.end , o.end);
        }

        @Override
        public String toString() {
            return "Presentation{" +
                    "name='" + name + '\'' +
                    ", start=" + start +
                    ", end=" + end +
                    '}';
        }
    }




    /**
     * You should implement this method.
     *
     * @param n              the number of students
     * @param presenterNames the names of the presenters p_1 through p_n. Note you should only use entries presenterNames[1] up to and including presenterNames[n].
     * @param startTimes     the start times of the presentations s_1 through s_n. Note you should only use entries startTimes[1] up to and including startTimes[n].
     * @param endTimes       the end times of the presentations e_1 through e_n. Note you should only use entries endTimes[1] up to and including endTimes[n].
     * @return a largest possible set of presenters whose presentation we can attend.
     */
    public static Set<String> whatPresentations(int n, String[] presenterNames, int[] startTimes, int[] endTimes) {

        if(n == 0) return Collections.<String>emptySet();
        if(n == 1) return Set.of(presenterNames[1]);

        ArrayList<Presentation> presentations = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            presentations.add(new Presentation(presenterNames[i] , startTimes[i] , endTimes[i]));
        }
        presentations.sort(Presentation::compareTo);
        Set<String> selected = new HashSet<>();
        var last = presentations.get(0);
        selected.add(last.name);
        int count = 1;
        for (int i = 1; i < presentations.size(); i++) {
            if(presentations.get(i).isCompatibleWith(last)) {
                last = presentations.get(i);
                count++;
                selected.add(last.name);
            }
        }
        return selected;
    }
}
