/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes1;

/**
 *
 * @author rezaramadhan
 */
public class Ruangan {
    public String Nama;
    public String JamMulai;
    public String JamSelesai;
    public String Hari;

    public Ruangan() {
		Nama = "";
		JamMulai = "0";
		JamSelesai = "0";
		Hari = "";
	}

	public Ruangan(String Nama, String JamMulai, String JamSelesai, String Hari) {
		this.Nama = Nama;
		this.JamMulai = JamMulai;
		this.JamSelesai = JamSelesai;
		this.Hari = Hari;
	}

	public String getNama() {
		return Nama;
	}

	public int getJamMulai() {
		return Integer.parseInt(JamMulai);
	}

	public int getJamSelesai() {
		return Integer.parseInt(JamSelesai);
	}

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

	public void setNama(String Nama) {
		this.Nama = Nama;
	}

	public void setJamMulai(int JamMulai) {
		this.JamMulai = Integer.toString(JamMulai);
	}

	public void setJamMulai(String JamMulai) {
		this.JamMulai = JamMulai;
	}

	public void setJamSelesai(int JamSelesai) {
		this.JamSelesai = Integer.toString(JamSelesai);
	}

	public void setJamSelesai(String JamSelesai) {
		this.JamSelesai = JamSelesai;
	}

	public void setHari(String Hari) {
		this.Hari = Hari;
	}
}
