/*
* CourseManager.java
* Hold course into list of course
*/
package tubes1;
import java.util.ArrayList;

public class CourseManager {
    
	// Holds the courses
	private static ArrayList<Jadwal> listOfCourse = new ArrayList<Jadwal>();

    // Adds a course into list
    public static void addCourse(Jadwal course) {
    	listOfCourse.add(course);
    }

    // Gets course at index = idx
    public static Jadwal getCourse(int idx) {
    	return (Jadwal)listOfCourse.get(idx);
    }

    // Get the number of list
    public static int numberOfCourse() {
    	return listOfCourse.size();
    }
    
}