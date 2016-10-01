/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes1;

import java.util.ArrayList;
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
    private List<String> bestIndividu;

    public GeneticAlgorithm(List<Jadwal> k, List<Ruangan> r) { //konstruktor
            matkul = k;
            ruang = r;
            nPopulation = 100;
            population = new String[nPopulation];
            bestIndividu = new ArrayList<>();
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
        
        List<String> listHari = new ArrayList<>();
        String daftarRuang = matkul.get(idxMatkul).Ruangan;
        
        int matkulMulai = Integer.parseInt(matkul.get(idxMatkul).JamMulai.substring(0, 2));
        int matkulSelesai = Integer.parseInt(matkul.get(idxMatkul).JamSelesai.substring(0, 2));
        int ruangMulai, ruangSelesai;
        
        min = 10;
        max = 1;
        ruangMulai = 0;
        ruangSelesai = 0;
        boolean exit = false;
        while (!exit) {
            listHari.clear();
            idx = rand.nextInt(ruang.size());

            for (int i = 1; i<6; i++){
                if (matkul.get(idxMatkul).Hari.contains(Integer.toString(i)) && ruang.get(idx).Hari.contains(Integer.toString(i))) {
                    listHari.add(Integer.toString(i));
                }
            }
            
            ruangMulai = Integer.parseInt(ruang.get(idx).JamMulai.substring(0, 2));
            ruangSelesai = Integer.parseInt(ruang.get(idx).JamSelesai.substring(0, 2));
            min = Math.max(matkulMulai, ruangMulai);
            max = Math.min(matkulSelesai, ruangSelesai) - Integer.parseInt(matkul.get(idxMatkul).Durasi);
            exit = !listHari.isEmpty() && min <= max && ("-".equals(daftarRuang) || daftarRuang.contains(ruang.get(idx).Nama));
        }
        tmp = tmp+iToC(idx);

        //generate hari
        hari = rand.nextInt(listHari.size());
        tmp = tmp + listHari.get(hari);
        

        //generate mulai
        mulai = rand.nextInt(max-min + 1) + min;
        tmp = tmp + iToC(mulai);

        //generate selesai
        selesai = mulai + Integer.parseInt(matkul.get(idxMatkul).Durasi) - 1;
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
                        j++;
                }
                tabJadwal[k]=strtemp;
        } else {
                //string salah, harus kelipatan 4
                //tabJadwal kosong
        }
        return tabJadwal;
    }
    
    public float hitungFitnessFunction(String str) {
        float tempValue;
        float bentrok = hitungBentrok(str);
//        tempValue = (bentrok + hitungPersentasiIsi(str)*bentrok);
        return bentrok;
    }
    
    public float hitungBentrok(String str){
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
            String[][] tabHariSama = new String [5][tabString.length];
            for (int x=0; x<5; x++){
                for (int y=0; y<tabString.length; y++){
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
                    if (strtemp.charAt(1) == idxHari){  //mengecek kelas pada hari yg sama
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
                    
                    while (idxc<tabString.length && tabHariSama[idxa][idxc] != ""){
                        strtemp1 = tabHariSama[idxa][idxc];
                        //System.out.println(strtemp + "-" + strtemp1);
                        
                        
                        if (    ((strtemp.charAt(2)==strtemp1.charAt(2)) ||                     //kedua kelas mulai pada jam sama
                                    (strtemp.charAt(3)==strtemp1.charAt(3))  ||                     //kedua kelas selesai pada jam sama
                                    (cToI(strtemp.charAt(2))>cToI(strtemp1.charAt(2)) && cToI(strtemp.charAt(2))<=cToI(strtemp1.charAt(3))) ||  //kelas 1 mulai saat kelas 2 berlangsung
                                    (cToI(strtemp.charAt(3))>=cToI(strtemp1.charAt(2)) && cToI(strtemp.charAt(3))<cToI(strtemp1.charAt(3))) ||   //kelas 2 mulai saat kelas 1 berlangsung
                                    (cToI(strtemp.charAt(2))<cToI(strtemp1.charAt(2)) && cToI(strtemp.charAt(3))>cToI(strtemp1.charAt(3))) ||
                                    (cToI(strtemp.charAt(2))>cToI(strtemp1.charAt(2)) && cToI(strtemp.charAt(3))<cToI(strtemp1.charAt(3)))  )
                                    && 
                                    isSameRoom(strtemp,strtemp1)) {     //kedua kelas pada ruangan yang sama 
                           
                                        //  System.out.println(strtemp + "-" + strtemp1);
                                        if (strtemp.charAt(2)==strtemp1.charAt(2) && strtemp.charAt(3)<strtemp1.charAt(3)) {
                                            countBentrok += (cToI(strtemp.charAt(3)) - cToI(strtemp.charAt(2)) + 1);
                                            //System.out.println("1");
                                        } else 
                                        if (strtemp.charAt(2)==strtemp1.charAt(2) && strtemp.charAt(3)>strtemp1.charAt(3)) {
                                            countBentrok += (cToI(strtemp1.charAt(3)) - cToI(strtemp1.charAt(2)) + 1);
                                            //System.out.println("2");
                                        } else 
                                        if ((strtemp.charAt(2)==strtemp1.charAt(2)) && (strtemp.charAt(3)==strtemp1.charAt(3))) {
                                            countBentrok += (cToI(strtemp.charAt(3)) - cToI(strtemp.charAt(2)) + 1);
                                            //System.out.println("3");
                                        } else 
                                        if (strtemp.charAt(2)<strtemp1.charAt(2) && strtemp.charAt(3)==strtemp1.charAt(3)) {
                                            countBentrok += (cToI(strtemp1.charAt(3)) - cToI(strtemp1.charAt(2)) + 1);
                                            //System.out.println("4");
                                        } else 
                                        if (strtemp.charAt(2)>strtemp1.charAt(2) && strtemp.charAt(3)==strtemp1.charAt(3)) {
                                            countBentrok += (cToI(strtemp.charAt(3)) - cToI(strtemp.charAt(2)) + 1);
                                            //System.out.println("5");
                                        } else 
                                        if (strtemp.charAt(2)<strtemp1.charAt(2) && strtemp.charAt(3)<strtemp1.charAt(3)) {
                                            countBentrok += (cToI(strtemp.charAt(3))+1 - cToI(strtemp1.charAt(2)));
                                            //System.out.println("6");
                                        } else 
                                        if (strtemp.charAt(2)>strtemp1.charAt(2) && strtemp.charAt(3)>strtemp1.charAt(3)) {
                                            countBentrok += (cToI(strtemp1.charAt(3)) + 1 - cToI(strtemp.charAt(2)));
                                            //System.out.println("7");
                                        } else 
                                        if (strtemp.charAt(2)<strtemp1.charAt(2) && strtemp.charAt(3)>strtemp1.charAt(3)) {
                                            countBentrok += (cToI(strtemp1.charAt(3)) - cToI(strtemp1.charAt(2)) + 1);
                                            //System.out.println("8");
                                        } else 
                                        if (strtemp.charAt(2)>strtemp1.charAt(2) && strtemp.charAt(3)<strtemp1.charAt(3)) {
                                            countBentrok += (cToI(strtemp.charAt(3)) - cToI(strtemp.charAt(2)) + 1);
                                            //System.out.println("9");
                                        }
                          
                                    } 
                        idxc++;
                    }
                }
            }

            //mengembalikan nilai fitness function yaitu 1/countBentrok
          //  System.out.println("jumlah bentrok : " +countBentrok);
            if (countBentrok == 0){ //tidak ada yg bentrok, menghindari infinite
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
        if (mutatedPosition < nKelas) {
            int idxChange = (mutatedPosition) * 4;
            String tmp = randomOneKelas(mutatedPosition);
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
        //menghasilkan anak 1
        for (int i=0; i<=((nSplit+1)*4); i++){
            strtemp = strtemp + str1.charAt(i);
        }
        for (int i=((nSplit+1)*4)+1; i<str2.length(); i++){
            strtemp = strtemp + str2.charAt(i);
        }

        //menghasilkan anak 2
        for (int i=0; i<=((nSplit+1)*4); i++){
            strtemp1 = strtemp1 + str2.charAt(i);
        }
        for (int i=((nSplit+1)*4)+1; i<str1.length(); i++){
            strtemp1 = strtemp1 + str1.charAt(i);
        }

        //menggabungkan kedua anak pada array of string
        String[] tabResult = new String[2];
        tabResult[0] = strtemp;
        tabResult[1] = strtemp1;
        return tabResult;
    }

    public String selection() {
        int selected = 0;
        
        float[] fitnessFunction = new float[nPopulation];
        
        boolean end = false;
        
        //hitung fitness function buat setiap individu
        float sum = 0;
        int itr;
//        System.out.println("Fitnessfunction");
        float best = 0;
        for (itr= 0; itr<nPopulation; itr++) {
            fitnessFunction[itr] = hitungFitnessFunction(population[itr]);
//            System.out.println(fitnessFunction[itr]);
            if (fitnessFunction[itr] >= best) {
                if (fitnessFunction[itr] > best) { //best baru
                    bestIndividu.clear();
                    best = fitnessFunction[itr];
                }
                bestIndividu.add(population[itr]);
            }
            if (fitnessFunction[itr] >= 999) {
                end = true;
                System.out.println("break");
                break;
            }
            sum = sum + fitnessFunction[itr];
        }
        if (!end) {
            //hitung persentasi setiap individu
//            System.out.println("Persentasi");
            for (int i= 0; i<nPopulation; i++) {
                fitnessFunction[i] = fitnessFunction[i]/sum;
//                System.out.println(fitnessFunction[i]);
            }
            sum = 0;
//            System.out.println("Adjusted");
            for (int i= 0; i<nPopulation; i++) {
                sum = sum + fitnessFunction[i];
                fitnessFunction[i] = sum*100;
//                System.out.println(fitnessFunction[i]);
            }


            Random rand = new Random();
            float randomNum = rand.nextFloat() * 100;
//            System.out.println("rnd "+randomNum);
//            System.out.println("psn "+fitnessFunction[selected]);
            while (randomNum > fitnessFunction[selected]) {
//                System.out.println("lo");
                selected++;
            }
//            System.out.println("selected" + selected);
            return population[selected];
        } else {
            String endselected = "=" + itr;
            return endselected;
        }
    }
    
    public float hitungPersentasiIsi(String str){
        
        
        String[] tabJadwal = pisahstring(str);
        int idxa;
        int idxb;
        int idxHari;
        int idxJamMulai;
        int idxJam;
        int idxJamSelesai;
        int sizeHari;
        int valHari;
        int countSlot = 0;
        int countUsed = 0;
        String strtemp = "";
        boolean isKetemu; 
        for (idxa = 0; idxa < ruang.size(); idxa++){
            strtemp = Character.toString(((ruang.get(idxa)).JamMulai).charAt(0)) + Character.toString(((ruang.get(idxa)).JamMulai).charAt(1));
            idxJamMulai = Integer.parseInt(strtemp);
            strtemp = Character.toString(((ruang.get(idxa)).JamSelesai).charAt(0)) + Character.toString(((ruang.get(idxa)).JamSelesai).charAt(1));
            idxJamSelesai = Integer.parseInt(strtemp);
            idxHari = 0;
            sizeHari = (((ruang.get(idxa)).Hari)).length();
            while (idxHari < sizeHari){	//iterasi hari                
                valHari = Integer.parseInt(Character.toString(((ruang.get(idxa)).Hari).charAt(idxHari)));
                
                //iterasi jam mulai - jam selesai
                idxJam = idxJamMulai;
                while (idxJam < idxJamSelesai){
                    isKetemu = false;
                    idxb = 0;
                    while (!isKetemu && (idxb < tabJadwal.length)){	//searching pada jadwal
                        if (	(cToI((tabJadwal[idxb]).charAt(0)) == idxa) && 		//ruangan sama
                                        (Integer.parseInt(Character.toString(tabJadwal[idxb].charAt(1))) == valHari) && 		//hari sama
                                        (((cToI((tabJadwal[idxb]).charAt(2))) <= idxJam && (cToI((tabJadwal[idxb]).charAt(3))) >= idxJam) 	//jam berada di antara mulai dan selesai sebuah matkul
                                    )
                            ){
                                countUsed++;
                                isKetemu = true;
                            }
                        idxb++;
                    }
                    countSlot++;
                    idxJam++;
                }
            idxHari = idxHari + 2;
            }


        }

        //Jadwal Algorithm
        // 1. pilih ruangan
        // 2. iterasi jam available ruangan
        // 3. cocokin ke string apakah ada yang sama?
        // 4. hitung persentasi
        return ((float)countUsed/(float)countSlot);
    }
    
    public String execute(){
        //generate random
        for (int i = 0; i<nPopulation; i++) {
            population[i] = generateRandom();
        }
        //loop sampe puas -> ??????
        int itr = 0;
        int maxIteration = 128;
        int end = -1;
        
        String parent1;
        String parent2;
        String[] tempArr = new String[nPopulation];
        String[] crossoverRetval;
        
        
//        parent1 = selection();
//        return parent1;
        while (itr < maxIteration && end == -1) {
            //select generasi baru, loop sebanyak populasi/2
//            System.out.println("lo" + itr);
            for (int i = 0; i< nPopulation; i+=2) {
                //select parent1 & parent2
//                System.out.println(nPopulation);
//                for (int j = 0; j<nPopulation; j++) {
//                    System.out.println(population[j]);
//                }
                parent1 = selection();
                if (parent1.charAt(0) == '=') {
//                    System.out.println("breakexecution");
//                    System.out.println(parent1);
                    end = Integer.parseInt(parent1.substring(1));
//                    System.out.println(end);
//                    System.out.println(population[end]);
                    break;
                }
                parent2 = selection();
                //crossover
                crossoverRetval = crossover(parent1, parent2);
                
                //mutation + add ke populasi baru
                tempArr[i] = mutation(crossoverRetval[0]);
                tempArr[i+1] = mutation(crossoverRetval[1]);
                
                
            }
            //populasi lama mati, populasi baru jadi populasi lama
            if (end == -1) {
                population = tempArr.clone();
                itr++;
            }
        }
//        System.out.println(population.length);
//        System.out.println(population[end]);            
        if (end != -1) {
            return population[end];
        } else {
            return cariBest();
        }
    }

    public String cariBest() {
        float max = -1;
        String bestOne = "";
        for (String s : bestIndividu) {
            if (max < hitungPersentasiIsi(s)) {
                max = hitungPersentasiIsi(s);
                bestOne = s;
            }
        }
        
        return bestOne;
    }
    
    public void printJadwal(String str){
        /*  Prosedur berfungsi untuk mencetak matriks jadwal dengan baris menunjukkan jam dan kolom menunjukkan hari
                dari tiap Ruangan (1 ruangan 1 matriks)
                I.S : ruang, matkul, dan str terdefinisi
                F.S : jadwal untuk tiap ruang tercetak ke layar
        */

        //kumpulan kamus lokal
        int idxb;       //buat iterasi tabJadwal
        int idxHari;    //buat iterasi hari
        int idxJam;     //buat iterasi jam
        int idxJamSelesai;  //batas iterasi jam
        int sizeHari;   //panjang string hari
        int valHari;    //nilai hari dalam angka
        String strtemp = "";    //penyimpan sring utk convert jam ruangan
        int x;  //iterasi baris
        int y;  //iterasi kolom
        int i;  //iterasi baris
        int j;  //iterasi kolom

        //membuat dan menginisialisasi matriks jadwal yang akan dicetak ke layar
        String[][] matriksjadwal = new String[11][5];
        

        //mengassign string jadwal ke bentuk array of string
        String[] tabJadwal = pisahstring(str);

        //mengisi dan mencetak matriksjadwal
        //iterasi ruangan
        for (int idxruang = 0; idxruang < ruang.size(); idxruang++){    
            idxHari = 0;
            sizeHari = ((ruang.get(idxruang)).Hari).length();
            for (i = 0; i < 11; i++){
                for (j = 0; j < 5; j++){
                    matriksjadwal[i][j] = "";
                }
            }

            //iterasi hari
            while (idxHari < sizeHari){     
                valHari = Integer.parseInt(Character.toString(((ruang.get(idxruang)).Hari).charAt(idxHari)));

                //mengisi idxJam dan idxJamSelesai dari ruang           
                strtemp = Character.toString(((ruang.get(idxruang)).JamMulai).charAt(0)) + Character.toString(((ruang.get(idxruang)).JamMulai).charAt(1));
                idxJam = Integer.parseInt(strtemp);
                
                strtemp = Character.toString(((ruang.get(idxruang)).JamSelesai).charAt(0)) + Character.toString(((ruang.get(idxruang)).JamSelesai).charAt(1));
                idxJamSelesai = Integer.parseInt(strtemp);

                //iterasi jam mulai - jam selesai
                while (idxJam < idxJamSelesai){
                    
                    matriksjadwal[idxJam-7][valHari-1] += "v";  //penanda jika slot tersedia
                    idxb = 0;

                    //searching digunakannya slot pada tabJadwal
                    while (idxb < tabJadwal.length){    
                        if (    (cToI((tabJadwal[idxb]).charAt(0)) == idxruang) &&      //ruangan sama
                                    (Integer.parseInt(Character.toString((tabJadwal[idxb]).charAt(1))) == valHari) &&       //hari sama
                                        //(cToI((tabJadwal[idxb]).charAt(2)) == idxJam ) || //jam mulai sama
                                          //  (cToI((tabJadwal[idxb]).charAt(3)) == idxJam + 1) ||  //jam selesai sama
                                            ((cToI((tabJadwal[idxb]).charAt(2))) <= idxJam && (cToI((tabJadwal[idxb]).charAt(3))) >= idxJam)    //jam berada di antara mulai dan selesai sebuah matkul
                                    
                            ){
                                //System.out.println((matkul.get(idxb)).NamaKegiatan + "jam mulai : "+idxJam);
                                matriksjadwal[idxJam-7][valHari-1] += " - " + (matkul.get(idxb)).NamaKegiatan;
                            }
                        idxb++;
                    }
                    idxJam++;
                }
                idxHari = idxHari + 2;
            }

            //pencetakan ke layar
            System.out.println("Jadwal Ruangan " + (ruang.get(idxruang)).Nama);
            System.out.print("|Jam▼ - Hari►|");
            System.out.println("             Senin             |             Selasa            |              Rabu             |             Kamis             |             Jumat             |");
            for (int d = 0; d < 175; d++){
                        System.out.print("_");
            }
            System.out.println();
            int z;
            for (x = 0; x < 11; x++){
                    if ((x+7) < 10){
                            System.out.print("|  0" + (x+7) +".00      |");
                    } else {
                            System.out.print("|  " + (x+7) +".00      |");
                    }

                    for (y = 0; y < 5; y++){
                        System.out.print(matriksjadwal[x][y]);
                        for (z = matriksjadwal[x][y].length(); z < 31; z++){
                            System.out.print(" ");
                        }
                        System.out.print("|");
                        
                    }
                    System.out.println();
                    for (int d = 0; d < 175; d++){
                        System.out.print("_");
                    }
                    System.out.println();
            }
            System.out.println("Keterangan:  'v' menunjukkan slot tersedia ");
            System.out.println();
            System.out.println();

        }
    }
    
    public List<MatrixJadwal> isiMatrixJadwal(String str){
        List<MatrixJadwal> lMJ = new ArrayList<>();
        int idxruang;
        int idxHari;
        int sizeHari;
        int valHari;
        String strtemp;
        int idxJam;
        int idxJamSelesai;
        int i;
        int j;
        int idxb;
        
        String[] tabJadwal = pisahstring(str);
        
        
        for (idxruang = 0; idxruang < ruang.size(); idxruang++){    
        
            MatrixJadwal m = new MatrixJadwal();
            
            idxHari = 0;
            sizeHari = ((ruang.get(idxruang)).Hari).length();
            
            //menginisalisasi matrixjadwal
            for (int x = 0; x < 11; x++){
                for (int y = 0; y < 5; y++){
                    m.elmt[x][y]="X";
                }
            }
            

            //iterasi hari
            while (idxHari < sizeHari){     
                valHari = Integer.parseInt(Character.toString(((ruang.get(idxruang)).Hari).charAt(idxHari)));

                //mengisi idxJam dan idxJamSelesai dari ruang           
                strtemp = Character.toString(((ruang.get(idxruang)).JamMulai).charAt(0)) + Character.toString(((ruang.get(idxruang)).JamMulai).charAt(1));
                idxJam = Integer.parseInt(strtemp);
                
                strtemp = Character.toString(((ruang.get(idxruang)).JamSelesai).charAt(0)) + Character.toString(((ruang.get(idxruang)).JamSelesai).charAt(1));
                idxJamSelesai = Integer.parseInt(strtemp);

                //iterasi jam mulai - jam selesai
                while (idxJam < idxJamSelesai){
                    idxb = 0;

                    m.elmt[idxJam-7][valHari-1] = "";
                    //searching digunakannya slot pada tabJadwal
                    while (idxb < tabJadwal.length){    
                        if (    (cToI((tabJadwal[idxb]).charAt(0)) == idxruang) &&      //ruangan sama
                                    (Integer.parseInt(Character.toString((tabJadwal[idxb]).charAt(1))) == valHari) &&       //hari sama
                                        //(cToI((tabJadwal[idxb]).charAt(2)) == idxJam ) || //jam mulai sama
                                          //  (cToI((tabJadwal[idxb]).charAt(3)) == idxJam + 1) ||  //jam selesai sama
                                            ((cToI((tabJadwal[idxb]).charAt(2))) <= idxJam && (cToI((tabJadwal[idxb]).charAt(3))) >= idxJam)    //jam berada di antara mulai dan selesai sebuah matkul
                                    
                            ){
                                if ((m.elmt[idxJam-7][valHari-1]).length() == 0){
                                    m.elmt[idxJam-7][valHari-1] = (matkul.get(idxb)).NamaKegiatan;
                                } else {
                                    m.elmt[idxJam-7][valHari-1] += " - " + (matkul.get(idxb)).NamaKegiatan;   
                                }
                            }
                        idxb++;
                    }
                    idxJam++;
                }
                idxHari = idxHari + 2;
            }
            lMJ.add(m);
        } 
            
       return lMJ; 
    }
    
    
    public void printMatrixJadwal(List<MatrixJadwal> lMJ){
        /*  Prosedur berfungsi untuk mencetak matriks jadwal dengan baris menunjukkan jam dan kolom menunjukkan hari
                dari tiap Ruangan (1 ruangan 1 matriks)
                I.S : ruang, matkul, dan str terdefinisi
                F.S : jadwal untuk tiap ruang tercetak ke layar
        */

        //kumpulan kamus lokal
        int idxb;       //buat iterasi tabJadwal
        int idxHari;    //buat iterasi hari
        int idxJam;     //buat iterasi jam
        int idxJamSelesai;  //batas iterasi jam
        int sizeHari;   //panjang string hari
        int valHari;    //nilai hari dalam angka
        String strtemp = "";    //penyimpan sring utk convert jam ruangan
        int x;  //iterasi baris
        int y;  //iterasi kolom
        int i;  //iterasi baris
        int j;  //iterasi kolom
        

        for (int idxruang = 0; idxruang < lMJ.size(); idxruang++){
            //pencetakan ke layar
            System.out.println("Jadwal Ruangan " + (ruang.get(idxruang)).Nama);
            System.out.print("|Jam▼ - Hari►|");
            System.out.println("             Senin             |             Selasa            |              Rabu             |             Kamis             |             Jumat             |");
            for (int d = 0; d < 175; d++){
                        System.out.print("_");
            }
            System.out.println();
            int z;
            for (x = 0; x < 11; x++){
                    if ((x+7) < 10){
                            System.out.print("|  0" + (x+7) +".00      |");
                    } else {
                            System.out.print("|  " + (x+7) +".00      |");
                    }

                    for (y = 0; y < 5; y++){
                        System.out.print((lMJ.get(idxruang)).elmt[x][y]);
                        for (z = (lMJ.get(idxruang)).elmt[x][y].length(); z < 31; z++){
                            System.out.print(" ");
                        }
                        System.out.print("|");
                        
                    }
                    System.out.println();
                    for (int d = 0; d < 175; d++){
                        System.out.print("_");
                    }
                    System.out.println();
            }
            System.out.println("Keterangan : 'X' menunjukkan ruangan pada slot waktu tersebut tidak tersedia");
            System.out.println();
            System.out.println();

        }
    }
}
