package tubes1;

import java.util.*;

public class Schedule {
	private ArrayList<Jadwal> schedule = new ArrayList<Jadwal>();
	private int conflict = 0;

	public Schedule() {
		for (int i = 0; i < CourseManager.numberOfCourse(); i++) {
			schedule.add(null);
		}
		conflict = 0;
	}

	public Schedule(ArrayList schedule, int conflict) {
		this.schedule = (ArrayList)schedule.clone();
		this.conflict = conflict;
	}

	public ArrayList getSchedule() {
		return schedule;
	}

	public Jadwal getScheduleAtIdx(int idx) {
		return schedule.get(idx);
	}

	public int getConflict() {
		int temp = 0;
		for (int i = 0; i < scheduleSize(); i++) {
			for (int j = i+1; j < scheduleSize(); j++) {
				if (isConflict(getCourse(i), getCourse(j))) {
					temp++;
				}
			}
		}
		setConflict(temp);
		return conflict;
	}

	public void setSchedule(ArrayList schedule) {
		this.schedule = (ArrayList)schedule.clone();
	}

	public void setConflict(int conflict) {
		this.conflict = conflict;
	}

	public Jadwal getCourse(int idx) {
		return (Jadwal)schedule.get(idx);
	}

	public void setCourseAtIdx(int idx, Jadwal course) {
		schedule.set(idx, course);
	}

	public void randomAllSchedule() {
		for (int i = 0; i < scheduleSize(); i++) {
			setCourseAtIdx(i, CourseManager.getCourse(i).randomJadwal());
		}
	}

	public boolean isConflict(Jadwal a, Jadwal b) {
		boolean isSameHari = Arrays.equals(a.getHari(), b.getHari());
		
		boolean isSameJam = false;
		if (((b.getJamMulai() >= a.getJamMulai())&&(b.getJamMulai() <= a.getJamSelesai())) || ((b.getJamSelesai() >= a.getJamMulai())&&(b.getJamSelesai() <= a.getJamSelesai()))) {
			isSameJam = true;
		}
		
		boolean isSameRuang = a.getRuangan().equals(b.getRuangan());

		if (isSameHari && isSameJam && isSameRuang) {
			return true;
		} else {
			return false;
		}
	}

	public int scheduleSize() {
		return schedule.size();
	}

	@Override
	public String toString() {
		String out = "";
		for (int i = 0; i < scheduleSize(); i++) {
			out += this.getScheduleAtIdx(i) + "\n"; 
		}
		return out;
	}
}