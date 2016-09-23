import java.util.*;

public class Schedule {
	private ArrayList<Course> schedule = new ArrayList<Course>();
	private int conflict = 0;

	public Schedule() {
		for (int i = 0; i < CourseManager.numberOfCourse(); i++) {
			schedule.add(null);
		}
	}

	public Schedule(ArrayList<Course> schedule, int conflict) {
		for (int i = 0; i < schedule.size(); i++) {
			this.schedule.add(schedule.get(i));
		}
		this.conflict = conflict;
	}

	public Schedule(ArrayList schedule) {
		this.schedule = (ArrayList) schedule.clone();
	}

	public ArrayList getSchedule() {
		return schedule;
	}

	public int getConflict() {
		for (int i = 0; i < schedule.size(); i++) {
			for (int j = i+1; j < schedule.size(); j++) {
				if (isConflict(getCourse(i), getCourse(j))) {
					conflict++;
				}
			}
		}
		return conflict;
	}

	public void setSchedule(ArrayList<Course> schedule) {
		ArrayList<Course> temp = new ArrayList<Course>();
		for (int i = 0; i < schedule.size(); i++) {
			temp.add(schedule.get(i));
		}
		this.schedule = temp;
	}

	public void setConflict(int conflict) {
		this.conflict = conflict;
	}

	public Course getCourse(int idx) {
		return (Course)schedule.get(idx);
	}

	public void setCourseAtIdx(int idx, Course course) {
		schedule.set(idx, course);
	}
/*
	public Course randomSchedule(Course course) {
		Random rnd = new Random();
		for (int i = 0; i < schedule.size(); i++) {
			Course temp = new Course();
			temp.setCourseAtIdx(i, getCourse(i));
			// random ruangan

			// random jam mulai
			int x = rnd.nextInt(getCourse(i).getAkhir() - getCourse(i).getDurasi()) + getCourse(i).getAwal();
			temp.setAwal(x);
			temp.setAkhir(x + getCourse(i).getDurasi());
			// random hari
			do {
				x = rnd.nextInt(5) + 1;
			}
			while (getCourse(i).getHari()[x] == false);
			// masukkan hari terpilih ke course
			// temp.setHari(x);
		}
	}
*/
	public void randomAllSchedule() {

	}

	public boolean isConflict(Course a, Course b) {
		boolean isSameHari = a.getHari() == b.getHari();
		boolean isSameJam = false;
		if ((b.getAwal() >= a.getAwal())&&(b.getAwal() <= a.getAkhir())) {
			isSameJam = true;
		}
		boolean isSameRuang = a.getRuang() = b.getRuang();

		if (isSameHari && isSameJam && isSameRuang) {
			return true;
		} else {
			return false;
		}
	}

	public int scheduleSize() {
		return schedule.size();
	}

	public String toString() {
		String out;
		for (int i = 0; i < scheduleSize(); i++) {
			out += getCourse(i) + "%n"; 
		}
		return out;
	}
}