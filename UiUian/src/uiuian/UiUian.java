/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiuian;

/**
 *
 * @author Ratna Dira
 */
public class UiUian {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        NewJFrame c1=new NewJFrame();
            c1.setVisible(true);
    }
    public static class Ruangan{
    public String Nama;
    public String JamMulai;
    public String JamSelesai;
    public String Hari;
}
public static class Jadwal{
    String NamaKegiatan;
    String Ruangan;
    String JamMulai;
    String JamSelesai;
    String Durasi;
    String Hari;

}
}
