/*
* Course.java
* Models a Course
*/

package sa;

import java.util.Arrays;

public class Course {
    private String nama;
    private String ruang;
    private double awal;
    private double akhir;
    private int durasi;
    private boolean[] hari = new boolean[5];
    

    // Constructs an intial course
    public Course(){
        this.nama = "IFXXXX";
        this.ruang = "";
        this.awal = 0;
        this.akhir = 0;
        this.durasi = 0;
        for (int i = 0; i < this.hari.length; i++) {
            hari[i] = false;
        }
    }
    
    // Constructs a course with certain of parameters
    public Course(String nm, String rg, double aw, double ak, int dur, boolean[] hr) {
        this.nama = nm;
        this.ruang = rg;
        this.awal = aw;
        this.akhir = ak;
        this.durasi = dur;
        for (int i = 0; i < this.hari.length; i++) {
            hari[i] = hr[i];
        }
    }

    // Constructs a course from another course
    public Course(Course course) {
        this.nama = course.getNama();
        this.ruang = course.getRuang();
        this.awal = course.getAwal();
        this.akhir = course.getAkhir();
        this.durasi = course.getDurasi();
        for (int i = 0; i < this.hari.length; i++) {
            hari[i] = course.hari[i];
        }
    }
    
    // Gets name of course
    public String getNama(){
        return this.nama;
    }
    
    // Gets course's room
    public String getRuang(){
        return this.ruang;
    }

    // Gets course's starting time
    public double getAwal(){
        return this.awal;
    }

    // Gets course's ending time
    public double getAkhir(){
        return this.akhir;
    }

    // Gets course's duration
    public int getDurasi(){
        return this.durasi;
    }

    // Gets course's day
    public boolean[] getHari(){
        return this.hari;
    }
    
    // Sets Nama to nm
    public void setNama(String nm) {
        this.nama = nm;
    }
    // Sets ruang to rg
    public void setRuang(String rg) {
        this.ruang = rg;
    }
    // Sets awal to aw
    public void setAwal(double aw) {
        this.awal = aw;
    }
    // Sets akhir to akh
    public void setAkhir(double akh) {
        this.akhir = akh;
    }
    // Sets durasi to dur
    public void setDurasi(int dur) {
        this.durasi = dur;
    }
    // Sets hari to hr
    public void setHari(boolean[] hr) {
        for (int i = 0; i < hari.length; i++) {
            this.hari[i] = hr[i];
        }
    }

    public Course randomCourse() {
        Random rnd = new Random();
        for (int i = 0; i < schedule.size(); i++) {
            Course temp = new Course();
            temp.setCourseAtIdx(i, getCourse(i));
            // random ruangan

            // random jam mulai
            int x = rnd.nextInt(getCourse(i).getAkhir() - getCourse(i).getDurasi()) + getCourse(i).getAwal();
            temp.setAwal(x);
            temp.setAkhir(x + getCourse(i).getDurasi());
            // random hari
            do {
                x = rnd.nextInt(5) + 1;
            }
            while (getCourse(i).getHari()[x] == false);
            // masukkan hari terpilih ke course
            // temp.setHari(x);
        }
    }


    @Override
    public String toString(){
        return getNama()+"; "+getRuang()+"; "+getAwal()+"; "+ getAkhir()+"; "+getDurasi()+"; "+Arrays.toString(getHari());
    }
}