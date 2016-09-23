package sa;

import java.util.ArrayList;

public class DriverCourseManager {
	
	public static void main(String[] args) {
		
		Course course1 = new Course();

		boolean[] hari = {true, true, true, true, true};
		Course course2 = new Course("IF3110", "7602", 7, 11, 2, hari);

		Course course3 = new Course("IF3160", "Labdas 2", 9, 15, 3, hari);

		CourseManager.addCourse(course1);
		CourseManager.addCourse(course2);
		CourseManager.addCourse(course3);

		System.out.println("Course1 : " + CourseManager.getCourse(0));
		System.out.println("Course2 : " + CourseManager.getCourse(1));
		System.out.println("Course3 : " + CourseManager.getCourse(2));

		System.out.println("Number of course : " + CourseManager.numberOfCourse());

		
	}
}