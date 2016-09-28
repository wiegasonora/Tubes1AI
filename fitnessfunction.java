//Author = Wiega

import java.util.Scanner;
import java.io.*;
import java.util.List;


public class fitnessfunction{
	public int cToI(char c) {
        return (int) c - (int) 'a';
    }
    
    public char iToC(int i) {
        return (char) (i + (int) 'a');
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
			String strtemp = "";
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
	
	public String[] crossOver(String str1, String str2){
		/* melakukan crossover antara dua string jadwal kuliah
		 * yang  menghasilkan pertukaran jadwal secara parsial
		 * dengan memecah secara random
		 */
		
		//dua penyimpan sementara hasil crossover
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

	public float hitungPersentasiIsi(List<Ruangan> lRuang,String str){
		String[] tabJadwal = pisahstring(str);
		int idxa;
		int idxb;
		int idxHari;
		int idxJam;
		int idxJamSelesai;
		int sizeHari;
		int valHari;
		int countSlot = 0;
		int countUsed = 0;
		String strtemp = "";
		boolean isKetemu; 
		for (idxa = 0; idxa < lRuang.size(); idxa++){
			strtemp = Character.toString(((lRuang.get(idxa)).JamMulai).charAt(0)) + Character.toString(((lRuang.get(idxa)).JamMulai).charAt(1));
 			idxJam = Integer.parseInt(strtemp);
 			strtemp = Character.toString(((lRuang.get(idxa)).JamSelesai).charAt(0)) + Character.toString(((lRuang.get(idxa)).JamSelesai).charAt(1));
 			idxJamSelesai = Integer.parseInt(strtemp);

			idxHari = 0;
			sizeHari = (Character.toString(((lRuang.get(idxa)).Hari).charAt(0))).length();
			while (idxHari < sizeHari){	//iterasi hari
				valHari = Integer.parseInt(Character.toString(((lRuang.get(idxa)).Hari).charAt(idxHari)));
			
				//iterasi jam mulai - jam selesai
	 			while (idxJam < idxJamSelesai){
	 				isKetemu = false;
	 				idxb = 0;
	 				while (!isKetemu && (idxb < tabJadwal.length)){	//searching pada jadwal
	 					if (	(cToI((tabJadwal[idxb]).charAt(0)) == idxa) && 		//ruangan sama
	 							(((tabJadwal[idxb]).charAt(1)) == valHari) && 		//hari sama
	 							((cToI((tabJadwal[idxb]).charAt(2)) + 7 == idxJam ) ||	//jam mulai sama
	 							(cToI((tabJadwal[idxb]).charAt(3)) + 7 == idxJam + 1) ||	//jam selesai sama
	 							((cToI((tabJadwal[idxb]).charAt(2)) + 7) < idxJam && (cToI((tabJadwal[idxb]).charAt(3)) + 7) > idxJam) 	//jam berada di antara mulai dan selesai sebuah matkul
	 							)
	 						){
	 							countUsed++;
	 							isKetemu = true;
	 						}
	 					countSlot++;
	 					idxb++;
	 				}
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

		return (countUsed/countSlot);
	}


	public boolean isSamaJam(int num, char c){
		return ((cToI(c)+7) == num);
	}

	public void printJadwal(List<Ruangan> lRuang, List<Jadwal> lJadwal, String str){
		/* 	Prosedur berfungsi untuk mencetak matriks jadwal dengan baris menunjukkan jam dan kolom menunjukkan hari
			dari tiap Ruangan (1 ruangan 1 matriks)
			I.S : lRuang, lJadwal, dan str terdefinisi
			F.S : jadwal untuk tiap ruang tercetak ke layar
		*/

		//kumpulan kamus lokal
		int idxb;		//buat iterasi tabJadwal
		int idxHari;	//buat iterasi hari
		int idxJam;		//buat iterasi jam
		int idxJamSelesai;	//batas iterasi jam
		int sizeHari;	//panjang string hari
		int valHari;	//nilai hari dalam angka
		String strtemp = "";	//penyimpan sring utk convert jam ruangan
		int x;	//iterasi baris
		int y;	//iterasi kolom
		int i;	//iterasi baris
		int j;	//iterasi kolom

		//membuat dan menginisialisasi matriks jadwal yang akan dicetak ke layar
		String[][] matriksjadwal = new String[11][5];
		for (i = 0; i < 11; i++){
			for (j = 0; j < 5; j++){
				matriksjadwal[i][j] = "";
			}
		}

		//mengassign string jadwal ke bentuk array of string
		String[] tabJadwal = pisahstring(str);
		
		//mengisi dan mencetak matriksjadwal
		//iterasi ruangan
		for (int idxruang = 0; idxruang < lRuang.size(); idxruang++){	
			idxHari = 0;
			sizeHari = ((lRuang.get(idxruang)).Hari).length();

			//iterasi hari
			while (idxHari < sizeHari){		
				valHari = Integer.parseInt(Character.toString(((lRuang.get(idxruang)).Hari).charAt(idxHari)));
				
				//mengisi idxJam dan idxJamSelesai dari lRuang			
				strtemp = Character.toString(((lRuang.get(idxruang)).JamMulai).charAt(0)) + Character.toString(((lRuang.get(idxruang)).JamMulai).charAt(1));
	 			idxJam = Integer.parseInt(strtemp);
	 			strtemp = Character.toString(((lRuang.get(idxruang)).JamSelesai).charAt(0)) + Character.toString(((lRuang.get(idxruang)).JamSelesai).charAt(1));
				idxJamSelesai = Integer.parseInt(strtemp);

				//iterasi jam mulai - jam selesai
	 			while (idxJam < idxJamSelesai){
	 				matriksjadwal[idxJam-7][idxHari] += "v";	//penanda jika slot tersedia
	 				idxb = 0;

	 				//searching digunakannya slot pada tabJadwal
	 				while (idxb < tabJadwal.length){	
	 					if (	(cToI((tabJadwal[idxb]).charAt(0)) == idxruang) && 		//ruangan sama
	 							(Integer.parseInt(Character.toString((tabJadwal[idxb]).charAt(1))) == valHari) && 		//hari sama
	 							(	(cToI((tabJadwal[idxb]).charAt(2)) == idxJam ) ||	//jam mulai sama
	 								(cToI((tabJadwal[idxb]).charAt(3)) == idxJam + 1) ||	//jam selesai sama
	 								((cToI((tabJadwal[idxb]).charAt(2))) < idxJam && (cToI((tabJadwal[idxb]).charAt(3))) > idxJam) 	//jam berada di antara mulai dan selesai sebuah matkul
	 							)
	 						){
	 							matriksjadwal[idxJam-7][idxHari] += " - " + (lJadwal.get(idxb)).NamaKegiatan;
	 						}
	 					idxb++;
	 				}
	 				idxJam++;
	 			}
				idxHari = idxHari + 2;
			}

			//pencetakan ke layar
			System.out.println("Jadwal Ruangan " + (lRuang.get(idxruang)).Nama);
			System.out.print("|Jam▼ - Hari►|");
			for (x = 0; x < 5; x++){
				System.out.print("    " + x +"    |");
			}
			System.out.println();
			for (x = 0; x < 11; x++){
				if ((x+7) < 10){
					System.out.print("  0" + (x+7) +".00    |");
				} else {
					System.out.print("  " + (x+7) +".00    |");
				}
				
				for (y = 0; y < 5; y++){
					System.out.print(" " + matriksjadwal[x][y] + "  ");
				}
				System.out.println();
			}
			System.out.println("Keterangan:  'v' menunjukkan slot tersedia ");
			System.out.println();
			System.out.println();

		}
	}

}


//// Biar lolos compile ////
class Ruangan {
    public String Nama;
    public String JamMulai;
    public String JamSelesai;
    public String Hari;
}

class Jadwal {
    public String NamaKegiatan;
    public String Ruangan;
    public String JamMulai;
    public String JamSelesai;
    public String Durasi;
    public String Hari;

    // Constructs an intial course
    public Jadwal() {
        this.NamaKegiatan = "IFXXXX";
        this.Ruangan = "";
        this.JamMulai = "0";
        this.JamSelesai = "0";
        this.Durasi = "0";
        this.Hari = "";
    }
    
    // Constructs a course with certain of parameters
    public Jadwal(String nm, String rg, String mulai, String selesai, String dur, String hr) {
        this.NamaKegiatan = nm;
        this.Ruangan = rg;
        this.JamMulai = mulai;
        this.JamSelesai = selesai;
        this.Durasi = dur;
        this.Hari = hr;
    }

    // Constructs a course from another course
    public Jadwal(Jadwal jadwal) {
        this.NamaKegiatan = jadwal.NamaKegiatan;
        this.Ruangan = jadwal.Ruangan;
        this.JamMulai = jadwal.JamMulai;
        this.JamSelesai = jadwal.JamSelesai;
        this.Durasi = jadwal.Durasi;
        this.Hari = jadwal.Hari;
    }
}