package tubes1;
import java.util.*;

public class SimulatedAnnealing {
	private CourseManager listOfCourse;
	private RoomManager listOfRoom;
	private Schedule solution;
	private double temperature;	// Sets initial temperature
	private double coolingRate; // Sets cooling rate
	public List<MatrixJadwal> scheduleWorld;

	public SimulatedAnnealing(List<Jadwal> jadwal, List<Ruangan> ruangan) {
		this.listOfCourse = new CourseManager();
		this.listOfRoom = new RoomManager();
		this.temperature = 1000;
		this.coolingRate = 0.003;

		for (int i = 0; i < jadwal.size(); i++) {
			Jadwal tempJadwal = new Jadwal();
			tempJadwal.setNamaKegiatan(jadwal.get(i).getNamaKegiatan());
			tempJadwal.setRuangan(jadwal.get(i).getRuangan());
			tempJadwal.setJamMulai(jadwal.get(i).getJamMulai());
			tempJadwal.setJamSelesai(jadwal.get(i).getJamSelesai());
			tempJadwal.setDurasi(jadwal.get(i).getDurasi());
			tempJadwal.setHari(jadwal.get(i).getHariAsString());
			listOfCourse.addCourse(tempJadwal);
		}

		for(int i = 0; i < ruangan.size(); i++) {
			Ruangan tempRuangan = new Ruangan();
			tempRuangan.setNama(ruangan.get(i).getNama());
			tempRuangan.setJamMulai(ruangan.get(i).getJamMulai());
			tempRuangan.setJamSelesai(ruangan.get(i).getJamSelesai());
			tempRuangan.setHari(ruangan.get(i).getHariAsString());
			listOfRoom.addRoom(tempRuangan);
			
			System.out.println(listOfRoom.getRoom(i));
		}

		this.solution = new Schedule();
		scheduleWorld = new ArrayList<MatrixJadwal>(ruangan.size());
	}

	// Calculate the acceptance probability
	public double acceptanceProbability(int conflict, int newConflict, double temperature) {
		// if new solution is better, take it
		if (newConflict < conflict) {
			return 1.0;
		} else {	// the new solution is worse, calculate an acceptance probability
			return Math.exp((conflict - newConflict) / temperature);
		}
	}

	public void execute() {
		// Initialize initial solution
		Schedule currentSolution = new Schedule();
		currentSolution.randomAllSchedule();
		// System.out.println(currentSolution);
		// System.exit(0);
		int currentConflict = currentSolution.getConflict();
		int neighbourConflict = 0;

		// Sets to be best solution
		Schedule bestSchedule = new Schedule(currentSolution.getSchedule(), currentSolution.getConflict());
		
		Random random = new Random();
		int indexToRandom;

		while (temperature > 1) {
			// Creates new neighbour course
			Schedule neighbourSolution = new Schedule(currentSolution.getSchedule(), currentSolution.getConflict());
			
			// Picks a random course to be rescheduled
			indexToRandom = random.nextInt(listOfCourse.numberOfCourse());
			// Random course in indexToRandom at courseManager
			Jadwal tempCourse = new Jadwal(listOfCourse.getCourse(indexToRandom).randomJadwal());
			// Changes/update value of course at index indexToRandom with new tempCourse
			neighbourSolution.setCourseAtIdx(indexToRandom, tempCourse);
			// Gets conflict of neighbourSolution
			neighbourConflict = neighbourSolution.getConflict();
			currentConflict = currentSolution.getConflict();

			// Decides if we should accept the neighbour
			if (acceptanceProbability(currentConflict, neighbourConflict, temperature) > random.nextDouble()) {
				currentSolution.setSchedule(neighbourSolution.getSchedule());
				currentSolution.setConflict(neighbourSolution.getConflict());
			}

			// Keep track the best solution so far
			if (currentSolution.getConflict() < bestSchedule.getConflict()) {
				bestSchedule.setSchedule(currentSolution.getSchedule());
				bestSchedule.setConflict(currentSolution.getConflict());
			}
			//System.out.println(temperature);
			// Cooling system
			temperature *= 1 - coolingRate;
		}

		solution.setSchedule(bestSchedule.getSchedule());
		solution.setConflict(bestSchedule.getConflict());

		setScheduleWorld(solution);
	}

	public Schedule showSolution() {
		return this.solution;
	}

	public int showConflict() {
		return this.solution.getConflict();
	}

	public void setScheduleWorld(Schedule solution) {
		String tempRuang;
		int hari;
		int jamAwal, durasi;

		for (int i = 0; i < listOfRoom.numberOfRoom(); i++) {
			MatrixJadwal m = new MatrixJadwal();
			tempRuang = listOfRoom.getRoom(i).getNama();

			for (int j = 0; j < solution.scheduleSize(); j++) {
				if (solution.getScheduleAtIdx(j).getRuangan().equals(tempRuang)) {
					hari = solution.getScheduleAtIdx(j).getHariTrue();
					jamAwal = solution.getScheduleAtIdx(j).getJamMulai();
					durasi = solution.getScheduleAtIdx(j).getDurasi();
					for (int k = 0; k < durasi; k++) {
						if (m.elmt[(jamAwal-7+k)][hari].equals("")) {
							m.elmt[(jamAwal-7+k)][hari] = solution.getScheduleAtIdx(j).getNamaKegiatan();
						}	 else {
							m.elmt[(jamAwal-7+k)][hari] += " - " + solution.getScheduleAtIdx(j).getNamaKegiatan();
						}
					}
				}
			}


			for (int j = 0; j < 5; j++) {
				if (listOfRoom.getRoom(i).getHariAtIdx(j)) {
					for (int k = 0; k < 11; k++) {
						if ((k >= (listOfRoom.getRoom(i).getJamMulai()-7))&&(k <= listOfRoom.getRoom(i).getJamSelesai()-7)) {

						} else {
							m.elmt[k][j] = "";
						}
					}
				} else {
					for (int k = 0; k < 11; k++) {
						m.elmt[k][j] = "";
					}
				}
			}

			this.scheduleWorld.add(m);
		}
	}

	@Override
	public String toString() {
		String out = "";
		for (int i = 0; i < scheduleWorld.size(); i++) {
			out += "Ruangan: " + listOfRoom.getRoom(i).getNama() + "\n";
			out += "";
			out += scheduleWorld.get(i);
		}
		return out;
	}

}