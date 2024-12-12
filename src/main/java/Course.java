package main.java;
import java.util.ArrayList;
// course class, contains information about a course
public class Course extends Study{
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Professor> professors = new ArrayList<Professor>();
    
    // constructor
    public Course(String courseName) {
        super(courseName);
    }
    // get a list of students in course
    public ArrayList<Student> getStudents() {
        return students;
    }

    // get a list of professors in course
    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    // add a student to the course
    public void addStudent(Student student) {
        students.add(student);
    }

    // add a professor to the course
    public void addProfessor(Professor professor) {
        professors.add(professor);
    }

    // remove a student from the course
    public void removeStudent(Student student) {
        students.remove(student);
    }

    // remove a professor from the course
    public void removeProfessor(Professor professor) {
        professors.remove(professor);
    }

    // list students in course
    @Override
    public void listParticipants() {
        if (students.isEmpty()) {
            System.out.println("No students at this time.");
        } else {
            System.out.println("STUDENT");
            System.out.println("------------------------------");
            for (Student student : students) {
                System.out.println(student.getName());
            }
        }
    }

    // list the name and professors of the course.
    @Override
    public void listInfo() {
        System.out.printf("%-15s", super.getName());
        if (professors.isEmpty()) {
            System.out.println("No professors assigned");
            return;
        }
        for (int i = 0; i < professors.size() - 1; i++) {
            System.out.print(professors.get(i).getName() + ", ");
        }
        System.out.println(professors.get(professors.size() - 1).getName());
    }
}
