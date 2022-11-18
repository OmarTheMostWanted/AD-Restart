package Fun;

import java.util.Arrays;

public class QuickSortButNotReally {
    public static Student[] sortStudents(Student[] students) {
        Arrays.sort(students , Student::compareTo);
        return students;
    }
    static class Student implements Comparable<Student>{
        int sid;
        int years;

        public Student(int sid, int years) {
            this.sid = sid;
            this.years = years;
        }


        @Override
        public String toString() {
            return "Student{" +
                    "sid=" + sid +
                    ", years=" + years +
                    '}';
        }

        @Override
        public int compareTo(Student student) {

            var res = Integer.compare(student.years, years);
            if (res == 0) {
                return Integer.compare(this.sid, student.sid);
            } else {
                return res;
            }
        }
    }

    public static void main(String[] args) {

        Student[] students = new Student[3];
        Student a = new Student(123, 6);
        var b = new Student(1232 , 3);
        var c = new Student(1231 , 3);

        students[0] = b;
        students[1] = a;
        students[2] = c;

        System.out.println(Arrays.toString(students));

        sortStudents(students);

        System.out.println(Arrays.toString(students));

        var x = 3;


    }

}
