package edu.uci.teamc;

/**
 * The SimplCourse class is only used to
 */
public class SimpleCourse {
    String courseName;
    String courseID;

    public SimpleCourse(String courseName, String courseID) {
        this.courseName = courseName;
        this.courseID = courseID;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public String getCourseID() {
        return this.courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
}
