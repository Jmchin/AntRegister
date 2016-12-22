package edu.uci.teamc;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

class Display {

    void banner() {
        System.out.println("***************************");
        System.out.println("*                         *");
        System.out.println("*       ANT REGISTER      *");
        System.out.println("*                         *");
        System.out.println("***************************");
        System.out.println();
    }

    void mainMenu() {
        System.out.println("[1] View Profile");
        System.out.println("[2] View Course Catalog");
        System.out.println("[3] View Detailed Catalog List");
        System.out.println("[4] Add Course");
        System.out.println("[5] Drop Course");
        System.out.println("[9] Logout");
        System.out.println();
        System.out.print("Enter menu selection: ");
    }

    void getStudentInfo (Student studentinfo){
    	// print student info'
    	System.out.println("****************************");
        System.out.println(" Welcome "+studentinfo.getFirstName()+" "+studentinfo.getLastName()  );
        System.out.println("****************************");
    	System.out.println(studentinfo.getFirstName()+"'s Dashboard ");
        System.out.println(studentinfo.getRegisteredCourses());
    }

    void sortedCourseCatalog(Map coursesList) {

        SortedMap<String, Course> sortedCourses = new TreeMap<>();

        // use SortedMap to sort Course objects by course name
        for (Object course : coursesList.values()) {
            Course c = (Course) course;
            sortedCourses.put(c.getCourseName(), c);
        }

        // print column headers
        System.out.printf("%-60s%-22s%-36s%-22s%-22s\n", "Course Name", "Course ID", "Dates", "Current Enrollment", "Maximum Enrollment");
        System.out.println();

        // print out sorted course registry
        for (Object course : sortedCourses.values()) {
            Course currentCourse = (Course) course;
            System.out.printf("%-60s%-22s%-36s%-22s%-22s\n", currentCourse.getCourseName(), currentCourse.getCourseID(), currentCourse.getDates(), currentCourse.getCurrentEnrollment(), currentCourse.getMaxEnrollment());
        }

    }

    /**
     * print successive newlines to "clear" terminal
     */
    void clearDisplay() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    void goodbye() {
        System.out.println("======================================================================================================");
        System.out.println("|| Team C: {James Wilson, Jia Tan, Justin Chin, Nehal Narsaraopally, Travis Jennings}               ||");
        System.out.println("|| Course: Java Programming I (I&C SCI_X460.10)                                                     ||");
        System.out.println("|| Website: uci-java1.github.io/team-c-fall-2016                                                    ||");
        System.out.println("======================================================================================================");
    }

    // an alternate course registry printer
    void course_print() {

    	File courses = new File("res/courses.txt"); {

    	    // try with resources
    	    try (Scanner cp = new Scanner(courses)) {


    	        // clear first line of file
    	        cp.nextLine();

    	        while (cp.hasNextLine()) {

    	            String line = cp.nextLine();
    	            String courseName =        "Course -          "+line.split("[,]")[0];
    	            String courseID =          "Course ID -       "+line.split("[,]")[1];
    	            String dates =             "Dates -           "+line.split("[,]")[2];
    	            String maxEnrollment =     "Max Enrollment -  "+line.split("[,]")[3];
    	            String currentEnrollment = "Curr Enrollment - "+line.split("[,]")[4];
    	            // int maxEnrollment = Integer.valueOf(line.split("[,]")[5]);
    	            // int currentEnrollment = Integer.valueOf(line.split("[,]")[6]);

    	            // System.out.println(Arrays.toString(strCourses));
    	            System.out.println(courseName);
    	            System.out.println(courseID);
    	            System.out.println(dates);
    	            System.out.println(maxEnrollment);
    	            System.out.println(currentEnrollment);
    	            System.out.println(" ");

    	        }
    	    }
    	    catch (IOException e) {
    	        e.printStackTrace();
    	    }

    	}

    }


}
