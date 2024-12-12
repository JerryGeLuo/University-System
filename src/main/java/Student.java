package main.java;
import java.util.ArrayList;
// student class, gives functions to students
public class Student extends Person{
    private int graduationYear;
    private ArrayList<Course> courses = new ArrayList<Course>();
    
    // constructor
    public Student(String name, String username, String password, String graduationYear) {
        super(name, username, password);
        this.graduationYear = Integer.valueOf(graduationYear);
    }

    // get the graduation year of the student
    public int getGraduationYear() {
        return graduationYear;
    }

    // set the graduation year of the student
    public void setGraduationYear(String newGraduationYear) {
        System.out.println("Success! Your graduation year have been changed from " + graduationYear + " to " + Integer.valueOf(newGraduationYear) + ".");
        graduationYear = Integer.valueOf(newGraduationYear);
    }

    // add a course to the student
    public void addCourse(Course course) {
        System.out.println("Success! " + course.getName() + " has been added to your courses!");
        courses.add(course);
    }

    // remove a course from the student
    public void removeCourse(Course course) {
        System.out.println("Success! " + course.getName() + " has been removed from your courses!");
        courses.remove(course);
        // System.out.println("Error! " + course.getName() + " is not a course that you are currently taking!");
    }

    // check if student has course
    public boolean containsCourse(Course course) {
        return courses.contains(course);
    }
    
    // list courses
    @Override
    public void listStudies() {
        if (courses.isEmpty()) {
            System.out.println("No courses at this time.");
        }
        else {
            System.out.printf("%-15s%-10s\n", "COURSE", "PROFESSOR");
            System.out.println("------------------------------");
            for (Course course : courses) {
                course.listInfo();
            }
        }
    }

    // list all information about student
    @Override
    public String toString() {
        return String.format("%-25s%-15s%-20s%-10s", super.getName(), super.getUserName(), "Student", graduationYear);
    }
}