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
class Jadwal {
    String namaKegiatan;
    String ruangan;
    String jamMulai;
    String jamSelesai;
    String durasi;
    String hari;

    // Constructs an intial course
    public Jadwal() {
        this.namaKegiatan = "IFXXXX";
        this.ruangan = "";
        this.jamMulai = "0";
        this.jamSelesai = "0";
        this.durasi = "0";
        this.hari = "";
    }
    
    // Constructs a course with certain of parameters
    public Jadwal(String nm, String rg, String mulai, String selesai, String dur, String hr) {
        this.namaKegiatan = nm;
        this.ruangan = rg;
        this.jamMulai = mulai;
        this.jamSelesai = selesai;
        this.durasi = dur;
        this.hari = hr;
    }

    // Constructs a course from another course
    public Jadwal(Jadwal jadwal) {
        this.namaKegiatan = jadwal.namaKegiatan;
        this.ruangan = jadwal.ruangan;
        this.jamMulai = jadwal.jamMulai;
        this.jamSelesai = jadwal.jamSelesai;
        this.durasi = jadwal.durasi;
        this.hari = jadwal.hari;
    }
    
    // Gets name of course
    public String getNamaKegiatan() {
        return this.namaKegiatan;
    }
    
    // Gets course's room
    public String getRuangan() {
        return this.ruangan;
    }

    // Gets course's starting time
    public int getJamMulai() {
        return Integer.parseInt(this.jamMulai);
    }

    // Gets course's ending time
    public int getJamSelesai() {
        return Integer.parseInt(this.jamSelesai);
    }

    // Gets course's duration
    public int getDurasi() {
        return Integer.parseInt(this.durasi);
    }

    // Gets course's day
    public boolean[] getHari() {
    	//inisialisasi isi array of boolean
		boolean[] temp = new boolean[5];
    	for (int i = 0; i < 5; i++) {
    		temp[i] = false;
    	}
    	//menghilangkan separator berupa koma (,) dari string
    	String str = hari.replaceAll(",", "");
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
        this.namaKegiatan = nm;
    }
    // Sets ruangan to rg
    public void setRuangan(String rg) {
        this.ruangan = rg;
    }
    // Sets jamMulai to aw
    public void setJamMulai(String aw) {
        this.jamMulai = aw;
    }
    // Sets jamMulai to aw
    public void setJamMulai(int aw) {
        this.jamMulai = Integer.toString(aw);
    }
    // Sets jamSelesai to akh
    public void setJamSelesai(String akh) {
        this.jamSelesai = akh;
    }
        // Sets jamSelesai to akh
    public void setJamSelesai(int akh) {
        this.jamSelesai = Double.toString(akh);
    }
    // Sets durasi to dur
    public void setDurasi(String dur) {
        this.durasi = dur;
    }
    // Sets durasi to dur
    public void setDurasi(int dur) {
        this.durasi = Integer.toString(dur);
    }
    // Sets hari to hr
    public void setHari(String hr) {
        this.hari = hr;
    }
    public void setHariAtIdx(int idx, boolean bool) {
    	if (bool == false) {
	        hari.replaceAll("idx", "");
	    }
    }

    public Jadwal randomJadwal() {
        Random rnd = new Random();
        Jadwal temp = new Jadwal();
        do {
            int x;
            // Random hari correct
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
            
            // Random ruangan
            if (getRuangan() == "") {
                x = rnd.nextInt(RoomManager.numberOfRoom());
                temp.setRuangan(RoomManager.getRoom(x).getRuangan());
            } else {
                temp.setRuangan(this.getRuangan());
            }
        } while (isNotInDomain(temp, RoomManager.getRoomByRuang(temp.getRuangan())));
        return temp;
    }

    public boolean isNotInDomain(Jadwal course, Room room) {
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
    

