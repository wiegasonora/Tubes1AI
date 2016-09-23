package sa;

import java.util.ArrayList;

public class DriverCourse {
	
	public static void main(String[] args) {
		
		Course course1 = new Course();
		System.out.println("Course1 = " + course1);
		System.out.println();

		boolean[] hari = {true, true, true, true, true};
		Course course2 = new Course("IF3110", "7602", 7, 11, 2, hari);
		System.out.println("Course1 = " + course2);

		course1.setNama("IF3160");
		course1.setRuang("Labdas 2");
		course1.setAwal(9);
		course1.setAkhir(12);
		course1.setDurasi(2);
		course1.setHari(hari);
		System.out.println("Course1 after set = " + course1);
		
	}
}