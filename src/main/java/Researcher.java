package main.java;
import java.util.ArrayList;
// researcher class, gives functions to researchers
public class Researcher extends Person{
    private String studyArea;
    private ArrayList<Research> researches = new ArrayList<Research>();
    
    // constructor
    public Researcher(String name, String username, String password, String studyArea) {
        super(name, username, password);
        this.studyArea = studyArea;
    }

    // get the graduation year of the researcher
    public String getStudyArea() {
        return studyArea;
    }

    // set the graduation year of the researcher
    public void setStudyArea(String newStudyArea) {
        System.out.println("Success! Your graduation year have been changed from " + studyArea + " to " + Integer.valueOf(newStudyArea) + ".");
        studyArea = newStudyArea;
    }

    // add a research to the researcher
    public void addResearch(Research research) {
        System.out.println("Success! " + research.getName() + " has been added to your researches!");
        researches.add(research);
    }

    // remove a research from the researcher
    public void removeResearch(Research research) {
        System.out.println("Success! " + research.getName() + " has been removed from your researches!");
        researches.remove(research);
        // System.out.println("Error! " + research.getName() + " is not a research that you are currently researching!");
    }

    // check if researcher has research
    public boolean containsResearch(Research research) {
        return researches.contains(research);
    }

    // list researches
    @Override
    public void listStudies() {
        if (researches.isEmpty()) {
            System.out.println("No researches at this time.");
        }
        else {
            System.out.printf("%-15s%-10s\n", "RESEARCH", "PROFESSOR");
            System.out.println("------------------------------");
            for (Research research : researches) {
                research.listInfo();
            }
        }
    }

    // list all information about researcher
    @Override
    public String toString() {
        return String.format("%-25s%-15s%-20s%-20s", super.getName(), super.getUserName(), "Researcher", studyArea);
    }
}