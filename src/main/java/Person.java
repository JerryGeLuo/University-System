package main.java;
// parent class, stores basic information for all users
public class Person{
    private String name;
    private String username;
    private String password;

    // constructor
    public Person(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    // get the name of the user
    public String getName() {
        return name;
    }

    // get the username of the user
    public String getUserName() {
        return username;
    }

    // get the password of the user
    public String getPassword() {
        return password;
    }

    // set the name of the user
    public void setName(String newName) {
        System.out.println("Success! Your name have been changed from " + name + " to " + newName + ".");
        name = newName;
    }

    // set the username of the user
    public void setUsername(String newUsername) {
        System.out.println("Success! Your username have been changed from " + username + " to " + newUsername + ".");
        username = newUsername;
    }

    // set the password of the user
    public void setPassword(String newPassword) {
        password = newPassword;
        System.out.println("Success! Your password have been changed.");
    }

    public void listStudies() {
        System.out.println("No studies at this time.");
    }

    // list all information about user
    public String toString() {
        return String.format("%-25s%-15s%-20s", name, username, "Person");
    }
}