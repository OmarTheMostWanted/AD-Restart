package exam19;

public class HowManyOnes {

    static class Skill {

        private String name;

        private boolean completed;

        public Skill(String name, boolean completed) {
            this.name = name;
            this.completed = completed;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        @Override
        public String toString() {
            return "Skill{" +
                    "name='" + name + '\'' +
                    ", completed=" + completed +
                    '}';
        }
    }

    /**
     * You should implement this method.
     *
     * @param n      the number of elements in skills.
     * @param skills the sorted array of `Skill`s (see Library for their implementation) to look through. Note that you should use entries skills[1] to skills[n]!
     * @return the number of completed skills in the sorted array.
     */
    public static int numberOfCompletedSkills(int n, Skill[] skills) {
        if (n == 0) return 0;
        if (n == 1) return skills[1].isCompleted() ? 1 : 0;
        return countCompleted(skills, 1, n);
    }

    private static int countCompleted(Skill[] skills, int start, int end) {
        if (start == end) return skills[start].isCompleted() ? 1 : 0;
        if(end - start == 1){
            if(skills[start].isCompleted()) return 2;
            if(skills[end].isCompleted()) return 1;
            else return 0;
        }
        var middle = (start + end) / 2;
        if(skills[middle].isCompleted()){
            var rightSide = end-middle+1;
            var leftSide = countCompleted(skills , start , middle-1);
            return leftSide + rightSide;
        } else {
            return countCompleted(skills , middle , end);
        }
    }

}
