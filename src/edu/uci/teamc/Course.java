package edu.uci.teamc;

/**
 * Created by justin on 11/30/16.
 */

public class Course implements Comparable {

    private String courseName;
    private String courseID;
    private String dates;
    private int currentEnrollment;
    private int maxEnrollment;

    public Course() {
        this.courseName = "Business Intelligence/Data Warehouse";
        this.courseID = "X425.22";
        this.currentEnrollment = 1;
    }

    public Course(String courseName, String courseID, String dates, int currentEnrollment, int maxEnrollment) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.dates = dates;
        this.currentEnrollment = currentEnrollment;
        this.maxEnrollment = maxEnrollment;
    }

    // TODO finish behavior
    public String getCourseName() { return this.courseName; }

    public String getCourseID() { return this.courseID; }

    public int getCurrentEnrollment() { return this.currentEnrollment; }

    public int getMaxEnrollment() { return this.maxEnrollment; }

    public String getDates() { return this.dates; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public void setCourseID(String courseId) { this.courseID = courseId; }

    public void setCurrentEnrollment(int currentEnrollment) { this.currentEnrollment = currentEnrollment; }

    @Override
    public int compareTo(Object o) {
        Course c = (Course) o;

        String s1 = this.courseName;
        String s2 = c.getCourseName();

        int result = s1.compareTo(s2);
        return result;

    }

    /**
     * Returns comma-delimited string of object fields. This gets
     * written to disk in file courses.txt
     */
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append(this.courseName + "," + this.courseID + "," + this.dates + "," + this.currentEnrollment + "," + this.maxEnrollment + "\n");

        return builder.toString();
    }
}
