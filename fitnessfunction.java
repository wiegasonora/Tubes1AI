//Author = Wiega

import java.util.Scanner;
import java.io.*;

public class fitnessfunction{
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
		 
		String[] tabJadwal = new String [20];
		for (int i=0; i<20; i++){
			tabJadwal[i] = "";
		}

		int a = str.length();
		int j=0;
		int k=0;
		String strtemp = "";
		if ((a % 4)==0) {
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
		}
		return tabJadwal;
	}
	
	public float hitungFitnessFunction(String tabString1){
		/* Menghitung fitnessfunction dengan sebelumnya mengecek
		 * terjadinya bentrok matkul pada hari dan jam yang sama
		 * yaitu dengan mencatat matkul apa saja yang ada pada hari
		 * yang sama dengan menempatkannya pada matriks sbg berikut:
		 * baris menandakan hari
		 * kolom menandakan index matkul pada hari tsb
		 */

		String[] tabString = pisahstring(tabString1);

		//inisialisasi tabHariSama
		String[][] tabHariSama = new String [5][5];
		for (int x=0; x<5; x++){
			for (int y=0; y<5; y++){
				tabHariSama[x][y] = "";
			}
		}
		
		//mengisi tabHariSama
		String strtemp = "";
		int idxKolHariSama = 0;
		int idxBrsHariSama = 0;
		for (char idxHari='a'; idxHari<='e'; idxHari++){
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
		for (int idxa=0; idxa<5; idxa++){
			for(int idxb=0; idxb<5; idxb++){
				strtemp = tabHariSama[idxa][idxb];
				
				for (int idxc=idxb+1; idxc<5; idxc++){
					strtemp1 = tabHariSama[idxa][idxc];
					if (((strtemp.charAt(2)==strtemp1.charAt(2)) || (strtemp.charAt(3)==strtemp1.charAt(3)) || 
						(strtemp.charAt(2)>strtemp1.charAt(2) && strtemp.charAt(2)<strtemp1.charAt(3)) || 
						(strtemp.charAt(3)>strtemp1.charAt(2) && strtemp.charAt(3)<strtemp1.charAt(3))) && 
						isSameRoom(strtemp,strtemp1)){
						countBentrok++;
					}
				}
			}
		}
		
		if (countBentrok==0){
			return 999;
		} else {
			return (1/countBentrok);
		}
	}
	
	public boolean isSameRoom(String s1, String s2){
		/* mengembalikan nilai kebenaran apakah kedua kelas berada
		 * pada ruangan yang sama
		 */
		return (s1.charAt(0)==s2.charAt(0));
	}
	
	public String[] crossOver(String str1, String str2){
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
}
