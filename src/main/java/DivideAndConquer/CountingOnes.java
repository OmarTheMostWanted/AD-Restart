package DivideAndConquer;

public class CountingOnes {
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
            return "Skill{" + "name='" + name + '\'' + ", completed=" + completed + '}';
        }
    }

    /**
     * You should implement this method.
     *
     * @param n      the number of elements in skills.
     * @param skills the sorted array of `Skill`s (see Library for their implementation) to look
     *               through. Note that you should use entries skills[1] to skills[n]!
     * @return the number of completed skills in the sorted array.
     */
    public static int numberOfCompletedSkills(int n, Skill[] skills) {
        if (n == 0) return 0;
        if (n == 1) return skills[n].isCompleted() ? 1 : 0;
        return helper(skills, 1, n);
    }

    private static int helper(Skill[] skills, int left, int right) {
        if (left == right) return skills[left].isCompleted() ? 1 : 0;
        if (right - left == 1) {
            if (skills[left].isCompleted()) return 2;
            else if (skills[right].isCompleted()) return 1;
            else return 0;
        }

        int middle = (left + right) / 2;
        if (skills[middle].isCompleted()) {
            var rightSide = (right - middle);
            var leftSide = helper(skills, left, middle );
            return leftSide + rightSide;
        } else {
            var rightSide = helper(skills, middle, right);
            return rightSide;
        }
    }
}
