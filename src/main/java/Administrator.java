package main.java;
import java.util.ArrayList;

public class Administrator extends Person{
    private ArrayList<String> securityQuestion = new ArrayList<String>();

    // constructor
    public Administrator(String name, String username, String password, String question, String answer) {
        super(name, username, password);
        securityQuestion.add(question);
        securityQuestion.add(answer);
    }

    // get the security question of the administrator
    public String getSecurityQuestion() {
        return securityQuestion.get(0);
    }

    // get the security answer of the administrator
    public String getSecurityAnswer() {
        return securityQuestion.get(1);
    }

    // set the security question of the administrator
    public void setSecurityQuestion(String newSecurityQuestion) {
        System.out.println("Success! Your security question has been updated.");
        securityQuestion.set(0, newSecurityQuestion);
    }

    // set the security question of the administrator
    public void setSecurityAnswer(String newSecurityAnswer) {
        System.out.println("Success! Your security answer has been updated.");
        securityQuestion.set(1, newSecurityAnswer);
    }

    // list all information about professor
    @Override
    public String toString() {
        return String.format("%-25s%-15s%-20s%-30s", super.getName(), super.getUserName(), "Administrator", securityQuestion);
    }
}