package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Database> universities = new ArrayList<>();
    private static Database currentUniversity = null;

    public static void main(String[] args) {
        while (true) {
            mainMenu();
        }
    }

    public static void mainMenu() {
        while (true) {
            System.out.println("\n--- University Management System ---");
            System.out.println("Please select an univeristy to begin.");
            int optionNumber = 1;
            for (Database university : universities) {
                System.out.println(optionNumber + ". " + university.getName());
                optionNumber++;
            }

            System.out.println(optionNumber++ + ". Create new university");
            System.out.println(optionNumber + ". Use demo university");

            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= universities.size()) {
                    currentUniversity = universities.get(choice - 1);
                    universityMenu();
                } else if (choice == universities.size() + 1) {
                    createUniversity();
                } else if (choice == universities.size() + 2) {
                    createDemo();
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please only enter numbers.");
            }
        }
    }


    public static void createUniversity() {
        System.out.println("Enter the name of the new university:");
        String universityName = scanner.nextLine();
        Database newUniversity = new Database(universityName);
        universities.add(newUniversity);
        currentUniversity = newUniversity;
        System.out.println("Success! " + universityName + " has been created.");
        universityMenu();
    }

    public static void createDemo() {
        Database demo = new Database("Demo University");
        for (int i = 1; i <= 50; i++) {
            demo.addUser(new Student("Student" + i, "student" + i, "123", "2026"));
        }
        for (int i = 1; i <= 10; i++) {
            demo.addUser(new Professor("Professor" + i, "professor" + i, "123", "PhD" + i));
        }
        for (int i = 1; i <= 15; i++) {
            demo.addUser(new Researcher("Researcher" + i, "researcher" + i, "123", "StudyArea" + i));
        }
        demo.addUser(new Administrator("Admin1", "admin", "adminpass", "Favorite pet?", "dog"));
        for (int i = 1; i <= 10; i++) {
            demo.addStudy(new Course("Course" + i));
        }
        for (int i = 1; i <= 5; i++) {
            demo.addStudy(new Research("ResearchTopic" + i));
        }
        currentUniversity = demo;
        universities.add(demo);
        demo.listAllUsers();
        System.out.println("Demo university loaded.");
        universityMenu();
    }

    public static void universityMenu() {
        while (true) {
            System.out.println("\n--- Welcome to " + currentUniversity.getName() + "! ---");
            System.out.println("1. Login");
            System.out.println("2. Signup");
            System.out.println("3. Select a different university");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    login();
                    break;
                case "2":
                    signup();
                    break;
                case "3":
                    currentUniversity = null;
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void login() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        Person user = currentUniversity.getUser(username, password);
        if (user == null) {
            System.out.println("Error! Username or password does not exist.");
            return;
        }

        if (user instanceof Administrator) {
            Administrator admin = (Administrator) user;
            System.out.println("Security question: " + admin.getSecurityQuestion());
            String answer = scanner.nextLine();
            if (!answer.equals(admin.getSecurityAnswer())) {
                System.out.println("Incorrect.");
                return;
            }
        }

        System.out.println("Successfully logged in as \"" + user.getClass().getSimpleName() + "\": " + user.getName() + "!");
        userMenu(user);
    }

    public static void signup() {
        System.out.println("Whats your role? (Student/Professor/Researcher/Administrator):");
        String role = scanner.nextLine();
        System.out.println("Full name:");
        String name = scanner.nextLine();
        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();

        Person newUser = null;
        switch (role.toLowerCase()) {
            case "student":
                System.out.println("Graduation year:");
                String gradYear = scanner.nextLine();
                newUser = new Student(name, username, password, gradYear);
                break;
            case "professor":
                System.out.println("Degree of education:");
                String degree = scanner.nextLine();
                newUser = new Professor(name, username, password, degree);
                break;
            case "researcher":
                System.out.println("Area of study:");
                String studyArea = scanner.nextLine();
                newUser = new Researcher(name, username, password, studyArea);
                break;
            case "administrator":
                System.out.println("Security question:");
                String question = scanner.nextLine();
                System.out.println("Answer to the security question:");
                String answer = scanner.nextLine();
                newUser = new Administrator(name, username, password, question, answer);
                break;
            default:
                System.out.println("Error! Invalid role.");
                return;
        }
        currentUniversity.addUser(newUser);
        System.out.println("Success! " + role + " account created!");
    }

    public static void userMenu(Person user) {
        while (true) {
            System.out.println("\n--- Welcome " + user.getName() + " ---");
            if (user instanceof Student) {
                System.out.println("1. Join course");
                System.out.println("2. Quit course");
                System.out.println("3. List my courses");
                System.out.println("4. List available courses");
            } else if (user instanceof Professor) {
                System.out.println("1. Join course");
                System.out.println("2. Quit course");
                System.out.println("3. Join research");
                System.out.println("4. Quit research");
                System.out.println("5. List my courses");
                System.out.println("6. List students in course");
                System.out.println("7. List researches");
                System.out.println("8. List researchers in research");
                System.out.println("9. List available courses");
                System.out.println("10. List available researches");
            } else if (user instanceof Researcher) {
                System.out.println("1. Join research");
                System.out.println("2. Quit research");
                System.out.println("3. List my researches");
                System.out.println("4. List available researches");
            } else if (user instanceof Administrator) {
                System.out.println("1. Add course");
                System.out.println("2. Remove course");
                System.out.println("3. Add research");
                System.out.println("4. Remove research");
                System.out.println("5. Add student to course");
                System.out.println("6. Remove student from course");
            }
            System.out.println("0. Logout");
    
            String option = scanner.nextLine();
            try {
                int choice = Integer.parseInt(option);

                if (choice == 0) return;
                if (user instanceof Student) {
                    student((Student) user, choice);
                } else if (user instanceof Professor) {
                    professor((Professor) user, choice);
                } else if (user instanceof Researcher) {
                    researcher((Researcher) user, choice);
                } else if (user instanceof Administrator) {
                    administrator((Administrator) user, choice);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please only enter numbers.");
            }
        }
    }

    public static void student(Student student, int choice) {
        switch (choice) {
            case 1:
                System.out.println("Enter the course name to join:");
                String courseName = scanner.nextLine();
                Study course = currentUniversity.getStudyFromName(courseName);
                if (course instanceof Course) {
                    currentUniversity.addPersonToStudy(student, course);
                } else {
                    System.out.println("Course not found.");
                }
                break;
            case 2:
                System.out.println("Enter the course name to quit:");
                String quitCourse = scanner.nextLine();
                Study quitStudy = currentUniversity.getStudyFromName(quitCourse);
                if (quitStudy instanceof Course) {
                    ((Course) quitStudy).removeStudent(student);
                } else {
                    System.out.println("Course not found.");
                }
                break;
            case 3:
                student.listStudies();
                break;
            case 4:
                currentUniversity.listAvailableCourses();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    public static void professor(Professor professor, int choice) {
        switch (choice) {
            case 1:
                System.out.println("Enter the course name to join:");
                String joinCourse = scanner.nextLine();
                Study course = currentUniversity.getStudyFromName(joinCourse);
                if (course instanceof Course) {
                    currentUniversity.addPersonToStudy(professor, course);
                } else {
                    System.out.println("Course not found.");
                }
                break;
            case 2:
                System.out.println("Enter the course name to quit:");
                String quitCourse = scanner.nextLine();
                Study quitStudy = currentUniversity.getStudyFromName(quitCourse);
                if (quitStudy instanceof Course) {
                    ((Course) quitStudy).removeProfessor(professor);
                } else {
                    System.out.println("Course not found.");
                }
                break;
            case 3:
                System.out.println("Enter the research name to join:");
                String researchName = scanner.nextLine();
                Study research = currentUniversity.getStudyFromName(researchName);
                if (research instanceof Research) {
                    currentUniversity.addPersonToStudy(professor, research);
                } else {
                    System.out.println("Research not found.");
                }
                break;
            case 4:
                System.out.println("Enter the research name to quit:");
                String quitResearch = scanner.nextLine();
                Study quitRes = currentUniversity.getStudyFromName(quitResearch);
                if (quitRes instanceof Research) {
                    ((Research) quitRes).removeProfessor(professor);
                } else {
                    System.out.println("Research not found.");
                }
                break;
            case 5:
                professor.listStudies();
                break;
            case 6:
                System.out.println("Enter the course name:");
                String courseName = scanner.nextLine();
                Study targetCourse = currentUniversity.getStudyFromName(courseName);
                if (targetCourse instanceof Course) {
                    ((Course) targetCourse).listParticipants();
                } else {
                    System.out.println("Course not found.");
                }
                break;
            case 7:
                professor.listStudies();
                break;
            case 8:
                System.out.println("Enter the research name:");
                String resName = scanner.nextLine();
                Study targetResearch = currentUniversity.getStudyFromName(resName);
                if (targetResearch instanceof Research) {
                    ((Research) targetResearch).listParticipants();
                } else {
                    System.out.println("Research not found.");
                }
                break;
            case 9:
                currentUniversity.listAvailableCourses();
                break;
            case 10:
                currentUniversity.listAvailableResearches();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    
    public static void researcher(Researcher researcher, int choice) {
        switch (choice) {
            case 1:
                System.out.println("Enter the research name to join:");
                String joinResearch = scanner.nextLine();
                Study research = currentUniversity.getStudyFromName(joinResearch);
                if (research instanceof Research) {
                    currentUniversity.addPersonToStudy(researcher, research);
                } else {
                    System.out.println("Research not found.");
                }
                break;
            case 2:
                System.out.println("Enter the research name to quit:");
                String quitResearch = scanner.nextLine();
                Study quitStudy = currentUniversity.getStudyFromName(quitResearch);
                if (quitStudy instanceof Research) {
                    ((Research) quitStudy).removeResearcher(researcher);
                } else {
                    System.out.println("Research not found.");
                }
                break;
            case 3:
                researcher.listStudies();
                break;
            case 4:
                currentUniversity.listAvailableResearches();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    public static void administrator(Administrator admin, int choice) {
        switch (choice) {
            case 1:
                System.out.println("Enter the course name to add:");
                String newCourse = scanner.nextLine();
                currentUniversity.addStudy(new Course(newCourse));
                break;
            case 2:
                System.out.println("Enter the course name to remove:");
                String removeCourse = scanner.nextLine();
                Study courseToRemove = currentUniversity.getStudyFromName(removeCourse);
                if (courseToRemove instanceof Course) {
                    currentUniversity.removeStudy(courseToRemove);
                } else {
                    System.out.println("Course not found.");
                }
                break;
            case 3:
                System.out.println("Enter the research name to add:");
                String newResearch = scanner.nextLine();
                currentUniversity.addStudy(new Research(newResearch));
                break;
            case 4:
                System.out.println("Enter the research name to remove:");
                String removeResearch = scanner.nextLine();
                Study researchToRemove = currentUniversity.getStudyFromName(removeResearch);
                if (researchToRemove instanceof Research) {
                    currentUniversity.removeStudy(researchToRemove);
                } else {
                    System.out.println("Research not found.");
                }
                break;
            case 5:
                System.out.println("Enter the student username and course name:");
                String studentUsername = scanner.nextLine();
                String courseName = scanner.nextLine();
                Person student = currentUniversity.getUser(studentUsername, "");
                Study course = currentUniversity.getStudyFromName(courseName);
                if (student instanceof Student && course instanceof Course) {
                    currentUniversity.addPersonToStudy(student, course);
                } else {
                    System.out.println("Invalid student or course.");
                }
                break;
            case 6:
                System.out.println("Enter the student username and course name:");
                String removeStudent = scanner.nextLine();
                String removeFromCourse = scanner.nextLine();
                Person studentToRemove = currentUniversity.getUser(removeStudent, "");
                Study courseToModify = currentUniversity.getStudyFromName(removeFromCourse);
                if (studentToRemove instanceof Student && courseToModify instanceof Course) {
                    ((Course) courseToModify).removeStudent((Student) studentToRemove);
                } else {
                    System.out.println("Invalid student or course.");
                }
                break;
            default:
                System.out.println("Invalid option.");
        }
    }    
}
