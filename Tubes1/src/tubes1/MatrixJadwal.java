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
}
