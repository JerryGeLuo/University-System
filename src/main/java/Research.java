package main.java;
import java.util.ArrayList;
// research class, contains information about a research
public class Research extends Study{
    private ArrayList<Researcher> researchers = new ArrayList<Researcher>();
    private ArrayList<Professor> professors = new ArrayList<Professor>();
    
    // constructor
    public Research(String researchName) {
        super(researchName);
    }

    // get a list of researchers in research
    public ArrayList<Researcher> getResearchers() {
        return researchers;
    }

    // get a list of professors in research
    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    // add a researcher to the research
    public void addResearcher(Researcher researcher) {
        researchers.add(researcher);
    }

    // add a professor to the research
    public void addProfessor(Professor professor) {
        professors.add(professor);
    }

    // remove a researcher from the research
    public void removeResearcher(Researcher researcher) {
        researchers.remove(researcher);
    }

    // remove a professor from the research
    public void removeProfessor(Professor professor) {
        professors.remove(professor);
    }

    // list researchers in research
    @Override
    public void listParticipants() {
        if (researchers.isEmpty()) {
            System.out.println("No researchers at this time.");
        } else {
            System.out.println("RESEARCHER");
            System.out.println("------------------------------");
            for (Researcher researcher : researchers) {
                System.out.println(researcher.getName());
            }
        }
    }

    // list the topic and professors of the research.
    @Override
    public void listInfo() {
        System.out.printf("%-15s", super.getName());
        for (int i = 0; i < professors.size() - 1; i++) {
            System.out.print(professors.get(i).getName() + ", ");
        }
        System.out.println(professors.get(professors.size() - 1).getName());
    }
}
