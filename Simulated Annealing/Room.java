import java.util.*;

public class Room {
	private String ruang;
	private double awal;
	private double akhir;
	private int durasi;
	private boolean[] hari = new boolean[5];

	public Room() {
		ruang = "";
		awal = 0.0;
		akhir = 0.0;
		durasi = 0;
		for (int i = 0; i < 5; i++) {
			hari[i] = false;
		}
	}

	public Room(String ruang, double awal, double akhir, int durasi, boolean[] hari) {
		this.ruang = ruang;
		this.awal = awal;
		this.akhir = akhir;
		this.durasi = durasi;
		for (int i = 0; i < 5; i++) {
			this.hari[i] = hari[i];
		}
	}

	public String getRuang() {
		return ruang;
	}

	public double getAwal() {
		return awal;
	}

	public double getAkhir() {
		return akhir;
	}

	public int getDurasi() {
		return durasi;
	}

	public boolean[] getHari() {
		return hari;
	}

	public void setRuang(String ruang) {
		this.ruang = ruang;
	}

	public void setAwal(double awal) {
		this.awal = awal;
	}

	public void setAkhir(double akhir) {
		this.akhir = akhir;
	}

	public void setDurasi(int durasi) {
		this.durasi = durasi;
	}

	public void setHari(boolean[] hari) {
		for (int i = 0; i < 5; i++) {
			this.hari[i] = hari[i];
		}
	}
	
}