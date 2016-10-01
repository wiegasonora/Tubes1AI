/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Ratna Dira
 */
public class HillClimbing {
    public static List<MatrixJadwal> HillClimbing(List<Ruangan> ruang,List<Jadwal> jadwal){
        int tess=0;
        int jam=0,jamakhir=0;
        String[][] semua = new String[5][11];
        int hitung=0;
        String[] pisah;
        String[] cekpisah;
        while (tess<ruang.size()){
            if (ruang.get(tess).Hari.contains("1")){
                jam=Integer.valueOf(ruang.get(tess).JamMulai.substring(0, 2));
                jamakhir=Integer.valueOf(ruang.get(tess).JamSelesai.substring(0, 2));
                while (jam<=jamakhir){
                    if (semua[0][jam-7] == null){
                         semua[0][jam-7]=ruang.get(tess).Nama+"-,";
                    } else{
                        semua[0][jam-7]=semua[0][jam-7]+ruang.get(tess).Nama+"-,";
                    }
                        jam++;
                }
                jam=0;
                jamakhir=0;
            }
            if (ruang.get(tess).Hari.contains("2")){
                jam=Integer.valueOf(ruang.get(tess).JamMulai.substring(0, 2));
                jamakhir=Integer.valueOf(ruang.get(tess).JamSelesai.substring(0, 2));
                while (jam<=jamakhir){
                    if (semua[1][jam-7] == null){
                         semua[1][jam-7]=ruang.get(tess).Nama+"-,";
                    } else{
                    semua[1][jam-7]=semua[1][jam-7]+ruang.get(tess).Nama+"-,";
                    }
                    jam++;
                }
                jam=0;
                jamakhir=0;
            }
            if (ruang.get(tess).Hari.contains("3")){
                jam=Integer.valueOf(ruang.get(tess).JamMulai.substring(0, 2));
                jamakhir=Integer.valueOf(ruang.get(tess).JamSelesai.substring(0, 2));
                while (jam<=jamakhir){
                    if (semua[2][jam-7] == null){
                         semua[2][jam-7]=ruang.get(tess).Nama+"-,";
                    } else {
                    semua[2][jam-7]=semua[2][jam-7]+ruang.get(tess).Nama+"-,";
                    }
                    jam++;
                    
                }
                jam=0;
                jamakhir=0;
            }
            if (ruang.get(tess).Hari.contains("4")){
                jam=Integer.valueOf(ruang.get(tess).JamMulai.substring(0, 2));
                jamakhir=Integer.valueOf(ruang.get(tess).JamSelesai.substring(0, 2));
                while (jam<=jamakhir){
                    if (semua[3][jam-7] == null){
                      semua[3][jam-7]=ruang.get(tess).Nama+"-,";
                    } else {
                    semua[3][jam-7]=semua[3][jam-7]+ruang.get(tess).Nama+"-,";
                   }
                    jam++;
                }
                jam=0;
                jamakhir=0;
            }
            if (ruang.get(tess).Hari.contains("5")){
                jam=Integer.valueOf(ruang.get(tess).JamMulai.substring(0, 2));
                jamakhir=Integer.valueOf(ruang.get(tess).JamSelesai.substring(0, 2));
                while (jam<=jamakhir){
                    if (semua[4][jam-7] == null){
                        semua[4][jam-7]=ruang.get(tess).Nama+"-,";
                    } else {
                    semua[4][jam-7]=semua[4][jam-7]+ruang.get(tess).Nama+"-,";
                    }
                    jam++;
                }
                jam=0;
                jamakhir=0;
            }
            tess++;
        }
        tess=0;
        int cobas=0;
        tess=0;
        int benar=0,count=0;
        while(tess<jadwal.size()){
            String[] simpan=jadwal.get(tess).Hari.split(",");
            benar=0;
            count=0;
            int top=Integer.valueOf(jadwal.get(tess).JamMulai.substring(0, 2))-7;
            while (benar==0 && count<30){
                top=Integer.valueOf(jadwal.get(tess).JamMulai.substring(0, 2))-7;
                int idx = new Random().nextInt(simpan.length);
                String random = (simpan[idx]);
                while (top<=((Integer.valueOf(jadwal.get(tess).JamSelesai.substring(0, 2))-7))-Integer.valueOf(jadwal.get(tess).Durasi) && semua[Integer.valueOf(random)-1][top+Integer.valueOf(jadwal.get(tess).Durasi)]!=null && benar==0) {
                if (semua[Integer.valueOf(random)-1][(Integer.valueOf(jadwal.get(tess).JamMulai.substring(0, 2)))-7]!=null) {
                boolean masukan=semua[Integer.valueOf(random)-1][top].contains("-,");
                int cek=semua[Integer.valueOf(random)-1][top].lastIndexOf("-,");
                String masuk="";
                if (cek!=-1) {
                String ubah=semua[Integer.valueOf(random)-1][top].substring(0, cek);
                    String[] pas=ubah.split(",");
                    masuk=pas[pas.length-1];
                } 
               if (cek!=-1 && ("-".equals(jadwal.get(tess).Ruangan) && semua[Integer.valueOf(random)-1][top].contains(masuk)&&semua[Integer.valueOf(random)-1][top+Integer.valueOf(jadwal.get(tess).Durasi)].contains(masuk))){
                    int tambah=top;
                    while (tambah<(Integer.valueOf(jadwal.get(tess).Durasi)+top)) {
                        if (semua[Integer.valueOf(random)-1][tambah]!=null) {
                            if (semua[Integer.valueOf(random)-1][tambah].contains(masuk+"-,")){
                    semua[Integer.valueOf(random)-1][tambah]=semua[Integer.valueOf(random)-1][tambah].replaceFirst(masuk+"-,", (masuk+"-"+jadwal.get(tess).NamaKegiatan+"-=,"));
                            } else semua[Integer.valueOf(random)-1][tambah]=semua[Integer.valueOf(random)-1][tambah].replaceFirst(masuk+"-", (masuk+"-"+jadwal.get(tess).NamaKegiatan+"-="));
                    
                        } else {semua[Integer.valueOf(random)-1][tambah]=jadwal.get(tess).NamaKegiatan;
                        }
                        tambah++;
                    }
                    benar=1;
                    
                } else 
               {
                   if (jadwal.get(tess).Ruangan.contains(",")){
                       String[] consruang= jadwal.get(tess).Ruangan.split(",");
                       int inter=0;
                       while (inter<consruang.length){
                            if (semua[Integer.valueOf(random)-1][top].contains(consruang[inter]+"-,") &&semua[Integer.valueOf(random)-1][top+Integer.valueOf(jadwal.get(tess).Durasi)].contains(consruang[inter]+"-,") ) {
                     int tambah=top;
                    while (tambah<(Integer.valueOf(jadwal.get(tess).Durasi)+top)) {
                        if (semua[Integer.valueOf(random)-1][tambah]!=null) {
                            if (semua[Integer.valueOf(random)-1][tambah].contains("-,")){
                     semua[Integer.valueOf(random)-1][tambah]=semua[Integer.valueOf(random)-1][tambah].replaceFirst((consruang[inter]+"-,"), (consruang[inter]+"-"+jadwal.get(tess).NamaKegiatan+"-=,"));
                            } else  semua[Integer.valueOf(random)-1][tambah]=semua[Integer.valueOf(random)-1][tambah].replaceFirst((consruang[inter]+"-"), (consruang[inter]+"-"+jadwal.get(tess).NamaKegiatan+"-="));
                    
                        } else {semua[Integer.valueOf(random)-1][tambah]=jadwal.get(tess).NamaKegiatan;
                        
                        }
                        tambah++;
                    }
                    benar=1;
                
                }
                          inter++; 
                       }
                   } else {
                    if (semua[Integer.valueOf(random)-1][top].contains((jadwal.get(tess).Ruangan)+"-,") &&semua[Integer.valueOf(random)-1][top+Integer.valueOf(jadwal.get(tess).Durasi)].contains((jadwal.get(tess).Ruangan)+"-,") ) {
                     int tambah=top;
                    while (tambah<(Integer.valueOf(jadwal.get(tess).Durasi)+top)) {
                        if (semua[Integer.valueOf(random)-1][tambah]!=null) {
                            if (semua[Integer.valueOf(random)-1][tambah].contains("-,")){
                     semua[Integer.valueOf(random)-1][tambah]=semua[Integer.valueOf(random)-1][tambah].replaceFirst((jadwal.get(tess).Ruangan+"-,"), (jadwal.get(tess).Ruangan+"-"+jadwal.get(tess).NamaKegiatan+"-=,"));
                            } else  semua[Integer.valueOf(random)-1][tambah]=semua[Integer.valueOf(random)-1][tambah].replaceFirst((jadwal.get(tess).Ruangan+"-"), (jadwal.get(tess).Ruangan+"-"+jadwal.get(tess).NamaKegiatan+"-="));
                    
                        } else {semua[Integer.valueOf(random)-1][tambah]=jadwal.get(tess).NamaKegiatan;
                        
                        }
                        tambah++;
                    }
                    benar=1;
                
                }
                   }
               }
                }
                top++;
                }
                count++;
                
            }
            top=Integer.valueOf(jadwal.get(tess).JamMulai.substring(0, 2))-7;
            if (benar==0){
                while (benar==0 ){
                    top=Integer.valueOf(jadwal.get(tess).JamMulai.substring(0, 2))-7;
                int idx = new Random().nextInt(simpan.length);
                String random = (simpan[idx]);
                while (top<=((Integer.valueOf(jadwal.get(tess).JamSelesai.substring(0, 2))-7))-Integer.valueOf(jadwal.get(tess).Durasi)&& semua[Integer.valueOf(random)-1][top+Integer.valueOf(jadwal.get(tess).Durasi)]!=null&&benar==0) {
                
                    int cek=semua[Integer.valueOf(random)-1][top].indexOf("-");
                String masuk="";
                if (cek!=-1) {
                String ubah=semua[Integer.valueOf(random)-1][top].substring(0, cek);
                    masuk=ubah;
                } 
                   
                    if (cek!=1&&( "-".equals(jadwal.get(tess).Ruangan))&&semua[Integer.valueOf(random)-1][top+Integer.valueOf(jadwal.get(tess).Durasi)].contains(masuk)){
                    
                    int tambah=top;
                    while (tambah<(Integer.valueOf(jadwal.get(tess).Durasi)+top)) {
                        semua[Integer.valueOf(random)-1][tambah]=semua[Integer.valueOf(random)-1][tambah].replaceFirst(masuk+"-", (masuk+"-"+jadwal.get(tess).NamaKegiatan+"-="));
                    
                        tambah++;
                    }
                    
                    benar=1;
                    
                } else { if (jadwal.get(tess).Ruangan.contains(",")){
                       String[] consruang= jadwal.get(tess).Ruangan.split(",");
                       int inter=0;
                       while (inter<consruang.length){
                           if (semua[Integer.valueOf(random)-1][top].contains(consruang[inter]) && semua[Integer.valueOf(random)-1][top+Integer.valueOf(jadwal.get(tess).Durasi)].contains(consruang[inter])){
                    int tambah=top;
                    
                    while (tambah<(Integer.valueOf(jadwal.get(tess).Durasi)+(top))) {
                    semua[Integer.valueOf(random)-1][tambah]=semua[Integer.valueOf(random)-1][tambah].replaceFirst((consruang[inter]+"-"), (consruang[inter]+"-"+jadwal.get(tess).NamaKegiatan+"-="));
                    tambah++;
                    }
                    benar=1;
                }
                           inter++;
                       }
                } else {
                        
                        if (semua[Integer.valueOf(random)-1][top].contains(jadwal.get(tess).Ruangan) && semua[Integer.valueOf(random)-1][top+Integer.valueOf(jadwal.get(tess).Durasi)].contains(jadwal.get(tess).Ruangan)){
                    int tambah=top;
                    
                    while (tambah<(Integer.valueOf(jadwal.get(tess).Durasi)+(top))) {
                    semua[Integer.valueOf(random)-1][tambah]=semua[Integer.valueOf(random)-1][tambah].replaceFirst((jadwal.get(tess).Ruangan+"-"), (jadwal.get(tess).Ruangan+"-"+jadwal.get(tess).NamaKegiatan+"-="));
                    tambah++;
                    }
                    benar=1;
                }
                }
                }
            top++;
                }
                }
            }
            tess++;
        }
        tess=0;
        cobas=0;
        List<String> yangcon=new ArrayList<String>();
        List<Integer> hitungyangcon=new ArrayList<Integer>();
        List<String> lokasiyangcon=new ArrayList<String>();
        int temp=0,temp1=0;
        int conflict=0;
        do {
            conflict=0;
             yangcon=new ArrayList<String>();
            hitungyangcon=new ArrayList<Integer>();
            while (tess<5){
            cobas=0;
            while (cobas<11) {
                if (semua[tess][cobas]!=null){
                if (semua[tess][cobas].contains(",")){
                pisah=semua[tess][cobas].split(",");
                while (hitung<pisah.length){
                    if (pisah[hitung].contains("=")){
                    cekpisah=pisah[hitung].split("=");
                   if (cekpisah.length!=1){
                       int hitungcon=cekpisah.length;
                       int k=1;
                       int total=0;
                       String[] gg=cekpisah[0].split("-");
                       if (!yangcon.contains(gg[1])){
                        yangcon.add(gg[1]);
                        lokasiyangcon.add(tess+","+cobas+"=");
                        hitungyangcon.add(cekpisah.length);
                       } else {
                           int fat=0;
                         while (fat<yangcon.size()){
                             if (yangcon.get(fat).equals(gg[1])){
                                 hitungyangcon.set(fat, (hitungyangcon.get(fat)+cekpisah.length));
                                 lokasiyangcon.set(fat, (lokasiyangcon.get(fat)+tess+","+cobas+"="));
                                 fat=yangcon.size();
                             }
                             fat++;
                         }  
                       }
                       while(k<hitungcon){
                           if (!yangcon.contains(cekpisah[k].substring(0, cekpisah[k].length()-1))){
                               yangcon.add(cekpisah[k].substring(0, cekpisah[k].length()-1));
                               hitungyangcon.add(cekpisah.length);
                               lokasiyangcon.add(tess+","+cobas+"=");
                           } else {
                               int fat=0;
                                while (fat<yangcon.size()){
                                    if (yangcon.get(fat).equals(cekpisah[k].substring(0, cekpisah[k].length()-1))){
                                        hitungyangcon.set(fat, (hitungyangcon.get(fat)+cekpisah.length));
                                        lokasiyangcon.set(fat, (lokasiyangcon.get(fat)+tess+","+cobas+"="));
                                        fat=yangcon.size();
                                    }
                                 fat++;
                                 } 
                               
                           }
                                                    
                           total=total+k;
                           k++;
                       }
                    conflict=conflict+total;
                    
                   }
                    cekpisah=null;
                    } 
                    hitung++;
                }
                hitung=0;
                } else conflict++;
                 }
                cobas++;
            }
            pisah=null;
           
            tess++;
        }
       tess=0;
        temp1=conflict;
        int keluar=0;
       temp=5;
       int iterasi=0;
       int simpanget=0;
       String simpanyangdiganti="",simpanyangdiganti1="=",kelasyangkeluar="",kelasyangkeluar1="";
       while(keluar<yangcon.size()){
                iterasi=0;
                while (iterasi<jadwal.size()){
                    if (jadwal.get(iterasi).NamaKegiatan.equals(yangcon.get(keluar))){
                        simpanget=iterasi;
                        iterasi=1000000;
                    }
                    iterasi++;
                }
            benar=0;
            int top,max=10000,max1=10000;
            String[] simpan=jadwal.get(simpanget).Hari.split(",");
            int idx = 0;
            String[] kelassama=lokasiyangcon.get(keluar).split("=");
            String testing=lokasiyangcon.get(keluar);
            ArrayList<Integer> row=new  ArrayList<Integer>(); 
            ArrayList<Integer> col=new  ArrayList<Integer>(); 
            int terus=0;
            
            int filess=testing.length()-testing.replaceAll("=", "").length();
            while (terus<filess){
                String[] rocol=kelassama[terus].split(",");
                
                row.add(Integer.valueOf(rocol[0]));
                col.add(Integer.valueOf(rocol[1]));
                terus++;
            }
            while(idx<simpan.length){
                    top=Integer.valueOf(jadwal.get(simpanget).JamMulai.substring(0, 2))-7;
                    String random = (simpan[idx]);
                            while (top<=((Integer.valueOf(jadwal.get(simpanget).JamSelesai.substring(0, 2))-7))-Integer.valueOf(jadwal.get(simpanget).Durasi)&& semua[Integer.valueOf(random)-1][top]!=null && semua[Integer.valueOf(random)-1][top+Integer.valueOf(jadwal.get(simpanget).Durasi)]!=null) {
                            if ("-".equals(jadwal.get(simpanget).Ruangan)){
                                String[] sip=semua[Integer.valueOf(random)-1][top].split(",");
                                int pindah=0;
                                ArrayList<Integer> cari=new ArrayList<Integer>();
                                ArrayList<String> pindahstring=new ArrayList<String>();
                                ArrayList<String> kelaskeluar=new ArrayList<String>();
                                while(pindah<sip.length){
                                    if (row.contains(Integer.valueOf(random)-1) && col.contains(top)){
                                       
                                        cari.add((sip[pindah].length() - sip[pindah].replace("=", "").length()));
                                        
                                    }   else 
                                         if (sip[pindah].contains("=")){
                                              cari.add((sip[pindah].length() - sip[pindah].replace("=", "").length())+1);
                                         } else
                                             cari.add(1);
                                       
                                        pindahstring.add((Integer.valueOf(random)-1)+","+top+"=");
                                        String[] siap=sip[pindah].split("-");
                                        kelaskeluar.add(siap[0]);
                                        pindah++;
                                    }
                                pindah=top+1;
                                while (pindah<(top+Integer.valueOf(jadwal.get(simpanget).Durasi))) {
                                    String[] sipp=semua[Integer.valueOf(random)-1][pindah].split(",");
                                    
                                    int pindahh=0;
                                    while(pindahh<sipp.length){
                                        if (pindahh<cari.size()){
                                         if (row.contains(Integer.valueOf(random)-1) && col.contains(pindah)){
                                            cari.set(pindahh, (cari.get(pindahh)+(sipp[pindahh].length() - sipp[pindahh].replace("=", "").length())));
                                            
                                         } else
                                             if (sipp[pindahh].contains("=")){
                                               cari.set(pindahh, (1+cari.get(pindahh)+(sipp[pindahh].length() - sipp[pindahh].replace("=", "").length())));
                                         
                                             } else
                                        cari.set(pindahh, (1+cari.get(pindahh)));
                                         pindahstring.set(pindahh, pindahstring.get(pindahh)+(Integer.valueOf(random)-1)+","+pindah+"=");
                                      String[] siap=sipp[pindahh].split("-");
                                        kelaskeluar.add(siap[0]);
                                        }
                                       
                                        pindahh++;
                                    }
                                    pindah++;
                                }
                                int teruskan=1;
                                int temps=0;
                                max=cari.get(0);
                                while (teruskan<cari.size()){
                                   temps=cari.get(teruskan);
                                   max=Math.min(max,temps);
                                   teruskan++;
                                }
                                int ditentukan=cari.indexOf(max);
                                simpanyangdiganti=pindahstring.get(ditentukan);
                                kelasyangkeluar=kelaskeluar.get(ditentukan);
                            } else 
                                if (semua[Integer.valueOf(random)-1][top].contains(jadwal.get(simpanget).Ruangan)&&semua[Integer.valueOf(random)-1][top+Integer.valueOf(jadwal.get(simpanget).Durasi)].contains((jadwal.get(simpanget).Ruangan))) {
                                ArrayList<String> pindahstring=new ArrayList<String>();
                                String[] sip=semua[Integer.valueOf(random)-1][top].split(",");
                                int pindah=0;
                                int pas=0;
                                while(pindah<sip.length){
                                    if (sip[pindah].contains(jadwal.get(simpanget).Ruangan)){
                                        pas=pindah;
                                    }    
                                    pindah++;
                                }
                                pindah=0;
                                ArrayList<Integer> cari=new ArrayList<Integer>();
                                if (row.contains(Integer.valueOf(random)-1) && col.contains(top)){
                                cari.add((sip[pas].length() - sip[pas].replace("=", "").length()));
                                
                                } else
                                cari.add(1+(sip[pas].length() - sip[pas].replace("=", "").length()));
                                pindahstring.add((Integer.valueOf(random)-1)+","+top+"=");
                                pindah=top+1;
                                while (pindah<(top+Integer.valueOf(jadwal.get(simpanget).Durasi))) {
                                    int pars=0;
                                    
                                    String[] sipp=semua[Integer.valueOf(random)-1][pindah].split(",");
                                    while(pars<sip.length){
                                    if (sipp[pars].contains(jadwal.get(simpanget).Ruangan)){
                                        pas=pars;
                                    }    
                                    pars++;
                                     }
                                    if (row.contains(Integer.valueOf(random)-1) && col.contains(pindah)){
                                    cari.set(0, ((cari.get(0)+(sipp[pas].length() - sipp[pas].replace("=", "").length()))));
                                    
                                    } else
                                    cari.set(0, (1+(cari.get(0)+(sipp[pas].length() - sipp[pas].replace("=", "").length()))));
                                    pindahstring.set(0, pindahstring.get(0)+(Integer.valueOf(random)-1)+","+pindah+"=");
                                    pindah++;
                                }
                                int teruskan=1;
                                int temps=0;
                                max=cari.get(0);
                                while (teruskan<cari.size()){
                                   temps=cari.get(teruskan);
                                   max=Math.min(max,temps);
                                   teruskan++;
                                }
                                int ditentukan=cari.indexOf(max);
                                simpanyangdiganti=pindahstring.get(ditentukan);

                            }
                            max1=Math.min(max,max1);
                            if (max1==max){
                                simpanyangdiganti1=simpanyangdiganti;
                                kelasyangkeluar1=kelasyangkeluar;
                            }
                            top++;
                            }
                    idx++;
                }
            if (hitungyangcon.get(keluar)>max1){
                terus=0;
                while (terus<row.size()){
                    semua[(row.get(terus))][(col.get(terus))]= semua[(row.get(terus))][(col.get(terus))].replaceAll((yangcon.get(keluar)+"-="), "");
                    terus++;
                }
                String[] pindahkelas=simpanyangdiganti1.split("=");
                ArrayList<Integer> rows=new  ArrayList<Integer>(); 
                ArrayList<Integer> cols=new  ArrayList<Integer>(); 
                int teruss=0;
                ArrayList<String> pindahstrings=new ArrayList<String>();
                while (teruss<pindahkelas.length){
                    String[] rocol=pindahkelas[teruss].split(",");
                    rows.add(Integer.valueOf(rocol[0]));
                    cols.add(Integer.valueOf(rocol[1]));
                    teruss++;
                }
                teruss=0;
                while (teruss<rows.size()){
                    if ("-".equals(jadwal.get(simpanget).Ruangan)){
                        semua[rows.get(teruss)][cols.get(teruss)]=semua[rows.get(teruss)][cols.get(teruss)].replaceFirst(kelasyangkeluar1+"-", kelasyangkeluar1+"-"+jadwal.get(simpanget).NamaKegiatan+"-=");
                        
                    } else {
                        semua[rows.get(teruss)][cols.get(teruss)]=semua[rows.get(teruss)][cols.get(teruss)].replaceFirst(jadwal.get(simpanget).Ruangan+"-", jadwal.get(simpanget).Ruangan+"-"+jadwal.get(simpanget).NamaKegiatan+"-=");
                        
                    }
                    teruss++;
                }
                
                //TEMPEL TEMPAT LAIN YANG RUANGANNYA SAMA
 
            }

               
              keluar++;  
            }
       conflict=0;
       while (tess<5){
            cobas=0;
            while (cobas<11) {
                if (semua[tess][cobas]!=null){
                if (semua[tess][cobas].contains(",")){
                pisah=semua[tess][cobas].split(",");
                while (hitung<pisah.length){
                    if (pisah[hitung].contains("=")){
                    cekpisah=pisah[hitung].split("=");
                   if (cekpisah.length!=1){
                       int hitungcon=cekpisah.length;
                       int k=1;
                       int total=0;
                       String[] gg=cekpisah[0].split("-");
                       if (!yangcon.contains(gg[1])){
                        yangcon.add(gg[1]);
                        lokasiyangcon.add(tess+","+cobas+"=");
                        hitungyangcon.add(cekpisah.length);
                       } else {
                           int fat=0;
                         while (fat<yangcon.size()){
                             if (yangcon.get(fat).equals(gg[1])){
                                 hitungyangcon.set(fat, (hitungyangcon.get(fat)+cekpisah.length));
                                 lokasiyangcon.set(fat, (lokasiyangcon.get(fat)+tess+","+cobas+"="));
                                 fat=yangcon.size();
                             }
                             fat++;
                         }  
                       }
                       while(k<hitungcon){
                           if (!yangcon.contains(cekpisah[k].substring(0, cekpisah[k].length()-1))){
                               yangcon.add(cekpisah[k].substring(0, cekpisah[k].length()-1));
                               hitungyangcon.add(cekpisah.length);
                               lokasiyangcon.add(tess+","+cobas+"=");
                           } else {
                               int fat=0;
                                while (fat<yangcon.size()){
                                    if (yangcon.get(fat).equals(cekpisah[k].substring(0, cekpisah[k].length()-1))){
                                        hitungyangcon.set(fat, (hitungyangcon.get(fat)+cekpisah.length));
                                        lokasiyangcon.set(fat, (lokasiyangcon.get(fat)+tess+","+cobas+"="));
                                        fat=yangcon.size();
                                    }
                                 fat++;
                                 } 
                               
                           }
                                                    
                           total=total+k;
                           k++;
                       }
                    conflict=conflict+total;
                    
                   }
                    cekpisah=null;
                    } 
                    hitung++;
                }
                hitung=0;
                } else conflict++;
                 }
                cobas++;
            }
            pisah=null;
           
            tess++;
        }
       tess=0;
        temp=conflict;
        } while(temp<temp1);

       conflict=0;
       int jumlahjampakai=0;
       List<MatrixJadwal> Reza = new ArrayList<MatrixJadwal>();
       int wiega=0;
       tess=0;
       while(wiega<ruang.size()){
           MatrixJadwal Tikong=new MatrixJadwal();
           tess=0;
       while (tess<5){
            cobas=0;
            while (cobas<11) {
                 if (semua[tess][cobas]!=null){
                if (semua[tess][cobas].contains(ruang.get(wiega).Nama)){
                    if (semua[tess][cobas].contains(",")){
                        pisah=semua[tess][cobas].split(",");
                        while (hitung<pisah.length){
                            if (pisah[hitung].contains(ruang.get(wiega).Nama)){
                                if (pisah[hitung].contains("-=")){
                                    cekpisah=pisah[hitung].split("-=");
                                    if (cekpisah.length!=1){
                                        int hitungcon=cekpisah.length;
                                        int k=1;
                                        int total=0;
                                        int v=0;
                                        while (v<hitungcon){
                                           if (Tikong.elmt[cobas][tess].equals("")){
                                               if (cekpisah[v].contains(ruang.get(wiega).Nama+"-")){
                                                    Tikong.elmt[cobas][tess]=cekpisah[v].substring(cekpisah[v].indexOf("-")+1, cekpisah[v].length());
                                                } else {
                                                   Tikong.elmt[cobas][tess]=cekpisah[v].substring(0, cekpisah[v].length());
                                                 }
                                           } else {
                                               if (cekpisah[v].contains(ruang.get(wiega).Nama+"-")){
                                                    Tikong.elmt[cobas][tess]=(Tikong.elmt[cobas][tess]+" - "+cekpisah[v].substring(cekpisah[v].indexOf("-")+1, cekpisah[v].length()));
                                           
                                                } else {
                                                  Tikong.elmt[cobas][tess]=(Tikong.elmt[cobas][tess]+" - "+cekpisah[v].substring(0, cekpisah[v].length()));
                                                }
                                            }
                                            v++; 
                                        }
                                    } else if (cekpisah.length==1){
                                    if (Tikong.elmt[cobas][tess].equals("")){
                                                if (cekpisah[0].contains(ruang.get(wiega).Nama+"-")){
                                                    Tikong.elmt[cobas][tess]=cekpisah[0].substring(cekpisah[0].indexOf("-")+1, cekpisah[0].length());
                                                } else {
                                                   Tikong.elmt[cobas][tess]=cekpisah[0].substring(0, cekpisah[0].length()-1);
                                                 }
                                        } else {
                                            if (cekpisah[0].contains(ruang.get(wiega).Nama+"-")){
                                                    Tikong.elmt[cobas][tess]=(Tikong.elmt[cobas][tess]+" - "+cekpisah[0].substring(cekpisah[0].indexOf("-")+1, cekpisah[0].length()));
                                           
                                                } else {
                                                  Tikong.elmt[cobas][tess]=(Tikong.elmt[cobas][tess]+" - "+cekpisah[0].substring(0, cekpisah[0].length()));
                                                }
                                    }
                                    }
                                    
                                }
                            }
                   
                    cekpisah=null;
                    
                    hitung++;
                }
                hitung=0;
                } else {
                        if (semua[tess][cobas].contains("-=")){
                                    cekpisah=semua[tess][cobas].split("-=");
                                    if (cekpisah.length!=1){
                                        int hitungcon=cekpisah.length;
                                        int k=1;
                                        int total=0;
                                        int v=0;
                                        while (v<hitungcon){
                                           if (Tikong.elmt[cobas][tess].equals("")){
                                               if (cekpisah[v].contains(ruang.get(wiega).Nama+"-")){
                                                    Tikong.elmt[cobas][tess]=cekpisah[v].substring(cekpisah[v].indexOf("-")+1, cekpisah[v].length());
                                                } else {
                                                   Tikong.elmt[cobas][tess]=cekpisah[v];
                                                 }
                                            } else {
                                                if (cekpisah[v].contains(ruang.get(wiega).Nama+"-")){
                                                    Tikong.elmt[cobas][tess]=(Tikong.elmt[cobas][tess]+" - "+cekpisah[v].substring(cekpisah[v].indexOf("-")+1, cekpisah[v].length()));
                                           
                                                } else {
                                                  Tikong.elmt[cobas][tess]=(Tikong.elmt[cobas][tess]+" - "+cekpisah[v]);
                                                }
                                           }
                                            v++; 
                                        }
                                    } else if (cekpisah.length==1){
                                    if (Tikong.elmt[cobas][tess].equals("")){
                                               if (cekpisah[0].contains(ruang.get(wiega).Nama+"-")){
                                                    Tikong.elmt[cobas][tess]=cekpisah[0].substring(cekpisah[0].indexOf("-")+1, cekpisah[0].length());
                                                } else {
                                                   Tikong.elmt[cobas][tess]=cekpisah[0];
                                                 }
                                            } else {
                                                if (cekpisah[0].contains(ruang.get(wiega).Nama+"-")){
                                                    Tikong.elmt[cobas][tess]=(Tikong.elmt[cobas][tess]+" - "+cekpisah[0].substring(cekpisah[0].indexOf("-")+1, cekpisah[0].length()));
                                           
                                                } else {
                                                  Tikong.elmt[cobas][tess]=(Tikong.elmt[cobas][tess]+" - "+cekpisah[0]);
                                                }}
                                    }
                                    
                                }
                        cekpisah=null;
                    }
                    }
                 }
                cobas++;
            }
            pisah=null;
           
            tess++;
        }
       Reza.add(Tikong);
        wiega++;   
       }
       return Reza;
    }
    
}
