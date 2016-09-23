import java.util.List;
import java.util.Random;

class GeneticAlgorithm {
	private List<Kelas> matkul;
	private List<Ruangan> ruang;
	private String[] population;
	private int nPopulation;
	
	public GeneticAlgorithm(List<Kelas> k, List<Ruangan> r) {
		matkul = k;
		ruang = r;
	}

	//generate String random, representasi satu individu
	public String generateRandom() {
		int totalGenerate = matkul.size();
		String str = "";
		
		String tmp;
		int max, min;
		int idx, hari, mulai, selesai;
		Random rand = new Random();
		//format: <idxruang><Hari><mulai><selesai>
		for (int i = 0; i < totalGenerate; i++) {
			tmp = "";

			//generate idxruang
			//TODO -> Cek barangkali kelasnya ga bisa di ruang yg diambil
			idx = rand.nextInt(ruang.size());
			tmp = tmp+idx;

			//generate hari
			hari = rand.nextInt(5) + 1;
			tmp = tmp + hari;

			min = Math.min(matkul.get[i].awal, ruang.get[idx].buka);
			max = Math.max(matkul.get[i].selesai, ruang.get[idx].tutup) - matkul.get[i].durasi;

			//generate mulai
			mulai = rand.nextInt(max-min + 1) + min;
			tmp = tmp + mulai;

			//generate selesai
			selesai = mulai + matkul.get[i].durasi;
			tmp = tmp + selesai;

			str = str + tmp;
		}
		return str;
	}
	
	public int fitnessFunction(String in) {

	}

	public String mutation (String in) {
		
	}

	public String crossover(String s1, String s2) {

	}

	public void fullProcess(){

	}
}