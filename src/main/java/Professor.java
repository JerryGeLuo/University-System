package main.java;
import java.util.ArrayList;
// professor class, gives functions to professors
public class Professor extends Person{
    private String degreeOfEducation;
    private ArrayList<Study> studies = new ArrayList<Study>();
    
    // constructor
    public Professor(String name, String username, String password, String degreeOfEducation) {
        super(name, username, password);
        this.degreeOfEducation = degreeOfEducation;
    }

    // get the degree of education of the professor
    public String getDegreeOfEducation() {
        return degreeOfEducation;
    }

    // set the degree of education of the professor
    public void setDegreeOfEducation(String newDegreeOfEducation) {
        System.out.println("Success! Your degree of education have been changed from " + degreeOfEducation + " to " + Integer.valueOf(newDegreeOfEducation) + ".");
        degreeOfEducation = newDegreeOfEducation;
    }

    // add a course to the professor
    public void addCourse(Course course) {
        System.out.println("Success! " + course.getName() + " has been added to your courses!");
        studies.add(course);
    }

    // remove a course from the professor
    public void removeCourse(Course course) {
        System.out.println("Success! " + course.getName() + " has been removed from your courses!");
        studies.remove(course);        // System.out.println("Error! " + course.getName() + " is not a course that you are currently teaching!");
    }

    // add a research to the professor
    public void addResearch(Research research) {
        System.out.println("Success! " + research.getName() + " has been added to your researches!");
        studies.add(research);
    }

    // remove a research from the professor
    public void removeResearch(Research research) {
        System.out.println("Success! " + research.getName() + " has been removed from your researches!");
        studies.remove(research);        
        // System.out.println("Error! " + research.getName() + " is not a research that you are currently researching!");
    }

    // check if professor has research
    public boolean containsResearch(Research research) {
        return studies.contains(research);
    }

    // check if professor has course
    public boolean containsCourse(Course course) {
        return studies.contains(course);
    }

    // list courses and researches
    @Override
    public void listStudies() {
        if (studies.isEmpty()) {
            System.out.println("No courses/researches at this time.");
        } else {
            System.out.println("COURSE");
            System.out.println("------------------------------");
            for (Study study : studies) {
                if (study instanceof Course)
                    study.listInfo();
            }
            System.out.println("RESEARCH");
            System.out.println("------------------------------");
            for (Study study : studies) {
                if (study instanceof Research)
                    study.listInfo();
            }
        }
    }

    // list all participants in study
    public void listPaiticipantsInStudy(Study study) {
        if (studies.contains(study)) {
            study.listParticipants();
        }
    }

    // list all information about professor
    @Override
    public String toString() {
        return String.format("%-25s%-15s%-20s%-15s", super.getName(), super.getUserName(), "Professor", degreeOfEducation);
    }
}