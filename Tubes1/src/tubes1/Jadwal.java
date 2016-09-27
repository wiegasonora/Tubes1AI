/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes1;

import java.util.*;
import java.io.*;

/**
 *
 * @author rezaramadhan
 */
public class Jadwal {
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
    
    // Gets name of course
    public String getNamaKegiatan() {
        return this.NamaKegiatan;
    }
    
    // Gets course's room
    public String getRuangan() {
        return this.Ruangan;
    }

    // Gets course's starting time
    public int getJamMulai() {
        return Integer.parseInt(this.JamMulai);
    }

    // Gets course's ending time
    public int getJamSelesai() {
        return Integer.parseInt(this.JamSelesai);
    }

    // Gets course's duration
    public int getDurasi() {
        return Integer.parseInt(this.Durasi);
    }

    // Gets course's day
    public boolean[] getHari() {
    	//inisialisasi isi array of boolean
		boolean[] temp = new boolean[5];
    	for (int i = 0; i < 5; i++) {
    		temp[i] = false;
    	}
    	//menghilangkan separator berupa koma (,) dari string
    	String str = Hari.replaceAll(",", "");
    	//konversi string ke nilai boolean
		for (int i = 0; i < str.length(); i++) {
			temp[Character.getNumericValue(str.charAt(i)) - 1] = true;
		}
        return temp;
    }

    public boolean getHariAtIdx(int idx) {
    	boolean[] temp = getHari();
        return temp[idx];
    }
    
    // Sets Nama to nm
    public void setNamaKegiatan(String nm) {
        this.NamaKegiatan = nm;
    }
    // Sets Ruangan to rg
    public void setRuangan(String rg) {
        this.Ruangan = rg;
    }
    // Sets JamMulai to aw
    public void setJamMulai(String aw) {
        this.JamMulai = aw;
    }
    // Sets JamMulai to aw
    public void setJamMulai(int aw) {
        this.JamMulai = Integer.toString(aw);
    }
    // Sets JamSelesai to akh
    public void setJamSelesai(String akh) {
        this.JamSelesai = akh;
    }
        // Sets JamSelesai to akh
    public void setJamSelesai(int akh) {
        this.JamSelesai = Integer.toString(akh);
    }
    // Sets Durasi to dur
    public void setDurasi(String dur) {
        this.Durasi = dur;
    }
    // Sets Durasi to dur
    public void setDurasi(int dur) {
        this.Durasi = Integer.toString(dur);
    }
    // Sets Hari to hr
    public void setHari(String hr) {
        this.Hari = hr;
    }
    public void setHariAtIdx(int idx, boolean bool) {
    	if (bool == false) {
	        Hari.replaceAll("idx", "");
	    }
    }

    public Jadwal randomJadwal() {
        Random rnd = new Random();
        Jadwal temp = new Jadwal();
        do {
            int x;
            // Random Hari correct
            do {
                x = rnd.nextInt(5);
            } while (getHariAtIdx(x) == false);
            
            for (int j = 0; j < 5; j++) {
                if (j != x) {
                    temp.setHariAtIdx(j, false);
                }
            }
            // Random jam kuliah correct
            x = rnd.nextInt(getJamSelesai() - getDurasi() - getJamMulai() + 1) + getJamMulai();
            temp.setJamMulai(x);
            temp.setJamSelesai(x + getDurasi());
            
            // Random Ruangan
            if (getRuangan() == "") {
                x = rnd.nextInt(RoomManager.numberOfRoom());
                temp.setRuangan(RoomManager.getRoom(x).getNama());
            } else {
                temp.setRuangan(this.getRuangan());
            }
        } while (isNotInDomain(temp, RoomManager.getRoomByRuang(temp.getRuangan())));
        return temp;
    }

    public boolean isNotInDomain(Jadwal course, Ruangan room) {
        int idxHari = 999;
        for (int i = 0; i < 5; i++) {
            if (course.getHariAtIdx(i) == true) {
                idxHari = i;
            }
        }
        boolean isHariIn = false;
        if (room.getHariAtIdx(idxHari) && course.getHariAtIdx(idxHari)) {
            isHariIn = true;
        }
        boolean isJamIn = false;
        if ((course.getJamMulai() >= room.getJamMulai())&&(course.getJamMulai() <= (room.getJamSelesai()-course.getDurasi()))) {
            isJamIn = true;
        }

        return (isHariIn && isJamIn);        
    }

    @Override
    public String toString() {
        return getNamaKegiatan() + "; " + getRuangan() + "; " + getJamMulai() + "; " + getJamSelesai() + "; " + getDurasi()
        + "; " + Arrays.toString(getHari());
    }
}
    

