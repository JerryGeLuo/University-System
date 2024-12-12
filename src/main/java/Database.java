package main.java;
import java.util.ArrayList;
// database class, used to link, store, and modify all university information 
public class Database {
    private String universityName;
    private static ArrayList<Person> users;
    private static ArrayList<Study> studies;

    // constructor
    public Database(String universityName) {
        this.universityName = universityName;
        users = new ArrayList<Person>();
        studies = new ArrayList<Study>();
    }

    // gets the name of the university
    public String getName() {
        return universityName;
    }

    // returns a list of users
    public ArrayList<Person> getUsers() {
        return users;
    }

    // returns a list of studies
    public ArrayList<Study> getStudies() {
        return studies;
    }

    // add user to database
    public void addUser(Person user) {
        for (Person guest : users) {
            if (guest.getUserName().equals(user.getUserName())) {
                System.out.println("Error! Username already exists!");
                return;
            }
        }
        users.add(user);
    }

    // add study to database
    public void addStudy(Study study) {
        for (Study learn : studies) {
            if (learn.getName().equals(study.getName()))
                System.out.println("Error! Course/Research already exists!");
                return;
        }
        studies.add(study);
    }

    // get a user from username and password
    public Person getUser(String username, String password) {
        for (Person user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // get study from it's name
    public Study getStudyFromName(String studyName) {
        for (Study study : studies) {
            if (study.getName().equalsIgnoreCase(studyName)) {
                return study;
            }
        }
        return null;
    }

    // remove a study
    public void removeStudy(Study study) {
        if (studies.remove(study)) {
            System.out.println("Success! removed: " + study.getName() +".");
        } else {
            System.out.println("Error: Study not found.");
        }
    }    

    // add a person to a study, at the same time, study to person
    public void addPersonToStudy(Person person, Study study) {
        if (person instanceof Student && study instanceof Course) {
            Course course = (Course) study;
            Student student = (Student) person;
            if (!course.getStudents().contains(student)) {
                course.addStudent(student);
                student.addCourse(course);
            } else {
                System.out.println("Course already exists in profile.");
            }
        } else if (person instanceof Professor && study instanceof Course) {
            Course course = (Course) study;
            Professor professor = (Professor) person;
            if (!course.getProfessors().contains(professor)) {
                course.addProfessor(professor);
                professor.addCourse(course);
            } else {
                System.out.println("Course already exists in profile.");
            }
        } else if (person instanceof Researcher && study instanceof Research) {
            Research research = (Research) study;
            Researcher researcher = (Researcher) person;
            if (!research.getResearchers().contains(researcher)) {
                research.addResearcher(researcher);
                researcher.addResearch(research);
            } else {
                System.out.println("Research already exists in profile.");
            }
        } else if (person instanceof Professor && study instanceof Research) {
            Research research = (Research) study;
            Professor professor = (Professor) person;
            if (!research.getProfessors().contains(professor)) {
                research.addProfessor(professor);
                professor.addResearch(research);
            } else {
                System.out.println("Research already exists in profile.");
            }
        }
    }

    // lists all users
    public void listAllUsers() {
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Professor> professors = new ArrayList<Professor>();
        ArrayList<Researcher> researchers = new ArrayList<Researcher>();
        ArrayList<Administrator> administrators = new ArrayList<Administrator>();
        for (Person user : users) {
            if (user instanceof Student) {
                students.add((Student) user);
            } else if (user instanceof Professor) {
                professors.add((Professor) user);
            } else if (user instanceof Researcher) {
                researchers.add((Researcher) user);
            } else if (user instanceof Administrator) {
                administrators.add((Administrator) user);
            }
        }
        if (!students.isEmpty()) {
            System.out.printf("%-25s%-15s%-20s%-10s\n", "NAME", "USERNAME", "ROLE", "GRAD YEAR");
            System.out.println("---------------------------------------------------------------");
            for (Student student : students) {
                System.out.println(student.toString());
            }
        }
        if (!professors.isEmpty()) {
            System.out.printf("%-25s%-15s%-20s%-15s\n", "NAME", "USERNAME", "ROLE", "EDUCATION");
            System.out.println("---------------------------------------------------------------");
            for (Professor professor : professors) {
                System.out.println(professor.toString());
            }
        }
        if (!researchers.isEmpty()) {
            System.out.printf("%-25s%-15s%-20s%-20s\n", "NAME", "USERNAME", "ROLE", "STUDY AREA");
            System.out.println("---------------------------------------------------------------");
            for (Researcher researcher : researchers) {
                System.out.println(researcher.toString());
            }
        
        }
        if (!administrators.isEmpty()) {
            System.out.printf("%-25s%-15s%-20s%-30s\n", "NAME", "USERNAME", "ROLE", "SECURITY QUESTION");
            System.out.println("---------------------------------------------------------------");
            for (Administrator administrator : administrators) {
                System.out.println(administrator.toString());
            }
        }
    }

    // list all courses
    public void listAvailableCourses() {
        ArrayList<Course> courses = new ArrayList<Course>();
        for (Study study : studies) {
            if (study instanceof Course) {
                courses.add((Course) study);
            }
        }
        if (!courses.isEmpty()) {
            System.out.printf("%-15s%-20s\n", "COURSE", "PROFESSOR(S)");
            System.out.println("----------------------------------------");
            for (Course course : courses) {
                course.listInfo();
            }
        } else {
            System.out.println("No Courses avaliable at this time.");
        }
    }

    // list all researches
    public void listAvailableResearches() {
        ArrayList<Research> researches = new ArrayList<>();
        for (Study study : studies) {
            if (study instanceof Research) {
                researches.add((Research) study);
            }
        }
        if (!researches.isEmpty()) {
            System.out.printf("%-15s%-20s\n", "RESEARCH", "PROFESSOR(S)");
            System.out.println("----------------------------------------");
            for (Research research : researches) {
                research.listInfo();
            }
        } else {
            System.out.println("No researches avaliable at this time.");
        }
    }
}