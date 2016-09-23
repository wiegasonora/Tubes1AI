/*
* CourseManager.java
* Hold course into list of course
*/

package sa;

import java.util.ArrayList;

public class CourseManager {
    
	// Holds the courses
	private static ArrayList<Course> listOfCourse = new ArrayList<Course>(); 
    
    // Adds a course into list
    public static void addCourse(Course course) {
    	listOfCourse.add(course);
    }

    // Gets course at index = idx
    public static Course getCourse(int idx) {
    	return (Course)listOfCourse.get(idx);
    }

    // Get the number of list
    public static int numberOfCourse() {
    	return listOfCourse.size();
    }
    
}