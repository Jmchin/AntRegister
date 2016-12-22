package edu.uci.teamc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by justin on 11/30/16.
 */
public class Student {

    private String studentID;
    private String password;
    private String firstName;
    private String lastName;
    private LinkedList<String> registeredCoursesList = new LinkedList<>();

    public Student(String studentID, String password, String firstName, String lastName, String registeredCourses) {

        this.studentID = studentID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;

        // populate linked list from comma-delimited list
        if (!registeredCourses.isEmpty()) {
            Collections.addAll(this.registeredCoursesList, registeredCourses.split("[,]"));
        }
    }

    void registerCourse(String courseID, Map coursesList) {

        // courseID must map to a course inside coursesList
        if (coursesList.containsKey(courseID)) {

            Course course = (Course) coursesList.get(courseID);

            if (course.getCurrentEnrollment() >= course.getMaxEnrollment()) {
                System.out.println("Unable to add course because it has reached capacity.");

            } else {
                if (registeredCoursesList.contains(course.getCourseID())) {

                    System.out.println("You're already registered for this course!");

                } else {

                    registeredCoursesList.add(courseID);
                    course.setCurrentEnrollment(course.getCurrentEnrollment() + 1);

                    //rewrite student file upon completion
                    this.toFile();
                    System.out.println("Update Complete!");

                }
            }
      } else {

            System.out.println("This courseID does not exist in the registry.");
        }

    }

    void unregisterCourse(String courseID, Map coursesList) {

        // prematurely exit program if there are no registered courses
        if (registeredCoursesList.size() <= 0) {
            System.out.println("You are not enrolled in any courses.");
            return;
        }

        if (coursesList.containsKey(courseID)) {

            // cast returned Object value from .get() as type Course
            Course course = (Course) coursesList.get(courseID);

            if (!registeredCoursesList.contains(courseID)) {
                System.out.println("You are not registered for this course.");
            } else {
                registeredCoursesList.remove(courseID);
                course.setCurrentEnrollment(course.getCurrentEnrollment() - 1);

                this.toFile();
                System.out.println("Update Complete!");
            }
        } else {

            // if the coursesList doesn't contain the courseID, but the student does, remove it because it is a typo
            registeredCoursesList.remove(courseID);
            System.out.println("This courseID does not exist in the registry.");
        }
    }

    private String getStudentID() { return this.studentID; }

    public void setStudentID(String studentID) { this.studentID = studentID; }

    private String getPassword() { return this.password; }

    public void setPassword(String password) { this.password = password; }

    String getFirstName() { return this.firstName; }

    public void setFirstName(String name) { this.firstName = name; }

    String getLastName() { return this.lastName; }

    String getRegisteredCourses() {
        StringBuilder builder = new StringBuilder();

        // rebuild comma-delimited list for registered courseIDs from the LinkedList
        for (String s : registeredCoursesList) {
            if (builder.length() != 0) {
                builder.append(",");
            }
            builder.append(s);
        }

        if (builder.toString().isEmpty()) {
            return "";
        } else {
            return builder.toString();
        }
    }

    /**
     * writes text representation of object out to disk {studentID}.txt
     */
    private void toFile() {

       File output = new File("res/" + this.studentID + ".txt");

       try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
           writer.write(this.getStudentID() + "\r\n");
           writer.write(this.getPassword()  + "\r\n");
           writer.write(this.getFirstName() + "\r\n");
           writer.write(this.getLastName()  + "\r\n");
           writer.write(this.getRegisteredCourses());
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

}
