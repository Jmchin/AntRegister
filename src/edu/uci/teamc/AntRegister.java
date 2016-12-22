package edu.uci.teamc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AntRegister {
	
	private Display display = new Display();

    private static Map<String, String> studentsList = new HashMap<>();     // <studentID, password>
    private static Map<String, Course> coursesList = new HashMap<>();      // <courseID, Course>

    private String username;
    private Student currentStudent;

    public static void main(String[] args) {

        AntRegister reg = new AntRegister();

        reg.loadStudents();
        reg.loadCourses();
        reg.login();
        reg.run();

    }

    private void loadStudents() {

        File students = new File("res/students.txt");

        // try with resources
        try (Scanner in = new Scanner(students)) {

            // clear first line of file
            in.nextLine();

            // read each line and put studentID and password into studentsList hashMap
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String studentID = line.split("[,]")[0].trim();
                String password = line.split("[,]")[1].trim();

                studentsList.put(studentID, password);
            }

            System.out.println("Students loaded into system.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadCourses() {

        File courses = new File("res/courses.txt");

        // try with resources
        try (Scanner in = new Scanner(courses)) {

            while (in.hasNextLine()) {

                String line = in.nextLine();
                String courseName = line.split("[,]")[0].trim();
                String courseID = line.split("[,]")[1].trim();
                String dates = line.split("[,]")[2].trim();
                int currentEnrollment = Integer.valueOf(line.split("[,]")[3]);
                int maxEnrollment = Integer.valueOf(line.split("[,]")[4]);

                Course course = new Course(courseName, courseID, dates, currentEnrollment, maxEnrollment);

                // populate coursesList HashMap<String courseID, Course course>
                coursesList.put(courseID, course);
            }

            System.out.println("Courses loaded into system.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void login() {

        Scanner in = new Scanner(System.in);
        String studentID;
        String password;
        boolean authenticated = false;

        // prompt for login info until success
        do {
            System.out.println();
            System.out.println("Please login.");
            System.out.println();
            System.out.print("Student ID (e.g jchi): ");

            studentID = in.next();
            username = studentID;

            System.out.print("Password (e.g admin1): ");
            password = in.next();

            if (studentsList.containsKey(studentID)) {
                if (password.equals(studentsList.get(studentID))) {
                	display.clearDisplay();
                    System.out.println("Login successful.");
                    System.out.println();

                    authenticated = true;
                } else {
                    System.out.println("Password does not match.");
                }
            } else {
                System.out.println("Student ID does not exist. Try again.");
            }

        } while(!authenticated);


    }

    /**
    * the main program loop
    */
    private void run() {

        File studentFile = new File("res/" + username + ".txt");

        // read student's individual file and construct Student object
        try (Scanner inFile = new Scanner(studentFile)) {
            //StringBuilder student = new StringBuilder();
            String[] studentInfo = new String[5];
            int count = 0;

            do {
              studentInfo[count] = inFile.nextLine();
              count++;
            } while (inFile.hasNextLine());

            // avoid nullPointerException, set empty cell to empty string
            if (studentInfo[4] == null) {
                studentInfo[4] = "";
            }

            currentStudent = new Student(studentInfo[0], studentInfo[1], studentInfo[2], studentInfo[3], studentInfo[4]);  // (studentID, password, firstName, lastName, courses)

        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean running = true;
        display.banner();

        Scanner in = new Scanner(System.in);

        // loop through main-menu selections
        while (running) {

            try {
                display.mainMenu();

                int option = in.nextInt();

                switch (option) {
                    case 1:
                        // display student profile
                        display.clearDisplay();
                        display.getStudentInfo(currentStudent);

                        System.out.println();
                        break;

                    case 2:
                        // display course catalog
                        display.clearDisplay();
                        display.sortedCourseCatalog(coursesList);

                        System.out.println();
                        break;

                    case 3:
                        // display detailed course catalog
                        display.clearDisplay();
                        display.course_print();
                        break;

                    case 4:
                        // register for a course
                        display.clearDisplay();
                        display.sortedCourseCatalog(coursesList);

                        System.out.println();
                        System.out.println("You're currently registered for: " + currentStudent.getRegisteredCourses());
                        System.out.print("Please enter the course number you want to add: ");

                        String currentCourse = in.next();
                        currentStudent.registerCourse(currentCourse.trim(), coursesList);
                        saveCoursesToFile();

                        System.out.println();
                        break;

                    case 5:
                        // unregister for a course
                        display.clearDisplay();
                        display.sortedCourseCatalog(coursesList);

                        System.out.println();
                        System.out.println("You're currently registered for: " + currentStudent.getRegisteredCourses());
                        System.out.print("Please enter the course number you want to drop: ");

                        currentCourse = in.next();
                        currentStudent.unregisterCourse(currentCourse.trim(), coursesList);
                        saveCoursesToFile();

                        System.out.println();
                        break;

                    // break out of loop and terminate program
                    case 9:
                        System.out.println("Logging out...");
                        display.goodbye();
                        running = false;
                        break;

                    default:
                        display.clearDisplay();
                        System.out.println("Invalid Option.\n");
                }
            } catch (InputMismatchException e) {
                display.clearDisplay();
                System.out.println("Invalid option.\n");
                in.nextLine();
            }
        }

        // no memory leaks!
        in.close();

    }

    private void saveCoursesToFile() {

        File objFile = new File("res/courses.txt");

        // write each Course object to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(objFile))) {
            for (Object c : coursesList.values()) {
                Course currentCourse = (Course) c;
                String line = currentCourse.toString();

                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
