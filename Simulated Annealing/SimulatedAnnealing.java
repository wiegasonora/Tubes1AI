package sa;

public class SimulatedAnnealing {
	private ArrayList jadwal;
	private ArrayList ruangan;
	private double temperature;	// Sets initial temperature
	private double coolingRate; // Sets cooling rate


	public SimulatedAnnealing(List<Jadwal> jadwal, List<Ruangan> ruangan) {
		this.jadwal = new ArrayList<Jadwal>();
		this.ruangan = new ArrayList<Ruangan>();
		this.temperature = 1000;
		this.coolingRate = 0.003;

		for (int i = 0; i < jadwal.size(); i++) {
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

		}
	}

	// Calculate the acceptance probability
	public double acceptancePorbability(int conflict, int newConflict, double temperature) {
		// if new solution is better, take it
		if (newConflict < conflict) {
			return 1.0;
		} else {	// the new solution is worse, calculate an acceptance probability
			return Math.exp((energy - newEnergy) / temperature);
		}
	}

	public Jadwal execute() {
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
			indexToRandom = random.nextInt(CourseManager.numberOfCourse());
			// Random course in indexToRandom at courseManager
			Jadwal tempCourse = new Jadwal(CourseCourseManager.getCourse(indexToRandom).randomJadwal());
			// Changes/update value of course at index indexToRandom with new tempCourse
			neighbourSolution.setCourseAtIdx(indexToRandom, tempCourse);
			// Gets conflict of neighbourSolution
			neighbourConflict = neighbourSolution.getConflict();

			// Decides if we should accept the neighbour
			if (acceptancePorbability(currentConflict, neighbourConflict, temperature) > random.nextDouble()) {
				currentSolution.setSchedule(neighbourSolution.getSchedule());
				currentSolution.setConflict(neighbourSolution.getConflict());
			}

			// Keep track the best solution so far
			if (currentSolution.getConflict() < bestSchedule.getConflict()) {
				bestSchedule.setSchedule(currentSolution.getSchedule());
				bestSchedule.setConflict(currentSolution.getConflict());
			}

			// Cooling system
			temp *= 1 - coolingRate;
		}

		return bestSchedule;
		//System.out.println("Final solution schedule : ");
		//System.out.println(bestSchedule);
		//System.out.println(bestSchedule.getConflict());
	}
}