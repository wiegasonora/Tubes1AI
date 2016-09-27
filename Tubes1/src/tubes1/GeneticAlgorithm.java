/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes1;

import java.util.ArrayList;
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
//        System.out.println(totalGenerate);
        String str = "";

         //format: <idxruang><Hari><mulai><selesai>
        for (int i = 0; i < totalGenerate; i++) {
            str = str + randomOneKelas(i);
        }
        return str;
    }
    
    public int cToI(char c) {
        return (int) c - (int) 'a';
    }
    
    public char iToC(int i) {
        return (char) (i + (int) 'a');
    }
    public String randomOneKelas(int idxMatkul) {
        String tmp = "";
        int max, min;
        int idx, hari, mulai, selesai;
        Random rand = new Random();

        //generate idxruang
        idx = rand.nextInt(ruang.size());
        String[] listruang = matkul.get(idxMatkul).Ruangan.split(",");
        
        
//        System.out.println(listruang.length);
//        System.out.println("a");

        List<String> listHari = new ArrayList<>();
        
        while ((listruang.length > 0 && Arrays.asList(listruang).contains(ruang.get(idx).Nama)) || listHari.size()==0) {
            listHari.clear();
            idx = rand.nextInt(ruang.size());

            for (int i = 1; i<6; i++){
                if (matkul.get(idxMatkul).Hari.contains(Integer.toString(i)) && ruang.get(idx).Hari.contains(Integer.toString(i))) {
                    listHari.add(Integer.toString(i));
                }
            }
        }
//        System.out.println("b");
        tmp = tmp+iToC(idx);

        //generate hari

        hari = rand.nextInt(listHari.size());
        tmp = tmp + listHari.get(hari);
        
        int matkulMulai = Integer.parseInt(matkul.get(idxMatkul).JamMulai.substring(0, 2));
        int matkulSelesai = Integer.parseInt(matkul.get(idxMatkul).JamSelesai.substring(0, 2));
        int ruangMulai = Integer.parseInt(ruang.get(idx).JamMulai.substring(0, 2));
        int ruangSelesai = Integer.parseInt(ruang.get(idx).JamSelesai.substring(0, 2));
//        System.out.println(matkulMulai);
//        System.out.println(matkulSelesai);
//        System.out.println(ruangMulai);
//        System.out.println(ruangSelesai);
        min = Math.min(matkulMulai, ruangMulai);
        max = Math.max(matkulSelesai, ruangSelesai) - Integer.parseInt(matkul.get(idxMatkul).Durasi);

        //generate mulai
        mulai = rand.nextInt(max-min + 1) + min;
        tmp = tmp + iToC(mulai);

        //generate selesai
        selesai = mulai + Integer.parseInt(matkul.get(idxMatkul).Durasi);
        tmp = tmp + iToC(selesai);
        
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
        int max = str.length()/4;
        String[] tabJadwal = new String [max];
        for (int i=0; i<max; i++){
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
                        strtemp = strtemp + str.charAt(i);
//                        strtemp.concat(Character.toString(str.charAt(i)));
                        j++;
                }
                tabJadwal[k]=strtemp;
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
//                   System.out.println(tabString[7]);
            String[][] tabHariSama = new String [5][tabString.length];
            for (int x=0; x<5; x++){
                for (int y=0; y<5; y++){
                    tabHariSama[x][y] = "";
                }
            }

            //mengisi tabHariSama
            String strtemp ;
            int idxBrsHariSama = 0;
            int idxKolHariSama;
            for (char idxHari='1'; idxHari<='5'; idxHari++){
                idxKolHariSama = 0;
                for (int i=0; i< tabString.length; i++){

                    strtemp = tabString[i];
//                                System.out.println(strtemp);
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
                for(int idxb=0; idxb<tabString.length; idxb++){
                    strtemp = tabHariSama[idxa][idxb];
                    idxc=idxb+1;
                    if (tabHariSama[idxa][idxb] == "") {
                        break;
                    }
                    while (idxc<5 && tabHariSama[idxa][idxc] != ""){
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
                return (1 / (float)countBentrok);
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
        int mutatedPosition = rand.nextInt(nKelas + nKelas/4);
//        System.out.println(mutatedPosition);
        if (mutatedPosition < nKelas) {
            int idxChange = (mutatedPosition) * 4;
            String tmp = randomOneKelas(mutatedPosition);
//            System.out.println(tmp);
            for (int i = 0; i<4; i++) {
                out[idxChange] = tmp.charAt(i);
                idxChange++;
            }
        }
        String retVal = String.valueOf(out);
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
        int nSplit = (int )(Math.random() * (str1.length()/4 - 1));
//        System.out.println("len " + str1.length());
//        System.out.println("nsplit " + nSplit);
        //menghasilkan anak 1
        for (int i=0; i<=((nSplit+1)*4); i++){
            strtemp = strtemp + str1.charAt(i);
//               strtemp.concat(Character.toString(str1.charAt(i)));
        }
        for (int i=((nSplit+1)*4)+1; i<str2.length(); i++){
            strtemp = strtemp + str2.charAt(i);
//               strtemp.concat(Character.toString(str2.charAt(i)));
        }

        //menghasilkan anak 2
        for (int i=0; i<=((nSplit+1)*4); i++){
            strtemp1 = strtemp1 + str2.charAt(i);
//               strtemp1.concat(Character.toString(str2.charAt(i)));
        }
        for (int i=((nSplit+1)*4)+1; i<str1.length(); i++){
            strtemp1 = strtemp1 + str1.charAt(i);
//               strtemp1.concat(Character.toString(str1.charAt(i)));
        }

        //menggabungkan kedua anak pada array of string
        String[] tabResult = new String[2];
        tabResult[0] = strtemp;
        tabResult[1] = strtemp1;
//        System.out.println(tabResult[0]);
//        System.out.println(tabResult[1]);
        return tabResult;
    }

    public int selection() {
        int selected = 0;
        
        float[] fitnessFunction = new float[nPopulation];
 
        //hitung fitness function buat setiap individu
        float sum = 0;
        for (int i= 0; i<nPopulation; i++) {
            fitnessFunction[i] = hitungFitnessFunction(population[i]);
            sum = sum + fitnessFunction[i];
        }
        
        
        //hitung persentasi setiap individu
        for (int i= 0; i<nPopulation; i++) {
            fitnessFunction[i] = fitnessFunction[i]/sum;
        }
        sum = 0;
        for (int i= 0; i<nPopulation; i++) {
            sum = sum + fitnessFunction[i];
            fitnessFunction[i] = sum;
        }
        
        
        Random rand = new Random();
        float randomNum = rand.nextFloat() * 100;
        while (randomNum < fitnessFunction[selected]) {
            selected++;
        }
        selected = selected - 1;
        
        return selected;
    }
            
    public void fullProcess(){
        //generate random
        
        //loop sampe puas -> ??????
            //select generasi baru, loop sebanyak populasi/2
                //select parent1 & parent2
                //crossover
                //mutation
                //add ke populasi baru
                //populasi lama mati, populasi baru jadi populasi lama
    }

}
