package tubes1;
import java.util.*;

public class SimulatedAnnealing {
	private CourseManager listOfCourse;
	private RoomManager listOfRoom;
	private Schedule solution;
	private double temperature;	// Sets initial temperature
	private double coolingRate; // Sets cooling rate

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
		}

		this.solution = new Schedule();

/*		for (int i = 0; i < jadwal.size(); i++) {
			this.jadwal.get(i).setNamaKegiatan(jadwal.get(i).getNamaKegiatan());
			this.jadwal.get(i).setRuangan(jadwal.get(i).getRuangan());
			this.jadwal.get(i).setJamMulai(jadwal.get(i).getJamMulai());
			this.jadwal.get(i).setJamSelesai(jadwal.get(i).getJamSelesai());
			this.jadwal.get(i).setDurasi(jadwal.get(i).getDurasi());
			this.jadwal.get(i).setHari(jadwal.get(i).getHariAsString());
			CourseManager.addCourse(this.jadwal.get(i));
		}

		for(int i = 0; i < ruangan.size(); i++) {
			this.ruangan.get(i).setNama(ruangan.get(i).getNama());
			this.ruangan.get(i).setJamMulai(ruangan.get(i).getJamMulai());
			this.ruangan.get(i).setJamSelesai(ruangan.get(i).getJamSelesai());
			this.ruangan.get(i).setHari(ruangan.get(i).getHariAsString());
			RoomManager.addRoom(this.ruangan.get(i));

		} */
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

		int currentConflict = currentSolution.getConflict();
		int neighbourConflict = 0;

		//System.out.println("Initial solution : ");
		/*for (int i = 0; i < currentSolution.scheduleSize(); i++) {
			System.out.println(currentSolution.getCourse(i));
		}*/
		//System.out.println("Total conflict : " + currentConflict);
		//System.out.println();

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

			// Cooling system
			temperature *= 1 - coolingRate;
		}

		solution.setSchedule(bestSchedule.getSchedule());
		solution.setConflict(bestSchedule.getConflict());
		//System.out.println("Final solution schedule : ");
		//System.out.println(bestSchedule);
		//System.out.println(bestSchedule.getConflict());
	}

	public Schedule showSolution() {
		return this.solution;
	}

	public int showConflict() {
		return this.solution.getConflict();
	}
}