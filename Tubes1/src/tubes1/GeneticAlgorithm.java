/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes1;
import java.util.Arrays;
import java.util.Random;
import java.util.List;
/**
 *
 * @author rezaramadhan
 */
public class GeneticAlgorithm {
    private List<Jadwal> matkul; //list jadwal yg perlu diisi
    private List<Ruangan> ruang; //list ruangan yang tersedia
    private String[] population; //populasi tempat gen2 berkembang biak
    private int nPopulation;

    public GeneticAlgorithm(List<Jadwal> k, List<Ruangan> r) { //konstruktor
            matkul = k;
            ruang = r;
            population = new String[nPopulation];
    }

    //generate String random, representasi satu individu
    public String generateRandom() {
        int totalGenerate = matkul.size();
        String str = "";

         //format: <idxruang><Hari><mulai><selesai>
        for (int i = 0; i < totalGenerate; i++) {
            str = str + randomOneKelas(i);
        }
        return str;
    }
    
    public String randomOneKelas(int idxMatkul) {
        String tmp = "";
        int max, min;
        int idx, hari, mulai, selesai;
        Random rand = new Random();
        
        
        
        //generate idxruang
        idx = rand.nextInt(ruang.size());
        String[] listruang = matkul.get(idx).Ruangan.split(",");

        while (!Arrays.asList(listruang).contains(ruang.get(idx).Nama)) {
            idx = rand.nextInt(ruang.size());
        }

        tmp = tmp+idx;

        //generate hari
        hari = rand.nextInt(5) + 1;
        tmp = tmp + hari;

        min = Math.min(Integer.parseInt(matkul.get(idxMatkul).JamMulai), Integer.parseInt(ruang.get(idx).JamMulai));
        max = Math.max(Integer.parseInt(matkul.get(idxMatkul).JamSelesai), Integer.parseInt(ruang.get(idx).JamSelesai)) - Integer.parseInt(matkul.get(idxMatkul).Durasi);

        //generate mulai
        mulai = rand.nextInt(max-min + 1) + min;
        tmp = tmp + mulai;

        //generate selesai
        selesai = mulai + Integer.parseInt(matkul.get(idxMatkul).Durasi);
        tmp = tmp + selesai;
        
        return tmp;
    } 

    public String[] pisahstring(String str){
        /* Memisahkan string panjang yang berisi daftar kelas beserta
         * hari, jam mulai, selesai menjadi array yang berisi string
         * dengan format
         * iHms | iHms | iHms | ...
         * dengan rincian:
         * i = index matkul (a-z)
         * H = hari dalam angka
         * m = jam mulai (a-z) a:7, b:8, dst
         * s = jam selesai (a-z) idem atas
         */

        //inisisalisasi tabJadwal 
        String[] tabJadwal = new String [20];
        for (int i=0; i<20; i++){
                tabJadwal[i] = "";
        }

        //pisah string empat empat ke tabJadwal
        int a = str.length();
        int j=0;
        int k=0;
        String strtemp = "";
        if ((a % 4)==0) {	//string kelipatan 4
                for (int i=0; i<a; i++){
                        if (j>3){
                                j=0;
                                tabJadwal[k]=strtemp;
                                strtemp = "";
                                k++;	
                        }
                        strtemp.concat(Character.toString(str.charAt(i)));
                        j++;
                }
        } else {
                //string salah, harus kelipatan 4
                //tabJadwal kosong
        }
        return tabJadwal;
    }

    public float hitungFitnessFunction(String str){
            /* Menghitung fitnessfunction dengan sebelumnya mengecek
             * terjadinya bentrok matkul pada hari dan jam yang sama
             * yaitu dengan mencatat matkul apa saja yang ada pada hari
             * yang sama dengan menempatkannya pada matriks sbg berikut:
             * baris menandakan hari
             * kolom menandakan index matkul pada hari tsb
             */

            if ((str.length() % 4) == 0){
                    String[] tabString = pisahstring(str);

                    //inisialisasi tabHariSama
                    String[][] tabHariSama = new String [5][5];
                    for (int x=0; x<5; x++){
                        for (int y=0; y<5; y++){
                                tabHariSama[x][y] = "";
                        }
                    }

                    //mengisi tabHariSama
                    String strtemp ;
                    int idxBrsHariSama = 0;
                    int idxKolHariSama;
                    for (char idxHari='a'; idxHari<='e'; idxHari++){
                        idxKolHariSama = 0;
                        for (int i=0; i< tabString.length; i++){
                                strtemp = tabString[i];
                                if (strtemp.charAt(1) == idxHari){	//mengecek kelas pada hari yg sama
                                        tabHariSama[idxBrsHariSama][idxKolHariSama] = strtemp;
                                        idxKolHariSama++;
                                }
                        }
                        idxBrsHariSama++;
                    }

                    //Mengecek terjadinya bentrok berdasar tabHariSama
                    String strtemp1 = "";
                    int countBentrok = 0;
                    int idxc;
                    for (int idxa=0; idxa<5; idxa++){
                        for(int idxb=0; idxb<5; idxb++){
                            strtemp = tabHariSama[idxa][idxb];
                            idxc=idxb+1;
                            while (idxc<5){
                                strtemp1 = tabHariSama[idxa][idxc];
                                if ((	(strtemp.charAt(2)==strtemp1.charAt(2)) || 					//kedua kelas mulai pada jam sama
                                            (strtemp.charAt(3)==strtemp1.charAt(3)) || 					//kedua kelas selesai pada jam sama
                                            (strtemp.charAt(2)>strtemp1.charAt(2) && strtemp.charAt(2)<strtemp1.charAt(3)) || 	//kelas 1 mulai saat kelas 2 berlangsung
                                            (strtemp.charAt(3)>strtemp1.charAt(2) && strtemp.charAt(3)<strtemp1.charAt(3))) 	//kelas 2 mulai saat kelas 1 berlangsung
                                            && 
                                            isSameRoom(strtemp,strtemp1)) {		//kedua kelas pada ruangan yang sama 
                                    countBentrok++;
                                }
                                idxc++;
                            }
                        }
                    }

                    //mengembalikan nilai fitness function yaitu 1/countBentrok
                    if (countBentrok == 0){	//tidak ada yg bentrok, menghindari infinite
                        return 999;
                    } else {
                        return (1 / countBentrok);
                    }
            } else {
                return -999; //error string tidak kelipatan 4
            }
    }
	
    public boolean isSameRoom(String s1, String s2){
        /* mengembalikan nilai kebenaran apakah kedua kelas berada
         * pada ruangan yang sama
         */
        return (s1.charAt(0) == s2.charAt(0));
    }

    
    public String mutation (String in) {
        /* 
            mutation of one genome
        */
        char[] out = in.toCharArray();
        int nKelas = matkul.size();
        Random rand = new Random();
        int mutatedPosition = rand.nextInt(nKelas + nKelas/2);
        if (mutatedPosition < nKelas) {
            int idxChange = (mutatedPosition - 1) * 4;
            String tmp = randomOneKelas(mutatedPosition);
            for (int i = 0; i<4; i++) {
                out[idxChange] = tmp.charAt(i);
                idxChange++;
            }
        }
        String retVal = out.toString();
        return retVal;
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

    public int selection() {
        int selected;
        
        float[] fitnessFunction = new float[nPopulation];
 
        //hitung fitness function buat setiap individu
        for (int i= 0; i<nPopulation; i++) {
            fitnessFunction[i] = hitungFitnessFunction(population[i]);
        }
        
        //hitung persentasi setiap individu
        
        
        
        return selected;
    }
            
    public void fullProcess(){

    }

}
