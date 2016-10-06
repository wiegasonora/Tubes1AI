/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes1;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author rezaramadhan
 */
public class MatrixJadwal {
    public String[][] elmt;
    public int nHari;
    public int nJam;
    public MatrixJadwal() {
        nHari = 5;
        nJam = 12;
        elmt = new String[nJam][nHari];
        for (int i = 0; i < nJam; i++) {
            for (int j = 0; j<nHari; j++) {

                elmt[i][j] = "";
            }
        }
    }

    public static int HitungConflict(List<MatrixJadwal> A, List<Ruangan> b) {
        int i=0,x=0,y=0;   
        String[] cekpisah=null;
        int conflictbaru=0;
        while (i<b.size()){
           x=0;
           while (x<5){
            y=0;
                while (y<12) {
                    if (A.get(i).elmt[y][x].contains("-")){
                        cekpisah=A.get(i).elmt[y][x].split("-");
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
    
    public static void PindahJadwal(String namakelas, String ruangpindah,int pindahx,int pindahy,List<MatrixJadwal> A,List<Ruangan> B, List<Jadwal> C){
       int i=0,benar=0,x=0,y=0,simpanruang=0;
        while (i<C.size() && benar==0){
            if (C.get(i).NamaKegiatan.equals(namakelas)){
                simpanruang=i;
                benar=1;
            }
            i++;
        }
        int benarhari=0;
        if (C.get(simpanruang).Hari.contains(",")){
            String[] hariboleh = C.get(simpanruang).Hari.split(",");
            int iterasi=0;
            while (iterasi<hariboleh.length){
                if (hariboleh[iterasi].equals(String.valueOf(pindahx+1))){
                    benarhari++;
                }
                iterasi++;
            }
        } else {
            String hariboleh=C.get(simpanruang).Hari;
            if (hariboleh.equals(String.valueOf(pindahx+1))){
                    benarhari++;
            }
        }
        int benarjam=0;
        if (pindahy>=(Integer.valueOf(C.get(simpanruang).JamMulai.substring(0, 2))-7) && pindahy<=(Integer.valueOf(C.get(simpanruang).JamSelesai.substring(0, 2))-7-Integer.valueOf(C.get(simpanruang).Durasi))){
            benarjam++;
        }
        benar=0;
        int ruangtersediahari=0,ruangtersediajam=0;
        int iterasi=0;
        while (iterasi<B.size() && benar==0){
                        if (B.get(iterasi).Nama.equals(ruangpindah)){
                            benar=1;
                        }
                        iterasi++;
        }
        iterasi--;
        if (B.get(iterasi).Hari.contains(",")){
            String[] hariboleh = B.get(iterasi).Hari.split(",");
            int iter=0;
            while (iter<hariboleh.length){
                if (hariboleh[iter].equals(String.valueOf(pindahx+1))){
                    ruangtersediahari++;
                }
                iter++;
            }
        } else {
            String hariboleh=B.get(iterasi).Hari;
            if (hariboleh.equals(String.valueOf(pindahx+1))){
                    ruangtersediahari++;
            }
        }
        if (pindahy>=(Integer.valueOf(B.get(iterasi).JamMulai.substring(0, 2))-7) && pindahy<=(Integer.valueOf(B.get(iterasi).JamSelesai.substring(0, 2))-7-Integer.valueOf(C.get(simpanruang).Durasi))){
            ruangtersediajam++;
        }
        
        benar=0;
        if (ruangtersediahari>0 &&ruangtersediajam>0){
            if (benarhari>0 && benarjam>0){
            
        if (C.get(simpanruang).Ruangan.equals("-")){
            while (i<B.size() && benar<Integer.valueOf(C.get(simpanruang).Durasi)){
                while (x<5 && benar<Integer.valueOf(C.get(simpanruang).Durasi)){
                 y=0;
                     while (y<12 && benar<Integer.valueOf(C.get(simpanruang).Durasi)) {
                         if (A.get(i).elmt[y][x].contains(namakelas)){
                             if (A.get(i).elmt[y][x].contains(namakelas+" - ")){
                                        if (A.get(i).elmt[y][x].contains(" - "+namakelas+" - ")){
                                             A.get(i).elmt[y][x]=A.get(i).elmt[y][x].replace(" - "+namakelas+" - ", "");
                                        } else {
                                            A.get(i).elmt[y][x]=A.get(i).elmt[y][x].replace(namakelas+" - ", "");
                                        }
                                    } else if (A.get(i).elmt[y][x].contains(" - "+namakelas)) {
                                        A.get(i).elmt[y][x]=A.get(i).elmt[y][x].replace(" - "+namakelas, "");
                                    } else {
                                        A.get(i).elmt[y][x]=A.get(i).elmt[y][x].replace(namakelas, "");
                                    }
                             benar++;
                             System.out.println(A.get(i).elmt[y][x]);
                         }
                     y++;
                     }
                x++;
                }      
                i++;
            }
        
        } else {
            if (C.get(simpanruang).Ruangan.contains(",")){
                String[] ruangmulti=C.get(simpanruang).Ruangan.split(",");
                int v=0,tidakbisa=0;
                while (v<ruangmulti.length){
                    if (!ruangpindah.equals(ruangmulti[v])){
                        tidakbisa=1;
                    }
                    v++;
                }
                v=0;
                if (tidakbisa==0){
                    while (v<ruangmulti.length && benar<Integer.valueOf(C.get(simpanruang).Durasi)) {
                    int cariruang=0;
                    int keluar=0;
                    while (cariruang<B.size() && keluar==0){
                        if (B.get(cariruang).Nama.equals(ruangmulti[v])){
                            keluar=1;
                        }
                        cariruang++;
                    }
                    x=0; benar=0;
                    while (x<5 && benar<Integer.valueOf(C.get(simpanruang).Durasi)){
                        y=0;
                            while (y<12 && benar<Integer.valueOf(C.get(simpanruang).Durasi)) {
                                if (A.get(cariruang-1).elmt[y][x].contains(namakelas)){
                                    if (A.get(cariruang-1).elmt[y][x].contains(namakelas+" - ")){
                                        if (A.get(cariruang-1).elmt[y][x].contains(" - "+namakelas+" - ")){
                                             A.get(cariruang-1).elmt[y][x]=A.get(cariruang-1).elmt[y][x].replace(" - "+namakelas+" - ", "");
                                        } else {
                                            A.get(cariruang-1).elmt[y][x]=A.get(cariruang-1).elmt[y][x].replace(namakelas+" - ", "");
                                        }
                                    } else if (A.get(cariruang-1).elmt[y][x].contains(" - "+namakelas)) {
                                        A.get(cariruang-1).elmt[y][x]=A.get(cariruang-1).elmt[y][x].replace(" - "+namakelas, "");
                                    } else {
                                        A.get(cariruang-1).elmt[y][x]=A.get(cariruang-1).elmt[y][x].replace(namakelas, "");
                                    }
                                    benar++;
                                    //System.out.println(A.get(i).elmt[x][y]);
                                }
                            y++;
                            }
                       x++;
                       } 
                    v++;
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "TIDAK BISA DIPINDAH KE RUANGAN TERSEBUT, KELAS HANYA BISA DIPINDAH KE "+C.get(simpanruang).Ruangan);
                }
                
            } else {
                int cariruang=0;
                    int keluar=0;
                    if (C.get(simpanruang).Ruangan.equals(ruangpindah)) {
                        while (cariruang<B.size() && keluar==0){
                        if (B.get(cariruang).Nama.equals(C.get(simpanruang).Ruangan)){
                            keluar=1;
                        }
                        cariruang++;
                        }
                        x=0;
                    while (x<5 && benar<Integer.valueOf(C.get(simpanruang).Durasi)){
                        y=0;
                            while (y<12 && benar<Integer.valueOf(C.get(simpanruang).Durasi)) {
                                if (A.get(cariruang-1).elmt[y][x].contains(namakelas)){
                                    if (A.get(cariruang-1).elmt[y][x].contains(namakelas+" - ")){
                                        if (A.get(cariruang-1).elmt[y][x].contains(" - "+namakelas+" - ")){
                                             A.get(cariruang-1).elmt[y][x]=A.get(cariruang-1).elmt[y][x].replace(" - "+namakelas+" - ", "");
                                        } else {
                                            A.get(cariruang-1).elmt[y][x]=A.get(cariruang-1).elmt[y][x].replace(namakelas+" - ", "");
                                        }
                                    } else if (A.get(cariruang-1).elmt[y][x].contains(" - "+namakelas)) {
                                        A.get(cariruang-1).elmt[y][x]=A.get(cariruang-1).elmt[y][x].replace(" - "+namakelas, "");
                                    } else {
                                        A.get(cariruang-1).elmt[y][x]=A.get(cariruang-1).elmt[y][x].replace(namakelas, "");
                                    }
                                    benar++;
                                }
                            y++;
                            }
                       x++;
                    }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "TIDAK BISA DIPINDAH KE RUANGAN TERSEBUT, KELAS HANYA BISA DIPINDAH KE "+C.get(simpanruang).Ruangan);
                    }
                    
            }
            
        }
        i=0;
        x=pindahx;
        y=pindahy;
        int simpannoruang=0,ruangsimpan=0;
        while (simpannoruang<B.size()){
            if (B.get(simpannoruang).Nama.equals(ruangpindah)){
                ruangsimpan=simpannoruang;
                simpannoruang=B.size();
            }
            simpannoruang++;
        }
        while (i<Integer.valueOf(C.get(simpanruang).Durasi)){
            if (A.get(ruangsimpan).elmt[y][x].equals("")){
                A.get(ruangsimpan).elmt[y][x]=namakelas;
            } else {
                A.get(ruangsimpan).elmt[y][x]= A.get(ruangsimpan).elmt[y][x]+" - "+namakelas;
            }
            y++;
            i++;
        }
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "TIDAK BISA DIPINDAH KE HARI/JAM TERSEBUT, KELAS MEMILIKI CONSTRAINT HARI: "+C.get(simpanruang).Hari+" DAN JAM: "+C.get(simpanruang).JamMulai+" HINGGA "+C.get(simpanruang).JamSelesai);
                
        }
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "RUANGAN "+ruangpindah+" TIDAK BUKA PADA HARI/JAM TERSEBUT");
             
        }
        
        
        
    }
    
    public static float HitungPersen(List<MatrixJadwal> A, List<Ruangan> b){
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
                while (y<12) {
                    if (!A.get(i).elmt[y][x].equals("")){
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
