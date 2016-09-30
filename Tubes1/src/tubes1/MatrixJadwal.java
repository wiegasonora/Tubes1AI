/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rezaramadhan
 */
public class MatrixJadwal {
    String[][] elmt;
    int nHari;
    int nJam;
    public MatrixJadwal() {
        nHari = 5;
        nJam = 11;
        elmt = new String[nJam][nHari];
        for (int i = 0; i < nJam; i++) {
            for (int j = 0; j<nHari; j++) {
                elmt[i][j] = "";
            }
        }
    }
    public static int HitungConflict(ArrayList<MatrixJadwal> A, List<Ruangan> b) {
        int i=0,x=0,y=0;   
        String[] cekpisah=null;
        int conflictbaru=0;
        while (i<b.size()){
           x=0;
           while (x<5){
            y=0;
                while (y<11) {
                    if (A.get(i).elmt[x][y].contains("-")){
                        cekpisah=A.get(i).elmt[x][y].split("-");
                        int hitungcon=cekpisah.length;
                        int k=1;
                        int total=0;
                        while(k<hitungcon){
                           total=total+k;
                           k++;
                        }
                    conflictbaru=conflictbaru+total;
                    } 
                y++;
                cekpisah=null;
                }
           x++;
           }      
        i++;
        }
        return conflictbaru;
    }
    public static float HitungPersen(ArrayList<MatrixJadwal> A, List<Ruangan> b){
        int i=0;
        int jam=0,jamakhir=0;
        int jamtotal=0,jam1=0,jumlahjpakai=0;
        while (i<b.size()){
            jam=Integer.valueOf(b.get(i).JamMulai.substring(0, 2));
            jamakhir=Integer.valueOf(b.get(i).JamSelesai.substring(0, 2));
            if (b.get(i).Hari.contains("1")){
                jam1=jam;
                while (jam1<=jamakhir){
                    if (jam1!=jamakhir){
                        jamtotal++;          
                        }
                    jam1++;
                }
                jam1=0;
            }
            if (b.get(i).Hari.contains("2")){
                jam1=jam;
                while (jam1<=jamakhir){
                    if (jam1!=jamakhir){
                        jamtotal++;          
                        }
                    jam1++;
                }
                jam1=0;
            }
            if (b.get(i).Hari.contains("3")){
                jam1=jam;
                while (jam1<=jamakhir){
                    if (jam1!=jamakhir){
                        jamtotal++;          
                        }
                    jam1++;
                }
                jam1=0;
            }
            if (b.get(i).Hari.contains("4")){
                jam1=jam;
                while (jam1<=jamakhir){
                    if (jam1!=jamakhir){
                        jamtotal++;          
                        }
                    jam1++;
                }
                jam1=0;
            }
            if (b.get(i).Hari.contains("5")){
                jam1=jam;
                while (jam1<=jamakhir){
                    if (jam1!=jamakhir){
                        jamtotal++;          
                        }
                    jam1++;
                }
                jam1=0;
            }
            i++;
        }
        int x=0,y=0;   
        i=0;
        while (i<b.size()){
           x=0;
           while (x<5){
            y=0;
                while (y<11) {
                    if (!A.get(i).elmt[x][y].equals("")){
                        jumlahjpakai++;
                    }
                y++;
                }
           x++;
           }      
        i++;
        }
        
        return (((float)jumlahjpakai/(float)jamtotal)*(float)100);
    }
}
