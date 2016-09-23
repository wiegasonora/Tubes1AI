package sa;

public class SimulatedAnnealing {

	// Calculate the acceptance probability
	public static double acceptancePorbability(int conflict, int newConflict, double temperature) {
		// if new solution is better, take it
		if (newConflict < conflict) {
			return 1.0;
		} else {	// the new solution is worse, calculate an acceptance probability
			return Math.exp((energy - newEnergy) / temperature);
		}
	}

	public static void main(String[] args) {
		// Create many dumb courses
		boolean[] hariIF2111 = {true, true, true, true, true};
		Course if2111 = new Course("IF2111", "7602", 7, 11, 2, hariIF2111);
		CourseManager.addCourse(if2111);
		boolean[] hariIF2222 = {true, true, true, false, false};
		Course if2222 = new Course("IF2222", "7606", 7, 15, 4, hariIF2222);
		CourseManager.addCourse(if2222);
		boolean[] hariIF2333 = {false, true, false, true, false};
		Course if2333 = new Course("IF2333", "7610", 9, 12, 2, hariIF2333);
		CourseManager.addCourse(if2333);
		boolean[] hariIF2444 = {true, false, true, false, true};
		Course if2111 = new Course("IF2444", "Multimedia", 12, 15, 2, hariIF2444);
		CourseManager.addCourse(if2444);
		boolean[] hariIF2555 = {false, false, false, false, true};
		Course if2555 = new Course("IF2555", "Labdas 2", 8, 10, 2, hariIF2555);
		CourseManager.addCourse(if2555);
		// Sets initial temperature
		double temperature = 10000;

		// Sets cooling rate
		double coolingRate = 0.003;

		// Initialize initial solution
		Schedule currentSolution = new Schedule();
		currentSolution.randomAllSchedule();

		int currentConflict = currentSolution.getConflict();
		int neighbourConflict = 0;

		System.out.println("Initial solution : ");
		for (int i = 0; i < currentSolution.scheduleSize(); i++) {
			System.out.println(currentSolution.getCourse(i));
		}
		System.out.println("Total conflict : " + currentConflict;
		System.out.println();

		// Sets to be best solution
		Schedule bestSchedule = new Schedule(currentSolution.getSchedule());
		
		Random random = new Random();
		int indexToRandom;
		while (temperature > 1) {
			// Creates new neighbour course
			Schedule neighbourSolution = new Schedule(currentSolution.getSchedule());

			// Picks a random course to be rescheduled
			indexToRandom = random.nextInt(CourseManager.numberOfCourse());
			// Random course in indexToRandom at courseManager
			Course tempCourse = new Course(CourseCourseManager.getCourse(indexToRandom).randomCourse());
			// Changes/update value of course at index indexToRandom with new tempCourse
			neighbourSolution.setCourseAtIdx(indexToRandom, tempCourse);
			// Gets conflict of neighbourSolution
			neighbourConflict = neighbourSolution.getConflict();

			// Decides if we should accept the neighbour
			if (acceptancePorbability(currentConflict, neighbourConflict, temperature) > random.nextDouble()) {
				currentSolution = new Schedule(neighbourSolution.getSchedule());
			}

			// Keep track the best solution so far
			if (currentSolution.getConflict() < bestSchedule.getConflict()) {
				bestSchedule = new Schedule(currentSolution.getSchedule());
			}

			// Cooling system
			temp *= 1 - coolingRate;
		}
		System.out.println("Final solution schedule : ");
		System.out.println(bestSchedule);
		System.out.println(bestSchedule.getConflict());
	}
}