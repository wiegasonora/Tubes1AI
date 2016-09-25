/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes1;
import java.util.Random;
import java.util.List;
/**
 *
 * @author rezaramadhan
 */
public class GeneticAlgorithm {
    private List<Jadwal> matkul;
    private List<Ruangan> ruang;
    private String[] population;
    private int nPopulation;

    public GeneticAlgorithm(List<Jadwal> k, List<Ruangan> r) {
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

                    min = Math.min(Integer.parseInt(matkul.get(i).JamMulai), Integer.parseInt(ruang.get(idx).JamMulai));
                    max = Math.max(Integer.parseInt(matkul.get(i).JamSelesai), Integer.parseInt(ruang.get(idx).JamSelesai)) - Integer.parseInt(matkul.get(i).Durasi);

                    //generate mulai
                    mulai = rand.nextInt(max-min + 1) + min;
                    tmp = tmp + mulai;

                    //generate selesai
                    selesai = mulai + Integer.parseInt(matkul.get(i).Durasi);
                    tmp = tmp + selesai;

                    str = str + tmp;
            }
            return str;
    }

    public int fitnessFunction(String in) {

    }

    public String mutation (String in) {

    }

    public String[] crossover(String str1, String str2) {
        /* melakukan crossover antara dua string jadwal kuliah
        * yang  menghasilkan pertukaran jadwal secara parsial
        * dengan memecah secara random
        */
        String strtemp = "";
        String strtemp1 = "";
        //random batas split
        int nSplit = (int )(Math.random() * (str1.length() - 1));

        //menghasilkan anak 1
        for (int i=0; i<=((nSplit+1)*4); i++){
               strtemp.concat(Character.toString(str1.charAt(i)));
        }
        for (int i=((nSplit+1)*4)+1; i<str2.length(); i++){
               strtemp.concat(Character.toString(str2.charAt(i)));
        }

        //menghasilkan anak 2
        for (int i=0; i<=((nSplit+1)*4); i++){
               strtemp1.concat(Character.toString(str2.charAt(i)));
        }
        for (int i=((nSplit+1)*4)+1; i<str1.length(); i++){
               strtemp1.concat(Character.toString(str1.charAt(i)));
        }

        //menggabungkan kedua anak pada array of string
        String[] tabResult = new String[2];
        tabResult[0] = strtemp;
        tabResult[1] = strtemp1;
        return tabResult;
    }

    public void fullProcess(){

    }

}
