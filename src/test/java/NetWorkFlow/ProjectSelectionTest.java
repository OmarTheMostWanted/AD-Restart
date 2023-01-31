package NetWorkFlow;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


import java.util.*;

import NetWorkFlow.ProjectSelection.*;

class ProjectSelectionTest {

    @Test
    @Timeout(100)
    public void example1Proj2Req() {
        Project project1 = new Project(1, 5, 0);
        Project project2 = new Project(2, 0, 1);
        Project project3 = new Project(3, 0, 2);
        project1.addRequirement(2);
        project1.addRequirement(3);
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);
        projects.add(project3);
        // Feasible project set: 1, 2, 3
        Assertions.assertEquals(2, ProjectSelection.maximumProjects(projects));
    }

    @Test
    @Timeout(100)
    public void example2() {
        Project project1 = new Project(1, 5, 0);
        Project project2 = new Project(2, 0, 1);
        Project project3 = new Project(3, 0, 2);
        Project project4 = new Project(4, 0, 10);
        project1.addRequirement(2);
        project1.addRequirement(3);
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);
        projects.add(project3);
        projects.add(project4);
        // Feasible project set: 1, 2, 3
        Assertions.assertEquals(2, ProjectSelection.maximumProjects(projects));
    }

    @Test
    @Timeout(100)
    public void example3() {
        Project project1 = new Project(1, 5, 0);
        Project project2 = new Project(2, 0, 1);
        Project project3 = new Project(3, 0, 2);
        Project project4 = new Project(4, 0, 1);
        project1.addRequirement(2);
        project1.addRequirement(3);
        project1.addRequirement(4);
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);
        projects.add(project3);
        projects.add(project4);
        // Feasible project set: 1, 2, 3, 4
        Assertions.assertEquals(1, ProjectSelection.maximumProjects(projects));
    }

    @Test
    @Timeout(100)
    public void exampleNoSolution() {
        Project project1 = new Project(1, 5, 0);
        Project project2 = new Project(2, 0, 1);
        Project project3 = new Project(3, 0, 20);
        project1.addRequirement(2);
        project1.addRequirement(3);
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);
        projects.add(project3);
        Assertions.assertEquals(0, ProjectSelection.maximumProjects(projects));
    }

    @Test()
    @Timeout(100)
    public void example4() {
        Project project1 = new Project(1, 5, 0);
        Project project2 = new Project(2, 0, 1);
        Project project3 = new Project(3, 0, 2);
        Project project4 = new Project(4, 3, 0);
        Project project5 = new Project(5, 0, 1);
        Project project6 = new Project(6, 0, 10);
        project1.addRequirement(2);
        project1.addRequirement(3);
        project4.addRequirement(5);
        project4.addRequirement(6);
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);
        projects.add(project3);
        projects.add(project4);
        projects.add(project5);
        projects.add(project6);
        // Feasible project set: 1, 2, 3
        Assertions.assertEquals(2, ProjectSelection.maximumProjects(projects));
    }

    @Test()
    @Timeout(100)
    public void testSamePrerequisite() {
        Project project1 = new Project(1, 1, 0);
        Project project2 = new Project(2, 3, 0);
        Project project3 = new Project(3, 0, 1);
        Project project4 = new Project(4, 0, 2);
        project1.addRequirement(3);
        project1.addRequirement(4);
        project2.addRequirement(3);
        project2.addRequirement(4);
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);
        projects.add(project3);
        projects.add(project4);
        // Feasible project set: 1, 2, 3, 4
        Assertions.assertEquals(1, ProjectSelection.maximumProjects(projects));
    }
}