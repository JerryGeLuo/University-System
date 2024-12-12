package main.java;

// parent class for course and research, stores the name
public abstract class Study {
    private String studyName;

    // constructor
    public Study(String studyName) {
        this.studyName = studyName;
    }

    // get the name of the study
    public String getName() {
        return studyName;
    }

    // list participants of a study
    public abstract void listParticipants();

    // list the name of the study
    public abstract void listInfo();
}